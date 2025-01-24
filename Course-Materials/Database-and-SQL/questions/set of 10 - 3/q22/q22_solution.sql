CREATE TABLE Bill (
    BillNo NUMBER PRIMARY KEY,
    StoreName VARCHAR2(20),
    Shopperid NUMBER,
    ArCode CHAR(5),
    Amount NUMBER,
    BillDate DATE,
    Quantity NUMBER(4) DEFAULT 1 CHECK (Quantity > 0),
    CONSTRAINT fk_StoreName FOREIGN KEY (StoreName) REFERENCES Store(StoreName),
    CONSTRAINT fk_Shopperid FOREIGN KEY (Shopperid) REFERENCES Shopper(Shopperid),
    CONSTRAINT fk_ArCode FOREIGN KEY (ArCode) REFERENCES Article(ArCode)
);
