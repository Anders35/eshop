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

## Tutorial 3

### Reflection

1. SOLID Principles Implemented:
   1. **Single Responsibility Principle (SRP)**

      Previously, the `ProductController` included functionality related to `CarController`, mixing their responsibilities. The `CarController` handles car-related endpoints, while the `ProductController` handles product-related endpoints. By separating the `CarController` into its own class file, each class now has a clear and different responsibility.

   2. **Liskov Substitution Principle (LSP)**

      Previously, the `CarController` extended `ProductController` and the two classes that handled different responsibilities. The `CarController` was inheriting methods and dependencies from `ProductController` that it didn't need, and it wasn't substitutable for its parent class. That's why I removed the inheritance.

   3. **Dependency Inversion Principle (DIP)**

      Previously, `CarController` directly depends on `CarServiceImpl`. This violates DIP because `CarController` should depend on the `CarService` interface instead. Therefore, I changed the data type of the `carService` variable in `CarController` to `CarService`.
   
2. By applying SOLID principles, the code is now more maintainable because each controller has a clear and different responsibility. If I need to modify how car operations work, I only need to look at `CarController` without worrying about affecting product operations. The code is also more extensible. I can add new controllers or modify existing ones without changing other parts of the application.

3. Without applying SOLID principles, modifying one class might unintentionally affect another. Testing would be more difficult because of the inherited dependencies. Additionally, the code would be less readable and harder to understand. That makes it more challenging for other people to work on the project. For example, in the original code, it wasn't immediately clear why `CarController` needed to extend `ProductController`.

## Tutorial 4

### Reflection

1. The TDD flow I followed was highly valuable. It helped clarify the required functionality before writing the actual implementation. By writing tests first, I defined the PaymentService's interface and behavior explicitly which guided my implementation. The RED phase forced me to think about what our code should do, the GREEN phase focused on making it work correctly, and the refactoring phase improved the design without fear of breaking functionality.

2. My tests generally follow the F.I.R.S.T. principles even though there's room for improvement. For Fast, my unit tests don't interact with databases or external services which make them quick to execute. For Independent, each test method stands alone and doesn't depend on the state from other tests. For Repeatable, my tests use mocks and don't rely on external state which make them consistently reproducible. For Self-validating, each test has clear assertions that either pass or fail without ambiguity. For Timely, I wrote tests before implementation as per TDD. 