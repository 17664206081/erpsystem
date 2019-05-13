CREATE TABLE erpsysteam.t_goods_stock
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    goods_name varchar(32),
    supplier_name varchar(32),
    goods_amt decimal(19,8),
    goods_price decimal(19,8),
    time datetime DEFAULT CURRENT_TIMESTAMP,
    kc enum('CK', 'RK')
);
INSERT INTO erpsysteam.t_goods_stock (id, goods_name, supplier_name, goods_amt, goods_price, time, kc) VALUES (7, '辣椒', '工艺案例', 2.00000000, 1.50000000, '2019-05-13 10:09:29', 'RK');
INSERT INTO erpsysteam.t_goods_stock (id, goods_name, supplier_name, goods_amt, goods_price, time, kc) VALUES (8, '辣椒', '工艺案例', 1.00000000, 1.50000000, '2019-05-13 10:11:20', 'CK');
INSERT INTO erpsysteam.t_goods_stock (id, goods_name, supplier_name, goods_amt, goods_price, time, kc) VALUES (9, '辣椒', '工艺案例', 6.00000000, 1.50000000, '2019-05-13 11:02:40', 'RK');
INSERT INTO erpsysteam.t_goods_stock (id, goods_name, supplier_name, goods_amt, goods_price, time, kc) VALUES (10, '辣椒', '工艺案例', 5.00000000, 1.50000000, '2019-05-13 11:04:30', 'CK');
CREATE TABLE erpsysteam.t_supplier
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(16) NOT NULL,
    address varchar(255),
    phone varchar(16)
);
INSERT INTO erpsysteam.t_supplier (id, name, address, phone) VALUES (5, '工艺案例', '小地方', '178787878');
INSERT INTO erpsysteam.t_supplier (id, name, address, phone) VALUES (6, '测试 ', '11111', '111111');
CREATE TABLE erpsysteam.t_user
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_name varchar(16) NOT NULL,
    password varchar(16) NOT NULL
);
INSERT INTO erpsysteam.t_user (id, user_name, password) VALUES (1, 'admin', 'admin');