CREATE TABLE pokedex.skills_types (
    skill_id bigint NOT NULL,
    type_id bigint NOT NULL,

    CONSTRAINT fk_skill
        FOREIGN KEY (skill_id)
            REFERENCES pokedex.skills(skill_id),

    CONSTRAINT fk_type
        FOREIGN KEY (type_id)
            REFERENCES pokedex.types(type_id)

);