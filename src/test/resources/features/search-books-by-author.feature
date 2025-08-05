Feature: Search books by author

  Background:
    Given The following authors in the system
      | author identifier                    | first name | last name |
      | da7557ff-9236-46db-90f7-074029dae4ad | Johann     | Hari      |
      | 589a0b4c-93b8-4f46-8c7e-02794a8c252e | Jane       | Austen    |
      | 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac | Isaac      | Asimov    |
    And the following books in the system
      | book identifier                      | title                 | publication date | author identifier                    |
      | e9d9f77b-4cb6-47a1-b40b-081c2303cf9b | Pride and Prejudice   | 1813             | 589a0b4c-93b8-4f46-8c7e-02794a8c252e |
      | 13781f7e-74a8-4704-b1ab-37313acb1e6e | Foundation            | 1951             | 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac |
      | fa9f0e61-95e6-4859-9dd0-d17b53b8792b | Prelude to Foundation | 1988             | 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac |

  Rule: the author must exists
    Example: the author exists
      When I search for the books of 589a0b4c-93b8-4f46-8c7e-02794a8c252e
      Then the books are
        | book identifier                      | title               | publication date | author identifier                    |
        | e9d9f77b-4cb6-47a1-b40b-081c2303cf9b | Pride and Prejudice | 1813             | 589a0b4c-93b8-4f46-8c7e-02794a8c252e |
    Example: the author doesn't exists
      When I search for the books of e9d9f77b-4cb6-47a1-b40b-081c2303cf9b
      Then an error is raised with message "author identifier 'e9d9f77b-4cb6-47a1-b40b-081c2303cf9b' doesn't exists"

  Rule: the search returns all the books of the author
    Example: 0 books
      When I search for the books of da7557ff-9236-46db-90f7-074029dae4ad
      Then the books are
        | book identifier | title | author identifier |
    Example: 1 book
      When I search for the books of 589a0b4c-93b8-4f46-8c7e-02794a8c252e
      Then the books are
        | book identifier                      | title               | publication date | author identifier                    |
        | e9d9f77b-4cb6-47a1-b40b-081c2303cf9b | Pride and Prejudice | 1813             | 589a0b4c-93b8-4f46-8c7e-02794a8c252e |
    Example: 2 books
      When I search for the books of 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac
      Then the books are
        | book identifier                      | title                 | publication date | author identifier                    |
        | 13781f7e-74a8-4704-b1ab-37313acb1e6e | Foundation            | 1951             | 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac |
        | fa9f0e61-95e6-4859-9dd0-d17b53b8792b | Prelude to Foundation | 1988             | 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac |