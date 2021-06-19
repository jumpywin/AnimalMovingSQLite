create table grade
(
    id              integer
        primary key,
    difficulty      integer default 1 not null,
    userid          integer
        references user
            on update cascade,
    completion_time integer
);

INSERT INTO grade (id, difficulty, userid, completion_time) VALUES (1, 1, 1, 11);
INSERT INTO grade (id, difficulty, userid, completion_time) VALUES (2, 1, 2, 7);
INSERT INTO grade (id, difficulty, userid, completion_time) VALUES (3, 1, 1, 5);


create table sqlite_master
(
    type     text,
    name     text,
    tbl_name text,
    rootpage int,
    sql      text
);

INSERT INTO sqlite_master (type, name, tbl_name, rootpage, sql) VALUES ('table', 'user', 'user', 2, 'CREATE TABLE user
(
    id    integer
        primary key,
    nick   varchar(20) not null
        unique,
    passwd varchar(16)
)');
INSERT INTO sqlite_master (type, name, tbl_name, rootpage, sql) VALUES ('index', 'sqlite_autoindex_user_1', 'user', 3, null);
INSERT INTO sqlite_master (type, name, tbl_name, rootpage, sql) VALUES ('table', 'grade', 'grade', 4, 'CREATE TABLE grade
(
    id             integer
        primary key,
    difficulty      integer default 1 not null,
    userid          integer
        references user
            on update cascade,
    completion_time integer
)');


create table user
(
    id     integer
        primary key,
    nick   varchar(20) not null
        unique,
    passwd varchar(16)
);

INSERT INTO user (id, nick, passwd) VALUES (1, 'cloudli', '0805');
INSERT INTO user (id, nick, passwd) VALUES (2, 'jumpy', '0805');