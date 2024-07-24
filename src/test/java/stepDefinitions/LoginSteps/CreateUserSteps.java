package stepDefinitions.LoginSteps;

import apiEngine.EndPoints;
import apiEngine.IRestResponse;
import apiEngine.ResponseHandler;
import apiEngine.responses.LoginResponses.CreateUserResponse;
import cucumber.DependencyInjector;
import cucumber.TestContext;
import enums.Context;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import org.netbeans.lib.cvsclient.response.ResponseException;

public class CreateUserSteps {

    private TestContext testContext;
    private Response response;

    public CreateUserSteps(){
        this.testContext = DependencyInjector.getTestContext();
    }



    @When("I provide valid user details \\(name and job),")
    public void iProvideValidUserDetailsNameAndJob() {
        EndPoints endPoints = testContext.getEndPoints();
        response = endPoints.CreateUser();
        testContext.getScenarioContext().setContext(Context.RESPONSE, response);

    }

    @Then("I should receive a successful response with the created user's information.")
    public void iShouldReceiveASuccessfulResponseWithTheCreatedUserSInformation() throws ResponseException {
        Response response = testContext.getScenarioContext().getContext(Context.RESPONSE);
        IRestResponse<CreateUserResponse> createUser = new ResponseHandler<>(CreateUserResponse.class, response);
        Assert.assertEquals(201,createUser.getStatusCode());
        System.out.println(createUser.getResponse().asString());
        System.out.println(createUser.getBody().getCreatedUser());
        Assert.assertEquals(createUser.getResponse().asString(),createUser.getBody().getCreatedUser());
        Assert.assertEquals("morpheus",createUser.getBody().getName());
        Assert.assertEquals("zion resident",createUser.getBody().getJob());
        Assert.assertNotNull(createUser.getBody().getId());
        Assert.assertNotNull(createUser.getBody().getCreatedAt());
    }



}
