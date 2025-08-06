package fr.geromeavecung.library.domain.boundedcontexts.shared;

public class BusinessException extends RuntimeException {


    public BusinessException(String message) {
        super(message);
    }
}
