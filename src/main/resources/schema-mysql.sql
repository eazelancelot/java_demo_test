CREATE TABLE IF NOT EXISTS `register` (
  `account` varchar(20) NOT NULL,
  `pwd` varchar(45) NOT NULL,
  `reg_time` datetime DEFAULT NULL,
  `active` tinyint NOT NULL DEFAULT 0,
  PRIMARY KEY (`account`)
);

