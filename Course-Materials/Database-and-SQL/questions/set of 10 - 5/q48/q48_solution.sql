SELECT
    Designation,
    Salary
FROM
    Empdetails
WHERE
    Designation IN ('Manager', 'Billing Staff')
    AND Salary BETWEEN 2500 AND 5000;
