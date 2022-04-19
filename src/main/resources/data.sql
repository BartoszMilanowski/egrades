#Dodanie funkcji
insert into egrades.role (name) value ('ROLE_STUDENT');
insert into egrades.role (name) value ('ROLE_TEACHER');
insert into egrades.role (name) value ('ROLE_ADMIN');

# Dodanie uczniów do bazy danych
insert into egrades.users (email, first_name, last_name, password, enabled) values ('student1@students.pl', 'sName1', 'sSurname1', '$2a$12$aQ95vY7DFoQif0/h8dQX4.NPiWBDswcCw5cm0pNOp31gLanzinwaq', 1);
insert into egrades.users (email, first_name, last_name, password, enabled) values ('student2@students.pl', 'sName2', 'sSurname2', '$2a$12$aQ95vY7DFoQif0/h8dQX4.NPiWBDswcCw5cm0pNOp31gLanzinwaq', 1);
insert into egrades.users (email, first_name, last_name, password, enabled) values ('student3@students.pl', 'sName3', 'sSurname3', '$2a$12$aQ95vY7DFoQif0/h8dQX4.NPiWBDswcCw5cm0pNOp31gLanzinwaq', 1);
insert into egrades.users (email, first_name, last_name, password, enabled) values ('student4@students.pl', 'sName4', 'sSurname4', '$2a$12$aQ95vY7DFoQif0/h8dQX4.NPiWBDswcCw5cm0pNOp31gLanzinwaq', 1);
insert into egrades.users (email, first_name, last_name, password, enabled) values ('student5@students.pl', 'sName5', 'sSurname5', '$2a$12$aQ95vY7DFoQif0/h8dQX4.NPiWBDswcCw5cm0pNOp31gLanzinwaq', 1);
insert into egrades.users (email, first_name, last_name, password, enabled) values ('student6@students.pl', 'sName6', 'sSurname6', '$2a$12$aQ95vY7DFoQif0/h8dQX4.NPiWBDswcCw5cm0pNOp31gLanzinwaq', 1);
insert into egrades.users (email, first_name, last_name, password, enabled) values ('student7@students.pl', 'sName7', 'sSurname7', '$2a$12$aQ95vY7DFoQif0/h8dQX4.NPiWBDswcCw5cm0pNOp31gLanzinwaq', 1);
insert into egrades.users (email, first_name, last_name, password, enabled) values ('student8@students.pl', 'sName8', 'sSurname8', '$2a$12$aQ95vY7DFoQif0/h8dQX4.NPiWBDswcCw5cm0pNOp31gLanzinwaq', 1);
insert into egrades.users (email, first_name, last_name, password, enabled) values ('student9@students.pl', 'sName9', 'sSurname9', '$2a$12$aQ95vY7DFoQif0/h8dQX4.NPiWBDswcCw5cm0pNOp31gLanzinwaq', 1);
insert into egrades.users (email, first_name, last_name, password, enabled) values ('student10@students.pl', 'sName10', 'sSurname10', '$2a$12$aQ95vY7DFoQif0/h8dQX4.NPiWBDswcCw5cm0pNOp31gLanzinwaq', 1);

#Dodanie nauczycieli
insert into egrades.users(email, first_name, last_name, password, enabled) VALUES ('teacher1@teachers.pl', 'tName1','tSurname1', '$2a$12$aQ95vY7DFoQif0/h8dQX4.NPiWBDswcCw5cm0pNOp31gLanzinwaq', 1);
insert into egrades.users(email, first_name, last_name, password, enabled) VALUES ('teacher2@teachers.pl', 'tName2','tSurname2', '$2a$12$aQ95vY7DFoQif0/h8dQX4.NPiWBDswcCw5cm0pNOp31gLanzinwaq', 1);
insert into egrades.users(email, first_name, last_name, password, enabled) VALUES ('teacher3@teachers.pl', 'tName3','tSurname3', '$2a$12$aQ95vY7DFoQif0/h8dQX4.NPiWBDswcCw5cm0pNOp31gLanzinwaq', 1);
insert into egrades.users(email, first_name, last_name, password, enabled) VALUES ('teacher4@teachers.pl', 'tName4','tSurname4', '$2a$12$aQ95vY7DFoQif0/h8dQX4.NPiWBDswcCw5cm0pNOp31gLanzinwaq', 1);
insert into egrades.users(email, first_name, last_name, password, enabled) VALUES ('teacher5@teachers.pl', 'tName5','tSurname5', '$2a$12$aQ95vY7DFoQif0/h8dQX4.NPiWBDswcCw5cm0pNOp31gLanzinwaq', 1);
insert into egrades.users(email, first_name, last_name, password, enabled) VALUES ('teacher6admin@teachers.pl', 'tName6','tSurname6Admin', '$2a$12$aQ95vY7DFoQif0/h8dQX4.NPiWBDswcCw5cm0pNOp31gLanzinwaq', 1);

