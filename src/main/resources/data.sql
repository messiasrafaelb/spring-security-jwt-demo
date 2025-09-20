-- ROLES
INSERT INTO roles (type) VALUES ('ADMIN');
INSERT INTO roles (type) VALUES ('USER');

-- USERS
INSERT INTO users (name, email, password) VALUES
  ('Alice Admin', 'alice@ex.com', '{bcrypt}$2a$10$UrUbd9LVENfLG5gBqIQGK.kyJyPeT1cFroVleG5ZxCB.zLz5sGFY2'),
  ('Bob User',   'bob@ex.com',   '{bcrypt}$2a$10$K2rYy3dmZlDKM.9ualp8XuTnhNn2sizCGZ02.HPH.Rd0mAUgI4tka');

-- USERS_ROLES
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1); -- Alice ADMIN admin123
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2); -- Bob USER 123456

-- POSTS
-- Alice
INSERT INTO posts (title, content, created_at, updated_at, user_id) VALUES
  ('Post 1 da Alice', 'Conteúdo do post 1 da Alice.', NOW(), NOW(), 1),
  ('Post 2 da Alice', 'Conteúdo do post 2 da Alice.', NOW(), NOW(), 1),
  ('Post 3 da Alice', 'Conteúdo do post 3 da Alice.', NOW(), NOW(), 1),
  ('Post 4 da Alice', 'Conteúdo do post 4 da Alice.', NOW(), NOW(), 1),
  ('Post 5 da Alice', 'Conteúdo do post 5 da Alice.', NOW(), NOW(), 1),
  ('Post 6 da Alice', 'Conteúdo do post 6 da Alice.', NOW(), NOW(), 1),
  ('Post 7 da Alice', 'Conteúdo do post 7 da Alice.', NOW(), NOW(), 1),
  ('Post 8 da Alice', 'Conteúdo do post 8 da Alice.', NOW(), NOW(), 1),
  ('Post 9 da Alice', 'Conteúdo do post 9 da Alice.', NOW(), NOW(), 1),
  ('Post 10 da Alice', 'Conteúdo do post 10 da Alice.', NOW(), NOW(), 1),
  ('Post 11 da Alice', 'Conteúdo do post 11 da Alice.', NOW(), NOW(), 1),
  ('Post 12 da Alice', 'Conteúdo do post 12 da Alice.', NOW(), NOW(), 1),
  ('Post 13 da Alice', 'Conteúdo do post 13 da Alice.', NOW(), NOW(), 1),
  ('Post 14 da Alice', 'Conteúdo do post 14 da Alice.', NOW(), NOW(), 1),
  ('Post 15 da Alice', 'Conteúdo do post 15 da Alice.', NOW(), NOW(), 1),
  ('Post 16 da Alice', 'Conteúdo do post 16 da Alice.', NOW(), NOW(), 1),
  ('Post 17 da Alice', 'Conteúdo do post 17 da Alice.', NOW(), NOW(), 1),
  ('Post 18 da Alice', 'Conteúdo do post 18 da Alice.', NOW(), NOW(), 1),
  ('Post 19 da Alice', 'Conteúdo do post 19 da Alice.', NOW(), NOW(), 1),
  ('Post 20 da Alice', 'Conteúdo do post 20 da Alice.', NOW(), NOW(), 1),
  ('Post 21 da Alice', 'Conteúdo do post 21 da Alice.', NOW(), NOW(), 1),
  ('Post 22 da Alice', 'Conteúdo do post 22 da Alice.', NOW(), NOW(), 1),
  ('Post 23 da Alice', 'Conteúdo do post 23 da Alice.', NOW(), NOW(), 1),
  ('Post 24 da Alice', 'Conteúdo do post 24 da Alice.', NOW(), NOW(), 1),
  ('Post 25 da Alice', 'Conteúdo do post 25 da Alice.', NOW(), NOW(), 1),
  ('Post 26 da Alice', 'Conteúdo do post 26 da Alice.', NOW(), NOW(), 1),
  ('Post 27 da Alice', 'Conteúdo do post 27 da Alice.', NOW(), NOW(), 1),
  ('Post 28 da Alice', 'Conteúdo do post 28 da Alice.', NOW(), NOW(), 1),
  ('Post 29 da Alice', 'Conteúdo do post 29 da Alice.', NOW(), NOW(), 1),
  ('Post 30 da Alice', 'Conteúdo do post 30 da Alice.', NOW(), NOW(), 1);

