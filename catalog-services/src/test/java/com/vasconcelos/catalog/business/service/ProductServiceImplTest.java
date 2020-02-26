package com.vasconcelos.catalog.business.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.vasconcelos.catalog.business.domain.ProductModel;
import com.vasconcelos.catalog.data.entity.Product;
import com.vasconcelos.catalog.data.repository.ProductRepository;

@SpringBootTest
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    public void setUp() {
        Product product1 = Product.builder().id(1L).name("Product Name 1").description("Product Description 1")
                .price(new BigDecimal("10.20")).imageUrl("/url/image1.jpg").build();
        Product product2 = Product.builder().id(2L).name("Product Name 2").description("Product Description 2")
                .price(new BigDecimal("20.20")).imageUrl("/url/image2.jpg").build();
        Product product3 = Product.builder().id(3L).name("Product Name 3").description("Product Description 3")
                .price(new BigDecimal("30.20")).imageUrl("/url/image3.jpg").build();

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product1));

        List<Product> products = Arrays.asList(new Product[] { product1, product2, product3 });
        Mockito.when(productRepository.findAll()).thenReturn(products);

        productService = new ProductServiceImpl(productRepository);
    }

    @DisplayName("Test getProduct when the product exist")
    @Test
    public void testGetProduct_whenExistingId_thenProductShouldBeFound() {
        Long id = 1L;
        Optional<ProductModel> found = productService.getProduct(id);

        assertNotNull(found.get(), "Product not found");
        assertEquals(id, found.get().getId(), "productId does not match");
    }

    @DisplayName("Test getProduct when the product does not exist")
    @Test
    public void testGetProduct_whenNotExistingId_thenProductShouldNotBeFound() {
        Long id = 10L;
        Optional<ProductModel> found = productService.getProduct(id);

        assertFalse(found.isPresent(), "Product not found");
    }

    @DisplayName("Test getAllProducts when there are products in DB")
    @Test
    public void testGetAllProducts_whenThereAreProductsInDb_thenProductListShouldRetrieved() {
        List<ProductModel> products = productService.getAllProducts();

        assertNotNull(products, "Product list is null");
        assertEquals(3, products.size());
        assertAll("The Ids of the ProductModels should match the ones in DB",
                () -> assertEquals(1L, products.get(0).getId()), () -> assertEquals(2L, products.get(1).getId()),
                () -> assertEquals(3L, products.get(2).getId()));
    }

}
