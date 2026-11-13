package me.jetblack.examples.server.service;

import me.jetblack.examples.shared.Country;
import me.jetblack.examples.shared.Region;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Path("service/")
@Produces({MediaType.APPLICATION_JSON})
public class CountryResource {

    public CountryResource() {

    }

    @GET
    @Path("/countries/")
    public List<Country> getCountries() {
        return IntStream.range(0, 10)
                .mapToObj(id -> {
                    Country country = new Country();
                    country.setName("Country with id " + id);
                    country.setId(id);
                    return country;
                })
                .collect(Collectors.toList());
    }

    @GET
    @Path("/regions/{countryId}")
    public List<Region> getRegions(@PathParam("countryId") Integer countryId) {
        return IntStream.range(0, 10)
                .mapToObj(id -> {
                    Region region = new Region();
                    region.setName("Region with id " + id);
                    region.setId(id);
                    region.setCountryId(countryId);
                    return region;
                })
                .collect(Collectors.toList());
    }


}
