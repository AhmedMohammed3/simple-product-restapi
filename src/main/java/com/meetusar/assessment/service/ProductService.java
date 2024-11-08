package com.meetusar.assessment.service;

import com.meetusar.assessment.dto.ProductDTO;
import com.meetusar.assessment.dto.ProductResponseDTO;
import com.meetusar.assessment.entity.Product;
import com.meetusar.assessment.exception.InvalidSortByException;
import com.meetusar.assessment.exception.ProductNotFoundException;
import com.meetusar.assessment.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private static final List<String> ALLOWED_SORT_FIELDS = Arrays.asList("id", "name", "price", "stockQuantity");

    public ProductResponseDTO createProduct(ProductDTO productDTO) {
        Product product = new Product(
                productDTO.getName(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                productDTO.getStockQuantity()
        );

        Product savedProduct = productRepository.save(product);

        return new ProductResponseDTO(savedProduct);
    }

    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return new ProductResponseDTO(product);
    }

    public Page<ProductResponseDTO> getAllProducts(int page, int size, String sortBy, String sortDirection) {
        if (!ALLOWED_SORT_FIELDS.contains(sortBy)) {
            throw new InvalidSortByException("Invalid 'sortBy' field. Allowed fields are: " + ALLOWED_SORT_FIELDS);
        }

        return productRepository.findAll(PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortBy))
                .map(product -> new ProductResponseDTO(product));
    }

    public ProductResponseDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());

        Product updatedProduct = productRepository.save(product);
        return new ProductResponseDTO(updatedProduct);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        productRepository.delete(product);
    }
}
