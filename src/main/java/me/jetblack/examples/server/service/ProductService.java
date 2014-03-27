package me.jetblack.examples.server.service;

import me.jetblack.examples.shared.ProductDetails;
import me.jetblack.examples.shared.ProductInfo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Path("product/")
@Produces({MediaType.APPLICATION_JSON})
public class ProductService {

    private final List<ProductInfo> products;

    public ProductService() {
        this.products = generateProducts();
    }

    @GET
    @Path("/{id}/")
    public ProductInfo getProductInfo(@PathParam("id") Integer productId) {
        return products.stream().filter((product) -> product.getId().equals(productId)).findFirst().get();
    }

    @GET
    public List<ProductInfo> getProducts() {
        return products;
    }

    @GET
    @Path("/details/{id}/")
    public ProductDetails getDetails(@PathParam("id") Integer productId) {
        ProductInfo info = getProductInfo(productId);
        return new ProductDetails(info.getId(), "Details for product " + info.getId());
    }

    public List<ProductInfo> generateProducts() {
        return IntStream.range(0, 15).mapToObj(id -> {
            ProductInfo productInfo = new ProductInfo();
            productInfo.setId(id);
            productInfo.setName("Product N." + id);
            productInfo.setDescription("Description for Product " + id);
            return productInfo;
        }).collect(Collectors.toList());
    }

}
