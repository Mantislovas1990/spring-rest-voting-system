DROP TABLE IF EXISTS voter_role;
DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS files;
DROP TABLE IF EXISTS candidates;
DROP TABLE IF EXISTS voters;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS elections;

CREATE TABLE voters
(
    id         BIGSERIAL PRIMARY KEY NOT NULL,
    username   VARCHAR(255) UNIQUE   NOT NULL,
    password   VARCHAR(255)          NOT NULL,
    first_name VARCHAR(255)          NOT NULL,
    last_name  VARCHAR(255)          NOT NULL,
    email      varchar(100)          NOT NULL,
    gender     VARCHAR(10)           NOT NULL,
    age        integer               NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE candidates
(
    id         BIGSERIAL PRIMARY KEY NOT NULL,
    first_name varchar(50)           NOT NULL,
    last_name  varchar(50)           NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP

);

-- select count(*) from votes group by candidate_id
CREATE TABLE elections
(
    id         BIGSERIAL PRIMARY KEY NOT NULL,
    title      varchar(100)          not null,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP


);

CREATE TABLE votes
(
    id           BIGSERIAL PRIMARY KEY NOT NULL,
    voter_id     BIGINT REFERENCES voters (id),
    candidate_id BIGINT REFERENCES candidates (id),
    election_id  BIGINT REFERENCES elections (id),
    created_at   timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at   timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE roles
(
    id   BIGSERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) UNIQUE   NOT NULL
);

CREATE TABLE voter_role
(
    voter_id BIGINT REFERENCES voters (id),
    role_id  BIGINT REFERENCES roles (id)
);

CREATE TABLE files
(
    id           BIGSERIAL PRIMARY KEY NOT NULL,
    filename     VARCHAR(255)          NOT NULL,
    size         BIGINT                NOT NULL,
    bytes        BYTEA                 NOT NULL,
    candidate_id BIGINT REFERENCES candidates (id),
    created_at      TIMESTAMP             NOT NULL DEFAULT NOW(),
    updated_at      TIMESTAMP             NOT NULL DEFAULT NOW()

);


INSERT INTO roles (name)
VALUES ('VOTER'),
       ('ADMIN');

--PASS admin123
--PASS user123
INSERT INTO voters(username, password, first_name, last_name, email, gender, age)
VALUES ('admin', '{bcrypt}$2a$10$rIWyUZWc2sDvt2Jv8BrPB.DgMJgOl.k26McT1vSOQYk4NN76ECb96', 'mantas', 'bieliunas',
        'mantas@mantas.com', 'male', 22),
       ('user', '{bcrypt}$2a$10$3I6yy2JhN2BWEvF2pV6A2etA3MnwYRtfPPjZ6i6KyAsGYxaXn1s06', 'userName', 'userLastName',
        'user@mantas.com', 'male', 22);

INSERT INTO voter_role(voter_id, role_id)
VALUES (1,2),
       (2,1);