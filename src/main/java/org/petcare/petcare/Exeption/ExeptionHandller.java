package org.petcare.petcare.Exeption;

import org.petcare.petcare.Response.ResponseAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExeptionHandller {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseAPI> handleException(ResourceNotFoundException e) {
        return ResponseEntity.status(404).body(new ResponseAPI("error", e.getMessage(), null));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ResponseAPI> handleException(UserAlreadyExistsException e) {
        return ResponseEntity.status(409).body(new ResponseAPI("error", e.getMessage(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseAPI> handleException(Exception e) {
        return ResponseEntity.status(500).body(new ResponseAPI("error", e.getMessage(), null));
    }
}
