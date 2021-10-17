CREATE TABLE Users
(
    id SERIAL PRIMARY KEY NOT NULL,
    team_id INT,
    name VARCHAR(50),
    clickCount INT NOT NULL,
    password VARCHAR(50)
);

CREATE TABLE Teams
(
    id SERIAL PRIMARY KEY NOT NULL,
    clickCount INT NOT NULL,
    admin INT
);

ALTER TABLE Users ADD FOREIGN KEY (team_id) REFERENCES Teams(id);
ALTER TABLE Teams ADD FOREIGN KEY (admin) REFERENCES Users(id);

CREATE INDEX idx_Teams_id ON Users(team_id);