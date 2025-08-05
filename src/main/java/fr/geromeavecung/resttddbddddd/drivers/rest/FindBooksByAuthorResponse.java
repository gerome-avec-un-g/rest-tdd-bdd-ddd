package fr.geromeavecung.resttddbddddd.drivers.rest;

import java.util.List;

public record FindBooksByAuthorResponse(List<BookSummaryResponse> books) {

}
