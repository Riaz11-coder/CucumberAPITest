Feature: Create User

	#Create new user
	@TEST_EL-30 @CreateUser @Regression
	Scenario: Create User

		When I provide valid user details (name and job), 
		Then I should receive a successful response with the created user's information.
		
