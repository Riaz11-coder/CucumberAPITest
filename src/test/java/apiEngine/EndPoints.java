package apiEngine;

import apiEngine.requests.LoginRequests.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dataProvider.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class EndPoints {

    private final RequestSpecification request;

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String ACCEPT = "Accept";
    private static final String APPLICATION_JSON = "application/json";
    public static final String BASE_URL = ConfigReader.getInstance().getBaseUrl();

    public EndPoints(String baseUrl) {
        this(baseUrl, null);
    }

    public EndPoints(String baseUrl, Map<String, String> additionalHeaders) {
        RestAssured.baseURI = baseUrl;
        request = given();
        request.header(CONTENT_TYPE, APPLICATION_JSON);
        request.header(ACCEPT, APPLICATION_JSON);

        if (additionalHeaders != null) {
            additionalHeaders.forEach(request::header);
        }
    }

    public RequestSpecification getRequest() {
        return request;
    }


    public Response login() {
        HappyPathRequest happyPathRequest = new HappyPathRequest(ConfigReader.getInstance().getEmail(), ConfigReader.getInstance().getPassword());
        Response response = given()
                .contentType("application/json")
                .body(happyPathRequest)
                .post(BASE_URL + "/login");
        System.out.println("Response: " + response.asString());
        return response;
    }

    public Response loginWithInvalidEmail() {
        SadPathRequest invalidLoginRequest = new SadPathRequest(ConfigReader.getInstance().getWrongEmail());
        Response response = given()
                .contentType("application/json")
                .body(invalidLoginRequest)
                .post(BASE_URL + "/login");
        return response;
    }

    public Response CreateUser(){
        CreateUserRequest createUserRequest = new CreateUserRequest(ConfigReader.getInstance().getName(), ConfigReader.getInstance().getJob());
        Response response = given()
                .contentType("application/json")
                .body(createUserRequest)
                .post(BASE_URL + "/users");
        return response;
    }

    public Response UpdateUser(){
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(ConfigReader.getInstance().getName(), ConfigReader.getInstance().getJob());
        Response response = given()
                .contentType("application/json")
                .body(updateUserRequest)
                .put(BASE_URL + "/users/2");

        return response;
    }

    public Response DeleteUser(){
        DeleteUserRequest deleteUserRequest = new DeleteUserRequest(ConfigReader.getInstance().getName(), ConfigReader.getInstance().getJob());
        Response response = given()
                .contentType("application/json")
                .body(deleteUserRequest)
                .delete(BASE_URL + "/users/2");

        return response;

    }

    public Response getListOfUsers() {
        Response response = given()
                .contentType("application/json")
                .get(BASE_URL + "/users");
        return response;
    }






}
