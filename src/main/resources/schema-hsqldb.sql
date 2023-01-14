DROP TABLE IF EXISTS account;

CREATE TABLE account(
    id          INTEGER IDENTITY PRIMARY KEY ,
    username    VARCHAR(50),
    password    VARCHAR(100),
    name        VARCHAR(50),
    telephone   VARCHAR(20),
    email       VARCHAR(100)
);

CREATE UNIQUE INDEX account_user ON account (username);
CREATE UNIQUE INDEX account_telephone ON account (telephone);
CREATE UNIQUE INDEX account_email ON account (email);

