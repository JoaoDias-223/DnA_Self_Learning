CREATE TABLE IF NOT EXISTS pokedex.skill_has_type (
    skill_id        VARCHAR(20)     NOT NULL,
    type_id         VARCHAR(20)     NOT NULL,

    CONSTRAINT fk_skill
        FOREIGN KEY (skill_id)
            REFERENCES pokedex.skills(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT fk_type
        FOREIGN KEY (type_id)
            REFERENCES pokedex.types(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);