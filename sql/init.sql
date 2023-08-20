# 创建数据库
create database bank;
# 创建表结构
CREATE TABLE bank.`account`
(
    `name`   varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
    `money`  int(255)                         DEFAULT NULL COMMENT '账户金额',
    `cardNo` varchar(255) CHARACTER SET latin1 NOT NULL COMMENT '银行卡号'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;
# 插入数据
INSERT INTO bank.account (name, money, cardNo)
VALUES ('韩梅梅', 100, '6029621011001');
INSERT INTO bank.account (name, money, cardNo)
VALUES ('李大雷', 100, '6029621011000');