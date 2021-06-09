ALTER TABLE IF EXISTS pokedex.pokemon_has_skill
    ADD CONSTRAINT uq_pokemon_has_skill UNIQUE(pokemon_id, skill_id);

ALTER TABLE IF EXISTS pokedex.pokemon_has_type
    ADD CONSTRAINT uq_pokemon_has_type UNIQUE(pokemon_id, type_id);

ALTER TABLE IF EXISTS pokedex.skill_has_type
    ADD CONSTRAINT uq_skill_has_type UNIQUE(skill_id, type_id);