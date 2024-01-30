package com.saitharun.springreactivedemos.controller;

import com.saitharun.springreactivedemos.dto.CustomerDto;
import com.saitharun.springreactivedemos.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;


/****
 * The Rest Controller Class
 * @author : Sai Tharun Pathakota
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    /***
     * The Traditional way is synchronous and Blocking
     * The data will be sent back only once after All the data is acquired.
     * So, It takes longer duration for the execution
     */
    @GetMapping("/traditionalway")
    public List<CustomerDto> getAllCustomerDataUsingTraditionalWay()
    {
        return  customerService.getAllCustomersUsingTraditionalApproach();
    }

    /***
     * The Reactive stream is Asynchronous and Non Blocking
     * That is the reason we get our data element to the subscriber as soon at is processed
     * So, It takes less execution time
     */

    @GetMapping(value = "/reactiveStream" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<CustomerDto> getAllCustomerDataUsingReactiveStream()
    {
        return  customerService.getAllCustomerDataUsingReactiveStream();
    }

    /***
     * Even when we use the Java Streams from util package
     * The way they function is also Blocking
     *
     */
    @GetMapping(value = "/IntStream" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Stream<CustomerDto> getAllCustomerDataUsingIntStream()
    {
        return  customerService.getAllCustomerDataUsingIntStream();
    }
}
