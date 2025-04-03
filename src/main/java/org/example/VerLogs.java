package org.example;

import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class VerLogs {

    public void hacerQuery(String esqlQuery) {

        // construye bien la query
        String actualQuery =
                "{\n" +
                "   \"query\": \"" + esqlQuery + "\"\n" +
                "}";

        // System.out.println(actualQuery); // DEBUG

        try (RestClient client = RestClient.builder(new HttpHost("localhost", 9200, "http")).build()) {
            Request request = new Request("POST", "/_query");
            request.setJsonEntity(actualQuery);

            Response response = client.performRequest(request);

            String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            System.out.println("Resultados de la consulta ES|QL:\nQuery hecha: " + esqlQuery + "\n" + responseBody);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}