#Przypisanie ról
insert into egrades.user_role (user_id, role_id) VALUES (1,1);
insert into egrades.user_role (user_id, role_id) VALUES (2,1);
insert into egrades.user_role (user_id, role_id) VALUES (3,1);
insert into egrades.user_role (user_id, role_id) VALUES (4,1);
insert into egrades.user_role (user_id, role_id) VALUES (5,1);
insert into egrades.user_role (user_id, role_id) VALUES (6,1);
insert into egrades.user_role (user_id, role_id) VALUES (7,1);
insert into egrades.user_role (user_id, role_id) VALUES (8,1);
insert into egrades.user_role (user_id, role_id) VALUES (9,1);
insert into egrades.user_role (user_id, role_id) VALUES (10,1);
insert into egrades.user_role (user_id, role_id) VALUES (11,2);
insert into egrades.user_role (user_id, role_id) VALUES (12,2);
insert into egrades.user_role (user_id, role_id) VALUES (13,2);
insert into egrades.user_role (user_id, role_id) VALUES (14,2);
insert into egrades.user_role (user_id, role_id) VALUES (15,2);
insert into egrades.user_role (user_id, role_id) VALUES (16,3);
#Stworzenie klas
insert into egrades.classes (class_name, supervising_teacher_id) VALUES ('IA', 11);
insert into egrades.classes (class_name, supervising_teacher_id) VALUES ('IB', 12);

#Stworzenie przedmiotów
insert into egrades.subjects (subject_name) value ('Język polski');
insert into egrades.subjects (subject_name) value ('Język angielski');
insert into egrades.subjects (subject_name) value ('Matematyka');
insert into egrades.subjects (subject_name) value ('Fizyka');
insert into egrades.subjects (subject_name) value ('Geografia');
insert into egrades.subjects (subject_name) value ('Chemia');
insert into egrades.subjects (subject_name) value ('Biologia');
insert into egrades.subjects (subject_name) value ('Wychowanie fizyczne');
insert into egrades.subjects (subject_name) value ('Historia');

#Przypisanie nauczycieli do przedmiotów
insert into egrades.subject_teachers (subject_id, teacher_id) VALUES (1,11);
insert into egrades.subject_teachers (subject_id, teacher_id) VALUES (1,12);
insert into egrades.subject_teachers (subject_id, teacher_id) VALUES (2,12);
insert into egrades.subject_teachers (subject_id, teacher_id) VALUES (3,13);
insert into egrades.subject_teachers (subject_id, teacher_id) VALUES (4,13);
insert into egrades.subject_teachers (subject_id, teacher_id) VALUES (5,14);
insert into egrades.subject_teachers (subject_id, teacher_id) VALUES (6,15);
insert into egrades.subject_teachers (subject_id, teacher_id) VALUES (7,15);
insert into egrades.subject_teachers (subject_id, teacher_id) VALUES (8,16);
insert into egrades.subject_teachers (subject_id, teacher_id) VALUES (9,16);

#Przypisanie uczniów do klas
insert into egrades.users_classes (user_id, class_id) VALUES (1,1);
insert into egrades.users_classes (user_id, class_id) VALUES (2,1);
insert into egrades.users_classes (user_id, class_id) VALUES (3,1);
insert into egrades.users_classes (user_id, class_id) VALUES (4,1);
insert into egrades.users_classes (user_id, class_id) VALUES (5,1);
insert into egrades.users_classes (user_id, class_id) VALUES (6,2);
insert into egrades.users_classes (user_id, class_id) VALUES (7,2);
insert into egrades.users_classes (user_id, class_id) VALUES (8,2);
insert into egrades.users_classes (user_id, class_id) VALUES (9,2);
insert into egrades.users_classes (user_id, class_id) VALUES (10,2);

#Przypisanie nauczycieli do klas
insert into egrades.users_classes (user_id, class_id) VALUES (11,1);
insert into egrades.users_classes (user_id, class_id) VALUES (12,1);
insert into egrades.users_classes (user_id, class_id) VALUES (13,1);
insert into egrades.users_classes (user_id, class_id) VALUES (14,1);
insert into egrades.users_classes (user_id, class_id) VALUES (15,1);
insert into egrades.users_classes (user_id, class_id) VALUES (16,1);
insert into egrades.users_classes (user_id, class_id) VALUES (11,2);
insert into egrades.users_classes (user_id, class_id) VALUES (12,2);
insert into egrades.users_classes (user_id, class_id) VALUES (13,2);
insert into egrades.users_classes (user_id, class_id) VALUES (14,2);
insert into egrades.users_classes (user_id, class_id) VALUES (15,2);
insert into egrades.users_classes (user_id, class_id) VALUES (16,2);

#Wpisanie ocen
insert into egrades.grades (date_time, grade_description, grade_value, student_id, subject_id, teacher_id) VALUES (CURDATE(), 'Dyktando', 5, 1,1,11);
insert into egrades.grades (date_time, grade_description, grade_value, student_id, subject_id, teacher_id) VALUES (CURDATE(), 'Dyktando', 4, 2,1,11);
insert into egrades.grades (date_time, grade_description, grade_value, student_id, subject_id, teacher_id) VALUES (CURDATE(), 'Dyktando', 5, 3,1,11);
insert into egrades.grades (date_time, grade_description, grade_value, student_id, subject_id, teacher_id) VALUES (CURDATE(), 'Dyktando', 3, 4,1,11);
insert into egrades.grades (date_time, grade_description, grade_value, student_id, subject_id, teacher_id) VALUES (CURDATE(), 'Dyktando', 5, 5,1,11);
insert into egrades.grades (date_time, grade_description, grade_value, student_id, subject_id, teacher_id) VALUES (CURDATE(), 'Sprawdzian', 3, 1,1,11);
insert into egrades.grades (date_time, grade_description, grade_value, student_id, subject_id, teacher_id) VALUES (CURDATE(), 'Praca domowa', 4, 1,1,11);

#Oceny końcowe
insert into egrades.final_grades (grade_value, student_id, subject_id, teacher_id) VALUES (3, 1, 1, 1);