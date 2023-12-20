=== Spring Boot Project Readme ===

1. **Running the Project:**
   - Ensure you have Java and Maven installed.
   - Extract the contents of this zip file.
   - Open a terminal/command prompt in the project directory.
   - Run the following command:
     ```
     mvn spring-boot:run
     ```
   - The Spring Boot application will start on http://localhost:8080.

2. **Setting Up the Database:**
   - The project uses MongoDB.
   - Configure the MongoDB connection in `application.properties` or `application.yml`.
   - Create a .env file in the resources folder and set the following MongoDB parameters:
       ```
       MONGO_DATABASE=""
       MONGO_USER=""
       MONGO_PASSWORD=""
       MONGO_CLUSTER=""
       ```

3. **API Documentation:**

   - **Add Employee:**
     - Endpoint: POST /api/employees/add
     - Request:
       ```json
       {
         "employeeName": "John Doe",
         "phoneNumber": "123-456-7890",
         "email": "john.doe@example.com",
         "reportsTo": "manager123",
         "profileImage": "https://example.com/johndoe.jpg"
       }
       ```
     - Response: Employee ID

   - **Get All Employees:**
     - Endpoint: GET /api/employees/all
     - Response: List of Employees

   - **Delete Employee by ID:**
     - Endpoint: DELETE /api/employees/delete/{employeeId}
     - Response: No Content

   - **Update Employee by ID:**
     - Endpoint: PUT /api/employees/update/{employeeId}
     - Request:
       ```json
       {
         "employeeName": "Updated Name",
         "phoneNumber": "Updated Phone",
         "email": "updated.email@example.com",
         "reportsTo": "newManager123",
         "profileImage": "https://example.com/updated.jpg"
       }
       ```
     - Response: Updated Employee

4. **Exception Handling and Validation:**
   - The project includes exception handling for better error responses.
   - Validation is applied using Spring Boot's validation starter. Invalid requests will result in appropriate error messages.

==================================
