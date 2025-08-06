package fr.geromeavecung.library.domain.steps;

import org.springframework.stereotype.Component;

@Component
public class SharedState {

    private Exception exception;

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
