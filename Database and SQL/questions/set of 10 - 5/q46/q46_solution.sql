SELECT
    Quotationid,
    Qdate,
    Quotedprice
FROM
    Quotation
WHERE
    Quotedprice > 1400
    AND Quotedprice < 2150;
