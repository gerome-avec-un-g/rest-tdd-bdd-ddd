Feature: Create an author

  Rule: data must be valid
    Scenario Outline: name is mandatory
      When I create an author named <first name> Doe
      Then an error is raised with message "Author's name is mandatory"
      Examples:
        | first name |
        | -2         |
        | 10         |
        | 99         |
        | -1         |