INSERT INTO author (id, name) VALUES (-1, 'Алексей');
INSERT INTO author (id, name) VALUES (-2, 'Игорь');
INSERT INTO author (id, name) VALUES (-3, 'Александр');

INSERT INTO book (id, title) VALUES (-1, 'книга Алексея');
INSERT INTO book (id, title) VALUES (-2, 'книга Алексея и Игоря');
INSERT INTO book (id, title) VALUES (-3, 'книга Игоря ');
INSERT INTO book (id, title) VALUES (-4, 'книга Алесандра ');

INSERT INTO author_books (author_id, books_id) VALUES (-1, -1);
INSERT INTO author_books (author_id, books_id) VALUES (-1, -2);
INSERT INTO author_books (author_id, books_id) VALUES (-2, -2);
INSERT INTO author_books (author_id, books_id) VALUES (-2, -3);
INSERT INTO author_books (author_id, books_id) VALUES (-3, -4);
