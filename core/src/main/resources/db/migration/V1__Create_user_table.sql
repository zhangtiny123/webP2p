create table USER (
    ID int not null AUTO_INCREMENT,
    NAME varchar(100) not null,
    ROLE enum('INVESTOR', 'BORROWER') NOT NULL ,
    EMAIL varchar(100) not null ,
    ID_NUMBER VARCHAR(20) not NULL ,
    BIRTHDAY DATE NOT NULL ,
    PRIMARY KEY (ID),
    UNIQUE KEY (EMAIL)
);