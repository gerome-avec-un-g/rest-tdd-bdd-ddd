# Feature: Create a book (described with Markdown)

## Background:

* Given The following authors in the system

| author identifier                    | first name | last name |
|--------------------------------------|------------|-----------|
| 589a0b4c-93b8-4f46-8c7e-02794a8c252e | Jane       | Austen    |
| 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac | Isaac      | Asimov    |

## Rule: data must be valid

### Scenario Outline: Title is mandatory and can't be longer than 30 characters

* When I create a book titled "<title>" published in 1951 with ISBN "978-0553293357" for author
  8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac
* Then an error is raised with message "<error message>"
  
#### Examples:

| title                           | error message                                                      |
|---------------------------------|--------------------------------------------------------------------|
| [null]                          | title 'null' is mandatory                                          |
| [empty]                         | title '' is mandatory                                              |
| [blank]                         | title ' ' is mandatory                                             |
| abcdeabcdeabcdeabcdeabcdeabcdea | title 'abcdeabcdeabcdeabcdeabcdeabcdea' is more than 30 characters |

### Scenario Outline: publication date is mandatory and can't be in the future

* When I create a book titled "Foundation" published in <publication date> with ISBN "978-0553293357" for author
  8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac
* Then an error is raised with message "<error message>"

#### Examples:

| publication date | error message                                          |
|------------------|--------------------------------------------------------|
| [null]           | publication date is mandatory                          |
| [empty]          | publication date format is invalid ''                  |
| [blank]          | publication date format is invalid ' '                 |
| abcd             | publication date format is invalid 'abcd'              |
| 2026             | publication date 2026 can't be after current year 2025 |

### Scenario Outline: ISBN is mandatory and has format 000-0000000000

* When I create a book titled "Foundation" published in 1951 with ISBN "<ISBN>" for author
  8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac
* Then an error is raised with message "<error message>"

#### Examples:

| ISBN             | error message                                                      | 
|------------------|--------------------------------------------------------------------|
| [null]           | ISBN is mandatory                                                  |
| [empty]          | ISBN '' doesn't correspond to format 000-0000000000                |
| [blank]          | ISBN ' ' doesn't correspond to format 000-0000000000               |
| 0000000000000    | ISBN '0000000000000' doesn't correspond to format 000-0000000000   |
| 000-00000000000  | ISBN '000-00000000000' doesn't correspond to format 000-0000000000 |
| 000-000000000    | ISBN '000-000000000' doesn't correspond to format 000-0000000000   |
| '000-000000000 ' | ISBN '000-000000000 ' doesn't correspond to format 000-0000000000  |
| a00-0000000000   | ISBN 'a00-0000000000' doesn't correspond to format 000-0000000000  |
| 000-a000000000   | ISBN '000-a000000000' doesn't correspond to format 000-0000000000  |
| 000_0000000000   | ISBN '000_0000000000' doesn't correspond to format 000-0000000000  |

### Scenario Outline: Author's identifier is mandatory and is a UUID

* When I create a book titled "Foundation" published in 1951 with ISBN "978-0553293357" for author <author identifier>
* Then an error is raised with message "<error message>"

#### Examples:

| author identifier | error message                 |
|-------------------|-------------------------------|
| [null]            | Invalid UUID string: [null]   |
| [empty]           | Invalid UUID string: [empty]  |
| [blank]           | Invalid UUID string: [blank]  |
| 9d628ae3          | Invalid UUID string: 9d628ae3 |

## Rule: the book's author must exist

### Example: the author doesn't exist

* When I create a book titled "Foundation" published in 1951 with ISBN "978-0553293357" for author
  da7557ff-9236-46db-90f7-074029dae4ad
* Then an error is raised with message "author identifier 'da7557ff-9236-46db-90f7-074029dae4ad' doesn't exist"
  Example: the author already exists
* When I create a book titled "Foundation" published in 1951 with ISBN "978-0553293357" for author
  8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac
* Then the book "Foundation" published in 1951 is created with ISBN 978-0553293357 for author
  8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac

## Rule: a book must not already exist for an author

### Example: the book doesn't exist for the author

* When I create a book titled "Foundation" published in 1951 with ISBN "978-0553293357" for author
  8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac
* Then the book "Foundation" published in 1951 is created with ISBN 978-0553293357 for author
  8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac

### Example: the book already exists for the author

* Given the book "Foundation" published in 1951 with ISBN 978-0553293357 for author 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac
* When I create a book titled "Foundation" published in 1951 with ISBN "978-0553293357" for author
  8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac
* Then an error is raised with message "Book 'Foundation' already exists for '8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac'"
  Example: the book already exists with another case for the author
* Given the book "Foundation" published in 1951 with ISBN 978-0553293357 for author 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac
* When I create a book titled "foundation" published in 1951 with ISBN "978-0553293357" for author
  8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac
* Then an error is raised with message "Book 'foundation' already exists for '8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac'"
  Example: the book already exists without trailing spaces for the author
* Given the book "Foundation" published in 1951 with ISBN 978-0553293357 for author 8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac
* When I create a book titled "Foundation " published in 1951 with ISBN "978-0553293357" for author
  8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac
* Then an error is raised with message "Book 'Foundation' already exists for '8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac'"

### Example: the book already exists for another author

* Given the book "Foundation" published in 1951 with ISBN 978-0553293357 for author 589a0b4c-93b8-4f46-8c7e-02794a8c252e
* When I create a book titled "Foundation" published in 1951 with ISBN "978-0553293357" for author
  8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac
* Then the book "Foundation" published in 1951 is created with ISBN 978-0553293357 for author
  8bb4daf7-3c5c-4f62-a416-99be9ae3a9ac