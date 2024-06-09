CREATE TABLE Address (
    HouseNo NUMBER PRIMARY KEY,
    Street VARCHAR2(30),
    City VARCHAR2(20),
    Zip NUMBER(6) CHECK (Zip >= 0),
    State VARCHAR2(5),
    CONSTRAINT fk_City FOREIGN KEY (City) REFERENCES City(City)
);
