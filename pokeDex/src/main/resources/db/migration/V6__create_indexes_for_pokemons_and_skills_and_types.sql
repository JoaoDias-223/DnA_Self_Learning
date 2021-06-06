CREATE UNIQUE INDEX IF NOT EXISTS pokemon_id_idx ON pokedex.pokemons(id);
CREATE UNIQUE INDEX IF NOT EXISTS skill_id_idx ON pokedex.skills(id);
CREATE UNIQUE INDEX IF NOT EXISTS type_id_idx ON pokedex.types(id);