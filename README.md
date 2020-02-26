# Shopping Cart Solution
Shopping Cart Solution using Spring Boot and Angular 8

Solution is composed of three projects: 
  * catalog-services: Spring Boot
  * shopping-cart-services: Spring Boot
  * ShoppingCartWeb: Angular 8
  
# Installation 
 
 1) Clone the repository:
  git clone https://github.com/ravasconcelos/shoppingCartSolution.git
 2) Build catalog-services (Tests are only working when executing from Eclipse)
  * cd catalog-services
  * mvn clean install -DskipTests
  * mvn eclipse:eclipse
 3) Build shopping-carts
  * cd ../shopping-cart
  * mvn clean install
  * mvn eclipse:eclipse
 4) Build ShoppingCartWeb
  * cd ../ShoppingCartWeb
  * npm install
  
# Execution
 1) Build catalog-services
  * cd ../catalog-services
  * mvn spring-boot:run &
  * Check http://localhost:8100/catalog-api/products/
 2) Build shopping-carts
  * cd ../shopping-cart
  * mvn spring-boot:run &
  * Check http://localhost:8200/shoppingcart-api/carts/
 3) ShoppingCartWeb
  * cd ../ShoppingCartWeb
  * ng serve &
  * Check http://localhost:4200/

  
