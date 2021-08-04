package com.napster.tags.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableMongoRepositories(basePackages = "com.napster.tags.repository")
public class MongoConfig  extends AbstractMongoClientConfiguration {

    @Autowired
    private Environment env;

    @Override
    protected String getDatabaseName() {
        return env.getProperty("source.database.name");
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }
    @Override
    public MongoClient mongoClient() {
        String uri = "mongodb://"+env.getProperty("source.database.uri")+"/"+env.getProperty("source.database.name");
        uri = "mongodb+srv://napster:napster123@cluster0.5xjzz.mongodb.net/tags?retryWrites=true&w=majority";
        System.out.println("uri["+uri+"]");
        ConnectionString connectionString = new ConnectionString(uri);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .applyToSslSettings(builder -> builder.enabled(true))
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Override
    public Collection getMappingBasePackages() {
        return Collections.singleton("com.napster.tags");
    }
}