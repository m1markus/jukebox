CREATE TABLE IF NOT EXISTS song (
    id VARCHAR(32) PRIMARY KEY,
    interpret VARCHAR(64),
    title VARCHAR(64)

) DEFAULT CHARSET=UTF8;

INSERT INTO song(id, interpret, title) VALUES('1', 'sandy', 'I can do')
