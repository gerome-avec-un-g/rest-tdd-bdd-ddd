package fr.geromeavecung.library.domain.boundedcontexts.shared;

public class NotFoundException extends RuntimeException {


    public NotFoundException(String message) {
        super(message);
    }
}
