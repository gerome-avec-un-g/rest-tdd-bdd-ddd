package fr.geromeavecung.library.domain.boundedcontexts.shared;

public class ValidationException extends RuntimeException {


    public ValidationException(String message) {
        super(message);
    }
}
