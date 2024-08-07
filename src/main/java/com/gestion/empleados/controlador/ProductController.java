package com.gestion.empleados.controlador;

import com.gestion.empleados.modelo.Order;
import com.gestion.empleados.modelo.OrderRequest;
import com.gestion.empleados.modelo.Product;
import com.gestion.empleados.modelo.ProductSearchCriteria;
import com.gestion.empleados.modelo.User;
import com.gestion.empleados.servicios.OrderService;
import com.gestion.empleados.servicios.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        if (updatedProduct == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/active-products")
    public List<Product> getActiveProducts() {
        return productService.getActiveProducts();
    }

    @GetMapping("/top-sold-products")
    public List<Product> getTopSoldProducts() {
        return productService.getTopSoldProducts();
    }

    @PostMapping("/search")
    public List<Product> searchProducts(@RequestBody ProductSearchCriteria criteria) {
        return productService.searchProducts(criteria);
    }

    @PostMapping("/orders")
    public Order createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }

}