CREATE TABLE `utilisateur` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pseudo` varchar(30) DEFAULT NULL,
  `motdepasse` VARCHAR (200)DEFAULT NULL,
  `role` VARCHAR(20) DEFAULT NULL,

  PRIMARY KEY (`id`)

)


INSERT INTO `utilisateur` (`pseudo`,`motdepasse`,`role`) VALUES('root','root','administrateur');