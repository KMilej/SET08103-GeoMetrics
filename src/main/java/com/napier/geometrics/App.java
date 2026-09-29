/*
 * SET08103 GeoMetrics
 * Licensed under the Apache License, Version 2.0.
 */
package com.napier.geometrics;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

/** Runs the minimal MongoDB example required by Lab 02. */
public final class App {
    private App() {
    }

    public static void main(final String[] args) {
        try (MongoClient client = new MongoClient("mongo-dbserver")) {
            MongoCollection<Document> collection = client
                    .getDatabase("mydb")
                    .getCollection("test");

            collection.insertOne(new Document("name", "GeoMetrics"));
            System.out.println(collection.find().first().toJson());
        }
    }
}
