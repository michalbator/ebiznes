CREATE TABLE countries (
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    short_name VARCHAR(10) NOT NULL
);

CREATE TABLE authors (
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    country INT,
    FOREIGN KEY (country) REFERENCES countries(id) ON DELETE CASCADE
);

CREATE TABLE books (
  id INT PRIMARY KEY,
    isbn VARCHAR(13) NOT NULL,
    title VARCHAR(100) NOT NULL,
    author INT NOT NULL,
    FOREIGN KEY (author) REFERENCES authors(id) ON DELETE CASCADE
);

CREATE TABLE users (
    id INT PRIMARY KEY,
    login VARCHAR(50) NOT NULL,
    password VARCHAR(20) NOT NULL
);

CREATE TABLE bookOrders (
    id INT PRIMARY KEY,
    book INT NOT NULL,
    idOrder INT NOT NULL,
    FOREIGN KEY (idOrder) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (book) REFERENCES books(id) ON DELETE CASCADE
);

CREATE TABLE orders (
    id INT PRIMARY KEY,
    user INT NOT NULL,
    status VARCHAR(50) NOT NULL,
    FOREIGN KEY (user) REFERENCES users(id) ON DELETE CASCADE
);