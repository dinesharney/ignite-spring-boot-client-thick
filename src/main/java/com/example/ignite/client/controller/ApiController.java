package com.example.ignite.client.controller;

import com.example.common.dto.CustomerDTO;
import com.example.common.dto.UserDTO;
import org.apache.ignite.Ignite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
        * REST Controller to handle requests.
 */
@RestController
@RequestMapping("/client")
public class ApiController {

    @Autowired
    private Ignite ignite;

    @PostMapping("/user")
    public String saveUser(@RequestBody UserDTO user) {
        ignite.cache("CustomerCache").put(user.getId(), user);
        return "Customer cached via thick client.";
    }

    @GetMapping("/user/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        return (UserDTO) ignite.cache("UserCache").get(id);
    }

    @PostMapping("/customer")
    public String saveCustomer(@RequestBody CustomerDTO customer) {
        ignite.cache("CustomerCache").put(customer.getId(), customer);
        return "Customer cached via thick client.";
    }

    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable Long id) {
        return (CustomerDTO) ignite.cache("CustomerCache").get(id);
    }
}

