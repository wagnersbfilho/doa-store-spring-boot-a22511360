-- ============================
-- EMPLOYEE HIERARCHY
-- ============================
CREATE TABLE employee (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    nif CHAR(9) NOT NULL UNIQUE,
    hire_date DATE NOT NULL,
    salary NUMERIC(12,2) NOT NULL CHECK (salary > 0)
);

CREATE TABLE salesperson (
    id BIGINT PRIMARY KEY,
    commission_rate NUMERIC(5,2) NOT NULL CHECK (commission_rate BETWEEN 0 AND 100),
    total_sales NUMERIC(14,2) NOT NULL CHECK (total_sales >= 0),
    CONSTRAINT fk_salesperson_employee
        FOREIGN KEY (id) REFERENCES employee(id)
);

CREATE TABLE manager (
    id BIGINT PRIMARY KEY,
    department VARCHAR(50) NOT NULL,
    bonus NUMERIC(12,2) NOT NULL CHECK (bonus >= 0),
    CONSTRAINT fk_manager_employee
        FOREIGN KEY (id) REFERENCES employee(id)
);

-- ============================
-- JEWELRY HIERARCHY
-- ============================
CREATE TABLE jewelry (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    type VARCHAR(20) NOT NULL,
    material VARCHAR(50) NOT NULL,
    weight NUMERIC(10,2) NOT NULL CHECK (weight > 0),
    price NUMERIC(12,2) NOT NULL CHECK (price > 0),
    stock INTEGER NOT NULL CHECK (stock >= 0),
    category VARCHAR(20) NOT NULL
);

CREATE TABLE necklace (
    id BIGINT PRIMARY KEY,
    length NUMERIC(10,2) NOT NULL CHECK (length > 0),
    CONSTRAINT fk_necklace_jewelry
        FOREIGN KEY (id) REFERENCES jewelry(id)
);

CREATE TABLE earring (
    id BIGINT PRIMARY KEY,
    clasp_type VARCHAR(50) NOT NULL,
    CONSTRAINT fk_earring_jewelry
        FOREIGN KEY (id) REFERENCES jewelry(id)
);

CREATE TABLE ring (
    id BIGINT PRIMARY KEY,
    size INTEGER NOT NULL CHECK (size BETWEEN 10 AND 30),
    CONSTRAINT fk_ring_jewelry
        FOREIGN KEY (id) REFERENCES jewelry(id)
);

-- ============================
-- CUSTOMER & ORDER
-- ============================
CREATE TABLE customer (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    nif CHAR(9) NOT NULL UNIQUE,
    email VARCHAR(150) NOT NULL,
    phone CHAR(9) NOT NULL,
    address TEXT NOT NULL
);

CREATE TABLE orders (
    id BIGSERIAL PRIMARY KEY,
    order_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    customer_id BIGINT NOT NULL,
    CONSTRAINT fk_order_customer
        FOREIGN KEY (customer_id) REFERENCES customer(id)
);

-- ============================
-- ORDER ITEM
-- ============================
CREATE TABLE order_item (
    id BIGSERIAL PRIMARY KEY,
    quantity INTEGER NOT NULL CHECK (quantity > 0),
    price_at_purchase NUMERIC(12,2) NOT NULL CHECK (price_at_purchase > 0),
    order_id BIGINT NOT NULL,
    jewelry_id BIGINT NOT NULL,
    CONSTRAINT fk_item_order FOREIGN KEY (order_id) REFERENCES orders(id),
    CONSTRAINT fk_item_jewelry FOREIGN KEY (jewelry_id) REFERENCES jewelry(id)
);

-- ============================
-- PAYMENT
-- ============================
CREATE TABLE payment (
    id BIGSERIAL PRIMARY KEY,
    amount NUMERIC(12,2) NOT NULL CHECK (amount > 0),
    payment_date DATE NOT NULL,
    payment_method VARCHAR(20) NOT NULL,
    order_id BIGINT NOT NULL,
    CONSTRAINT fk_payment_order FOREIGN KEY (order_id) REFERENCES orders(id)
);
