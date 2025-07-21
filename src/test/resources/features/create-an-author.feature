Feature: Create an author

  Rule: data must be valid
    Scenario Outline: first name is mandatory and can't be longer than 20 characters
      When I create an author named <first name> Doe
      Then an error is raised with message "<error message>"
      Examples:
        | first name            | error message                                                 |
        | [null]                | first name 'null' is mandatory                                |
        | [empty]               | first name '' is mandatory                                    |
        | [blank]               | first name ' ' is mandatory                                   |
        | abcdeabcdeabcdeabcdea | first name 'abcdeabcdeabcdeabcdea' is more than 20 characters |
        #| $    | special characters    |
    Scenario Outline: last name is mandatory and can't be longer than 20 characters
      When I create an author named John <last name>
      Then an error is raised with message "<error message>"
      Examples:
        | last name             | error message                                                |
        | [null]                | last name 'null' is mandatory                                |
        | [empty]               | last name '' is mandatory                                    |
        | [blank]               | last name ' ' is mandatory                                   |
        | abcdeabcdeabcdeabcdea | last name 'abcdeabcdeabcdeabcdea' is more than 20 characters |
        #| $    | special characters    |