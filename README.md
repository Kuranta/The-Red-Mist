# The Red Mist 
### Spring Boot/Security(with JWT)/MVC/Data  Application with some frontend code - HTML, JavaScript, jQuery, Bootstrap 
 
Application contains CRUD operations, which you can execute in admin panel(for admins obviously), 
and you have user panel, where usual users see almost all data.

Application contains authentication based on JWT.

    To start you'll need to open terminal and run next commands:
    1. docker build . -t the-red-mist:v1
    2. docker-compose run -d
    3. docker cp query.sql java-mysql:/query.sql
    4. Get-Content .\query.sql | docker exec -i java-mysql mysql -uroot -pkottom2 user
    5. To perform CRUD operation you must authenticate. Email: test@mail.ru Password: test321

http://localhost:8080/user - view for users.

http://localhost:8080/admin - view for admins.

http://localhost:8080/api/users - api for CRUD user operations.

http://localhost:8080/api/role - api for get/post role.