CREATE TABLE genre
(
    id          UUID PRIMARY KEY,
    title       VARCHAR(255) UNIQUE,
    create_date TIMESTAMP WITHOUT TIME ZONE,
    update_date TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE movie
(
    id          UUID PRIMARY KEY,
    title       VARCHAR(255),
    description TEXT,
    year        INT,
    player_url  VARCHAR(255),
    imdb_rating DOUBLE PRECISION,
    duration    TIME,
    create_date TIMESTAMP WITHOUT TIME ZONE,
    update_date TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE movie_genre
(
    movie_id UUID,
    genre_id UUID,
    PRIMARY KEY (movie_id, genre_id),
    FOREIGN KEY (movie_id) REFERENCES movie (id),
    FOREIGN KEY (genre_id) REFERENCES genre (id)
);

CREATE TABLE users
(
    id       UUID PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE roles
(
    id   UUID PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

CREATE TABLE user_roles
(
    user_id UUID REFERENCES users (id),
    role_id UUID REFERENCES roles (id),
    PRIMARY KEY (user_id, role_id)
);

INSERT INTO public.roles VALUES ('09caec76-bc1c-4628-94bd-53c2c61f0894', 'ROLE_USER');
INSERT INTO public.roles VALUES ('09caec76-bc1c-4628-94bd-53c2c61f0895', 'ROLE_MODERATOR');
INSERT INTO public.roles VALUES ('09caec76-bc1c-4628-94bd-53c2c61f0896', 'ROLE_ADMIN');

INSERT INTO public.users VALUES ('04e45937-0019-4f22-b9ec-70b55a7b0f0e', 'Admin', 'Admin@gmail.com', '$2a$10$zh8PSN4Dv20amoPYl9g33ebN3ommp14cTgbcuS37Y0eToqA00yIbS');