package fr.geromeavecung.library.drivers.rest;

import java.util.List;

public record FindBooksByAuthorResponse(List<BookSummaryResponse> books) {

}
