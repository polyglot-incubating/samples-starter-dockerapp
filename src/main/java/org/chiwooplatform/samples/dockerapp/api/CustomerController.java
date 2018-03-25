package org.chiwooplatform.samples.dockerapp.api;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.chiwooplatform.samples.dockerapp.message.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class CustomerController {

    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private List<Customer> customers = new LinkedList<>(
            Arrays.asList(new Customer(1, "Gildong", "Hong", 1.0), new Customer(2, "Sunsin", "Lee", 9.9)));

    @ApiOperation(value = "Retrieve all customer data", notes = "bla..bla..with note example!", response = Customer.class, responseContainer = "List", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(path = "/api/customers", method = GET)
    public ResponseEntity<List<Customer>> getCustomers() {
        logger.info("getCustomers");
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @ApiOperation(value = "Select customer by Id", notes = "bla..bla..with note example!", response = Customer.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/api/customers/{id}", method = GET)
    public ResponseEntity<Customer> getCustomerById(@PathVariable final Integer id) {
        logger.info("customer's id is '{}'.", id);
        Optional<Customer> customer = customers.stream().filter(p -> p.getId().intValue() == id.intValue()).findFirst();
        if (customer.isPresent()) {
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Create or Update customer.", notes = "Not available.", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/api/customers", method = { POST, PUT })
    public ResponseEntity<String> saveCustomer(@Valid @RequestBody Customer customer) {
        logger.info("customer: {}", customer);
        Optional<Customer> result = customers.stream().filter(p -> customer != null && p.getId().intValue() == customer.getId().intValue())
                .findFirst();
        if (result.isPresent()) {
            customers.remove(result.get());
        }
        Optional.ofNullable(customer).ifPresent(c -> customers.add(c));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Delete customer by Id", notes = "bla..bla..with note example!", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/api/customers/{id}", method = DELETE)
    public ResponseEntity<String> deleteCustomerById(@PathVariable final Integer id) {
        logger.info("customer's id is '{}'.", id);
        Optional<Customer> customer = customers.stream().filter(p -> p.getId().intValue() == id.intValue()).findFirst();
        if (customer.isPresent()) {
            customers.remove(customer.get());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}