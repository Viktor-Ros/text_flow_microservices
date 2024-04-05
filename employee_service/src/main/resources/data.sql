CREATE TABLE employee (
                        id VARCHAR(15) NOT NULL UNIQUE,
                        creation_date datetime NOT NULL,
                        modify_date datetime NOT NULL,
                        name VARCHAR(100) NOT NULL,
                        PRIMARY KEY (id)
);

CREATE TABLE story (
                        id VARCHAR(15) NOT NULL UNIQUE,
                        creation_date datetime NOT NULL,
                        modify_date datetime NOT NULL,
                        author_id VARCHAR(15) NOT NULL,
                        text_value VARCHAR(500) NOT NULL,
                        PRIMARY KEY (id),
                        FOREIGN KEY (author_id) REFERENCES employee(id) ON DELETE CASCADE
);

CREATE TABLE subscription (
                        id VARCHAR(15) NOT NULL UNIQUE,
                        creation_date datetime NOT NULL,
                        modify_date datetime NOT NULL,
                        writer_id VARCHAR(15) NOT NULL,
                        subscriber_id VARCHAR(15) NOT NULL,
                        PRIMARY KEY (id),
                        FOREIGN KEY (writer_id) REFERENCES employee(id) ON DELETE CASCADE,
                        FOREIGN KEY (subscriber_id) REFERENCES employee(id) ON DELETE CASCADE
);


INSERT INTO employee (id, creation_date, modify_date, name) VALUE ('emp000000000001', now(), now(), 'testUser1');
INSERT INTO employee (id, creation_date, modify_date, name) VALUE ('emp000000000002', now(), now(), 'testUser2');
INSERT INTO employee (id, creation_date, modify_date, name) VALUE ('emp000000000003', now(), now(), 'testUser3');

INSERT INTO story (id, creation_date, modify_date, text_value, author_id) VALUE ('str000000000001', now(), now(), 'testStory1', 'emp000000000001');
INSERT INTO story (id, creation_date, modify_date, text_value, author_id) VALUE ('str000000000002', now(), now(), 'testStory2', 'emp000000000001');
INSERT INTO story (id, creation_date, modify_date, text_value, author_id) VALUE ('str000000000003', now(), now(), 'testStory3', 'emp000000000002');

INSERT INTO subscription (id, creation_date, modify_date, writer_id, subscriber_id) VALUE ('sub000000000001', now(), now(),'emp000000000001', 'emp000000000002');
INSERT INTO subscription (id, creation_date, modify_date, writer_id, subscriber_id) VALUE ('sub000000000002', now(), now(),'emp000000000001', 'emp000000000003');
INSERT INTO subscription (id, creation_date, modify_date, writer_id, subscriber_id) VALUE ('sub000000000003', now(), now(),'emp000000000002', 'emp000000000003');
