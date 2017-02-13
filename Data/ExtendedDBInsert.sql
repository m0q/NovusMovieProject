INSERT INTO Films (film_name, film_year, imdb_id, imdb_rating) VALUES
	('X-Men Apocalypse', '2016', '3385516', '7.1'),
	('Batman: The Dark Knight', '2008', '0468569', '9.0'),
	('Batman: The Dark Knight Rises', '2012', '1345836', '8.5'),
	('Man Of Steel', '2013', '0770828', '7.1'),
	('X-Men: Days of Future Past', '2014', '1877832', '8.0'),
	('X-Men: First Class', '2011', '1270798', '7.8'),
	('Deadpool', '2016', '1431045', '8.1'),
	('Suicide Squad', '2016', '1386697', '6.3'),
	('Batman Begins','2005','0372784','8.3'),
	('Avengers Assemble','2012','0848228','8.1'),
	('Avengers: Age of Ultron','2015','2395427','7.4'),
	('Captain America: The First Avenger','2011','0458339','6.9'),
	('Captain America: The Winter Soilder', '2014', '1843866', '7.8'),
	('Captain America: Civil War','2016','3498820','7.9'),
	('The Amazing Spider-Man','2012','0948470','7.0'),
	('Iron Man','2008','0371746','7.9'),
	('Iron Man 2','2010','1228705','7.0'),
	('Iron Man 3', '2013', '1300854', '7.2'),
	('Guardians Of The Galaxy','2014','2015381','8.1'),
	('X-Men: The Last Stand','2006','0376994','6.7'),
	('Thor: The Dark World','2013','1981115','7.1'),
	('Thor','2011','0800369','7.0'),
	('The Green Lantern','2011','1133985','5.6'),
	('The Amazing Spider-Man 2', '2014', '1872181', '6.7'),
	('Teenage Mutant Ninja Turtles','2014','1291150','5.9'),
	('Fantastic Four','2015','1502712','4.3'),
	('Ant-Man','2015','0478970','7.3');

INSERT INTO Actors (actor_firstNames, actor_lastName, imdb_id) VALUES
	('James','McAvoy','0564215'),
	('Christian','Bale','0000288'),
	('Patrick','Stewert','0001772'),
	('Ryan','Reynolds','0005351'),
	('Will','Smith','0000226'),
	('Robert','Downey Jr.','0000375'),
	('Chris','Evans','0262635'),
	('Andrew','Garfield','1940449'),
	('Chris','Pratt','0695435'),
	('Chris','Hemsworth','1165110'),
	('Megan','Fox','1083271'),
	('Miles','Teller','1886602'),
	('Paul','Rudd','0748620');

INSERT INTO Directors (director_firstNames, director_lastName, imdb_id) VALUES
	('Bryan','Singer','0001741'),
	('Christopher','Nolan','0634240'),
	('Matthew','Vaughn','0891216'),
	('Tim','Miller','1783265'),
	('David','Ayer','0043742'),
	('Joss','Whedon','0923736'),
	('Joe','Johnston','0002653'),
	('Anthony','Russo','0751577'),
	('Marc','Webb','1989536'),
	('Jon','Favreau','0269463'),
	('Shane','Black','0000948'),
	('James','Gunn','0348181'),
	('Brett','Ratner','0711840'),
	('Alan','Taylor','0851930'),
	('Kenneth','Branagh','0000110'),
	('Martin', 'Campball', '0132709'),
	('Jonathan','Liebesman','0509448'),
	('Josh','Trank','2503633'),
	('Peyton','Reed','0715636');

INSERT INTO Lookup_Film_Actors (film_id, actor_id) VALUES
	('4','4'),
	('5','5'),
	('6','5'),
	('7','2'),
	('8','6'),
	('9','4'),
	('10','7'),
	('11','8'),
	('12','5'),
	('13','9'),
	('14','9'),
	('15','10'),
	('16','10'),
	('17','10'),
	('18','11'),
	('19','9'),
	('20','9'),
	('21','9'),
	('22','12'),
	('23','6'),
	('24','13'),
	('25','13'),
	('26','7'),
	('27','11'),
	('28','14'),
	('29','15'),
	('30','16');

INSERT INTO Lookup_Film_Directors (film_id, director_id) VALUES
	('4','4'),
	('5','5'),
	('6','5'),
	('7','2'),
	('8','4'),
	('9','6'),
	('10','7'),
	('11','8'),
	('12','5'),
	('13','9'),
	('14','9'),
	('15','10'),
	('16','11'),
	('17','11'),
	('18','12'),
	('19','13'),
	('20','13'),
	('21','14'),
	('22','15'),
	('23','16'),
	('24','17'),
	('25','18'),
	('26','19'),
	('27','12'),
	('28','20'),
	('29','21'),
	('30','22');





