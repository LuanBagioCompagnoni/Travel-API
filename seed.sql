CREATE TABLE routes (
    id SERIAL PRIMARY KEY,
    path VARCHAR(255) NOT NULL,
    method VARCHAR(10) NOT NULL
);

INSERT INTO routes (path, method) VALUES
    ('^/destination.*', 'GET'),
    ('^/destination.*', 'POST'),
    ('^/destination.*', 'PATCH'),
    ('^/destination.*', 'PUT'),
    ('^/destination.*', 'DEL'),
    ('^/destination-review.*', 'POST'),
    ('^/user.*', 'POST');

CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE roles_routes (
    role_id INTEGER NOT NULL REFERENCES roles(id) ON DELETE CASCADE,
    route_id INTEGER NOT NULL REFERENCES routes(id) ON DELETE CASCADE,
    PRIMARY KEY (role_id, route_id)
);

INSERT INTO roles (name) VALUES ('ADMIN');

INSERT INTO roles_routes (role_id, route_id)
SELECT 1, id FROM routes;

INSERT INTO roles (name) VALUES ('USER');

INSERT INTO roles_routes (role_id, route_id)
SELECT 2, id FROM routes WHERE method = 'GET';

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    token VARCHAR(255),
    role_id INTEGER REFERENCES roles(id)
);

-- Insere usuário admin e senha admin
-- Insere usuário user e senha user
INSERT INTO users (username, password, role_id) VALUES
    ('admin', '$2y$10$6TuHWwH35jmWRMLhbuQkeutSa2WvM3Q9rRQCCbjYM/q6AV6AmWofi', 1),
    ('user', '$2y$10$mSpestF0v7Csm4gxt.LKsOPi99T2ISkh/gDO0fe6ToHG2qCfT.E.q', 2);
