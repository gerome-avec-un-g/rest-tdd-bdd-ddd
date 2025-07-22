Feature: Search for authors
  For now check is done in the domain rather than in the JPA query, depending on performance, it might change
  # TODO can i tie this feature to full h2 ?

  Rule: Search for authors by first name, last name or partial combination of both
    Scenario Outline: search term
      Given The following authors in the system
        | first name | last name |
        | John       | Doe       |
        | John       | Doherty   |
        | Johann     | Hari      |
        | Jane       | Doe       |
        | Jane       | Austen    |
        | Isaac      | Asimov    |
      When I search for "<search term>"
      Then the result contains "<result>"
      Examples:
        | search term | result                              |
        | Jane        | Jane Doe, Jane Austen               |
        | Joh         | John Doe, John Doherty, Johann Hari |
        | Doe         | John Doe, Jane Doe                  |
        | Asi         | Isaac Asimov                        |
        | Steve       |                                     |
        | Rowling     |                                     |
        | John Doe    | John Doe                            |
        | John Do     | John Doe, John Doherty              |
        | Joh Do      |                                     |
        | JohnDo      |                                     |
        | john doe    | John Doe                            |
# 'John Do '
  # "title" for each exemple?
      # order ?

  Rule: search term must be valid
    Scenario Outline: search term is mandatory and must be between 3 and 20 characters
      When I search for "<search term>"
      Then an error is raised with message "<error message>"
      Examples:
        | search term           | error message                                                          |
        | [null]                | search term 'null' is mandatory                                        |
        | [empty]               | search term '' is mandatory                                            |
        | [blank]               | search term ' ' is mandatory                                           |
        | Do                    | search term 'Do' must be between 3 and 20 characters                    |
        | AsimovAsimovAsimovAsi | search term 'AsimovAsimovAsimovAsi' must be between 3 and 20 characters |