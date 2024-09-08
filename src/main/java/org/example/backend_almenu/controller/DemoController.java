package org.example.backend_almenu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v01/")
@RequiredArgsConstructor
public class DemoController {

    @PostMapping(value = "demo")
    public String welcome() {
        return "Hello World!";
    }

}
