package me.jetblack.examples.client.service;


import me.jetblack.examples.shared.Country;
import me.jetblack.examples.shared.Region;
import org.fusesource.restygwt.client.RestService;
import org.fusesource.restygwt.client.future.Future;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("../rest/service")
@Consumes(MediaType.APPLICATION_JSON)
public interface CountryService extends RestService{

    @GET
    @Path("/countries/")
    public Future<List<Country>> getCountries();

    @GET
    @Path("/regions/{countryId}/")
    public Future<List<Region>> getRegions(@PathParam("countryId") Integer countryId);


}
