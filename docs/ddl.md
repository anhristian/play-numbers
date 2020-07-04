## Data Definition Language (DDL)

```sqlite
CREATE TABLE IF NOT EXISTS `User` 
(
`user_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
`name` TEXT NOT NULL COLLATE NOCASE,
`age` INTEGER
);


CREATE UNIQUE INDEX IF NOT EXISTS `index_User_name` ON `User` (`name`); 














