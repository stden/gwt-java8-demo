package me.jetblack.examples.shared;

public class ProductDetails {

    private Integer productId;
    private String additionalDescription;

    public ProductDetails() {
    }

    public ProductDetails(Integer productId, String additionalDescription) {
        this.productId = productId;
        this.additionalDescription = additionalDescription;
    }

    public String getAdditionalDescription() {
        return additionalDescription;
    }

    public void setAdditionalDescription(String additionalDescription) {
        this.additionalDescription = additionalDescription;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductDetails{" +
                "productId=" + productId +
                ", additionalDescription='" + additionalDescription + '\'' +
                '}';
    }
}
