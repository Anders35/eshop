package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        product.setProductId(UUID.randomUUID().toString());
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(String id) {
        for (int i = 0; i < productData.size(); i++) {
            if (productData.get(i).getProductId().equals(id)) {
                return productData.get(i);
            }
        }
        return null;
    }

    public Product update(String id, Product updatedProduct) {
        Product product = findById(id);
        if (product != null) {
            updatedProduct.setProductId(id);
            productData.set(productData.indexOf(product), updatedProduct);
            return updatedProduct;
        }
        return null;
    }

    public void delete(String id) {
        Product product = findById(id);
        if (product != null) {
            productData.remove(product);
        }
    }
}
