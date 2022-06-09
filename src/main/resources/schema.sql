/*==============================================================*/

DROP TABLE IF EXISTS hotel CASCADE;

/*==============================================================*/
/* Table: hotel                                                 */
/*==============================================================*/
CREATE TABLE hotel
(
    id              INT     NOT NULL PRIMARY KEY AUTO_INCREMENT,
    category        INT     NOT NULL,
    name            TEXT    NOT NULL,
    owner           TEXT    NOT NULL,
    contact         TEXT    NOT NULL,
    address         TEXT    NOT NULL,
    city            TEXT    NOT NULL,
    zip             TEXT    NOT NULL,
    phone           TEXT    NOT NULL,
    rooms           INT     NOT NULL,
    beds            INT     NOT NULL,
    url             TEXT    NOT NULL,
    family_friendly boolean NOT NULL DEFAULT FALSE,
    dog_friendly    boolean NOT NULL DEFAULT FALSE,
    spa             boolean NOT NULL DEFAULT FALSE,
    fitness         boolean NOT NULL DEFAULT FALSE
);

DROP TABLE IF EXISTS occupancy;
/*==============================================================*/
/* Table: occupancy                                             */
/*==============================================================*/
CREATE TABLE occupancy
(
    id               INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    room_count       INT NOT NULL,
    room_utilization INT NOT NULL,
    bed_count        INT NOT NULL,
    bed_utilization  INT NOT NULL,
    year             INT NOT NULL,
    month            INT NOT NULL,
    hotel_id         INT DEFAULT NULL,
    FOREIGN KEY (hotel_id) REFERENCES hotel (id) ON UPDATE CASCADE ON DELETE CASCADE
);