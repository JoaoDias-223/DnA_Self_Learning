CREATE TABLE IF NOT EXISTS pokedex.pokemon_has_skill (
    pokemon_id      VARCHAR(40)     NOT NULL,
    skill_id        VARCHAR(40)     NOT NULL,

    CONSTRAINT fk_pokemon
        FOREIGN KEY (pokemon_id)
            REFERENCES pokedex.pokemons(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,

    CONSTRAINT fk_skill
       FOREIGN KEY (skill_id)
            REFERENCES pokedex.skills(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);