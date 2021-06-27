Feature: Charge hours into a task
  As a developer
  I want to charge hours into a project
  In order to let everyone know how much I have worked on it

  Background:
    Given I am a developer
    And Project Stopify exists
    And Task Development exists in project Stopify
    And Task Refactor exists

  Scenario: Charge hours correctly
    When I complete project Stopify, task Development, and 5 hours correctly
    Then I get a message that the hours have been charged correctly.

  Scenario: Developer does not belong to the project
    When I complete project Stopify, task Development, and 5 hours but I do not belong to the project
    Then I get an error message telling me that I do not belong to that project

  Scenario: Task does not belong to project
    When I complete project Stopify, task Refactor, and 5 hours but the task does not belong to the project
    Then I get an error message telling me that the task does not belong to the project

  Scenario: Task does not belong to the developer
    When I complete project name, task name, and hours but the task does not belong to me
    Then I get an error message telling me that the task does not belong to me

  Scenario: Hours not positive
    When I complete project name, task name, and hours but the hours are not greater than zero
    Then I get an error message telling me that the amount of hours is not valid