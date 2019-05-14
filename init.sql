CREATE TABLE erpsysteam.t_goods
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    bill_no varchar(200),
    goods_name varchar(200),
    supplier_id int(11),
    supplier_name varchar(200),
    price decimal(19,8),
    stock decimal(19,8)
);
INSERT INTO erpsysteam.t_goods (id, bill_no, goods_name, supplier_id, supplier_name, price, stock) VALUES (2, '11', '辣椒', 5, '工艺案例', 1.20000000, 5.00000000);
CREATE TABLE erpsysteam.t_goods_stock
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    goods_name varchar(32),
    supplier_name varchar(32),
    goods_amt decimal(19,8),
    goods_price decimal(19,8),
    buse_date datetime,
    kc enum('CK', 'RK'),
    goods_id int(11),
    store_id int(11),
    store_name varchar(200),
    supplier_id int(11)
);
INSERT INTO erpsysteam.t_goods_stock (id, goods_name, supplier_name, goods_amt, goods_price, buse_date, kc, goods_id, store_id, store_name, supplier_id) VALUES (14, '辣椒', '工艺案例', 8.00000000, 1.20000000, null, 'RK', 2, 2, '张三店', 5);
INSERT INTO erpsysteam.t_goods_stock (id, goods_name, supplier_name, goods_amt, goods_price, buse_date, kc, goods_id, store_id, store_name, supplier_id) VALUES (15, '辣椒', '工艺案例', 5.00000000, 1.20000000, null, 'CK', 2, 2, '张三店', 5);
CREATE TABLE erpsysteam.t_store
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(16),
    address varchar(16),
    phone varchar(16)
);
INSERT INTO erpsysteam.t_store (id, name, address, phone) VALUES (2, '张三店', '11221', '1786578');
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
INSERT INTO erpsysteam.t_user (id, user_name, password) VALUES (2, 'zhangsan', '123123');