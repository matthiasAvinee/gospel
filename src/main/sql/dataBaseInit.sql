
CREATE TABLE `membre` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pseudo` varchar(30) DEFAULT NULL,
  `mdp` VARCHAR (200)DEFAULT NULL,
  `role` VARCHAR(20) DEFAULT NULL,

  PRIMARY KEY (`id`)

)

ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `paragraphe` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titre` varchar(50) DEFAULT NULL,
  `texte` VARCHAR (5000)DEFAULT NULL,
  `page` VARCHAR (30)DEFAULT NULL,
  `ordre` int (30)DEFAULT NULL,

  PRIMARY KEY (`id`)

)

ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `evenement` (
  `id` int NOT NULL AUTO_INCREMENT,
  `prix` DOUBLE DEFAULT NULL,
  `nom` VARCHAR (500)DEFAULT NULL,
  `description` VARCHAR (5000)DEFAULT NULL,
  `adresse` VARCHAR (500)DEFAULT NULL,
  `date` VARCHAR (15)DEFAULT NULL ,
  PRIMARY KEY (`id`)
)

ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `membre` (`id`,`pseudo`,`mdp`,`role`) VALUES(1,'root','$argon2i$v=19$m=65536,t=2,p=1$4BBf74e80ETLZiQT/WvCxw$bsZ1wJcaUY1OalxuMgvwXSKyf0LT+HhFp4M516lzlTQ','administrateur');
INSERT INTO `membre` (`id`,`pseudo`,`mdp`,`role`) VALUES(2,'quentin','$argon2i$v=19$m=65536,t=2,p=1$4BBf74e80ETLZiQT/WvCxw$bsZ1wJcaUY1OalxuMgvwXSKyf0LT+HhFp4M516lzlTQ','administrateur');
INSERT INTO `membre` (`id`,`pseudo`,`mdp`,`role`) VALUES(3,'matthias','$argon2i$v=19$m=65536,t=2,p=1$slv6QY6wp3yJytsnXb3TXg$6MK0sBb8jrp5BD2QUP/SbacltM5YwVHOYJ4ycFb74Z0','membre');