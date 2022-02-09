package com.infybuzz.service;

import com.infybuzz.entity.Student;
import com.infybuzz.feignclients.AddressFeignClient;
import com.infybuzz.repository.StudentRepository;
import com.infybuzz.request.CreateStudentRequest;
import com.infybuzz.response.AddressResponse;
import com.infybuzz.response.StudentResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    WebClient webClient;

    @Autowired
    AddressFeignClient addressFeignClient;

    @Autowired
    CommonService commonService;


    public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {

        Student student = new Student();
        student.setFirstName(createStudentRequest.getFirstName());
        student.setLastName(createStudentRequest.getLastName());
        student.setEmail(createStudentRequest.getEmail());

        student.setAddressId(createStudentRequest.getAddressId());
        student = studentRepository.save(student);

        StudentResponse studentResponse = new StudentResponse(student);
        // fetch address details by calling address service

        studentResponse.setAddressResponse(commonService.getAddressById(student.getAddressId()));

        return studentResponse;
    }

    public StudentResponse getById(long id) {

        Student student = studentRepository.findById(id).get();
        StudentResponse studentResponse = new StudentResponse(student);

        // fetch address details by calling address service
//        studentResponse.setAddressResponse(getAddressById(student.getAddressId()));

        studentResponse.setAddressResponse(commonService.getAddressById(student.getAddressId()));

        return studentResponse;

    }

    // commenting out these methods because resilience4j uses proxy pattern so these methods have to be moved to new class

//    // name is the instance name we have provided in application.properties file which is addressService
//    @CircuitBreaker(name = "addressService", fallbackMethod = "fallbackGetAddressById")
//    public AddressResponse getAddressById(long addressId) {
//
//        AddressResponse addressResponse = addressFeignClient.getAddressById(addressId);
//
//        return addressResponse;
//    }
//
//
//    // return dummy response in fallback method
//    // we can also add an optional method parameter throwable which allow us to log exceptions
//    public AddressResponse fallbackGetAddressById(long addressId, Throwable throwable) {
//
//        AddressResponse addressResponse = new AddressResponse();
//
//        return addressResponse;
//    }


//    // calling address microservice using web-client
//    public AddressResponse getAddressById(long addressId) {
//
//        Mono<AddressResponse> addressResponseMono =
//                webClient.get().uri("/getById/" + addressId)
//                        .retrieve()
//                        .bodyToMono(AddressResponse.class);
//
//        return addressResponseMono.block();
//    }
}
