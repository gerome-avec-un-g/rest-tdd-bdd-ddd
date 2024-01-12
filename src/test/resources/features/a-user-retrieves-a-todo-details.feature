Feature: A user retrieves the details of one of his todo

  Rule: todo identifier
    Example: todo exists
      Given the user "Leanne Graham"
      When the user retrieves a todo details for todo 1
      Then the todo details is "delectus aut autem"
#    Example: todo doesn't exists
#      Given the user "Leanne Graham"
#      When the user retrieves a todo details for todo 12345
#      Then the todo details is "a"