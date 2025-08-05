Feature: Search book by identifier

  Background:
    Given the following books in the system
      | book identifier                      | title                 | publication date | author identifier                    |
      | 13781f7e-74a8-4704-b1ab-37313acb1e6e | Foundation            | 1951             | 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac |

  Rule: the search returns all the books of the author
    Example: the book doesn't exists
      When I search for the book da7557ff-9236-46db-90f7-074029dae4ad
      Then an error is raised with message "Book da7557ff-9236-46db-90f7-074029dae4ad not found"
    Example: the book exists
      When I search for the book 13781f7e-74a8-4704-b1ab-37313acb1e6e
      Then the book is
        | book identifier                      | title                 | publication date | author identifier                    |
        | 13781f7e-74a8-4704-b1ab-37313acb1e6e | Foundation            | 1951             | 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac |