CREATE TABLE account_details (
    id   bigint      NOT NULL,
    name VARCHAR(128),acc_create_date DATE,
    PRIMARY KEY (id)
);


INSERT INTO account_details(id,name,acc_create_date) VALUES
				 (1234567,'kalu','2021-02-23');
INSERT INTO account_details(id,name,acc_create_date) VALUES
				 (1234568,'siba','2021-02-13');
INSERT INTO account_details(id,name,acc_create_date) VALUES
				 (1234569,'kalja','2021-02-11');
INSERT INTO account_details(id,name,acc_create_date) VALUES
				 (1234570,'jilu','2021-02-17');