package fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos;

public record TodoPresentation(String userIdentifier, String todoIdentifier, String title, String status,
                               String userName, String userCity) {
}
