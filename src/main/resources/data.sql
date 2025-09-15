-- ROLES
INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_USER');

-- USERS (use os hashes gerados no console)
INSERT INTO users (name, email, password) VALUES
  ('Alice Admin', 'alice@ex.com', '{bcrypt}$2a$10$UrUbd9LVENfLG5gBqIQGK.kyJyPeT1cFroVleG5ZxCB.zLz5sGFY2'),
  ('Bob User',   'bob@ex.com',   '{bcrypt}$2a$10$K2rYy3dmZlDKM.9ualp8XuTnhNn2sizCGZ02.HPH.Rd0mAUgI4tka');

-- USERS_ROLES (id é identity; pode omitir)
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1); -- Alice ADMIN admin123
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2); -- Bob USER 123456
