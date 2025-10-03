package be.vaneyck.mock_api.controllers;

import java.util.Map;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public Map<String, Object> handleError(HttpServletResponse response) {
        if (response.getStatus() == 404) {
            return Map.of("status", 404, "error", "Not Found", "message", "The resource does not exist");
        }
        return Map.of("status", response.getStatus(), "error", "Unexpected Error");
    }
}