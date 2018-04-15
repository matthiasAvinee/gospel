CREATE TABLE `album` (
  `album_id` int(10) NOT NULL AUTO_INCREMENT,
  `nom_album` varchar(50) NOT NULL,
  PRIMARY KEY (`album_id`)
)

ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `photo` (
  `photo_id` int(10) NOT NULL AUTO_INCREMENT,
  `chemin` varchar(100) NOT NULL,
  album_id_fk INT (10),
  PRIMARY KEY (`photo_id`),
  CONSTRAINT FOREIGN KEY (album_id_fk) REFERENCES album (album_id) ON DELETE NO ACTION ON UPDATE NO ACTION
)

ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `pdf` (
  `pdf_id` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`pdf_id`),
)

ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `video` (
  `video_id` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`pdf_id`),
)

ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `enregis` (
  `enregis_id` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`pdf_id`),
)

ENGINE=InnoDB DEFAULT CHARSET=utf8;



