CREATE TABLE pokedex.pokemons_skills (
    pokemon_id bigint NOT NULL,
    skill_id bigint NOT NULL,

    CONSTRAINT fk_pokemon
        FOREIGN KEY (pokemon_id)
            REFERENCES pokedex.pokemons(pokemon_id),

    CONSTRAINT fk_skill
        FOREIGN KEY (skill_id)
            REFERENCES pokedex.skills(skill_id)
);