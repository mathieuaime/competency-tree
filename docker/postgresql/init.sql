CREATE SCHEMA roadmap;

CREATE USER liquibase WITH PASSWORD 'liquibase';
CREATE USER roadmap_user WITH PASSWORD 'user';

GRANT CONNECT ON DATABASE roadmap TO liquibase;
GRANT CONNECT ON DATABASE roadmap TO roadmap_user;

GRANT USAGE ON SCHEMA roadmap TO liquibase;

ALTER DEFAULT PRIVILEGES
FOR USER liquibase
IN SCHEMA roadmap
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO liquibase;

ALTER DEFAULT PRIVILEGES
FOR USER roadmap_user
IN SCHEMA roadmap
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO roadmap_user;