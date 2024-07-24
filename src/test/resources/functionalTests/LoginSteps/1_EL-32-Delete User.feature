Feature: Delete User

	#Deletes user
	@TEST_EL-32 @DeleteUser @Regression
	Scenario: Delete User

		When I delete a user by providing a user ID,
		Then I should receive a successful response indicating the user has been deleted.
		
