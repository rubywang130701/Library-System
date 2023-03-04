package ca.shopping.springbootlibrary.config;

import ca.shopping.springbootlibrary.entity.Book;
import ca.shopping.springbootlibrary.entity.Message;
import ca.shopping.springbootlibrary.entity.Review;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private String theAllowedOrigins = "http://localhost:3000";
// TODO
//  This is a Java method for configuring a REST API built using Spring Data REST.
//  The method is used to customize the behavior of the REST API.
//  The config parameter is an instance of RepositoryRestConfiguration which allows the configuration of various aspects of the REST API. The config.exposeIdsFor(...) method calls are used to expose the entity IDs for Book, Review, and Message classes in the API responses.
//  The disableHttpMethods(...) method calls are used to disable certain HTTP methods (POST, PATCH, DELETE, PUT) for the specified entity classes.
//  The cors parameter is an instance of CorsRegistry which allows the configuration of Cross-Origin Resource Sharing (CORS) for the REST API.
//  The cors.addMapping(...) method call is used to add a CORS mapping that allows requests from specified origins (theAllowedOrigins) to access the REST API.
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config,
                                                     CorsRegistry cors) {
        HttpMethod[] theUnsupportedActions = {
                HttpMethod.POST,
                HttpMethod.PATCH,
                HttpMethod.DELETE,
                HttpMethod.PUT};

        config.exposeIdsFor(Book.class);
        config.exposeIdsFor(Review.class);
        config.exposeIdsFor(Message.class);

        disableHttpMethods(Book.class, config, theUnsupportedActions);
        disableHttpMethods(Review.class, config, theUnsupportedActions);
        disableHttpMethods(Message.class, config, theUnsupportedActions);
        cors.addMapping(config.getBasePath() + "/**")
                .allowedOrigins(theAllowedOrigins);
    }
// TODO
//  This is a private helper method used in the configureRepositoryRestConfiguration method.
//  The method is used to disable certain HTTP methods (specified in theUnsupportedActions) for a given entity class (theClass).
//  The forDomainType method is used to specify the entity class,
//  and withItemExposure and withCollectionExposure are used to configure the exposure of the entity class in the API.
//  The httpMethods.disable method is used to disable the specified HTTP methods for both item exposure and collection exposure.
    private void disableHttpMethods(Class theClass,
                                    RepositoryRestConfiguration config,
                                    HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) ->
                        httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) ->
                        httpMethods.disable(theUnsupportedActions));
    }
}
