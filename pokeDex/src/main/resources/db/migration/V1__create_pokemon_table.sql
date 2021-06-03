CREATE TABLE pokedex.pokemons
(
    pokemon_id bigint NOT NULL,
    pokemon_description character varying(255) COLLATE pg_catalog."default" NOT NULL,
    pokemon_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    PRIMARY KEY (pokemon_id)
);
