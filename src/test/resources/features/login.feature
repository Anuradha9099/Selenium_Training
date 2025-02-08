Feature: Login to the suace demo system
  This feature will test the login functionality of the sauce demo system
  and block the user from logging in with invalid credentials

  @smoke
  Scenario: Test the login functionality with valid credentials
    Given User has accessed to the saucedemo login page
    When User enters valid credentials
    Then User should be able to navigate to the landing page

  @regression
  Scenario Outline:
    Given User has accessed to the saucedemo login page
    When User enters invalid credentials username "<username>" and password "<password>"
    Then User should see the error message "<errorMessage>"
    Examples:
      | username       | password      | errorMessage                                                              |
      |                | secret_sauce  | Epic sadface: Username is required                                        |
      | standard_user  |               | Epic sadface: Password is required                                        |
      |                |               | Epic sadface: Username is required                                        |
      | standard_user1 | secret_sauce1 | Epic sadface: Username and password do not match any user in this service |








