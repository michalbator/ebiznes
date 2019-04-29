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