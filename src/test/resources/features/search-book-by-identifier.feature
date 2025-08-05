Feature: Search book by identifier

  Background:
    Given the following books in the system
      | ISBN           | title      | publication date | author identifier                    |
      | 978-0553293357 | Foundation | 1951             | 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac |

  Rule: the search returns all the books of the author
    Example: the book doesn't exists
      When I search for the book 000-0000000000
      Then an error is raised with message "Book 000-0000000000 not found"
    Example: the book exists
      When I search for the book 978-0553293357
      Then the book is
        | ISBN           | title      | publication date | author identifier                    |
        | 978-0553293357 | Foundation | 1951             | 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac |