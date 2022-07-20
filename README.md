# Todo List
<div align="center">
    <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white" alt="Java">
    <img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" alt="Spring">
</div>

**Todo List is Spring API that allows users to manage their todo list.**

## Entity
- User

| Variable     | Type          |
|--------------|---------------|
| id           | Long          |
| name         | String        |
| email        | String        |
| gender       | Character     |
| birthdate    | LocalDate     |
| createdDate  | LocalDateTime |
| modifiedDate | LocalDateTime |
| todoList     | List\<Todo\>  |

- Todo

| Variable     | Type          |
|--------------|---------------|
| id           | Long          |
| content      | String        |
| isComplete   | Boolena       |
| dueAt        | LocalDateTime |
| createdDate  | LocalDateTime |
| modifiedDate | LocalDateTime |
| user         | User          |

## API

| Content               | HTTP Method | URI                       | Response                    |
|-----------------------|-------------|---------------------------|-----------------------------|
| User signUp           | POST        | /user                     | HTTP response + userId      |
| User update           | PATCH       | /user/{userId}            | HTTP response + userId      |
| User findOne          | GET         | /user/{userId}            | HTTP response + User        |
| User findAll          | GET         | /user                     | HTTP response + List<User>  |
| Todo create           | POST        | /user/{userId}/todo       | HTTP response + todoId      |
| Todo updateContent    | PATCH       | /todo/{todoId}/content    | HTTP response + todoId      |
| Todo updateIsComplete | PATCH       | /todo/{todoId}/isComplete | HTTP response + todoId      |
| Todo delete           | DELETE      | /todos/{todoId}           | HTTP response + User        |
| Todo findTodoWithUser | GET         | /todos/{todoId}           | HTTP response + Todo + User |

## Test Case
- userService
  - signUp
  - duplicateUserException
  - update
  - nonExistingUserUpdateException
- todoService
  - create
  - updateContent
  - nonExistingTodoUpdateException
  - updateIsComplete
  - nonExistingTodoUpdateException
  - delete
  - nonExistingTodoDeleteException
