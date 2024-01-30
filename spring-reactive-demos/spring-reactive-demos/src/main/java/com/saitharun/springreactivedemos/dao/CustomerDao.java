package com.saitharun.springreactivedemos.dao;

import com.saitharun.springreactivedemos.dto.CustomerDto;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class CustomerDao {


    /****
     * This Class represents a Customer Dao that extracts the data
     * @author : Sai Tharun Pathakota
     */

    /****
     * We are writing code in th Dao itself
     *     In real world, the data is pulled from the Repos
     */
    public  List<CustomerDto> getAllCustomersUsingTraditionalWay()
    {
        return IntStream.rangeClosed(1,20)
                .peek(CustomerDao::slowTheExecution)
                .peek(i-> System.out.println("Processing the customer id "+i))
                .mapToObj(i-> new CustomerDto(i, "Customer num"+i))
                .collect(Collectors.toList());
    }

    private static void slowTheExecution(int i)  {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Flux<CustomerDto> getAllCustomersUsingReactiveStream() {

        return  Flux.range(1,20)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i-> System.out.println("Processing the customer id "+i))
                .map(i-> new CustomerDto(i, "Customer num"+i));


    }

    public Stream<CustomerDto> getAllCustomersUsingIntStream() {

        return IntStream.rangeClosed(1,20)
                .peek(CustomerDao::slowTheExecution)
                .peek(i-> System.out.println("Processing the customer id "+i))
                .mapToObj(i-> new CustomerDto(i, "Customer num"+i));

    }
}
