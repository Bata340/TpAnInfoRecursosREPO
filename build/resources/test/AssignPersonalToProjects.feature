Feature: Assign personal into a project
  As a project leader
  I want to assign personal into a project
  In order to have a better organization about personal's administration

  Background:
    Given I am a project leader

  Scenario: Assign personal into a project
    When a project is already created
    Then I can assign personal already existent into the project

  Scenario: Remove personal from a project
    When there is a person assigned to a project
    Then I can remove that person from the project

  Scenario: Personal non-existent
    When I want to add a person to a project and he does not exist
    Then I get an error message telling me that that person does not exist