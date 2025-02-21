# eshop

## Tutorial 1

### Reflection 1

After reviewing my implementation for the new features, I have applied several clean code principles and secure coding practices. In my code, I've maintained the Single Responsibility Principle by ensuring each class has a specific purpose - the controller handles HTTP requests, the service layer manages business logic, and the repository handles data access. I've also implemented proper input validation and error handling to enhance security and reliability. However, I notice areas for improvement in my code. For example, when a new product is created, I noticed that it is not assigned by an ID. After that, I assigned a random UUID for every new product created.

### Reflection 2

1. After implementing unit tests, I feel a sense of relief because my code is now more robust and bug-free. As for number of test cases, I think it depends on how many features or functions we implemented in our code. Achieving 100% code coverage doesn't guarantee bug-free code. Code coverage is a metric that shows how much of the source code has been executed during testing, but it doesn't validate the quality or correctness of the tests themselves.
2. I think it will not affect the code quality and cleanliness.

## Tutorial 2

### Reflection

1. Code Quality Issues:
   1. Field Injection Issues
      
      Switch from field injection to constructor injection. By using constructor injection, the dependencies are explicit and must be passed during an object’s construction.

   2. Remove the declaration of thrown exception 'java.lang.Exception', as it cannot be thrown from method's body.

      Removed the unnecessary `throws Exception` declaration. This eliminates confusion for developers about potential exceptions that need to be handled.
   
   3. Remove empty method

      Removed the method entirely. Removing them keeps the codebase clean and focused.   

   4. Remove unused import

      Removed the unused imports. By removing them, the code becomes cleaner.

2. Yes, I believe my current implementation meets the definitions of Continuous Integration and Continuous Deployment. For Continuous Integration, I’ve implemented GitHub Actions that triggers an automated test suite using Gradle whenever there is push and pull request events. I've also implemented a workflow that triggers code scanning using SonarCloud. Additionally, for Continuous Deployment, I implemented an auto-deploy mechanism to Koyeb where every new commit is deployed to Koyeb, fulfilling the principles of Continuous Deployment by delivering updates to production in real-time.