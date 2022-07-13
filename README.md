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
| gender       | char          |
| birthdate    | LocalDate     |
| createdDate  | LocalDateTime |
| modifiedDate | LocalDateTime |
| todoList     | List<Todo>    |

- Todo

| Variable     | Type          |
|--------------|---------------|
| id           | Long          |
| content      | String        |
| isComplete   | Boolean       |
| dueAt        | LocalDateTime |
| createdDate  | LocalDateTime |
| modifiedDate | LocalDateTime |
| user         | User          |

## API

| Content               | HTTP Method | URI                  | Response                    |
|-----------------------|-------------|----------------------|-----------------------------|
| User signUp           | POST        | /users               | HTTP response + userId      |
| User update           | PATCH       | /users/{userId}      | HTTP response + userId      |
| User findOne          | GET         | /users/{userId}      | HTTP response + User        |
| User findAll          | GET         | /users               | HTTP response + List<User>  |
| Todo create           | POST        | /users/{userId}/todo | HTTP response + todoId      |
| Todo update           | PATCH       | /todo/{todoId}       | HTTP response + todoId      |
| Todo delete           | DELETE      | /todos/{todoId}      | HTTP response + User        |
| Todo findTodoWithUser | GET         | /todos/{todoId}      | HTTP response + Todo + User |

## Test Case
- userService
  - signUp
  - duplicateUserException
  - update
  - nonExistingUserUpdateException
- todoService
  - create
  - update
  - nonExistingTodoUpdateException
  - delete
  - nonExistingTodoDeleteException
