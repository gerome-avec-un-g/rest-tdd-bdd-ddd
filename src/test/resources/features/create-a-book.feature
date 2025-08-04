Feature: Create a book

  Background:
    Given The following authors in the system
      | author identifier                    | first name | last name |
      | 589a0b4c-93b8-4f46-8c7e-02794a8c252e | Jane       | Austen    |
      | 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac | Isaac      | Asimov    |

  Rule: data must be valid
    Scenario Outline: Title is mandatory and can't be longer than 30 characters
      When I create a book titled "<title>" for author 9d628ae3-4147-4f6e-91d4-3851be1af7c6
      Then an error is raised with message "<error message>"
      Examples:
        | title                           | error message                                                      |
        | [null]                          | title 'null' is mandatory                                          |
        | [empty]                         | title '' is mandatory                                              |
        | [blank]                         | title ' ' is mandatory                                             |
        | abcdeabcdeabcdeabcdeabcdeabcdea | title 'abcdeabcdeabcdeabcdeabcdeabcdea' is more than 30 characters |
        #| $    | special characters    |
    Scenario Outline: Author's identifier is mandatory and is a UUID
      When I create a book titled "Foundation" for author <author identifier>
      Then an error is raised with message "<error message>"
      Examples:
        | author identifier | error message                 |
        | [null]            | Invalid UUID string: [null]   |
        | [empty]           | Invalid UUID string: [empty]  |
        | [blank]           | Invalid UUID string: [blank]  |
        | 9d628ae3          | Invalid UUID string: 9d628ae3 |
      # TODO ? better error message but it would mean to defer UUID conversion into Book class

  Rule: the book's author must exists
    Example: the author doesn't exists
      When I create a book titled "Foundation" for author da7557ff-9236-46db-90f7-074029dae4ad
      Then an error is raised with message "author identifier 'da7557ff-9236-46db-90f7-074029dae4ad' doesn't exists"
    Example: the author already exists
      When I create a book titled "Foundation" for author 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac
      Then the book "Foundation" is created with its unique identifier 1160aed8-eb2f-4fb3-92e4-43480fff64f5 for author 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac

#  Rule: a book must not already exists for an author
#    Example: the author doesn't exists
#      When I create an author named John Doe
#      Then the author John Doe is created with its unique identifier "1160aed8-eb2f-4fb3-92e4-43480fff64f5"
#    Example: the author already exists
#      Given The author John Doe exists in the system
#      When I create an author named John Doe
#      Then an error is raised with message "The author 'John Doe' already exists"