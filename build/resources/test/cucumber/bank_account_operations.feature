Feature: Charge hours into a task
  As a developer
  I want to charge hours into a project
  In order to let everyone know how much I have worked on it

  Scenario: Hours invalid
    Given developer 1 working on project 1 task 1
    When he charges 0 hours into proyect 1 task 1 on 22/01/2020
    Then he gets a message saying that the amount of hours is invalid

  Scenario: Charge Successfull
    Given developer 1 working on project 1 task 1
    When he charges 5 hours into proyect 1 task 1 on 22/01/2020
    Then he gets a message saying that the charge is successful