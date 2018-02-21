CREATE TABLE `album` (
  `album_id` int(10) NOT NULL AUTO_INCREMENT,
  `nom_album` varchar(50) NOT NULL,
  PRIMARY KEY (`album_id`)
);

CREATE TABLE `photo` (
  `photo_id` int(10) NOT NULL AUTO_INCREMENT,
  `chemin` varchar(100) NOT NULL,
  album_id_fk INT (10),
  PRIMARY KEY (`photo_id`),
  CONSTRAINT FOREIGN KEY (album_id_fk) REFERENCES album (album_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);
