Feature: Create a book

  Background:
    Given The following authors in the system
      | author identifier                    | first name | last name |
      | 589a0b4c-93b8-4f46-8c7e-02794a8c252e | Jane       | Austen    |
      | 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac | Isaac      | Asimov    |

  Rule: data must be valid
    Scenario Outline: Title is mandatory and can't be longer than 30 characters
      When I create a book titled "<title>" published in 1951 with ISBN 978-0553293357 for author 9d628ae3-4147-4f6e-91d4-3851be1af7c6
      Then an error is raised with message "<error message>"
      Examples:
        | title                           | error message                                                      |
        | [null]                          | title 'null' is mandatory                                          |
        | [empty]                         | title '' is mandatory                                              |
        | [blank]                         | title ' ' is mandatory                                             |
        | abcdeabcdeabcdeabcdeabcdeabcdea | title 'abcdeabcdeabcdeabcdeabcdeabcdea' is more than 30 characters |
        #| $    | special characters    |
    # TODO Scenario Outline: publication date
    # TODO Scenario Outline: isbn
    Scenario Outline: Author's identifier is mandatory and is a UUID
      When I create a book titled "Foundation" published in 1951 with ISBN 978-0553293357 for author <author identifier>
      Then an error is raised with message "<error message>"
      Examples:
        | author identifier | error message                 |
        | [null]            | Invalid UUID string: [null]   |
        | [empty]           | Invalid UUID string: [empty]  |
        | [blank]           | Invalid UUID string: [blank]  |
        | 9d628ae3          | Invalid UUID string: 9d628ae3 |
      # TODO ? better error message but it would mean to defer UUID conversion into Book class
  # TODO trailing spaces

  Rule: the book's author must exists
    Example: the author doesn't exists
      When I create a book titled "Foundation" published in 1951 with ISBN 978-0553293357 for author da7557ff-9236-46db-90f7-074029dae4ad
      Then an error is raised with message "author identifier 'da7557ff-9236-46db-90f7-074029dae4ad' doesn't exists"
    Example: the author already exists
      When I create a book titled "Foundation" published in 1951 with ISBN 978-0553293357 for author 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac
      Then the book "Foundation" published in 1951 is created with ISBN 978-0553293357 for author 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac

  Rule: a book must not already exists for an author
    Example: the book doesn't exists for the author
      When I create a book titled "Foundation" published in 1951 with ISBN 978-0553293357 for author 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac
      Then the book "Foundation" published in 1951 is created with ISBN 978-0553293357 for author 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac
      # should we go read author's name for an error message?
    Example: the book already exists for the author
      Given the book "Foundation" published in 1951 with ISBN 978-0553293357 for author 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac
      When I create a book titled "Foundation" published in 1951 with ISBN 978-0553293357 for author 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac
      Then an error is raised with message "Book 'Foundation' already exists for '8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac'"
    Example: the book already exists with another case for the author
      Given the book "Foundation" published in 1951 with ISBN 978-0553293357 for author 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac
      When I create a book titled "foundation" published in 1951 with ISBN 978-0553293357 for author 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac
      Then an error is raised with message "Book 'foundation' already exists for '8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac'"
    Example: the book already exists without trailing spaces for the author
      Given the book "Foundation" published in 1951 with ISBN 978-0553293357 for author 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac
      When I create a book titled "Foundation   " published in 1951 with ISBN 978-0553293357 for author 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac
      Then an error is raised with message "Book 'Foundation' already exists for '8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac'"
      # error message does not contains trailing spaces
    Example: the book already exists for another author
      Given the book "Foundation" published in 1951 with ISBN 978-0553293357 for author 589a0b4c-93b8-4f46-8c7e-02794a8c252e
      When I create a book titled "Foundation" published in 1951 with ISBN 978-0553293357 for author 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac
      Then the book "Foundation" published in 1951 is created with ISBN 978-0553293357 for author 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac