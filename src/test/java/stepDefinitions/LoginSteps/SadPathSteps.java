package stepDefinitions.LoginSteps;

import apiEngine.EndPoints;
import apiEngine.IRestResponse;
import apiEngine.ResponseHandler;
import apiEngine.responses.LoginResponses.HappyPathResponse;
import apiEngine.responses.LoginResponses.SadPathResponse;
import cucumber.DependencyInjector;
import cucumber.TestContext;
import enums.Context;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import org.netbeans.lib.cvsclient.response.ResponseException;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SadPathSteps {

    private TestContext testContext;
    private Response response;

    public SadPathSteps() {
        this.testContext = DependencyInjector.getTestContext();
    }

    @When("I provide invalid login details,")
    public void iProvideInvalidLoginDetails() {
        EndPoints endPoints = testContext.getEndPoints();
        response = endPoints.loginWithInvalidEmail();
        testContext.getScenarioContext().setContext(Context.RESPONSE, response);
    }

    @Then("I should receive an unsuccessful response.")
    public void iShouldReceiveAnUnsuccessfulResponse() throws ResponseException {
        Response response = testContext.getScenarioContext().getContext(Context.RESPONSE);


        IRestResponse<SadPathResponse> loginResponse = new ResponseHandler<>(SadPathResponse.class,response);
        Assert.assertEquals(400,loginResponse.getStatusCode());
        Assert.assertEquals("Missing password",loginResponse.getBody().getError());
        System.out.println(loginResponse.getResponse().asString());
        System.out.println(loginResponse.getBody().getError());
    }



}
