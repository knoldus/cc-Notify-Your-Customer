CREATE TABLE IF NOT EXISTS client (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  user_name VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL,
  email VARCHAR(60) NOT NULL,
  phone VARCHAR(15) NOT NULL
);

CREATE TABLE IF NOT EXISTS client_address (
  id BIGSERIAL PRIMARY KEY,
  address VARCHAR(100) NOT NULL,
  country VARCHAR(30) NOT NULL,
  pin_code VARCHAR(11) NOT NULL,
  client_id BIGINT REFERENCES client(id)
);

CREATE TABLE IF NOT EXISTS access_token (
  id BIGSERIAL PRIMARY KEY,
  client_id BIGINT REFERENCES client(id),
  token VARCHAR(100) NOT NULL UNIQUE,
  created_at TIMESTAMP NOT NULL
);
