package com.distribuida.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.neo4j.driver.*;


@ApplicationScoped
public class DatabaseConfig {

    @Inject
    @ConfigProperty(name = "neo4j.uri")
    private String uri;


    @Inject
    @ConfigProperty(name = "neo4j.database")
    private String database;

    @Inject
    @ConfigProperty(name = "neo4j.user")
    private String username;

    @Inject
    @ConfigProperty(name = "neo4j.password")
    private String password;

    @Produces
    public Driver driver(){
        return GraphDatabase.driver(uri,
                AuthTokens.basic(username,password));
    }
    @Produces
    public Session session(Driver driver){
        return driver.session(SessionConfig.forDatabase(database));
    }






}
