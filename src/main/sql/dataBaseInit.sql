CREATE TABLE `membre` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pseudo` varchar(30) DEFAULT NULL,
  `mdp` VARCHAR (200)DEFAULT NULL,
  `role` VARCHAR(20) DEFAULT NULL,

  PRIMARY KEY (`id`)

)

CREATE TABLE `paragraphe` (
  `id` VARCHAR (30) NOT NULL,
  `titre` varchar(50) DEFAULT NULL,
  `texte` VARCHAR (1000)DEFAULT NULL,
  `img` blob DEFAULT NULL,

  PRIMARY KEY (`id`)

)

