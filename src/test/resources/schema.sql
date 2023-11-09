CREATE TABLE IF NOT EXISTS "user" (
  id CHAR(36),
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (email)
);
