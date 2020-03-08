package com.vasconcelos.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author Rodolfo Vasconcelos.
 */
@SpringBootApplication
@EnableCaching
public class CatalogServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogServicesApplication.class, args);
    }

}
