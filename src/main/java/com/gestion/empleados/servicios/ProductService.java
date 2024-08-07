package com.gestion.empleados.servicios;

import com.gestion.empleados.modelo.Product;
import com.gestion.empleados.modelo.ProductSearchCriteria;
import com.gestion.empleados.repositorio.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getActiveProducts() {
        return productRepository.findByActive(true);
    }

    public List<Product> getTopSoldProducts() {
        Pageable pageable = PageRequest.of(0, 5); // Página 0, tamaño 5
        return productRepository.findTopSoldProducts(pageable).getContent();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        if (!productRepository.existsById(id)) {
            return null;
        }
        product.setId(id);
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> searchProducts(ProductSearchCriteria criteria) {
        return productRepository.searchProducts(criteria.getName(), criteria.getMinPrice(), criteria.getMaxPrice());
    }
}