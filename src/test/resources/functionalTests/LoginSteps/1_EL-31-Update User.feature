Feature: Update user

	#User should be able to update User information.
	@TEST_EL-31 @UpdateUser
	Scenario: Update user

		When I provide valid user update details (name and job)
		Then I should receive a successful response with the updated user's information
		
