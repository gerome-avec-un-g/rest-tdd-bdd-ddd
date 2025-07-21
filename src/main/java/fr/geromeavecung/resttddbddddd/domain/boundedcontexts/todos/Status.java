package fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos;

public enum Status {

    PENDING, COMPLETED;

    public static Status from(boolean status) {
        if (status) {
            return COMPLETED;
        }
        return PENDING;
    }
}
