SELECT
    Empid,
    Salary AS "Current Salary",
    Salary * 1.10 AS "New Salary",
    Salary * 0.10 AS "Incremented Amount"
FROM
    Empdetails;
