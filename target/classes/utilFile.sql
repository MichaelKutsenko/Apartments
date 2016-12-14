DROP TABLE IF EXISTS Apartments;

DROP TABLE IF EXISTS Apartments;

CREATE TABLE Apartments (
  id          INT UNSIGNED     NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id     INT UNSIGNED              DEFAULT NULL,
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