#Dodanie funkcji
insert into functions (function_name) value ('uczeń');
insert into functions (function_name) value ('nauczyciel');
insert into functions (function_name) value ('administrator');

# Dodanie uczniów do bazy danych
insert into users (email, first_name, last_name, password, function_id) values ('student1@students.pl', 'sName1', 'sSurname1', 'haslo', 1);
insert into users (email, first_name, last_name, password, function_id) values ('student2@students.pl', 'sName2', 'sSurname2', 'haslo', 1);
insert into users (email, first_name, last_name, password, function_id) values ('student3@students.pl', 'sName3', 'sSurname3', 'haslo', 1);
insert into users (email, first_name, last_name, password, function_id) values ('student4@students.pl', 'sName4', 'sSurname4', 'haslo', 1);
insert into users (email, first_name, last_name, password, function_id) values ('student5@students.pl', 'sName5', 'sSurname5', 'haslo', 1);
insert into users (email, first_name, last_name, password, function_id) values ('student6@students.pl', 'sName6', 'sSurname6', 'haslo', 1);
insert into users (email, first_name, last_name, password, function_id) values ('student7@students.pl', 'sName7', 'sSurname7', 'haslo', 1);
insert into users (email, first_name, last_name, password, function_id) values ('student8@students.pl', 'sName8', 'sSurname8', 'haslo', 1);
insert into users (email, first_name, last_name, password, function_id) values ('student9@students.pl', 'sName9', 'sSurname9', 'haslo', 1);
insert into users (email, first_name, last_name, password, function_id) values ('student10@students.pl', 'sName10', 'sSurname10', 'haslo', 1);

#Dodanie nauczycieli
insert into users(email, first_name, last_name, password, function_id) VALUES ('teacher1@teachers.pl', 'tName1','tSurname1', 'haslo', 2);
insert into users(email, first_name, last_name, password, function_id) VALUES ('teacher2@teachers.pl', 'tName2','tSurname2', 'haslo', 2);
insert into users(email, first_name, last_name, password, function_id) VALUES ('teacher3@teachers.pl', 'tName3','tSurname3', 'haslo', 2);
insert into users(email, first_name, last_name, password, function_id) VALUES ('teacher4@teachers.pl', 'tName4','tSurname4', 'haslo', 2);
insert into users(email, first_name, last_name, password, function_id) VALUES ('teacher5@teachers.pl', 'tName5','tSurname5', 'haslo', 2);
insert into users(email, first_name, last_name, password, function_id) VALUES ('teacher6admin@teachers.pl', 'tName6','tSurname6Admin', 'haslo', 3);

#Stworzenie klas
insert into classes (class_name, supervising_teacher_id) VALUES ('IA', 11);
insert into classes (class_name, supervising_teacher_id) VALUES ('IB', 12);

#Stworzenie przedmiotów
insert into subjects (subject_name) value ('Język polski');
insert into subjects (subject_name) value ('Język angielski');
insert into subjects (subject_name) value ('Matematyka');
insert into subjects (subject_name) value ('Fizyka');
insert into subjects (subject_name) value ('Geografia');
insert into subjects (subject_name) value ('Chemia');
insert into subjects (subject_name) value ('Biologia');
insert into subjects (subject_name) value ('Wychowanie fizyczne');
insert into subjects (subject_name) value ('Historia');

#Przypisanie nauczycieli do przedmiotów
insert into subject_teachers (subject_id, teacher_id) VALUES (1,11);
insert into subject_teachers (subject_id, teacher_id) VALUES (1,12);
insert into subject_teachers (subject_id, teacher_id) VALUES (2,12);
insert into subject_teachers (subject_id, teacher_id) VALUES (3,13);
insert into subject_teachers (subject_id, teacher_id) VALUES (4,13);
insert into subject_teachers (subject_id, teacher_id) VALUES (5,14);
insert into subject_teachers (subject_id, teacher_id) VALUES (6,15);
insert into subject_teachers (subject_id, teacher_id) VALUES (7,15);
insert into subject_teachers (subject_id, teacher_id) VALUES (8,16);
insert into subject_teachers (subject_id, teacher_id) VALUES (9,16);

#Przypisanie uczniów do klas
insert into users_classes (user_id, class_id) VALUES (1,1);
insert into users_classes (user_id, class_id) VALUES (2,1);
insert into users_classes (user_id, class_id) VALUES (3,1);
insert into users_classes (user_id, class_id) VALUES (4,1);
insert into users_classes (user_id, class_id) VALUES (4,1);
insert into users_classes (user_id, class_id) VALUES (5,1);
insert into users_classes (user_id, class_id) VALUES (6,2);
insert into users_classes (user_id, class_id) VALUES (7,2);
insert into users_classes (user_id, class_id) VALUES (8,2);
insert into users_classes (user_id, class_id) VALUES (9,2);
insert into users_classes (user_id, class_id) VALUES (10,2);

#Przypisanie nauczycieli do klas
insert into users_classes (user_id, class_id) VALUES (11,1);
insert into users_classes (user_id, class_id) VALUES (12,1);
insert into users_classes (user_id, class_id) VALUES (13,1);
insert into users_classes (user_id, class_id) VALUES (14,1);
insert into users_classes (user_id, class_id) VALUES (15,1);
insert into users_classes (user_id, class_id) VALUES (16,1);
insert into users_classes (user_id, class_id) VALUES (11,2);
insert into users_classes (user_id, class_id) VALUES (12,2);
insert into users_classes (user_id, class_id) VALUES (13,2);
insert into users_classes (user_id, class_id) VALUES (14,2);
insert into users_classes (user_id, class_id) VALUES (15,2);
insert into users_classes (user_id, class_id) VALUES (16,2);

#Wpisanie ocen
insert into grades (date_time, grade_description, grade_value, student_id, subject_id, teacher_id) VALUES (CURDATE(), 'Dyktando', 5, 1,1,11);
insert into grades (date_time, grade_description, grade_value, student_id, subject_id, teacher_id) VALUES (CURDATE(), 'Dyktando', 4, 2,1,11);
insert into grades (date_time, grade_description, grade_value, student_id, subject_id, teacher_id) VALUES (CURDATE(), 'Dyktando', 5, 3,1,11);
insert into grades (date_time, grade_description, grade_value, student_id, subject_id, teacher_id) VALUES (CURDATE(), 'Dyktando', 3, 4,1,11);
insert into grades (date_time, grade_description, grade_value, student_id, subject_id, teacher_id) VALUES (CURDATE(), 'Dyktando', 5, 5,1,11);
insert into grades (date_time, grade_description, grade_value, student_id, subject_id, teacher_id) VALUES (CURDATE(), 'Sprawdzian', 3, 1,1,11);
insert into grades (date_time, grade_description, grade_value, student_id, subject_id, teacher_id) VALUES (CURDATE(), 'Praca domowa', 4, 1,1,11);