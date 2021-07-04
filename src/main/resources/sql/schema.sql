CREATE DATABASE IF NOT EXISTS IncomeManagement;
USE IncomeManagement;

CREATE TABLE IF NOT EXISTS TransactionType
(
    id       int          NOT NULL AUTO_INCREMENT,
    typeName VARCHAR(255) NOT NULL,
    createAt DATE         NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS UserAccount
(
    id              int NOT NULL AUTO_INCREMENT,
    userAccountName VARCHAR(255),
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS Category
(
    id           int NOT NULL AUTO_INCREMENT,
    categoryName VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS SubCategory
(
    id              int NOT NULL AUTO_INCREMENT,
    subCategoryName VARCHAR(255),
    categoryId      int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (categoryId) REFERENCES Category (id)
);

CREATE TABLE IF NOT EXISTS Transaction
(
    id               int  NOT NULL AUTO_INCREMENT,
    transactionName  VARCHAR(255),
    transDate        DATE NOT NULL,

    typeId           int  NOT NULL,

    userAccountInId  int,
    userAccountOutId int,

    categoryId       int  NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (typeId) REFERENCES TransactionType (id),
    FOREIGN KEY (userAccountInId) REFERENCES UserAccount (id),
    FOREIGN KEY (userAccountOutId) REFERENCES UserAccount (id),
    FOREIGN KEY (categoryId) REFERENCES Category (id),

    # No mínimo uma conta.
    CONSTRAINT MinimunAcc CHECK (userAccountInId IS NOT NULL or userAccountOutId IS NOT NULL)
);