-- Bob
INSERT INTO posts (title, content, created_at, updated_at, user_id) VALUES
  ('Post 1 do Bob', 'Conteúdo do post 1 do Bob.', NOW(), NOW(), 2),
  ('Post 2 do Bob', 'Conteúdo do post 2 do Bob.', NOW(), NOW(), 2),
  ('Post 3 do Bob', 'Conteúdo do post 3 do Bob.', NOW(), NOW(), 2),
  ('Post 4 do Bob', 'Conteúdo do post 4 do Bob.', NOW(), NOW(), 2),
  ('Post 5 do Bob', 'Conteúdo do post 5 do Bob.', NOW(), NOW(), 2),
  ('Post 6 do Bob', 'Conteúdo do post 6 do Bob.', NOW(), NOW(), 2),
  ('Post 7 do Bob', 'Conteúdo do post 7 do Bob.', NOW(), NOW(), 2),
  ('Post 8 do Bob', 'Conteúdo do post 8 do Bob.', NOW(), NOW(), 2),
  ('Post 9 do Bob', 'Conteúdo do post 9 do Bob.', NOW(), NOW(), 2),
  ('Post 10 do Bob', 'Conteúdo do post 10 do Bob.', NOW(), NOW(), 2),
  ('Post 11 do Bob', 'Conteúdo do post 11 do Bob.', NOW(), NOW(), 2),
  ('Post 12 do Bob', 'Conteúdo do post 12 do Bob.', NOW(), NOW(), 2),
  ('Post 13 do Bob', 'Conteúdo do post 13 do Bob.', NOW(), NOW(), 2),
  ('Post 14 do Bob', 'Conteúdo do post 14 do Bob.', NOW(), NOW(), 2),
  ('Post 15 do Bob', 'Conteúdo do post 15 do Bob.', NOW(), NOW(), 2),
  ('Post 16 do Bob', 'Conteúdo do post 16 do Bob.', NOW(), NOW(), 2),
  ('Post 17 do Bob', 'Conteúdo do post 17 do Bob.', NOW(), NOW(), 2),
  ('Post 18 do Bob', 'Conteúdo do post 18 do Bob.', NOW(), NOW(), 2),
  ('Post 19 do Bob', 'Conteúdo do post 19 do Bob.', NOW(), NOW(), 2),
  ('Post 20 do Bob', 'Conteúdo do post 20 do Bob.', NOW(), NOW(), 2),
  ('Post 21 do Bob', 'Conteúdo do post 21 do Bob.', NOW(), NOW(), 2),
  ('Post 22 do Bob', 'Conteúdo do post 22 do Bob.', NOW(), NOW(), 2),
  ('Post 23 do Bob', 'Conteúdo do post 23 do Bob.', NOW(), NOW(), 2),
  ('Post 24 do Bob', 'Conteúdo do post 24 do Bob.', NOW(), NOW(), 2),
  ('Post 25 do Bob', 'Conteúdo do post 25 do Bob.', NOW(), NOW(), 2),
  ('Post 26 do Bob', 'Conteúdo do post 26 do Bob.', NOW(), NOW(), 2),
  ('Post 27 do Bob', 'Conteúdo do post 27 do Bob.', NOW(), NOW(), 2),
  ('Post 28 do Bob', 'Conteúdo do post 28 do Bob.', NOW(), NOW(), 2),
  ('Post 29 do Bob', 'Conteúdo do post 29 do Bob.', NOW(), NOW(), 2),
  ('Post 30 do Bob', 'Conteúdo do post 30 do Bob.', NOW(), NOW(), 2);