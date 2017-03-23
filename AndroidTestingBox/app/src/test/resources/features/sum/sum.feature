Feature: Sum computation

  Scenario Outline: Sum 2 integers
    Given two int <a> and <b> to sum
    When computing sum
    Then it should be <sum>

    Examples:
      |  a |  b | sum |
      |  1 |  3 |   4 |
      | -1 | -3 |  -4 |
      | -1 |  3 |   2 |