CREATE TABLE genre
(
    id         UUID PRIMARY KEY,
    title      VARCHAR(255) UNIQUE,
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
    create_date  TIMESTAMP WITHOUT TIME ZONE,
    update_date  TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE movie_genre
(
    movie_id UUID,
    genre_id UUID,
    PRIMARY KEY (movie_id, genre_id),
    FOREIGN KEY (movie_id) REFERENCES movie (id),
    FOREIGN KEY (genre_id) REFERENCES genre (id)
);