CREATE TABLE pokedex.skills
(
    skill_id bigint NOT NULL,
    skill_damage integer NOT NULL,
    skill_description character varying(255) COLLATE pg_catalog."default" NOT NULL,
    skill_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    PRIMARY KEY (skill_id)
);