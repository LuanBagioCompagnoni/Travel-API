-- 1. Criação da tabela route
CREATE TABLE route (
                       id SERIAL PRIMARY KEY,
                       path VARCHAR(255) NOT NULL,
                       method VARCHAR(10) NOT NULL
);

-- Insert de exemplo na route
INSERT INTO route (path, method) VALUES
                                     ('/destinations', 'GET'),
                                     ('/destinations/{id}', 'GET'),
                                     ('/users', 'POST'),
                                     ('/users/{id}', 'DELETE');

-- 2. Criação da tabela role
CREATE TABLE role (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(50) NOT NULL
);

-- 3. Tabela intermediária role_route para ManyToMany entre role e route
CREATE TABLE role_route (
                            role_id INTEGER NOT NULL REFERENCES role(id) ON DELETE CASCADE,
                            route_id INTEGER NOT NULL REFERENCES route(id) ON DELETE CASCADE,
                            PRIMARY KEY (role_id, route_id)
);

-- Inserir o grupo de permissão admin (id 1)
INSERT INTO role (name) VALUES ('ADMIN');

-- 3. Associar todas as rotas criadas acima à role admin (id 1)
INSERT INTO role_route (role_id, route_id)
SELECT 1, id FROM route;

-- 4. Criação da tabela user
CREATE TABLE "user" (
                        id SERIAL PRIMARY KEY,
                        username VARCHAR(100) UNIQUE NOT NULL,
                        password VARCHAR(255) NOT NULL,
                        token VARCHAR(255),
                        role_id INTEGER REFERENCES role(id)
);

-- Inserir usuário admin com senha 'admin' (precisa ser hashed na aplicação)
INSERT INTO "user" (username, password, role_id) VALUES
    ('admin', 'admin', 1);
