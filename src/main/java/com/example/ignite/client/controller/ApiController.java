package com.example.ignite.client.controller;

import com.example.common.dto.CustomerDTO;
import org.apache.ignite.Ignite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
        * REST Controller to handle requests.
 */
@RestController
@RequestMapping("/client/customers")
public class ApiController {

    @Autowired
    private Ignite ignite;

    @PostMapping
    public String saveCustomer(@RequestBody CustomerDTO customer) {
        ignite.cache("CustomerCache").put(customer.getId(), customer);
        return "Customer cached via thick client.";
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomer(@PathVariable Long id) {
        return (CustomerDTO) ignite.cache("CustomerCache").get(id);
    }
}

