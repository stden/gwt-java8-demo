package me.jetblack.examples.client.service;


import me.jetblack.examples.client.util.ExtendedList;
import me.jetblack.examples.shared.City;
import me.jetblack.examples.shared.Country;
import me.jetblack.examples.shared.Region;
import org.fusesource.restygwt.client.future.Future;

public class CountryService {

    public Future<ExtendedList<Country>> getCountries() {
        return null;
    };

    public Future<ExtendedList<Region>> getRegions(Integer countryId) {
        return null;
    }

}
