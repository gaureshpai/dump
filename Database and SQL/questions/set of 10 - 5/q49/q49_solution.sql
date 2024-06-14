CREATE TABLE Shopper (
    Shopperid NUMBER(4) PRIMARY KEY,
    ShopperName VARCHAR2(20) NOT NULL,
    Gender CHAR(6) CHECK (Gender IN ('Male', 'Female')),
    MobileNo VARCHAR2(10) NOT NULL CHECK (REGEXP_LIKE(MobileNo, '^[0-9]{10}$')),
    Address VARCHAR2(50)
);
