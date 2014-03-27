package me.jetblack.examples.client.service;

import me.jetblack.examples.shared.ProductDetails;
import me.jetblack.examples.shared.ProductInfo;
import org.fusesource.restygwt.client.RestService;
import org.fusesource.restygwt.client.future.Future;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("../rest/product")
@Consumes(MediaType.APPLICATION_JSON)
public interface ProductRestService extends RestService {

    @GET
    public Future<List<ProductInfo>> getProducts();

    @GET
    @Path("/{id}/")
    public Future<ProductInfo> getProduct(@PathParam("id") Integer productId);

    @GET
    @Path("/details/{id}/")
    public Future<ProductDetails> getDetails(@PathParam("id") Integer productId);

}
