CREATE TABLE pokedex.pokemons_types (
    pokemon_id bigint NOT NULL,
    type_id bigint NOT NULL,

    CONSTRAINT fk_pokemon
        FOREIGN KEY (pokemon_id)
            REFERENCES pokedex.pokemons(pokemon_id),

    CONSTRAINT fk_type
        FOREIGN KEY (type_id)
            REFERENCES pokedex.types(type_id)
);