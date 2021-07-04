Feature: Charge hours into a task
  As a developer
  I want to charge hours into a project
  In order to let everyone know how much I have worked on it

  Scenario: Successful Charge
    Given developer named Agustin working on Stopify task Development
    When he charges 5 hours into Stopify task Development
    Then charge is successful

  Scenario: Person Does not belong to project
    Given developer named Agustin working on Stopify task Development
    When he charges 5 hours into Instabook task Development
    Then he gets a message saying that he does not belong to the project

  Scenario: Task does not belong to person
    Given developer named Agustin working on Stopify task Development
    When he charges 5 hours into Stopify task Refactor
    Then he gets a message saying that the task does not belong to him

  Scenario: Hours invalid
    Given developer named Agustin working on Stopify task Development
    When he charges 0 hours into Stopify task Development
    Then he gets a message saying that the amount of hours is invalid