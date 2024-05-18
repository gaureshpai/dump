CREATE TABLE Match (
    MId INTEGER,
    TId INTEGER,
    Player1 INTEGER,
    Player2 INTEGER,
    MatchDt DATE NOT NULL,
    Winner INTEGER,
    Score VARCHAR2(30) NOT NULL,
    PRIMARY KEY (MId, TId),
    FOREIGN KEY (TId) REFERENCES Tournament(TId),
    FOREIGN KEY (Player1) REFERENCES Player(PId),
    FOREIGN KEY (Player2) REFERENCES Player(PId),
    FOREIGN KEY (Winner) REFERENCES Player(PId),
    CHECK (Player1 <> Player2)
);
