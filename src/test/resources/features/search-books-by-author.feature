Feature: Search books by author

  Background:
    Given The following authors in the system
      | author identifier                    | first name | last name |
      | da7557ff-9236-46db-90f7-074029dae4ad | Johann     | Hari      |
      | 589a0b4c-93b8-4f46-8c7e-02794a8c252e | Jane       | Austen    |
      | 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac | Isaac      | Asimov    |
    And the following books in the system
      | ISBN           | title                 | publication date | author identifier                    |
      | 978-0141439518 | Pride and Prejudice   | 1813             | 589a0b4c-93b8-4f46-8c7e-02794a8c252e |
      | 978-0553293357 | Foundation            | 1951             | 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac |
      | 978-0385233132 | Prelude to Foundation | 1988             | 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac |

  Rule: the author must exists
    Example: the author exists
      When I search for the books of 589a0b4c-93b8-4f46-8c7e-02794a8c252e
      Then the books are
        | ISBN           | title               | publication date | author identifier                    |
        | 978-0141439518 | Pride and Prejudice | 1813             | 589a0b4c-93b8-4f46-8c7e-02794a8c252e |
    Example: the author doesn't exists
      When I search for the books of 3afb5374-7d1f-4a92-9a96-de01a8820a43
      Then an error is raised with message "author identifier '3afb5374-7d1f-4a92-9a96-de01a8820a43' doesn't exist"

  Rule: the search returns all the books of the author
    Example: 0 books
      When I search for the books of da7557ff-9236-46db-90f7-074029dae4ad
      Then the books are
        | ISBN | title | author identifier |
    Example: 1 book
      When I search for the books of 589a0b4c-93b8-4f46-8c7e-02794a8c252e
      Then the books are
        | ISBN           | title               | publication date | author identifier                    |
        | 978-0141439518 | Pride and Prejudice | 1813             | 589a0b4c-93b8-4f46-8c7e-02794a8c252e |
    Example: 2 books
      When I search for the books of 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac
      Then the books are
        | ISBN           | title                 | publication date | author identifier                    |
        | 978-0553293357 | Foundation            | 1951             | 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac |
        | 978-0385233132 | Prelude to Foundation | 1988             | 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac |