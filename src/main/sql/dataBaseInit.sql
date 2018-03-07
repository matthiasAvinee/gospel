CREATE TABLE `membre` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pseudo` varchar(30) DEFAULT NULL,
  `mdp` VARCHAR (200)DEFAULT NULL,
  `role` VARCHAR(20) DEFAULT NULL,

  PRIMARY KEY (`id`)

);

CREATE TABLE `paragraphe` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titre` varchar(50) DEFAULT NULL,
  `texte` VARCHAR (5000)DEFAULT NULL,
  `page` VARCHAR (30)DEFAULT NULL,
  `ordre` int (30)DEFAULT NULL,

  PRIMARY KEY (`id`)

)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `paragraphe` (`id`,`titre`,`texte`,`page`,`ordre`) VALUES(1,'Paragraphe 1','Elle est probablement construite vers le milieu du xiie siècle bien qu''à quelques décennies près cette datation soit encore discutée, ce qui entretient l''incertitude sur l''ordre monastique, bénédictin ou cartusien, qui l''a fondée. De même, sa dédicace à saint Jean, pouvant s''appliquer à un autre monument, est contestée. Elle est rattachée à la chartreuse du Liget jusqu''à la Révolution française ; c''est alors que, déjà ruinée, elle est vendue à des propriétaires privés puis à l''État. Ce dernier en entreprend la restauration dans les années 1860 après qu''elle est classée au titre des monuments historiques en 1862. La chapelle est la propriété de la commune de Sennevières depuis 2007.','home',1);
INSERT INTO `paragraphe` (`id`,`titre`,`texte`,`page`,`ordre`) VALUES(2,'Paragraphe 2','Elle est probablement construite vers le milieu du xiie siècle bien qu''à quelques décennies près cette datation soit encore discutée, ce qui entretient l''incertitude sur l''ordre monastique, bénédictin ou cartusien, qui l''a fondée. De même, sa dédicace à saint Jean, pouvant s''appliquer à un autre monument, est contestée. Elle est rattachée à la chartreuse du Liget jusqu''à la Révolution française ; c''est alors que, déjà ruinée, elle est vendue à des propriétaires privés puis à l''État. Ce dernier en entreprend la restauration dans les années 1860 après qu''elle est classée au titre des monuments historiques en 1862. La chapelle est la propriété de la commune de Sennevières depuis 2007.','home',2);
INSERT INTO `paragraphe` (`id`,`titre`,`texte`,`page`,`ordre`) VALUES(3,'Paragraphe 3','Elle est probablement construite vers le milieu du xiie siècle bien qu''à quelques décennies près cette datation soit encore discutée, ce qui entretient l''incertitude sur l''ordre monastique, bénédictin ou cartusien, qui l''a fondée. De même, sa dédicace à saint Jean, pouvant s''appliquer à un autre monument, est contestée. Elle est rattachée à la chartreuse du Liget jusqu''à la Révolution française ; c''est alors que, déjà ruinée, elle est vendue à des propriétaires privés puis à l''État. Ce dernier en entreprend la restauration dans les années 1860 après qu''elle est classée au titre des monuments historiques en 1862. La chapelle est la propriété de la commune de Sennevières depuis 2007.','home',3);
INSERT INTO `paragraphe` (`id`,`titre`,`texte`,`page`,`ordre`) VALUES(4,'Paragraphe 1','Elle est probablement construite vers le milieu du xiie siècle bien qu''à quelques décennies près cette datation soit encore discutée, ce qui entretient l''incertitude sur l''ordre monastique, bénédictin ou cartusien, qui l''a fondée. De même, sa dédicace à saint Jean, pouvant s''appliquer à un autre monument, est contestée. Elle est rattachée à la chartreuse du Liget jusqu''à la Révolution française ; c''est alors que, déjà ruinée, elle est vendue à des propriétaires privés puis à l''État. Ce dernier en entreprend la restauration dans les années 1860 après qu''elle est classée au titre des monuments historiques en 1862. La chapelle est la propriété de la commune de Sennevières depuis 2007.','contacter',1);
INSERT INTO `paragraphe` (`id`,`titre`,`texte`,`page`,`ordre`) VALUES(5,'Paragraphe 2','Elle est probablement construite vers le milieu du xiie siècle bien qu''à quelques décennies près cette datation soit encore discutée, ce qui entretient l''incertitude sur l''ordre monastique, bénédictin ou cartusien, qui l''a fondée. De même, sa dédicace à saint Jean, pouvant s''appliquer à un autre monument, est contestée. Elle est rattachée à la chartreuse du Liget jusqu''à la Révolution française ; c''est alors que, déjà ruinée, elle est vendue à des propriétaires privés puis à l''État. Ce dernier en entreprend la restauration dans les années 1860 après qu''elle est classée au titre des monuments historiques en 1862. La chapelle est la propriété de la commune de Sennevières depuis 2007.','contacter',2);
INSERT INTO `paragraphe` (`id`,`titre`,`texte`,`page`,`ordre`) VALUES(6,'Paragraphe 3','Elle est probablement construite vers le milieu du xiie siècle bien qu''à quelques décennies près cette datation soit encore discutée, ce qui entretient l''incertitude sur l''ordre monastique, bénédictin ou cartusien, qui l''a fondée. De même, sa dédicace à saint Jean, pouvant s''appliquer à un autre monument, est contestée. Elle est rattachée à la chartreuse du Liget jusqu''à la Révolution française ; c''est alors que, déjà ruinée, elle est vendue à des propriétaires privés puis à l''État. Ce dernier en entreprend la restauration dans les années 1860 après qu''elle est classée au titre des monuments historiques en 1862. La chapelle est la propriété de la commune de Sennevières depuis 2007.','contacter',3);


INSERT INTO `membre` (`id`,`pseudo`,`mdp`,`role`) VALUES(1,'root','$argon2i$v=19$m=65536,t=2,p=1$4BBf74e80ETLZiQT/WvCxw$bsZ1wJcaUY1OalxuMgvwXSKyf0LT+HhFp4M516lzlTQ','administrateur');
INSERT INTO `membre` (`id`,`pseudo`,`mdp`,`role`) VALUES(2,'quentin','$argon2i$v=19$m=65536,t=2,p=1$4BBf74e80ETLZiQT/WvCxw$bsZ1wJcaUY1OalxuMgvwXSKyf0LT+HhFp4M516lzlTQ','administrateur');
INSERT INTO `membre` (`id`,`pseudo`,`mdp`,`role`) VALUES(3,'matthias','$argon2i$v=19$m=65536,t=2,p=1$slv6QY6wp3yJytsnXb3TXg$6MK0sBb8jrp5BD2QUP/SbacltM5YwVHOYJ4ycFb74Z0','membre');