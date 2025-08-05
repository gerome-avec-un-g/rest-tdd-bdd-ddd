Feature: Search books by author

Given The following authors in the system
| author identifier                    | first name | last name |
| 589a0b4c-93b8-4f46-8c7e-02794a8c252e | Jane       | Austen    |
| 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac | Isaac      | Asimov    |
| 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac | Isaac      | Asimov    |

  Rule: the author must exists
    Example: the author exists
    Example: the author doesn't exists

  Rule: the search returns all the books of the author
    Example: 0 books
    Example: 1 book
    Example: 2 books