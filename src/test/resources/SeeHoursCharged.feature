Feature: See hours charged
  As a finance staff
  I want to see the hours charged into a project
  In order to bill them

  Background:
    Given I am a finance staff

  Scenario: See hours charged to bill them
    When I want to bill a project
    Then I can visualize the hours of the workers who worked in the project