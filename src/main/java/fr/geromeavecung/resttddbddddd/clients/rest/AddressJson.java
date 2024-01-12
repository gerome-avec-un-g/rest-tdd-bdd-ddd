package fr.geromeavecung.resttddbddddd.clients.rest;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.users.Address;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.users.City;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.users.Street;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.users.ZipCode;

public record AddressJson(String street, String city, String zipcode) {

    public Address toDomain() {
        return new Address(new Street(street), new City(city), new ZipCode(zipcode));
    }

}
