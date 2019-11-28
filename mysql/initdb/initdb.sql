CREATE DATABASE TestApp;

CREATE TABLE TestApp.Repositories (id int NOT NULL AUTO_INCREMENT, name varchar(256), stars int(10) NOT NULL, PRIMARY KEY (id));

CREATE TABLE TestApp.Template (title varchar(256) NOT NULL, subtitle varchar(256) NOT NULL, isActive bit(1) NOT NULL);

INSERT INTO TestApp.Repositories (name, stars)
VALUES ('Front End SPA', 3),
       ('Web API', 5),
       ('Orm', 1),
       ('Business Tier', 2),
       ('Native Android App', 3),
       ('Native IOS App', 1);

INSERT INTO TestApp.Template
VALUES ('Test Repository Viewer', 'This is where the magic happens', b'1'),
       ('Lorem Ipsum', 'Lorem Subtitle', b'0');