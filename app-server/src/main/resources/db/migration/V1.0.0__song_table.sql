CREATE TABLE IF NOT EXISTS `song` (

    `id` varchar(32) PRIMARY KEY,
    `interpret` varchar(64),
    `title` varchar(64)

) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

INSERT INTO song(id, interpret, title) VALUES('1', 'sandy', 'I can do')
