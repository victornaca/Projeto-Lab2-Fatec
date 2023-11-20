# Project Nadalete - E-Auction with Quarkus+Hibernate

## How to Run:
```shell script
mvn quarkus:dev
```

## If you encounter any Java version-related errors:
```shell script
mvn clean
```
## View Endpoints with Swagger:
* [Swagger UI](http://localhost:8080/q/swagger-ui/#/)

## How to Export .DOT?
- Open Swagger;
- Find the Export Resource package;
- Click on any endpoint;
- Press the "Try it out" button;
- Within responses click on "Download File";
- Or put the url in your browser example:
```shell script
http://localhost:8080/exportar/clientes
```
## Source Code Location

The source code for the project is available at [GitHub](https://github.com/victornaca/Projeto-Lab2-Fatec/tree/main/projeto.quarkus/src/main).

### Team Members:
* Emanuele Campos
* Victor Fernandes

## Project Overview:

Welcome to Project E-Auction, a university project aimed at creating a Java+Quarkus application to facilitate an electronic auction platform. The focus is on auctions for vehicles and computer devices.

### Project Dependencies and Guides

- **Hibernate ORM with Panache:** Define your persistent model with Hibernate ORM and Jakarta Persistence. [Hibernate ORM Guide](https://quarkus.io/guides/hibernate-orm-panache)
- **JDBC Driver - H2:** Connect to the H2 database via JDBC. [JDBC Driver Guide](https://quarkus.io/guides/datasource)
- **SmallRye OpenAPI:** Document your REST APIs with OpenAPI, comes with Swagger UI. [OpenAPI Guide](https://quarkus.io/guides/openapi-swaggerui)
- **RESTEasy Classic:** REST endpoint framework implementing Jakarta REST and more. [RESTEasy Guide](https://quarkus.io/guides/resteasy)

### Provided Code Samples

#### Hibernate ORM with Panache

- Create your first JPA entity. [Hibernate ORM with Panache Guide Section](https://quarkus.io/guides/hibernate-orm-panache)

#### RESTEasy JAX-RS

- Easily start your RESTful Web Services. [Related guide section](https://quarkus.io/guides/getting-started#the-jax-rs-resources)

