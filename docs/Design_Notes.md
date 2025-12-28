# Design Notes

## Why ArrayList?
ArrayList allows dynamic resizing and easy iteration, unlike arrays which are fixed in size.
This makes it suitable for in-memory storage of students, courses, and enrollments.

## Use of Static Members
Static ID counters are used in `IdGenerator` to ensure unique IDs across the application
without creating objects.

## Inheritance Usage
`Person` is a base class for `Student`. This avoids duplication of common fields like
name and email and demonstrates inheritance and polymorphism.

## Exception Handling
- `EntityNotFoundException` is used when requested entities are missing.
- `InvalidInputException` is used for handling invalid user input.
  This keeps error handling clean and meaningful.