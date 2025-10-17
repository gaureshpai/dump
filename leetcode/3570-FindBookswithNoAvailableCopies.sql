/*
Question:
Table: library_books
+------------------+---------+
| Column Name      | Type    |
+------------------+---------+
| book_id          | int     |
| title            | varchar |
| author           | varchar |
| genre            | varchar |
| publication_year | int     |
| total_copies     | int     |
+------------------+---------+
book_id is the unique identifier for this table.
Each row contains information about a book in the library, including the total number of copies owned by the library.
Table: borrowing_records
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| record_id     | int     |
| book_id       | int     |
| borrower_name | varchar |
| borrow_date   | date    |
| return_date   | date    |
+---------------+---------+
record_id is the unique identifier for this table.
Each row represents a borrowing transaction and return_date is NULL if the book is currently borrowed and hasn't been returned yet.
Write a solution to find all books that are currently borrowed (not returned) and have zero copies available in the library.
A book is considered currently borrowed if there exists a borrowing record with a NULL return_date
Return the result table ordered by current borrowers in descending order, then by book title in ascending order.
The result format is in the following example.
*/

WITH curr_borrowers AS (
  SELECT book_id, COUNT(*) AS current_borrowers
  FROM borrowing_records
  WHERE return_date IS NULL
  GROUP BY book_id
)
SELECT
  lb.book_id,
  lb.title,
  lb.author,
  lb.genre,
  lb.publication_year,
  cb.current_borrowers
FROM library_books lb
JOIN curr_borrowers cb ON lb.book_id = cb.book_id
WHERE cb.current_borrowers = lb.total_copies
ORDER BY cb.current_borrowers DESC, lb.title ASC;