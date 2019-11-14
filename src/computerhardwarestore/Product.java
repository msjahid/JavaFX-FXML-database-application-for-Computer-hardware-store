/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerhardwarestore;

/**
 *
 * @author razubhuiyan
 */
public class Product {
    int productId;
    String productName;
    String brandName;
    String model;
    String category;
    String description;
    int price;

    public Product() {
        
    }

    public Product(int productId, String productName, String brandName, String model, String category, String description, int price) {
        this.productId = productId;
        this.productName = productName;
        this.brandName = brandName;
        this.model = model;
        this.category = category;
        this.description = description;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getModel() {
        return model;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", productName=" + productName + ", brandName=" + brandName + ", model=" + model + ", category=" + category + ", description=" + description + ", price=" + price + '}';
    }
    
    
    
}
