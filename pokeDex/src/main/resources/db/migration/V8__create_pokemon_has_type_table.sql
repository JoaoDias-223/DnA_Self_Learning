CREATE TABLE IF NOT EXISTS pokedex.pokemon_has_type (
    pokemon_id      VARCHAR(40)     NOT NULL,
    type_id         VARCHAR(40)     NOT NULL,

    CONSTRAINT fk_pokemon
        FOREIGN KEY (pokemon_id)
            REFERENCES pokedex.pokemons(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT fk_type
        FOREIGN KEY (type_id)
            REFERENCES pokedex.types(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);