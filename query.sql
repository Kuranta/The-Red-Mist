INSERT INTO users (age, email, firstName, lastName, password) VALUES (19, 'test@mail.ru', 'Test', 'Zabeg',
                                                                      '$2a$12$34IH/W2gtM8Pf6uTTYogn.uMnrGaoftUAzHeRJsUoS1trv2VrdSDe');
# Password - test321

INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');

INSERT INTO users_roles (User_id, Roles_id) VALUES (1, 1);
