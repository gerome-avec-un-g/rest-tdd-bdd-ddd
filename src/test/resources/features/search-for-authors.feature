Feature: Search for authors
  For now check is done in the domain rather than in the JPA query, depending on performance, it might change
  # TODO can i tie this feature to full h2 ?

  Rule: Search for authors by first name, last name or partial combination of both
    Scenario Outline: search term
      Given The following authors in the system
        | author identifier                    | first name | last name |
        | 59127042-7a81-4922-a5bd-57090bd01726 | John       | Doe       |
        | 9b53aa7f-2c58-4e9b-889a-73e09236e0eb | John       | Doherty   |
        | da7557ff-9236-46db-90f7-074029dae4ad | Johann     | Hari      |
        | ec9a8831-90f0-4705-893e-3e023c7b3115 | Jane       | Doe       |
        | 589a0b4c-93b8-4f46-8c7e-02794a8c252e | Jane       | Austen    |
        | 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac | Isaac      | Asimov    |
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
  # "title" for each example?
      # order ?

  Rule: search term must be valid
    Scenario Outline: search term is mandatory and must be between 3 and 20 characters
      When I search for "<search term>"
      Then an error is raised with message "<error message>"
      Examples:
        | search term           | error message                                                           |
        | [null]                | search term 'null' is mandatory                                         |
        | [empty]               | search term '' is mandatory                                             |
        | [blank]               | search term ' ' is mandatory                                            |
        | Do                    | search term 'Do' must be between 3 and 20 characters                    |
        | AsimovAsimovAsimovAsi | search term 'AsimovAsimovAsimovAsi' must be between 3 and 20 characters |