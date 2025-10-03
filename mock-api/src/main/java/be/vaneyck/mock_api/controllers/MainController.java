package be.vaneyck.mock_api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {
    @GetMapping("/")
    public String getRoot() {
        return "Hello, World!, see /api/issues for issues";
    }
}