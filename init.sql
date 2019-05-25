CREATE TABLE t_goods
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    bill_no varchar(200),
    goods_name varchar(200),
    supplier_id int(11),
    supplier_name varchar(200),
    price decimal(19,8),
    stock decimal(19,8),
    home_id int(11),
    ck_name varchar(16)
);
INSERT INTO t_goods (id, bill_no, goods_name, supplier_id, supplier_name, price, stock,home_id,ck_name) VALUES (2, '11', '辣椒', 5, '工艺案例', 1.20000000, 5.00000000,1,冷藏库);
CREATE TABLE t_storehome (
  home_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  ck_name varchar(16)
)
INSERT INTO t_storehome VALUES (1,'冷藏库'),(2,'蔬菜库'),(3,'材料库');
CREATE TABLE t_goods_stock
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
    supplier_id int(11),
    home_id int(11),
    ck_name varchar(16)
);
INSERT INTO t_goods_stock (id, goods_name, supplier_name, goods_amt, goods_price, buse_date, kc, goods_id, store_id, store_name, supplier_id,home_id,ck_name) VALUES (14, '辣椒', '工艺案例', 8.00000000, 1.20000000, '2019-05-15 00:02:01', 'RK', 2, 2, '张三店', 5,1,'冷藏库');
INSERT INTO t_goods_stock (id, goods_name, supplier_name, goods_amt, goods_price, buse_date, kc, goods_id, store_id, store_name, supplier_id,home_id,ck_name) VALUES (15, '辣椒', '工艺案例', 5.00000000, 1.20000000, '2019-05-15 08:19:23', 'CK', 2, 2, '张三店', 5,1,'冷藏库');
INSERT INTO t_goods_stock (id, goods_name, supplier_name, goods_amt, goods_price, buse_date, kc, goods_id, store_id, store_name, supplier_id,home_id,ck_name) VALUES (16, '辣椒', '工艺案例', 2.00000000, 1.20000000, null, 'CK', 2, 2, '张三店', 5,1,'冷藏库');
CREATE TABLE t_store
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(16),
    address varchar(16),
    phone varchar(16)
);
INSERT INTO t_store (id, name, address, phone) VALUES (2, '张三店', '11221', '1786578');
INSERT INTO t_store (id, name, address, phone) VALUES (3, '李四店', '222', '22');
CREATE TABLE t_supplier
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(16) NOT NULL,
    address varchar(255),
    phone varchar(16)
);
INSERT INTO t_supplier (id, name, address, phone) VALUES (5, '工艺案例', '小地方', '178787878');
INSERT INTO t_supplier (id, name, address, phone) VALUES (6, '测试 ', '11111', '111111');
CREATE TABLE T_USER
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_name varchar(16) NOT NULL,
    password varchar(16) NOT NULL
);
INSERT INTO T_USER (id, user_name, password) VALUES (1, 'admin', 'admin');
INSERT INTO T_USER (id, user_name, password) VALUES (2, 'zhangsan', '123123');