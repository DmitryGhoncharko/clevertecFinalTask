delete  from comments;
delete from news;
insert into news(id,text,time,title) values
                                      (1,'news1',CURRENT_TIMESTAMP,'title1'),
                                      (2,'news2',CURRENT_TIMESTAMP,'title2'),
                                      (3,'news3',CURRENT_TIMESTAMP,'title3'),
                                      (4,'news4',CURRENT_TIMESTAMP,'title4'),
                                      (5,'news5',CURRENT_TIMESTAMP,'title5'),
                                      (6,'news6',CURRENT_TIMESTAMP,'title6'),
                                      (7,'news7',CURRENT_TIMESTAMP,'title7'),
                                      (8,'news8',CURRENT_TIMESTAMP,'title8'),
                                      (9,'news9',CURRENT_TIMESTAMP,'title9'),
                                      (10,'news10',CURRENT_TIMESTAMP,'title10'),
                                      (11,'news11',CURRENT_TIMESTAMP,'title11'),
                                      (12,'news12',CURRENT_TIMESTAMP,'title12'),
                                      (13,'news13',CURRENT_TIMESTAMP,'title13'),
                                      (14,'news14',CURRENT_TIMESTAMP,'title14'),
                                      (15,'news15',CURRENT_TIMESTAMP,'title15'),
                                      (16,'news16',CURRENT_TIMESTAMP,'title16'),
                                      (17,'news17',CURRENT_TIMESTAMP,'title17'),
                                      (18,'news18',CURRENT_TIMESTAMP,'title18'),
                                      (19,'news19',CURRENT_TIMESTAMP,'title19'),
                                      (20,'news20',CURRENT_TIMESTAMP,'title20');

