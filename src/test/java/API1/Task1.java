package API1;

import UTILS.ConfigReader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Task1 {

    /*
    Create a new maven project with Junit, Apache HttpClient, FasterXML dependencies.
2. Write test cases to test Star Wars APIs:
- Films: -
-
-
3. Make sure you do positive and negative tests.
4. Verification should be based on status codes and response body.
5. Add properties file to reuse baseURL and other possible properties.
6. Implement reporting and generate reports.



Test www.reqres.in GET endpoints.
- Add automated tests for reqres.in to:
1. “List Users”,
2. “Single User”,
3. “Single User not Found”,
4. “List Resources”,
5. “Single Resource”,
6. “Single Resource not Found” endpoints.

     */
    @Test
    public void test1() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder();
        builder.setScheme(ConfigReader.getProperty("scheme"));
        builder.setHost(ConfigReader.getProperty("setHost"));
        builder.setPath(ConfigReader.getProperty("FilmPath"));

        HttpGet httpGet = new HttpGet(builder.build());
        //Let server know that we expect response in JSON format
        httpGet.setHeader("Accept", "application/json");
        HttpResponse response = client.execute(httpGet);

        Assert.assertEquals(200, response.getStatusLine().getStatusCode());

        HttpEntity responseBody = response.getEntity();

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> responseMap = objectMapper.readValue(responseBody.getContent(),
                new TypeReference<Map<String, Object>>() {
                });


        List<Map<String, Object>> resultList = (List<Map<String, Object>>) responseMap.get("results");
        System.out.println(resultList.get(5).get("title"));


        List<String> names = new ArrayList<>();

        names.add("A New Hope");
        names.add("The Empire Strikes Back");
        names.add("Return of the Jedi");
        names.add("The Phantom Menace");
        names.add("Attack of the Clones");
        names.add("Revenge of the Sith");

        for (int i = 0; i < resultList.size(); i++) {
            System.out.println(resultList.get(i).get("title"));
            Assert.assertEquals(names.get(i), resultList.get(i).get("title"));
        }

    }


    @Test
    public void test2() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder();
        builder.setScheme(ConfigReader.getProperty("scheme"));
        builder.setHost(ConfigReader.getProperty("setHost"));
        builder.setPath(ConfigReader.getProperty("PeoplePath"));


        HttpGet httpGet = new HttpGet(builder.build());
        //Let server know that we expect response in JSON format
        httpGet.setHeader("Accept", "application/json");
        HttpResponse response = client.execute(httpGet);

        Assert.assertEquals(200, response.getStatusLine().getStatusCode());

        HttpEntity responseBody = response.getEntity();

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> responseMap = objectMapper.readValue(responseBody.getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) responseMap.get("results");
        System.out.println(resultList.get(5).get("name"));


        List<String> names = new ArrayList<>();

        names.add("Luke Skywalker");
        names.add("C-3PO");
        names.add("R2-D2");
        names.add("Darth Vader");
        names.add("Leia Organa");
        names.add("Owen Lars");
        names.add("Beru Whitesun lars");
        names.add("R5-D4");
        names.add("Biggs Darklighter");
        names.add("Obi-Wan Kenobi");


        for (int i = 0; i < resultList.size(); i++) {
            System.out.println(resultList.get(i).get("name"));
            Assert.assertEquals(names.get(i), resultList.get(i).get("name"));
        }


    }

    @Test
    public void test3() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder();
        builder.setScheme(ConfigReader.getProperty("scheme"));
        builder.setHost(ConfigReader.getProperty("setHost"));
        builder.setPath(ConfigReader.getProperty("PeoplePath1"));

        HttpGet httpGet = new HttpGet(builder.build());
        //Let server know that we expect response in JSON format
        httpGet.setHeader("Accept", "application/json");
        HttpResponse response = client.execute(httpGet);

        Assert.assertEquals(200, response.getStatusLine().getStatusCode());
        HttpEntity responseBody = response.getEntity();
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> responseMap = objectMapper.readValue(responseBody.getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        System.out.println(responseMap.get("name"));
        Assert.assertEquals("Luke Skywalker", responseMap.get("name"));


    }


    @Test
    public void listUsersTest() throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder();

        builder.setScheme(ConfigReader.getProperty("scheme"));
        builder.setHost(ConfigReader.getProperty("reqresHost"));
        builder.setPath(ConfigReader.getProperty("listUsersPath"));

        HttpGet httpGet = new HttpGet(builder.build());
        httpGet.setHeader("Accept", "application/json");
        HttpResponse response = client.execute(httpGet);

        Assert.assertEquals(200, response.getStatusLine().getStatusCode());
        HttpEntity responseBody = response.getEntity();

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> responseMap = objectMapper.readValue(responseBody.getContent(),
                new TypeReference<Map<String, Object>>() {
                });


        List<Map<String, Integer>> resultList = (List<Map<String, Integer>>) responseMap.get("data");


        List<Integer> ID = new ArrayList<>();

        ID.add(7);
        ID.add(8);
        ID.add(9);
        ID.add(10);
        ID.add(11);
        ID.add(12);

        for (int i = 0; i < resultList.size(); i++) {
            System.out.println(resultList.get(i).get("id"));
            Assert.assertEquals(ID.get(i), resultList.get(i).get("id"));
        }


    }

    @Test
    public void singleUser() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder();
        builder.setScheme(ConfigReader.getProperty("scheme"));
        builder.setHost(ConfigReader.getProperty("reqresHost"));
        builder.setPath(ConfigReader.getProperty("singleUserPath"));

        HttpGet httpGet = new HttpGet(builder.build());
        //Let server know that we expect response in JSON format
        httpGet.setHeader("Accept", "application/json");
        HttpResponse response = client.execute(httpGet);

        Assert.assertEquals(200, response.getStatusLine().getStatusCode());
        HttpEntity responseBody = response.getEntity();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> responseMap = objectMapper.readValue(responseBody.getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        System.out.println(responseMap.get("id"));
        Assert.assertEquals(2, responseMap.get("id"));    // not equal. Actual id = null


    }

    @Test
    public void SingleUserNotFound() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder();
        builder.setScheme(ConfigReader.getProperty("scheme"));
        builder.setHost(ConfigReader.getProperty("reqresHost"));
        builder.setPath(ConfigReader.getProperty("singleUserNotfoundPath"));

        HttpGet httpGet = new HttpGet(builder.build());
        httpGet.setHeader("Accept", "application/json");
        HttpResponse response = client.execute(httpGet);

        Assert.assertEquals(404, response.getStatusLine().getStatusCode());

    }


    @Test
    public void ListResources() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder();
        builder.setScheme(ConfigReader.getProperty("scheme"));
        builder.setHost(ConfigReader.getProperty("reqresHost"));
        builder.setPath(ConfigReader.getProperty("listresourcePath"));

        HttpGet httpGet = new HttpGet(builder.build());
        httpGet.setHeader("Accept", "application/json");
        HttpResponse response = client.execute(httpGet);

        Assert.assertEquals(200, response.getStatusLine().getStatusCode());

    }



    @Test
    public void singleResources() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder();
        builder.setScheme(ConfigReader.getProperty("scheme"));
        builder.setHost(ConfigReader.getProperty("reqresHost"));
        builder.setPath(ConfigReader.getProperty("singleResourcePath"));

        HttpGet httpGet = new HttpGet(builder.build());
        httpGet.setHeader("Accept", "application/json");
        HttpResponse response = client.execute(httpGet);

        Assert.assertEquals(200, response.getStatusLine().getStatusCode());

    }


    @Test
    public void singleResourcesNotFound() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder();
        builder.setScheme(ConfigReader.getProperty("scheme"));
        builder.setHost(ConfigReader.getProperty("reqresHost"));
        builder.setPath(ConfigReader.getProperty("singleResourceNotFoundPath"));

        HttpGet httpGet = new HttpGet(builder.build());
        httpGet.setHeader("Accept", "application/json");
        HttpResponse response = client.execute(httpGet);

        Assert.assertEquals(404, response.getStatusLine().getStatusCode());

    }





}