
CREATE TABLE IF NOT EXISTS User (
  username VARCHAR(100) NOT NULL,  
  name VARCHAR(100) NOT NULL,
  lastname VARCHAR(100) NOT NULL,
  userID  BINARY(36) NOT NULL,
  password BINARY(60) NOT NULL,
  age INTEGER,
  email VARCHAR(100),
  created_date datetime,
  last_modified_date datetime,
  CONSTRAINT PK_User PRIMARY KEY (username)
  
);