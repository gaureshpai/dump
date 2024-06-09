CREATE TABLE Article (
    ArCode CHAR(5) PRIMARY KEY CHECK (ArCode LIKE 'A%'),
    ArName VARCHAR2(30) NOT NULL,
    Rate NUMBER(8,2),
    Quantity NUMBER(4) DEFAULT 0 CHECK (Quantity >= 0),
    Class CHAR(1) CHECK (Class IN ('A', 'B', 'C'))
);
