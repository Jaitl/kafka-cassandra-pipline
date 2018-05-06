CREATE KEYSPACE kafka
  WITH REPLICATION = {
   'class' : 'SimpleStrategy',
   'replication_factor' : 1
  };

use kafka;

CREATE TYPE kafka.full_info (
  "age" int,
  "lastName" text,
  "jobs" list<text>
);

CREATE TABLE kafka.person (
   "ID" text,
   "firstName" text,
   "fullInfo" FROZEN<full_info>,
PRIMARY KEY ("ID"));
