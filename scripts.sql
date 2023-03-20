SELECT *
FROM student;

ALTER TABLE avatar DROP COLUMN image;
ALTER TABLE avatar ADD COLUMN image bytea;

SELECT *
FROM student
WHERE age > 12
  and age < 20;

SELECT student.name
from student;

SELECT *
FROM student
where name like '%i%';

SELECT *
FROM student
where age < student.id;

SELECT * from faculty, student
where student.id = faculty.id

ALTER TABLE avatar DROP COLUMN image;
ALTER TABLE avatar ADD COLUMN image bytea;