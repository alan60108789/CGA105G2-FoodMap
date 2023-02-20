-- A-創表
CREATE SCHEMA `cga105g2`;
-- B-1-1.會員
CREATE TABLE `cga105g2`.`MEMBER` (
    `MEM_ID`            INT		    	NOT NULL AUTO_INCREMENT			    COMMENT '編號',
    `MEM_STATUS`	    TINYINT			NOT NULL DEFAULT '0'  	 	        COMMENT '狀態',
    `MEM_ACC` 			VARCHAR(50)		NOT NULL UNIQUE						COMMENT '帳號',
    `MEM_PWD`			VARCHAR(50)		NOT NULL							COMMENT '密碼',
    `MEM_MAIL` 			VARCHAR(50)		NOT NULL UNIQUE					    COMMENT '電子信箱',
    `MEM_PIC`			LONGBLOB											COMMENT '會員照片',
    `MEM_NAME`			VARCHAR(50)		NOT NULL							COMMENT '用戶名稱',
    `MEM_RECIPIENT`		VARCHAR(50)		NOT NULL							COMMENT '收件姓名',
    `MEM_TW_ID`			VARCHAR(50)		NOT NULL							COMMENT '身分證字號',
    `MEM_BIRTHDAY`		DATE			NOT NULL							COMMENT '出生日期',
    `MEM_PHONE`			VARCHAR(50)		NOT NULL							COMMENT '手機',
    `MEM_POSTAL_CODE`	INT				NOT NULL							COMMENT '郵遞區號',
    `MEM_CITY`			VARCHAR(50)		NOT NULL							COMMENT '縣市',
    `MEM_DISTRICT`		VARCHAR(50)		NOT NULL							COMMENT '區域',
    `MEM_ADDRESS`		VARCHAR(50)		NOT NULL							COMMENT '地址',
    `MEM_TEXT`			VARCHAR(2000)										COMMENT '個人簡介',
    `MEM_TIME`			DATETIME        NOT NULL  DEFAULT CURRENT_TIMESTAMP	COMMENT '註冊時間',
    `MEM_POINT`			INT				NOT NULL  DEFAULT '0'	  	        COMMENT '我的點數',
    PRIMARY KEY (`MEM_ID`)
);
-- C-3-1 後台員工
CREATE TABLE `cga105g2`.`EMPLOYEE` (
    `EMP__STATUS`       TINYINT         NOT NULL                DEFAULT '0'                                                 COMMENT '狀態 0:啟用 1:停權',
    `EMP_ID`            INT             NOT NULL AUTO_INCREMENT                                                             COMMENT '編號',
    `EMP_ACC`           VARCHAR(20)     NOT NULL                                                                            COMMENT '帳號 修改新增' ,
    `EMP_PWD`           VARCHAR(20)     NOT NULL                                                                            COMMENT '密碼' ,
    `EMP_PER`           TINYINT         NOT NULL                DEFAULT '0'                                                 COMMENT '權限  0:員工 1:主管',
    `EMP_TIME`          DATETIME        NOT NULL                DEFAULT CURRENT_TIMESTAMP                                   COMMENT '新增日期',
    `EMP_RTIME`         TIMESTAMP                               DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP       COMMENT '修改日期',
    PRIMARY KEY (`EMP_ID`)
);
-- D-2-1 店家
CREATE TABLE `cga105g2`.`STORE` (
    `STORE_ID`          INT             NOT NULL AUTO_INCREMENT                                                                     COMMENT '訂單編號',
    `EMP_ID`	        INT                                                                                                         COMMENT '員工編號',
    `STORE_STATUS`      TINYINT         NOT NULL            DEFAULT '0'                                                             COMMENT '狀態 0:未註冊 1:審核中  2:審核通過 3:停權',
    `STORE_NAME`	    VARCHAR(50)	    NOT NULL                                                                                    COMMENT '店家',
    `STORE_PHONE1`      VARCHAR(50)     NOT NULL                                                                                    COMMENT '電話',
    `STORE_HOURS`       VARCHAR(500)    NOT NULL                                                                                    COMMENT '營業時間',
    `STORE_MAP`         VARCHAR(200)	UNIQUE                                                                                      COMMENT '地圖經緯度',
    `STORE_CITY`	    VARCHAR(50)	    NOT NULL                                                                                    COMMENT '營業縣市',
    `STORE_DISTRICT`	VARCHAR(50)	    NOT NULL                                                                                    COMMENT '營業區域',
    `STORE_ADDRESS`     VARCHAR(50)	    NOT NULL                                                                                    COMMENT '營業地址',
    `STORE_URL`         VARCHAR(200)                                                                                                COMMENT 'googleMap',
    `STORE_WEB`         VARCHAR(200)                                                                                                COMMENT 'website',
    `STORE_ACC`         VARCHAR(50)     UNIQUE                                                                                      COMMENT '帳號',
    `STORE_PWD`         VARCHAR(50)                                                                                                 COMMENT '密碼',
    `STORE_MAIL`	    VARCHAR(50)                                                                                                 COMMENT '電子信箱',
    `STORE_COM_ID`      VARCHAR(50)                                                                                                 COMMENT '統一編號',
    `STORE_COM_ADDRESS` VARCHAR(50)                                                                                                 COMMENT '代表人姓名',
    `STORE_TW_ID`       VARCHAR(50)                                                                                                 COMMENT '身分證字號',
    `STORE_PHONE2`      VARCHAR(50)                                                                                                 COMMENT '聯絡人電話',
    `STORE_TEXT`	    VARCHAR(2000)                                                                                               COMMENT '店家簡介',
    `STORE_PLAN`	    TINYINT                                                                                                     COMMENT '目前方案',
    `STORE_NPLAN`       TINYINT                                                                                                     COMMENT '次月方案',
    `STORE_TIME`	    DATETIME        NOT NULL            DEFAULT CURRENT_TIMESTAMP                                               COMMENT '新增時間',
    `STORE_ONTIME`      DATE                                                                                                        COMMENT '註冊時間',
    `STORE_RETIME`      TIMESTAMP                           DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP                   COMMENT '修改時間',
    `STORE_ETIME`       VARCHAR(50)                                                                                                 COMMENT '訂位時段',
    `STORE_TABLE`       INT                                                                                                         COMMENT '桌數',
    `STORE_ETABLE`      INT                                                                                                         COMMENT '訂位上限數',
    PRIMARY KEY (`STORE_ID`),
    CONSTRAINT `FK_STORE_EMP_ID_EMPLOYEE_EMP_ID` FOREIGN KEY (`EMP_ID`) REFERENCES `EMPLOYEE`(`EMP_ID`)
);
-- E-1-2.文章
CREATE TABLE `cga105g2`.`ARTICLE` (
    `ART_ID`		INT 			NOT NULL AUTO_INCREMENT	                                                                        COMMENT '編號',
    `MEM_ID`		INT 			NOT NULL				                                                                        COMMENT '會員編號',
    `STORE_ID`		INT				NOT NULL 				                                                                        COMMENT '店家編號',
    `ART_HEADER`	VARCHAR(50)		NOT NULL				                                                                        COMMENT '標題',
    `ART_TEXT`		VARCHAR(2000)	NOT NULL				                                                                        COMMENT '內容',
    `ART_IMG`		LONGBLOB 		NOT NULL						                                                                COMMENT '圖片 修改not null',
    `ART_TAG`		VARCHAR(200)							                                                                        COMMENT '標籤',
    `ART_STATUS`	TINYINT 		NOT NULL                    DEFAULT'0'                                                          COMMENT '狀態;0:顯示  1:刪除',
    `ART_TIME`		DATETIME        NOT NULL                    DEFAULT CURRENT_TIMESTAMP                                           COMMENT '新增日期',
    `ART_RETIME`	TIMESTAMP 								    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP               COMMENT '修改時間',
    `ART_SUMLIKE`	INT 			NOT NULL                    DEFAULT'0'		                                                    COMMENT '總按讚數',
    `ART_SCORE`		INT 			NOT NULL				                                                                        COMMENT '評分',
    PRIMARY KEY (`ART_ID`),
    CONSTRAINT `FK_ARTICLE_MEMBER_MEM_ID`       FOREIGN KEY (`MEM_ID`)      REFERENCES `MEMBER` (`MEM_ID`),
    CONSTRAINT `FK_ARTICLE_STORE_STORE_ID`      FOREIGN KEY (`STORE_ID`)    REFERENCES `STORE` (`STORE_ID`)
);
-- F-2-8 餐點
CREATE TABLE `cga105g2`.`MEAL` (
    `MEAL_ID`            INT             NOT NULL AUTO_INCREMENT             COMMENT '編號',
    `STORE_ID`           INT             NOT NULL                            COMMENT '店家編號',
    `MEAL_NAME`          VARCHAR(50)	 NOT NULL                            COMMENT '名稱',
    `MEAL_PRICE`         INT             NOT NULL                            COMMENT '價格',
    `MEAL_STATUS`        TINYINT         DEFAULT'0'                          COMMENT '狀態 0: 下架  1:上架',
    PRIMARY KEY (`MEAL_ID`),
    CONSTRAINT `FK_MEAL_STORE_STORE_ID` FOREIGN KEY (`STORE_ID`) REFERENCES `STORE`(`STORE_ID`)
);
-- G-3-2 權限
CREATE TABLE `cga105g2`.`ROOT` (
    `ROOT_ID`       INT             NOT NULL  AUTO_INCREMENT        COMMENT '編號',
    `ROOT_TEXT`     VARCHAR(20)     NOT NULL                        COMMENT '名稱',
    PRIMARY KEY (`ROOT_ID`)
);
-- H-3-3 後台員工權限
CREATE TABLE `cga105g2`.`EMPLOYEE_ROOT` (
    `EMP_ID`    INT                 NOT NULL                        COMMENT '員工編號',
    `ROOT_ID`   INT                 NOT NULL                        COMMENT '權限編號',
    PRIMARY KEY (`EMP_ID`,`ROOT_ID`),
    CONSTRAINT `FK_EMPLOYEE_ROOT_EMPLOYEE_EMP_ID`    FOREIGN KEY (`EMP_ID`)      REFERENCES `EMPLOYEE`(`EMP_ID`),
    CONSTRAINT `FK_EMPLOYEE_ROOT_ROOT_ROOT_ID`       FOREIGN KEY (`ROOT_ID`)     REFERENCES `ROOT`(`ROOT_ID`)
);
-- I-1-3 收藏文章
CREATE TABLE `cga105g2`.`SAVE_ART` (
    `ART_ID`	INT 	NOT NULL	COMMENT '文章編號',
    `MEM_ID`	INT 	NOT NULL	COMMENT '會員編號',
    PRIMARY KEY (`ART_ID`,`MEM_ID`),
    CONSTRAINT `FK_SAVE_ART_ARTICLE_ART_ID`    FOREIGN KEY (`ART_ID`)      REFERENCES `ARTICLE`(`ART_ID`),
    CONSTRAINT `FK_SAVE_ART_MEMBER_MEM_ID`     FOREIGN KEY (`MEM_ID`)      REFERENCES `MEMBER`(`MEM_ID`)
);
-- J-1-4 文章按讚
CREATE TABLE `cga105g2`.`LIKE_ART` (
    `LIKE_ID`	INT 	NOT NULL  AUTO_INCREMENT	COMMENT '編號',
    `ART_ID`	INT 	NOT NULL 	                COMMENT '文章編號',
    `MEM_ID`	INT 	NOT NULL 	                COMMENT '會員編號',
    PRIMARY KEY (`LIKE_ID`),
    CONSTRAINT `FK_LIKE_ART_ARTICLE_ART_ID`    FOREIGN KEY (`ART_ID`)      REFERENCES `ARTICLE`(`ART_ID`),
    CONSTRAINT `FK_LIKE_ART_MEMBER_MEM_ID`     FOREIGN KEY (`MEM_ID`)      REFERENCES `MEMBER`(`MEM_ID`)
);
-- K-2-3 優惠劵
CREATE TABLE `cga105g2`.`CODE` (
   `CODE_ID`        INT             NOT NULL AUTO_INCREMENT                     COMMENT '編號',
   `STORE_ID`       INT             NOT NULL                                    COMMENT '店家編號',
   `EMP_ID`         INT           						                        COMMENT '員工編號 修改新增',
   `CODE_NUM`       VARCHAR(50)	    NOT NULL                                    COMMENT '優惠券碼',
   `CODE_OFF`       INT             NOT NULL                                    COMMENT '折扣',
   `CODE_STATUS`    TINYINT         NOT NULL       DEFAULT '1'                  COMMENT '狀態 1:審核  2:通過  3:失效',
   `CODE_TEXT`      VARCHAR(200)    NOT NULL                                    COMMENT '備註',
   `CODE_TIME`      DATETIME        NOT NULL       DEFAULT CURRENT_TIMESTAMP    COMMENT '新增日期',
   `CODE_RTIME`     DATE                                                        COMMENT '通過日期',
   `CODE_NTIME`     DATE            NOT NULL                                    COMMENT '到期日期',
   PRIMARY KEY (`CODE_ID`),
   CONSTRAINT `FK_CODE_STORE_STORE_ID` FOREIGN KEY (`STORE_ID`) REFERENCES `STORE`(`STORE_ID`),
   CONSTRAINT `FK_CODE_EMPLOYEE_EMP_ID` FOREIGN KEY (`EMP_ID`) REFERENCES `EMPLOYEE`(`EMP_ID`)
);
-- L-2-4 廣告
CREATE TABLE `cga105g2`.`ADVERTISE` (
    `ADV_ID`     INT           NOT NULL AUTO_INCREMENT                          COMMENT '編號',
    `STORE_ID`   INT           NOT NULL                                         COMMENT '店家編號',
    `EMP_ID`     INT           						                            COMMENT '員工編號 修改NOT NULL',
    `ADV_STATUS` TINYINT       NOT NULL  DEFAULT '1'                            COMMENT '狀態 1:審核中  2:審核通過  3:失效',
    `ADV_IMG`    LONGBLOB      		                                            COMMENT '圖片 修改not null',
    `ADV_TEXT`   VARCHAR(2000) NOT NULL                                         COMMENT '內容',
    `ADV_TIME`   DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP               COMMENT '新增日期',
    `ADV_STIME`  DATE          NOT NULL                                         COMMENT '開始日期',
    `ADV_NTIME`  DATE          NOT NULL                                         COMMENT '到期日期',
     PRIMARY KEY (`ADV_ID`),
     CONSTRAINT `FK_ADVERTISE_STORE_STORE_ID` FOREIGN KEY (`STORE_ID`) REFERENCES `STORE`(`STORE_ID`),
     CONSTRAINT `FK_ADVERTISE_EMPLOYEE_EMP_ID` FOREIGN KEY (`EMP_ID`) REFERENCES `EMPLOYEE`(`EMP_ID`)
);
-- M-1-5 追蹤會員
CREATE TABLE `cga105g2`.`FOLLOW_MEM` (
    `FOLLOW_ID`	INT 	NOT NULL AUTO_INCREMENT	COMMENT '編號',
    `MEM_ID1`	INT 	NOT NULL	            COMMENT 'A會員編號',
    `MEM_ID2`	INT 	NOT NULL    	        COMMENT 'B會員編號',
    PRIMARY KEY (`FOLLOW_ID`),
    CONSTRAINT `FK_FOLLOW_MEM_FOLLOW_MEM_MEM_ID1`    FOREIGN KEY (`MEM_ID1`)      REFERENCES `MEMBER`(`MEM_ID`),
    CONSTRAINT `FK_FOLLOW_MEM_FOLLOW_MEM_MEM_ID2`    FOREIGN KEY (`MEM_ID2`)      REFERENCES `MEMBER`(`MEM_ID`)
);
-- N-2-3-2 會員優惠券
CREATE TABLE `cga105g2`.`MEM_CODE` (
   `CODE_ID` INT NOT NULL COMMENT '優惠券編號',
   `MEM_ID`  INT NOT NULL COMMENT '會員編號',
   PRIMARY KEY (`CODE_ID`,`MEM_ID`),
   CONSTRAINT `FK_MEM_CODE_CODE_CODE_ID`    FOREIGN KEY (`CODE_ID`)   REFERENCES `CODE`(`CODE_ID`),
   CONSTRAINT `FK_MEM_CODE_MEMBER_MEM_ID`   FOREIGN KEY (`MEM_ID`)    REFERENCES `MEMBER`(`MEM_ID`)
);
-- O-1-6 聊天紀錄
CREATE TABLE `cga105g2`.`MESSAGE` (
    `MES_ID`	INT 	        NOT NULL  AUTO_INCREMENT	                        COMMENT '編號',
    `FOLLOW_ID`	INT 	        NOT NULL 	                                        COMMENT '追蹤編號',
    `MES_TEXT`	VARCHAR(2000)	NOT NULL	                                        COMMENT '內容',
    `MES_TIME`	DATETIME 	    NOT NULL DEFAULT CURRENT_TIMESTAMP	                COMMENT '時間',
    PRIMARY KEY (`MES_ID`),
    CONSTRAINT `FK_MESSAGE_FOLLOW_MEM_FOLLOW_ID`    FOREIGN KEY (`FOLLOW_ID`)      REFERENCES `FOLLOW_MEM`(`FOLLOW_ID`)
);
-- P-2-5 店家商品
CREATE TABLE `cga105g2`.`GOODS` (
    `GOODS_ID`      INT            NOT NULL AUTO_INCREMENT                                                  COMMENT '編號',
    `STORE_ID`      INT            NOT NULL                                                                 COMMENT '店家編號',
    `GOODS_IMG`     LONGBLOB       NOT NULL                                                                 COMMENT '圖片',
    `GOODS_NAME`    VARCHAR(50)    NOT NULL                                                                 COMMENT '名稱',
    `GOODS_STATUS`  TINYINT        NOT NULL DEFAULT '0'                                                     COMMENT '狀態  0:下架1:上架 2:審核中 新增修改狀態為：2 審核完畢後改狀態為：0(讓店家選擇是否要上架)',
    `GOODS_PRICE`   INT            NOT NULL                                                                 COMMENT '價格',
    `GOODS_TEXT`    VARCHAR(2000)  NOT NULL                                                                 COMMENT '介紹',
    `GOODS_TIME`    DATETIME       NOT NULL     DEFAULT CURRENT_TIMESTAMP                                   COMMENT '新增日期',
    `GOODS_RTIME`   TIMESTAMP                   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP       COMMENT '修改日期',
     PRIMARY KEY (`GOODS_ID`),
     CONSTRAINT `FK_GOODS_STORE_STORE_ID` FOREIGN KEY (`STORE_ID`) REFERENCES `STORE`(`STORE_ID`)
);
-- Q-1-7 點數紀錄
CREATE TABLE `cga105g2`.`POINT` (
    `POINT_ID`	    INT 	    NOT NULL  AUTO_INCREMENT	COMMENT '編號',
    `MEM_ID`	    INT 	    NOT NULL 	                COMMENT '會員編號',
    `POINT_CHANGE`	VARCHAR(50)	NOT NULL 	                COMMENT '異動原因',
    `POINT_NUMBER`	INT 	    NOT NULL 	                COMMENT '異動點數',
    PRIMARY KEY (`POINT_ID`),
    CONSTRAINT `FK_POINT_MEMBER_MEM_ID`    FOREIGN KEY (`MEM_ID`)      REFERENCES `MEMBER`(`MEM_ID`)
);
-- R-1-8 訂閱店家
CREATE TABLE `cga105g2`.`SUBSCRIBE` (
    `SUB_ID`	INT 	NOT NULL  AUTO_INCREMENT	COMMENT '編號',
    `STORE_ID`	INT 	NOT NULL	                COMMENT '店家編號',
    `MEM_ID`	INT 	NOT NULL	                COMMENT '會員編號',
    PRIMARY KEY (`SUB_ID`),
    CONSTRAINT `FK_SUBSCRIBE_STORE_STORE_ID`    FOREIGN KEY (`STORE_ID`)      REFERENCES `STORE`(`STORE_ID`),
    CONSTRAINT `FK_SUBSCRIBE_MEMBER_MEM_ID`     FOREIGN KEY (`MEM_ID`)        REFERENCES `MEMBER`(`MEM_ID`)
);
-- S-2-9 訂位
CREATE TABLE `cga105g2`.`RESERVA` (
  REN_ID        INT         NOT NULL AUTO_INCREMENT                     COMMENT '編號',
  STORE_ID      INT         NOT NULL                                    COMMENT '店家編號',
  MEM_ID        INT                                                     COMMENT '會員編號',
  REN_NAME      VARCHAR(50) NOT NULL                                    COMMENT '姓名',
  REN_PHONE     VARCHAR(50) NOT NULL                                    COMMENT '電話',
  REN_TIME      VARCHAR(50) NOT NULL                                    COMMENT '時段',
  REN_STATUS    TINYINT     NOT NULL DEFAULT '0'                        COMMENT '狀態  0: 成立   1:取消  2:報到  3:完成',
  REN_TABLE     INT                                                     COMMENT '桌號',
  REN_DATE      DATE    NOT NULL  						                COMMENT '訂位日期',
  REN_CURDATE   DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP          COMMENT '時間',
  REN_HEADCOUNT INT         NOT NULL                                    COMMENT '人數',
  CODE_ID       INT                                                     COMMENT '優惠券編號',
  REN_PRICE     INT                                                     COMMENT '總金額',
  REN_FPRICE    INT                                                     COMMENT '支付金額',
  PRIMARY KEY (`REN_ID`),
  CONSTRAINT `FK_RESERVA_STORE_STORE_ID`    FOREIGN KEY (`STORE_ID`)    REFERENCES `STORE`(`STORE_ID`),
#   CONSTRAINT `FK_RESERVA_MEMBER_MEM_ID`     FOREIGN KEY (`MEM_ID`)      REFERENCES `MEMBER`(`MEM_ID`),
  CONSTRAINT `FK_RESERVA_CODE_CODE_ID`    FOREIGN KEY (`CODE_ID`) REFERENCES `CODE`(`CODE_ID`)
);
-- T-2-2 推播訊息
CREATE TABLE `cga105g2`.`SMESSAGE` (
    `SMESSAGE_ID`	INT 	    NOT NULL  AUTO_INCREMENT	                        COMMENT '編號',
    `SUB_ID`	    INT 	    NOT NULL    	                                    COMMENT '訂閱編號',
    `SMESSAGE_TXET`	VARCHAR(50)	NOT NULL	                                        COMMENT '內容',
    `SMESSAGE_TIME`	DATETIME 	NOT NULL DEFAULT CURRENT_TIMESTAMP	                COMMENT '新增時間',
    PRIMARY KEY (`SMESSAGE_ID`),
    CONSTRAINT `FK_SMESSAGE_SUBSCRIBE_SUB_ID`    FOREIGN KEY (`SUB_ID`)      REFERENCES `SUBSCRIBE`(`SUB_ID`)
);
-- U-2-11 候位明細
CREATE TABLE `cga105g2`.`STANDBY` (
    `STA_ID`	    INT	        NOT NULL AUTO_INCREMENT	                            COMMENT '編號',
    `STORE_ID`	    INT	        NOT NULL    	                                    COMMENT '店家編號',
    `STA_NAME`	    VARCHAR(50)	NOT NULL	                                        COMMENT '姓名',
    `STA_PHONE`	    VARCHAR(50)	NOT NULL	                                        COMMENT '電話',
    `STA_NUMBER`	INT	        NOT NULL	                                        COMMENT '人數',
    `STA_TIME`	    DATETIME 	NOT NULL  DEFAULT CURRENT_TIMESTAMP	                COMMENT '新增時間',
    `STA_STATUS`	TINYINT	    NOT NULL  DEFAULT'0'                                COMMENT '狀態;0:候位中   1:已通知   2:已報到',
    PRIMARY KEY (`STA_ID`),
    CONSTRAINT `FK_STANDBY_STORE_STORE_ID`    FOREIGN KEY (`STORE_ID`)      REFERENCES `STORE`(`STORE_ID`)
);
-- V-2-6 店家訂單
CREATE TABLE `cga105g2`.`ORDER` (
    `ORDER_ID`       INT              NOT NULL   AUTO_INCREMENT                                             COMMENT '編號',
    `MEM_ID`         INT              NOT NULL                                                              COMMENT '會員編號',
    `STORE_ID`       INT              NOT NULL                                                              COMMENT '店家編號',
    `ORDER_PRICE`    INT              NOT NULL                                                              COMMENT '總金額',
    `CODE_ID`        INT                                                                                    COMMENT '優惠券編號',
    `ORDER_FRE`      TINYINT          NOT NULL   DEFAULT'80'                                                COMMENT '運費  80: 超商  120: 宅配',
    `ORDER_FPRICE`   INT              NOT NULL                                                              COMMENT '支付金額',
    `ORDER_TEXT`     VARCHAR(2000)                                                                          COMMENT '備註',
    `ORDER_STATUS`   TINYINT          NOT NULL   DEFAULT'0'                                                 COMMENT '狀態  0: 成立   1: 取消   2: 出貨  3:完成   ',
    `ORDER_TIME`     DATETIME         NOT NULL   DEFAULT CURRENT_TIMESTAMP                                  COMMENT '新增日期',
    `ORDER_OTIME`    DATE                                                                                   COMMENT '出貨日期',
    `ORDER_RTIME`    TIMESTAMP        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP                 COMMENT '修改日期',
    PRIMARY KEY (`ORDER_ID`),
    CONSTRAINT `FK_ORDER_MEMBER_MEM_ID`     FOREIGN KEY (`MEM_ID`)      REFERENCES `MEMBER`(`MEM_ID`),
    CONSTRAINT `FK_ORDER_STORE_STORE_ID`    FOREIGN KEY (`STORE_ID`)    REFERENCES `STORE`(`STORE_ID`),
    CONSTRAINT `FK_ORDER_CODE_CODE_ID`      FOREIGN KEY (`CODE_ID`)     REFERENCES `CODE`(`CODE_ID`)
);
-- W-3-4 點數商品
CREATE TABLE `cga105g2`.`POINT_GOODS` (
    `PD_ID`	    INT	            NOT NULL  AUTO_INCREMENT	                                            COMMENT '編號',
    `PD_IMG`	LONGBLOB	    NOT NULL	                                                            COMMENT '圖片',
    `PD_NAME`	VARCHAR(50)	    NOT NULL	                                                            COMMENT '名稱',
    `PD_PRICE`	INT	            NOT NULL	                                                            COMMENT '單價',
    `PD_TEXT`	VARCHAR(200)	NOT NULL	                                                            COMMENT '介紹',
    `PD_TIME`	DATETIME	    NOT NULL   DEFAULT CURRENT_TIMESTAMP	                                COMMENT '新增日期',
    `PD_RTIME`	TIMESTAMP		           DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP        COMMENT '修改日期',
    `PD_STATUS`	INT	DEFAULT'0' 	                            COMMENT '狀態;0: 下架 1:上架',
    PRIMARY KEY (`PD_ID`)
);
-- X-2-7 店家訂單明細
CREATE TABLE `cga105g2`.`ORDER_DETAIL` (
    ORDER_ID        INT         NOT NULL    COMMENT '訂單編號',
    GOODS_ID        INT         NOT NULL    COMMENT '商品編號',
    DETAIL_QUANTITY INT         NOT NULL    COMMENT '商品數量',
    DETAILPRICE     INT         NOT NULL    COMMENT '商品價格',
    PRIMARY KEY (`ORDER_ID`,`GOODS_ID`),
    CONSTRAINT FK_ORDER_DETAIL_ORDER_ORDER_ID       FOREIGN KEY (`ORDER_ID`)      REFERENCES `ORDER`(`ORDER_ID`),
    CONSTRAINT FK_ORDER_DETAIL_GOODS_GOODS_ID       FOREIGN KEY (`GOODS_ID`)      REFERENCES `GOODS`(`GOODS_ID`)
);
-- Y-3-5 點數訂單
CREATE TABLE `cga105g2`.`POINT_ORDER` (
    `PO_ID`	    INT	            NOT NULL  AUTO_INCREMENT	                            COMMENT '編號',
    `MEM_ID`	INT	            NOT NULL 	                                            COMMENT '會員編號',
    `PD_ID`	    INT	            NOT NULL 	                                            COMMENT '商品編號',
    `PO_PRICE`	INT	            NOT NULL	                                            COMMENT '單價',
    `PO_TEXT`	VARCHAR(2000)		                                                    COMMENT '備註',
    `PO_STATUS`	TINYINT	        NOT NULL  DEFAULT'0'     	                            COMMENT '狀態;0: 成立   1:出貨  2:完成',
    `PO_TIME`	DATETIME	    NOT NULL  DEFAULT CURRENT_TIMESTAMP  	                COMMENT '新增日期',
    `PO_UTIME`	DATETIME		                                                        COMMENT '出貨日期',
    `EMP_ID`	INT				                                                        COMMENT '員工編號 修改NOT NULL',
    PRIMARY KEY (`PO_ID`),
    CONSTRAINT `FK_POINT_ORDER_MEMBER_MEM_ID`       FOREIGN KEY (`MEM_ID`)  REFERENCES `MEMBER`(`MEM_ID`),
    CONSTRAINT `FK_POINT_ORDER_POINT_GOODS_PD_ID`   FOREIGN KEY (`PD_ID`)   REFERENCES `POINT_GOODS`(`PD_ID`),
    CONSTRAINT `FK_POINT_ORDER_EMPLOYEE_EMP_ID`     FOREIGN KEY (`EMP_ID`)  REFERENCES `EMPLOYEE`(`EMP_ID`)
);
-- Z-2-10 訂餐明細
CREATE TABLE `cga105g2`.`RESERVA_DETAIL` (
     `REN_ID`       INT NOT NULL                COMMENT '訂位編號',
     `MEAL_ID`      INT NOT NULL                COMMENT '餐點編號',
     `RD_QUANTITY`  INT NOT NULL                COMMENT '數量',
     `PD_PRICE`     INT NOT NULL                COMMENT '總金額',
     PRIMARY KEY (`REN_ID`,`MEAL_ID`),
     CONSTRAINT `FK_RESERVA_DETAIL_RESERVA_REN_ID`  FOREIGN KEY (`REN_ID`) REFERENCES `RESERVA`(`REN_ID`),
     CONSTRAINT `FK_RESERVA_DETAIL_MEAL_MEAL_ID`    FOREIGN KEY (`MEAL_ID`) REFERENCES `MEAL`(`MEAL_ID`)
);