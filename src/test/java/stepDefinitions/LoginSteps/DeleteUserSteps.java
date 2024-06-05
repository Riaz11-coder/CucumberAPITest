package stepDefinitions.LoginSteps;

import apiEngine.EndPoints;
import apiEngine.IRestResponse;
import apiEngine.ResponseHandler;
import apiEngine.responses.LoginResponses.CreateUserResponse;
import apiEngine.responses.LoginResponses.DeleteUserResponse;
import cucumber.DependencyInjector;
import cucumber.TestContext;
import enums.Context;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import org.netbeans.lib.cvsclient.response.ResponseException;

public class DeleteUserSteps {

    private TestContext testContext;
    private Response response;

    public DeleteUserSteps(){
        this.testContext = DependencyInjector.getTestContext();
    }


    @When("I delete a user by providing a user ID,")
    public void iDeleteAUserByProvidingAUserID() {
        EndPoints endPoints = testContext.getEndPoints();
        response = endPoints.DeleteUser();
        testContext.getScenarioContext().setContext(Context.RESPONSE, response);

    }

    @Then("I should receive a successful response indicating the user has been deleted.")
    public void iShouldReceiveASuccessfulResponseIndicatingTheUserHasBeenDeleted() throws ResponseException {
        Response response = testContext.getScenarioContext().getContext(Context.RESPONSE);
        IRestResponse<DeleteUserResponse> deleteUserResponse = new ResponseHandler<>(DeleteUserResponse.class, response);
        System.out.println(deleteUserResponse.getStatusCode());
        Assert.assertEquals(204,deleteUserResponse.getStatusCode());

    }
}
