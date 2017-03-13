Feature: Sum computation

  Scenario: Sum 2 positive integers
    Given two int 1 and 3 to sum
    When computing sum
    Then it should be 4

  Scenario: Sum 2 negative integers
    Given two int -1 and -3 to sum
    When computing sum
    Then it should be -4

  Scenario: Sum 1 negative and 1 positive integers
    Given two int -1 and 3 to sum
    When computing sum
    Then it should be 2