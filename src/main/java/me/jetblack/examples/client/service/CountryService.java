
package me.jetblack.examples.client.service;

import me.jetblack.examples.shared.Country;
import me.jetblack.examples.shared.Region;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("../rest/service")
@Consumes(MediaType.APPLICATION_JSON)
public interface CountryService extends RestService {
    @GET
    @Path("/countries/")
    void getCountries(MethodCallback<List<Country>> callback);

    @GET
    @Path("/regions/{countryId}/")
    void getRegions(@PathParam("countryId") Integer countryId, MethodCallback<List<Region>> callback);
}
