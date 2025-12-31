-- Criacao da tabela employee
CREATE TABLE employee (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    nif CHAR(9) NOT NULL UNIQUE,
    hire_date DATE NOT NULL,
    salary NUMERIC(12,2) NOT NULL CHECK (salary > 0)
);