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

- Todo List

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
- User

| Content   | HTTP Method | URI | Response |
|-----------|-------------|-----|----------|
| signUp    | POST        |     |          |
| update    | PATCH       |     |          |
| delete    | DELETE      |     |          |
| findOne   | GET         |     |          |
| findUsers | GET         |     |          |

- Todo List

## How it Works

