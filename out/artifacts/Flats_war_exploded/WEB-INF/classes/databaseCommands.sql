-- General commands
DROP DATABASE IF EXISTS Apartments_db;

CREATE DATABASE Apartments_db;

USE Apartments_db;

-- Table: apartments
CREATE TABLE Apartments (
  id          INT UNSIGNED     NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id     INT UNSIGNED     NOT NULL,
  status      VARCHAR(32)      NOT NULL,
  type        VARCHAR(32)      NOT NULL,
  floor       TINYINT UNSIGNED NOT NULL,
  location    VARCHAR(32)      NOT NULL,
  street      VARCHAR(128)     NOT NULL,
  price       INT              NOT NULL,
  phone       VARCHAR(17)      NOT NULL,
  name        VARCHAR(64)      NOT NULL,
  description TEXT(500)        NOT NULL,
  date        DATE             NOT NULL
);

-- Table: photos
CREATE TABLE Photos (
  id           INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  apartment_id INT                   DEFAULT NULL,
  serial       INT                   DEFAULT NULL,
  name         TEXT         NOT NULL,
  body         LONGBLOB     NOT NULL
);

-- Table: users
CREATE TABLE users (
  id               INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username         VARCHAR(255) NOT NULL,
  password         VARCHAR(255) NOT NULL,
  designation_name VARCHAR(255) NOT NULL
);

-- Table: roles
CREATE TABLE roles (
  id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

-- Table for mapping user and roles: user_roles
CREATE TABLE user_roles (
  user_id INT UNSIGNED NOT NULL,
  role_id INT          NOT NULL
);

-- Insert data
INSERT INTO users VALUES (
  1, 'kvartirant', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG', 'superAdmin'
);

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ANONYMOUS');
INSERT INTO roles VALUES (3, 'ROLE_ADMIN');

INSERT INTO user_roles VALUES (1, 3);