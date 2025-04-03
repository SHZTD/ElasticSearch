package org.example;

import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class VerLogs {

    public void hacerQuery(String esqlQuery) {

        String actualQuery = "{ \"query\": \"" + esqlQuery + "\" }";

        try (RestClient client = RestClient.builder(new HttpHost("localhost", 9200, "http")).build()) {
            Request request = new Request("POST", "/_query");
            request.setJsonEntity(actualQuery);
            Response response = client.performRequest(request);
            String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);

            // jackson para mapear bien el objeto
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(responseBody);

            // extraemos solo la parte de "values"
            JsonNode valuesNode = jsonResponse.get("values");

            // formatamos
            System.out.println("Resultados de la consulta ES|QL:\nQuery hecha: " + esqlQuery);
            System.out.println(valuesNode.toPrettyString() + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
