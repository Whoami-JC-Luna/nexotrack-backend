package com.nexologistics.nexotrack;


import com.nexologistics.nexotrack.shared.error.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestExceptionController {



    @GetMapping("/test/not-found")
    public void notFound() {
        throw new ResourceNotFoundException("Usuario no encontrado");
    }

    @PostMapping("/test/bad-request")
    public void badRequest(@Valid @RequestBody TestRequest request) {

    }

    @GetMapping("/test/server-error")
    public void serverError() {
        throw new IllegalStateException("INTERNAL_SECRET_MARKER");
    }

}
