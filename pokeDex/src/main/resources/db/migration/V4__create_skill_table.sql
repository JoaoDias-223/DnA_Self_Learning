CREATE TABLE IF NOT EXISTS pokedex.skills
(
    id              VARCHAR(40)     NOT NULL,
    name            VARCHAR(255)    NOT NULL,
    description     VARCHAR(255)    NOT NULL,
    action          VARCHAR(20)     NOT NULL,
    action_points   integer         NOT NULL,

    PRIMARY KEY (id)
);