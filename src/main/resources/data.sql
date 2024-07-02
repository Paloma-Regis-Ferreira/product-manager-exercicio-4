-- Arquivo inicial para criação de tabela e inserção de dados para o H2

-- Drop table para reiniciar do zero, se necessário
DROP TABLE IF EXISTS products;

-- Criação da tabela products
CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    price DOUBLE NOT NULL
);

-- Inserção de alguns dados iniciais
INSERT INTO products (name, description, price) VALUES ('Notebook', 'Dell', 19.99);
INSERT INTO products (name, description, price) VALUES ('Tablet', 'Tablet Sansung', 29.99);
INSERT INTO products (name, description, price) VALUES ('Teclado', NULL, 9.99);
INSERT INTO products (name, description, price) VALUES ('Mouse', 'Mouse sem fio', 49.99);
INSERT INTO products (name, description, price) VALUES ('Celular', 'Iphone 11', 39.99);