insert into comments(text,time,username,news_id) values
                                                     ('comm1',CURRENT_TIMESTAMP,'user1',1),
                                                     ('comm2',CURRENT_TIMESTAMP,'user2',1),
                                                     ('comm3',CURRENT_TIMESTAMP,'user3',1),
                                                     ('comm4',CURRENT_TIMESTAMP,'user4',1),
                                                     ('comm5',CURRENT_TIMESTAMP,'user5',1),
                                                     ('comm6',CURRENT_TIMESTAMP,'user6',1),
                                                     ('comm7',CURRENT_TIMESTAMP,'user7',1),
                                                     ('comm8',CURRENT_TIMESTAMP,'user8',1),
                                                     ('comm9',CURRENT_TIMESTAMP,'user9',1),
                                                     ('comm10',CURRENT_TIMESTAMP,'user10',1),
                                                     ('comm1',CURRENT_TIMESTAMP,'user1',2),
                                                     ('comm2',CURRENT_TIMESTAMP,'user2',2),
                                                     ('comm3',CURRENT_TIMESTAMP,'user3',2),
                                                     ('comm4',CURRENT_TIMESTAMP,'user4',2),
                                                     ('comm5',CURRENT_TIMESTAMP,'user5',2),
                                                     ('comm6',CURRENT_TIMESTAMP,'user6',2),
                                                     ('comm7',CURRENT_TIMESTAMP,'user7',2),
                                                     ('comm8',CURRENT_TIMESTAMP,'user8',2),
                                                     ('comm9',CURRENT_TIMESTAMP,'user9',2),
                                                     ('comm10',CURRENT_TIMESTAMP,'user10',2),
                                                     ('comm1',CURRENT_TIMESTAMP,'user1',3),
                                                     ('comm2',CURRENT_TIMESTAMP,'user2',3),
                                                     ('comm3',CURRENT_TIMESTAMP,'user3',3),
                                                     ('comm4',CURRENT_TIMESTAMP,'user4',3),
                                                     ('comm5',CURRENT_TIMESTAMP,'user5',3),
                                                     ('comm6',CURRENT_TIMESTAMP,'user6',3),
                                                     ('comm7',CURRENT_TIMESTAMP,'user7',3),
                                                     ('comm8',CURRENT_TIMESTAMP,'user8',3),
                                                     ('comm9',CURRENT_TIMESTAMP,'user9',3),
                                                     ('comm10',CURRENT_TIMESTAMP,'user10',3),
                                                     ('comm1',CURRENT_TIMESTAMP,'user1',4),
                                                     ('comm2',CURRENT_TIMESTAMP,'user2',4),
                                                     ('comm3',CURRENT_TIMESTAMP,'user3',4),
                                                     ('comm4',CURRENT_TIMESTAMP,'user4',4),
                                                     ('comm5',CURRENT_TIMESTAMP,'user5',4),
                                                     ('comm6',CURRENT_TIMESTAMP,'user6',4),
                                                     ('comm7',CURRENT_TIMESTAMP,'user7',4),
                                                     ('comm8',CURRENT_TIMESTAMP,'user8',4),
                                                     ('comm9',CURRENT_TIMESTAMP,'user9',4),
                                                     ('comm10',CURRENT_TIMESTAMP,'user10',4),
                                                     ('comm1',CURRENT_TIMESTAMP,'user1',5),
                                                     ('comm2',CURRENT_TIMESTAMP,'user2',5),
                                                     ('comm3',CURRENT_TIMESTAMP,'user3',5),
                                                     ('comm4',CURRENT_TIMESTAMP,'user4',5),
                                                     ('comm5',CURRENT_TIMESTAMP,'user5',5),
                                                     ('comm6',CURRENT_TIMESTAMP,'user6',5),
                                                     ('comm7',CURRENT_TIMESTAMP,'user7',5),
                                                     ('comm8',CURRENT_TIMESTAMP,'user8',5),
                                                     ('comm9',CURRENT_TIMESTAMP,'user9',5),
                                                     ('comm10',CURRENT_TIMESTAMP,'user10',5),
                                                     ('comm1',CURRENT_TIMESTAMP,'user1',6),
                                                     ('comm2',CURRENT_TIMESTAMP,'user2',6),
                                                     ('comm3',CURRENT_TIMESTAMP,'user3',6),
                                                     ('comm4',CURRENT_TIMESTAMP,'user4',6),
                                                     ('comm5',CURRENT_TIMESTAMP,'user5',6),
                                                     ('comm6',CURRENT_TIMESTAMP,'user6',6),
                                                     ('comm7',CURRENT_TIMESTAMP,'user7',6),
                                                     ('comm8',CURRENT_TIMESTAMP,'user8',6),
                                                     ('comm9',CURRENT_TIMESTAMP,'user9',6),
                                                     ('comm10',CURRENT_TIMESTAMP,'user10',6),
                                                     ('comm1',CURRENT_TIMESTAMP,'user1',7),
                                                     ('comm2',CURRENT_TIMESTAMP,'user2',7),
                                                     ('comm3',CURRENT_TIMESTAMP,'user3',7),
                                                     ('comm4',CURRENT_TIMESTAMP,'user4',7),
                                                     ('comm5',CURRENT_TIMESTAMP,'user5',7),
                                                     ('comm6',CURRENT_TIMESTAMP,'user6',7),
                                                     ('comm7',CURRENT_TIMESTAMP,'user7',7),
                                                     ('comm8',CURRENT_TIMESTAMP,'user8',7),
                                                     ('comm9',CURRENT_TIMESTAMP,'user9',7),
                                                     ('comm10',CURRENT_TIMESTAMP,'user10',7),
                                                     ('comm1',CURRENT_TIMESTAMP,'user1',8),
                                                     ('comm2',CURRENT_TIMESTAMP,'user2',8),
                                                     ('comm3',CURRENT_TIMESTAMP,'user3',8),
                                                     ('comm4',CURRENT_TIMESTAMP,'user4',8),
                                                     ('comm5',CURRENT_TIMESTAMP,'user5',8),
                                                     ('comm6',CURRENT_TIMESTAMP,'user6',8),
                                                     ('comm7',CURRENT_TIMESTAMP,'user7',8),
                                                     ('comm8',CURRENT_TIMESTAMP,'user8',8),
                                                     ('comm9',CURRENT_TIMESTAMP,'user9',8),
                                                     ('comm10',CURRENT_TIMESTAMP,'user10',8),
                                                     ('comm1',CURRENT_TIMESTAMP,'user1',9),
                                                     ('comm2',CURRENT_TIMESTAMP,'user2',9),
                                                     ('comm3',CURRENT_TIMESTAMP,'user3',9),
                                                     ('comm4',CURRENT_TIMESTAMP,'user4',9),
                                                     ('comm5',CURRENT_TIMESTAMP,'user5',9),
                                                     ('comm6',CURRENT_TIMESTAMP,'user6',9),
                                                     ('comm7',CURRENT_TIMESTAMP,'user7',9),
                                                     ('comm8',CURRENT_TIMESTAMP,'user8',9),
                                                     ('comm9',CURRENT_TIMESTAMP,'user9',9),
                                                     ('comm10',CURRENT_TIMESTAMP,'user10',9),
                                                     ('comm1',CURRENT_TIMESTAMP,'user1',10),
                                                     ('comm2',CURRENT_TIMESTAMP,'user2',10),
                                                     ('comm3',CURRENT_TIMESTAMP,'user3',10),
                                                     ('comm4',CURRENT_TIMESTAMP,'user4',10),
                                                     ('comm5',CURRENT_TIMESTAMP,'user5',10),
                                                     ('comm6',CURRENT_TIMESTAMP,'user6',10),
                                                     ('comm7',CURRENT_TIMESTAMP,'user7',10),
                                                     ('comm8',CURRENT_TIMESTAMP,'user8',10),
                                                     ('comm9',CURRENT_TIMESTAMP,'user9',10),
                                                     ('comm10',CURRENT_TIMESTAMP,'user10',10),
                                                     ('comm1',CURRENT_TIMESTAMP,'user1',11),
                                                     ('comm2',CURRENT_TIMESTAMP,'user2',11),
                                                     ('comm3',CURRENT_TIMESTAMP,'user3',11),
                                                     ('comm4',CURRENT_TIMESTAMP,'user4',11),
                                                     ('comm5',CURRENT_TIMESTAMP,'user5',11),
                                                     ('comm6',CURRENT_TIMESTAMP,'user6',11),
                                                     ('comm7',CURRENT_TIMESTAMP,'user7',11),
                                                     ('comm8',CURRENT_TIMESTAMP,'user8',11),
                                                     ('comm9',CURRENT_TIMESTAMP,'user9',11),
                                                     ('comm10',CURRENT_TIMESTAMP,'user10',11),
                                                     ('comm1',CURRENT_TIMESTAMP,'user1',12),
                                                     ('comm2',CURRENT_TIMESTAMP,'user2',12),
                                                     ('comm3',CURRENT_TIMESTAMP,'user3',12),
                                                     ('comm4',CURRENT_TIMESTAMP,'user4',12),
                                                     ('comm5',CURRENT_TIMESTAMP,'user5',12),
                                                     ('comm6',CURRENT_TIMESTAMP,'user6',12),
                                                     ('comm7',CURRENT_TIMESTAMP,'user7',12),
                                                     ('comm8',CURRENT_TIMESTAMP,'user8',12),
                                                     ('comm9',CURRENT_TIMESTAMP,'user9',12),
                                                     ('comm10',CURRENT_TIMESTAMP,'user10',12),
                                                     ('comm1',CURRENT_TIMESTAMP,'user1',13),
                                                     ('comm2',CURRENT_TIMESTAMP,'user2',13),
                                                     ('comm3',CURRENT_TIMESTAMP,'user3',13),
                                                     ('comm4',CURRENT_TIMESTAMP,'user4',13),
                                                     ('comm5',CURRENT_TIMESTAMP,'user5',13),
                                                     ('comm6',CURRENT_TIMESTAMP,'user6',13),
                                                     ('comm7',CURRENT_TIMESTAMP,'user7',13),
                                                     ('comm8',CURRENT_TIMESTAMP,'user8',13),
                                                     ('comm9',CURRENT_TIMESTAMP,'user9',13),
                                                     ('comm10',CURRENT_TIMESTAMP,'user10',13),
                                                     ('comm1',CURRENT_TIMESTAMP,'user1',14),
                                                     ('comm2',CURRENT_TIMESTAMP,'user2',14),
                                                     ('comm3',CURRENT_TIMESTAMP,'user3',14),
                                                     ('comm4',CURRENT_TIMESTAMP,'user4',14),
                                                     ('comm5',CURRENT_TIMESTAMP,'user5',14),
                                                     ('comm6',CURRENT_TIMESTAMP,'user6',14),
                                                     ('comm7',CURRENT_TIMESTAMP,'user7',14),
                                                     ('comm8',CURRENT_TIMESTAMP,'user8',14),
                                                     ('comm9',CURRENT_TIMESTAMP,'user9',14),
                                                     ('comm10',CURRENT_TIMESTAMP,'user10',14),
                                                     ('comm1',CURRENT_TIMESTAMP,'user1',15),
                                                     ('comm2',CURRENT_TIMESTAMP,'user2',15),
                                                     ('comm3',CURRENT_TIMESTAMP,'user3',15),
                                                     ('comm4',CURRENT_TIMESTAMP,'user4',15),
                                                     ('comm5',CURRENT_TIMESTAMP,'user5',15),
                                                     ('comm6',CURRENT_TIMESTAMP,'user6',15),
                                                     ('comm7',CURRENT_TIMESTAMP,'user7',15),
                                                     ('comm8',CURRENT_TIMESTAMP,'user8',15),
                                                     ('comm9',CURRENT_TIMESTAMP,'user9',15),
                                                     ('comm10',CURRENT_TIMESTAMP,'user10',15),
                                                     ('comm1',CURRENT_TIMESTAMP,'user1',16),
                                                     ('comm2',CURRENT_TIMESTAMP,'user2',16),
                                                     ('comm3',CURRENT_TIMESTAMP,'user3',16),
                                                     ('comm4',CURRENT_TIMESTAMP,'user4',16),
                                                     ('comm5',CURRENT_TIMESTAMP,'user5',16),
                                                     ('comm6',CURRENT_TIMESTAMP,'user6',16),
                                                     ('comm7',CURRENT_TIMESTAMP,'user7',16),
                                                     ('comm8',CURRENT_TIMESTAMP,'user8',16),
                                                     ('comm9',CURRENT_TIMESTAMP,'user9',16),
                                                     ('comm10',CURRENT_TIMESTAMP,'user10',16),
                                                     ('comm1',CURRENT_TIMESTAMP,'user1',17),
                                                     ('comm2',CURRENT_TIMESTAMP,'user2',17),
                                                     ('comm3',CURRENT_TIMESTAMP,'user3',17),
                                                     ('comm4',CURRENT_TIMESTAMP,'user4',17),
                                                     ('comm5',CURRENT_TIMESTAMP,'user5',17),
                                                     ('comm6',CURRENT_TIMESTAMP,'user6',17),
                                                     ('comm7',CURRENT_TIMESTAMP,'user7',17),
                                                     ('comm8',CURRENT_TIMESTAMP,'user8',17),
                                                     ('comm9',CURRENT_TIMESTAMP,'user9',17),
                                                     ('comm10',CURRENT_TIMESTAMP,'user10',17),
                                                     ('comm1',CURRENT_TIMESTAMP,'user1',18),
                                                     ('comm2',CURRENT_TIMESTAMP,'user2',18),
                                                     ('comm3',CURRENT_TIMESTAMP,'user3',18),
                                                     ('comm4',CURRENT_TIMESTAMP,'user4',18),
                                                     ('comm5',CURRENT_TIMESTAMP,'user5',18),
                                                     ('comm6',CURRENT_TIMESTAMP,'user6',18),
                                                     ('comm7',CURRENT_TIMESTAMP,'user7',18),
                                                     ('comm8',CURRENT_TIMESTAMP,'user8',18),
                                                     ('comm9',CURRENT_TIMESTAMP,'user9',18),
                                                     ('comm10',CURRENT_TIMESTAMP,'user10',18),
                                                     ('comm1',CURRENT_TIMESTAMP,'user1',19),
                                                     ('comm2',CURRENT_TIMESTAMP,'user2',19),
                                                     ('comm3',CURRENT_TIMESTAMP,'user3',19),
                                                     ('comm4',CURRENT_TIMESTAMP,'user4',19),
                                                     ('comm5',CURRENT_TIMESTAMP,'user5',19),
                                                     ('comm6',CURRENT_TIMESTAMP,'user6',19),
                                                     ('comm7',CURRENT_TIMESTAMP,'user7',19),
                                                     ('comm8',CURRENT_TIMESTAMP,'user8',19),
                                                     ('comm9',CURRENT_TIMESTAMP,'user9',19),
                                                     ('comm10',CURRENT_TIMESTAMP,'user10',19),
                                                     ('comm1',CURRENT_TIMESTAMP,'user1',20),
                                                     ('comm2',CURRENT_TIMESTAMP,'user2',20),
                                                     ('comm3',CURRENT_TIMESTAMP,'user3',20),
                                                     ('comm4',CURRENT_TIMESTAMP,'user4',20),
                                                     ('comm5',CURRENT_TIMESTAMP,'user5',20),
                                                     ('comm6',CURRENT_TIMESTAMP,'user6',20),
                                                     ('comm7',CURRENT_TIMESTAMP,'user7',20),
                                                     ('comm8',CURRENT_TIMESTAMP,'user8',20),
                                                     ('comm9',CURRENT_TIMESTAMP,'user9',20),
                                                     ('comm10',CURRENT_TIMESTAMP,'user10',20);