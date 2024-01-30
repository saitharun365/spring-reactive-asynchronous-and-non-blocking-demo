package com.saitharun.springreactivedemos.service;

import com.saitharun.springreactivedemos.dao.CustomerDao;
import com.saitharun.springreactivedemos.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;


/****
 * This Service class handles the Customer data extraction
 * @author : Sai Tharun Pathakota
 */
@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;


    public List<CustomerDto> getAllCustomersUsingTraditionalApproach()
    {
        long executionTimeStart = System.currentTimeMillis();
        List<CustomerDto> customersList = customerDao.getAllCustomersUsingTraditionalWay();
        long executionTimeEnd = System.currentTimeMillis();
        System.out.println("Total Time Taken "+ (executionTimeEnd - executionTimeStart) );
        return  customersList;
    }


    public Flux<CustomerDto> getAllCustomerDataUsingReactiveStream() {
        long executionTimeStart = System.currentTimeMillis();
        Flux<CustomerDto> customersFlux = customerDao.getAllCustomersUsingReactiveStream();
        long executionTimeEnd = System.currentTimeMillis();
        System.out.println("Total Time Taken "+ (executionTimeEnd - executionTimeStart) );
        return  customersFlux;

    }

    public Stream<CustomerDto> getAllCustomerDataUsingIntStream() {

        long executionTimeStart = System.currentTimeMillis();
        Stream<CustomerDto> customersIntStream = customerDao.getAllCustomersUsingIntStream();
        long executionTimeEnd = System.currentTimeMillis();
        System.out.println("Total Time Taken "+ (executionTimeEnd - executionTimeStart) );
        return  customersIntStream;
    }
}
