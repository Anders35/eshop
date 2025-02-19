package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Spy
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testCreate() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        Product savedProduct = productService.create(product);
        assertEquals(product, savedProduct);
    }

    @Test
    void testFindAll() {
        List<Product> products = productService.findAll();
        assertTrue(products.isEmpty());

        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productService.create(product);

        products = productService.findAll();
        assertNotNull(products);
        assertEquals(1, products.size());
    }

    @Test
    void testFindById() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productService.create(product);

        Product savedProduct = productService.findById(product.getProductId());

        assertNotNull(savedProduct);
        assertEquals(product, savedProduct);
    }

    @Test
    void testUpdate() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productService.create(product);

        Product newProduct = new Product();
        newProduct.setProductName("Sampo Cap Usep");
        newProduct.setProductQuantity(50);
        productService.update(product.getProductId(), newProduct);

        Product savedProduct = productService.findById(product.getProductId());

        assertEquals(newProduct, savedProduct);
    }

    @Test
    void testDelete() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productService.create(product);

        Product savedProduct = productService.findById(product.getProductId());
        assertNotNull(savedProduct);
        productService.delete(product.getProductId());

        savedProduct = productService.findById(product.getProductId());
        assertNull(savedProduct);
    }
}