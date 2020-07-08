## Data Definition Language (DDL)

```sqlite
CREATE TABLE IF NOT EXISTS `User`
(
    `user_id`   INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `name`      TEXT                              NOT NULL COLLATE NOCASE,
    `age`       INTEGER                           NOT NULL,
    `oauth_key` TEXT COLLATE NOCASE
);

CREATE UNIQUE INDEX IF NOT EXISTS `index_User_name_oauth_key` ON `User` (`name`, `oauth_key`);

CREATE TABLE IF NOT EXISTS `Activity`
(
    `activity_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `name`        TEXT                              NOT NULL,
    `class_name`  TEXT                              NOT NULL,
    `level`       INTEGER                           NOT NULL
);

CREATE INDEX IF NOT EXISTS `index_Activity_name` ON `Activity` (`name`);

CREATE INDEX IF NOT EXISTS `index_Activity_level` ON `Activity` (`level`);

CREATE TABLE IF NOT EXISTS `Progress`
(
    `progress_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `activity_id` INTEGER,
    `user_id`     INTEGER,
    `start`       INTEGER                           NOT NULL,
    `end`         INTEGER,
    FOREIGN KEY (`activity_id`) REFERENCES `Activity` (`activity_id`) ON UPDATE NO ACTION ON DELETE CASCADE,
    FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS `index_Progress_activity_id` ON `Progress` (`activity_id`);

CREATE INDEX IF NOT EXISTS `index_Progress_user_id` ON `Progress` (`user_id`);
```

[`ddl.sql`](sql/ddl.sql)












