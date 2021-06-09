CREATE TABLE IF NOT EXISTS pokedex.pokemons
(
    id              VARCHAR(40)     NOT NULL,
    name            VARCHAR(255)    NOT NULL,
    description     VARCHAR(255)    NOT NULL,

    PRIMARY KEY (id)
);