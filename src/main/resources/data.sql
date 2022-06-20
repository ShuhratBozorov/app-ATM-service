insert into role (name)
values ('ROLE_DIRECTOR'),
       ('ROLE_STAFF'),
       ('ROLE_CLIENT'),
       ('ROLE_CARD'),
       ('ROLE_ATM_STAFF');

insert into account_type(name)
values ('INCOME'),
       ('EXPENDITURE');

insert into card_type(name)
values ('UZCARD'),
       ('HUMO'),
       ('VISA');

insert into currency(name)
values ('SUM'),
       ('US_DOLLAR'),
       ('RUSSIAN_RUBLE');

insert into banknote(value, currency_id)
values (1000, 1),
       (2000, 1),
       (5000, 1),
       (10000, 1),
       (20000, 1),
       (50000, 1),
       (100000, 1),
       (1, 2),
       (5, 2),
       (10, 2),
       (20, 2),
       (50, 2),
       (100, 2);

insert into bank(name)
values ('IPAK_YULI_BANKI'),
       ('XALQ_BANKI'),
       ('AGROBANK'),
       ('ALOQABANK');