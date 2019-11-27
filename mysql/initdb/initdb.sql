CREATE DATABASE TestApp;

CREATE TABLE TestApp.Repositories (id int(10) not null, name varchar(256), stars int(10) not null);

CREATE TABLE TestApp.Template (title varchar(256) not null, subtitle varchar(256) not null, isActive bit(1) not null);

INSERT INTO TestApp.Repositories
VALUES (0, 'Front End SPA', 3),
       (1, 'Web API', 5),
       (2, 'Orm', 1),
       (3, 'Business Tier', 2),
       (4, 'Native Android App', 3),
       (5, 'Native IOS App', 1);

INSERT INTO TestApp.Template
VALUES ('Test Repository Viewer', 'This is where the magic happens', b'1'),
       ('Lorem Ipsum', 'Lorem Subtitle', b'0');