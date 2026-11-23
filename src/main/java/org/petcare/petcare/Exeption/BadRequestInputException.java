package org.petcare.petcare.Exeption;

public class BadRequestInputException extends RuntimeException {
    public BadRequestInputException(String massage) {
        super(massage);
    }
}
