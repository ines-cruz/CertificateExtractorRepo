CREATE TABLE IF NOT EXISTS CERTIFICATEINFO (
        ID INTEGER  NOT NULL AUTO_INCREMENT,
        SUBJECT VARCHAR(255)    NOT NULL,
        ISSUER VARCHAR(255)    NOT NULL,
        ISVALID BOOLEAN)