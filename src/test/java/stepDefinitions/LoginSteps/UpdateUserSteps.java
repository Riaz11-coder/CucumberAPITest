package stepDefinitions.LoginSteps;

import apiEngine.EndPoints;
import apiEngine.IRestResponse;
import apiEngine.ResponseHandler;
import apiEngine.responses.LoginResponses.UpdateUserResponse;
import cucumber.DependencyInjector;
import cucumber.TestContext;
import enums.Context;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import org.netbeans.lib.cvsclient.response.ResponseException;

public class UpdateUserSteps {

    private TestContext testContext;
    private Response response;

    public UpdateUserSteps(){
        this.testContext = DependencyInjector.getTestContext();
    }

    @When("I provide valid user update details \\(name and job)")
    public void iProvideValidUserUpdateDetailsNameAndJob() {
        EndPoints endPoints = testContext.getEndPoints();
        response = endPoints.UpdateUser();
        testContext.getScenarioContext().setContext(Context.RESPONSE, response);
    }

    @Then("I should receive a successful response with the updated user's information")
    public void iShouldReceiveASuccessfulResponseWithTheUpdatedUserSInformation() throws ResponseException {
        testContext.getScenarioContext().getContext(Context.RESPONSE);
        IRestResponse<UpdateUserResponse> userResponseIRestResponse = new ResponseHandler<UpdateUserResponse>(UpdateUserResponse.class,response);
        System.out.println(userResponseIRestResponse.getStatusCode());
        System.out.println(userResponseIRestResponse.getResponse().asString());
        Assert.assertEquals(200,userResponseIRestResponse.getStatusCode());
        Assert.assertEquals("morpheus",userResponseIRestResponse.getBody().getName());
        Assert.assertEquals("zion resident",userResponseIRestResponse.getBody().getJob());
        Assert.assertNotNull(userResponseIRestResponse.getBody().getUpdatedAt());
    }


}
