@smoke
Feature: User Verification

Scenario: verify information about logged year
  Given I logged Bookit api using "lfinnisz@yolasite.com" and "lissiefinnis"
  When I get the current user information from api
  Then status code should be 200

  Scenario: verify information about logged user from api and database
    Given I logged Bookit api using "lfinnisz@yolasite.com" and "lissiefinnis"
    When I get the current user information from api
    Then the information about current user from api and database should match

  Scenario: three point/layer (UI,API,DATABASE)
    Given user logs in using "lfinnisz@yolasite.com" "lissiefinnis"
    And user is on the my self page
    And I logged Bookit api using "lfinnisz@yolasite.com" and "lissiefinnis"
    When I get the current user information from api
    Then UI,API and Database user information must be match
  @db @wip
  Scenario Outline: three point/layer (UI,API,DATABASE)
    Given user logs in using "<email>" "<password>"
    And user is on the my self page
    And I logged Bookit api using "<email>" and "<password>"
    When I get the current user information from api
    Then UI,API and Database user information must be match

    Examples:
      | email                 | password      |
      | fscoughx@msu.edu      | feodorascough |
      | lfinnisz@yolasite.com | lissiefinnis  |