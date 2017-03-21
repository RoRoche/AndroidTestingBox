Feature: Main activity

  Scenario: Click on the button
    Given the initial state is shown
    When clicking on the button
    Then the text changed to "Text changed after button click"