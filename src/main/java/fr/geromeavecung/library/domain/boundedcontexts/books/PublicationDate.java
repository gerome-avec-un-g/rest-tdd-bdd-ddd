package fr.geromeavecung.library.domain.boundedcontexts.books;

import fr.geromeavecung.library.domain.boundedcontexts.shared.ValidationException;

import java.time.Year;

public record PublicationDate(Year year) {

    public static PublicationDate create(String year, Year currentYear) {
        if (year == null) {
            throw new ValidationException("publication date is mandatory");
        }
        Year publicationYear;
        try {
            publicationYear = Year.of(Integer.parseInt(year));
        } catch (NumberFormatException e) {
            throw new ValidationException("publication date format is invalid '%s'".formatted(year));
        }
        if (publicationYear.isAfter(currentYear)) {
            throw new ValidationException("publication date %s can't be after current year %s".formatted(publicationYear, currentYear));
        }
        return new PublicationDate(publicationYear);

    }

    public static PublicationDate read(int publicationDate) {
        return new PublicationDate(Year.of(publicationDate));
    }

    @Override
    public String toString() {
        return year.toString();
    }

    // TODO private constructor
}
