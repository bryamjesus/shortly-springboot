CREATE TABLE
  url (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    original_url TEXT NOT NULL,
    short_code VARCHAR(8) NOT NULL UNIQUE,
    hit_count INT,
    created_at TIMESTAMP NOT NULL,
    modified_at TIMESTAMP
  );