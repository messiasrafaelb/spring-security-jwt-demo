-- Roles
INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_USER');

-- Users
INSERT INTO users (id, name, email, password) VALUES (1, 'Alice', 'alice@example.com', '$2a$10$hashedpassword1');
INSERT INTO users (id, name, email, password) VALUES (2, 'Bob', 'bob@example.com', '$2a$10$hashedpassword2');

-- Users_Roles
INSERT INTO users_roles (id, user_id, role_id) VALUES (1, 1, 1); -- Alice é ADMIN
INSERT INTO users_roles (id, user_id, role_id) VALUES (2, 2, 2); -- Bob é USER
