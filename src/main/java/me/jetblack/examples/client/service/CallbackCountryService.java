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

@Path("../service")
@Consumes(MediaType.APPLICATION_JSON)
public interface CallbackCountryService extends RestService {

    @GET
    @Path("/countires/")
    public void getCountries(MethodCallback<List<Country>> callback);

    @GET
    @Path("/regions/{countryId}/")
    public void getRegions(@PathParam("countryId") Integer countryId, MethodCallback<List<Region>> callback);

}
