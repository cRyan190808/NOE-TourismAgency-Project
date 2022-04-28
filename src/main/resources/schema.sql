/*==============================================================*/

DROP TABLE IF EXISTS hotel;

/*==============================================================*/
/* Table: hotel                                                 */
/*==============================================================*/
CREATE TABLE hotel
(
  id      INT  NOT NULL PRIMARY KEY AUTO_INCREMENT,
  category int NOT NULL,
  name    TEXT NOT NULL,
  owner   TEXT NOT NULL,
  contact TEXT NOT NULL,
  address TEXT NOT NULL,
  city    TEXT NOT NULL,
  zip     TEXT NOT NULL,
  phone   TEXT NOT NULL,
  rooms   int  NOT NULL,
  beds    int  NOT NULL,
  url     TEXT NOT NULL,
  family_friendly boolean NOT NULL,
  dog_friendly boolean NOT NULL,
  spa     boolean NOT NULL,
  fitness boolean NOT NULL
);

