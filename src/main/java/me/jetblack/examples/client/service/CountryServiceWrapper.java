package me.jetblack.examples.client.service;

import com.google.inject.Inject;
import me.jetblack.examples.client.util.ExtendedArrayList;
import me.jetblack.examples.client.util.ExtendedList;
import me.jetblack.examples.shared.Country;
import me.jetblack.examples.shared.Region;
import org.fusesource.restygwt.client.future.Future;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

public class CountryServiceWrapper {

    private CountryService service;

    @Inject
    public CountryServiceWrapper(CountryService service) {
        this.service = service;
    }

    public Future<ExtendedList<Country>> getCountries() {
        return service.getCountries().map(countries -> new ExtendedArrayList<Country>(countries));
    }

    public Future<ExtendedList<Region>> getRegions(Integer countryId) {
        return service.getRegions(countryId).map(regions -> new ExtendedArrayList<Region>(regions));
    }
}
