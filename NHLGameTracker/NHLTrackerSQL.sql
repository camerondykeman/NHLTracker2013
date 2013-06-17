CREATE DATABASE NHL2013db;

CREATE TABLE NHL2013db.Games(
	GameID INT NOT NULL AUTO_INCREMENT,
	HomeTeamID INT,
	AwayTeamID INT,
	HomeTeamGoals INT,
	AwayTeamGoals INT,
	GameMonth VARCHAR(9),
	GameDay INT,
	GameYear INT,
	Overtime BOOL,
	Shootout BOOL,
	PRIMARY KEY (GameID)
);

CREATE TABLE NHL2013db.Teams(
	TeamID INT,
	TeamName VARCHAR(50),
	Division VARCHAR(30),
	GamesPlayed INT,
	Wins INT,
	RegulationLosses INT,
	OvertimeLosses INT,
	ShootoutLosses INT,
	Points INT,
	PRIMARY KEY (TeamID)
);

-- Northeast Division --
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (1, "Boston Bruins", "Northeast", 0, 0, 0, 0, 0, 0);
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (2, "Buffalo Sabres", "Northeast", 0, 0, 0, 0, 0, 0);
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (3, "Montreal Canadiens", "Northeast", 0, 0, 0, 0, 0, 0);
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (4, "Ottawa Senators", "Northeast", 0, 0, 0, 0, 0, 0);
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (5, "Toronto Maple Leafs", "Northeast", 0, 0, 0, 0, 0, 0);

-- Atlantic Division --
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (6, "New Jersey Devils", "Atlantic", 0, 0, 0, 0, 0, 0);
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (7, "New York Islanders", "Atlantic", 0, 0, 0, 0, 0, 0);
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (8, "New York Rangers", "Atlantic", 0, 0, 0, 0, 0, 0);
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (9, "Philidelphia Flyers", "Atlantic", 0, 0, 0, 0, 0, 0);
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (10, "Pittsburgh Penguins", "Atlantic", 0, 0, 0, 0, 0, 0);


-- Southeast Division --
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (11, "Carolina Hurricanes", "Southeast", 0, 0, 0, 0, 0, 0);
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (12, "Florida Panthers", "Southeast", 0, 0, 0, 0, 0, 0);
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (13, "Tampa Bay Lightning", "Southeast", 0, 0, 0, 0, 0, 0);
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (14, "Washington Capitals", "Southeast", 0, 0, 0, 0, 0, 0);
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (15, "Winnipeg Jets", "Southeast", 0, 0, 0, 0, 0, 0);

-- Pacific Division --
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (16, "Anaheim Ducks ", "Pacific", 0, 0, 0, 0, 0, 0);
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (17, "Dallas Stars", "Pacific", 0, 0, 0, 0, 0, 0);
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (18, "Los Angeles Kings", "Pacific", 0, 0, 0, 0, 0, 0);
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (19, "Phoenix Coyotes", "Pacific", 0, 0, 0, 0, 0, 0);
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (20, "San Jose Sharks", "Pacific", 0, 0, 0, 0, 0, 0);

-- Central Division --
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (21, "Chicago Blackhawks", "Central", 0, 0, 0, 0, 0, 0);
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (22, "Columbus Blue Jackets", "Central", 0, 0, 0, 0, 0, 0);
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (23, "Detroit Red Wings", "Central", 0, 0, 0, 0, 0, 0);
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (24, "Nashville Predators", "Central", 0, 0, 0, 0, 0, 0);
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (25, "St. Louis Blues", "Central", 0, 0, 0, 0, 0, 0);

-- Northwest Division --
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (26, "Calgary Flames", "Northwest", 0, 0, 0, 0, 0, 0);
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (27, "Colorado Avalanche", "Northwest", 0, 0, 0, 0, 0, 0);
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (28, "Edmonton Oilers", "Northwest", 0, 0, 0, 0, 0, 0);
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (29, "Minnesota Wild", "Northwest", 0, 0, 0, 0, 0, 0);
INSERT INTO NHL2013db.Teams (TeamID, TeamName, Division, GamesPlayed, Wins, RegulationLosses, OvertimeLosses, ShootoutLosses, Points)
VALUES (30, "Vancouver Cannucks", "Northwest", 0, 0, 0, 0, 0, 0);

