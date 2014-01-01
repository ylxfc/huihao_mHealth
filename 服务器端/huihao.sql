# Host: localhost  (Version: 5.5.32)
# Date: 2013-07-31 10:02:37
# Generator: MySQL-Front 5.3  (Build 4.4)

/*!40101 SET NAMES utf8 */;

#
# Source for table "admin"
#

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;

#
# Data for table "admin"
#

INSERT INTO `admin` VALUES (1,'xfc','xfc');

#
# Source for table "discount"
#

DROP TABLE IF EXISTS `discount`;
CREATE TABLE `discount` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `discount` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gbk;

#
# Data for table "discount"
#

INSERT INTO `discount` VALUES (1,'全部药品限时7折'),(2,'全部药品限时5折');

#
# Source for table "feedback"
#

DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=gbk;

#
# Data for table "feedback"
#

INSERT INTO `feedback` VALUES (1,'good'),(2,'nice'),(3,'great'),(4,'bad'),(5,'perfect'),(6,'好的'),(7,'1234534'),(8,'3545'),(9,'韩语顾u'),(10,'789uhhgu'),(11,'5765');

#
# Source for table "medicine"
#

DROP TABLE IF EXISTS `medicine`;
CREATE TABLE `medicine` (
  `medId` char(10) NOT NULL DEFAULT '',
  `medName` varchar(20) DEFAULT NULL,
  `medClass` varchar(10) DEFAULT NULL,
  `medPrice` varchar(16) DEFAULT NULL,
  `medDate` varchar(255) DEFAULT NULL,
  `medSpecification` varchar(20) DEFAULT NULL,
  `medIntroducation` varchar(100) DEFAULT NULL,
  `medStock` varchar(11) DEFAULT NULL,
  `medBeenSold` varchar(255) DEFAULT NULL,
  `medDiscount` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`medId`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Data for table "medicine"
#

INSERT INTO `medicine` VALUES ('H10910003','吗丁啉','非处方药','8','2013-7-14','10mg','适应于消化不良，腹胀、嗳气、恶心、呕吐','2560','1340','无'),('H10930064','百多邦','非处方药','15','2013-6-27','5克/支','适用于革兰阳性球菌引起的皮肤感染','2400','1300','无'),('H10940250','白加黑','非处方药','15','2013-2-19','2g×9片','感冒用药','200','100','半价'),('H20020205','卵磷脂络合碘片','处方药','20','2013-3-21','1.5mg×60片','缺碘性甲状腺肿','8565','440','八折'),('H20067998','罗红霉素','处方药','15','2013-7-20','2板x6粒','适用于化脓性链球菌引起的咽炎及扁桃体炎','3400','1200','八五折'),('H20110007','依达拉奉注射液','处方药',' 243.50','2013-1-28','20ml:30mg','用于改善急性脑梗塞所致的神经症状','8974','1640','无'),('H44021351','阿莫西林','处方药','12','2013-4-18',' 0.125g','用以治疗伤寒、其他沙门菌感染和伤寒带菌者','6820','3740','七五折'),('H53022106','皮康王','非处方药','5','2013-7-16','10克/盒','用于体癣手足癣股癣','7500','2400','无'),('J20090130','达克宁','非处方药','28','2013-7-28','15克/支','可局部治疗念珠菌性外阴阴道病和革兰阳性细菌引起的双重感染','8700','4300','无'),('Z11020056','六味地黄丸','非处方药','36','2013-5-26','9g×10丸','用于肾阴亏损头晕耳鸣腰膝酸软骨蒸潮热盗汗遗精','3760','1480','七折'),('Z20013220','江中健胃消食片','非处方药','10','2013-6-23','2.5mg×40片','适用于消化不良症状','10340','2790','九五折'),('Z20050529','板蓝根','非处方药','16','2013-7-4','15粒/盒','用于清热解毒用于病毒性感冒咽喉肿痛','16540','8970','无'),('Z41022325','双黄连口服液','非处方药','6','2013-7-18','10瓶/盒','疏风解表清热解毒','5600','4500','无'),('Z51022515','慢严舒柠','非处方药','24','2013-6-24','2板x9片','用于慢性咽炎引起的咽干，咽痒','4200','3600','无'),('Z52020455','维C银翘片','非处方药','2','2013-7-14','20粒/袋','用于疏风解表清热解毒','6400','2860','九折');

#
# Source for table "postinfo"
#

DROP TABLE IF EXISTS `postinfo`;
CREATE TABLE `postinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `serId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=gbk;

#
# Data for table "postinfo"
#

INSERT INTO `postinfo` VALUES (1,'为感谢广大顾客的厚爱，特举行降价优惠活动。本店所售吗丁啉原价8元每盒，现价6元每盒。广大顾客欲购从速，机不可失，时不再来。','07-15 14:24','012111'),(2,'为感谢广大顾客的厚爱，特举行降价优惠活动。本店所售白加黑原价15元每盒，现价10元每盒。广大顾客欲购从速，机不可失，时不再来。','07-15 14:23','012111'),(3,'为感谢广大顾客的厚爱，特举行降价优惠活动。本店所售六味地黄丸原价36元每盒，现价30元每盒。广大顾客欲购从速，机不可失，时不再来。','07-15 14:25','012112'),(4,'为感谢广大顾客的厚爱，特举行降价优惠活动。本店所售江中健胃消食片原价10元每盒，现价8元每盒。广大顾客欲购从速，机不可失，时不再来。','07-15 14:27','012113'),(5,'为感谢广大顾客的厚爱，特举行降价优惠活动。本店所售阿莫西林原价12元每盒，现价10元每盒。广大顾客欲购从速，机不可失，时不再来。','07-15 14:28','012114'),(6,'为感谢广大顾客的厚爱，特举行降价优惠活动。本店所售卵磷脂络合碘片原价20元每盒，现价16元每盒。广大顾客欲购从速，机不可失，时不再来。','07-16 14:49','012113'),(7,'为感谢广大顾客的厚爱，特举行降价优惠活动。本店所售依达拉奉注射液原价243.5元每盒，现价200元每盒。广大顾客欲购从速，机不可失，时不再来。','07-16 14:56','012115'),(8,'为感谢广大顾客的厚爱，特举行降价优惠活动。本店所售所有药品一律八折，购物满500元还有精美礼品相送哦。广大顾客欲购从速，机不可失，时不再来。','07-16 14:56','012117'),(9,'为感谢广大顾客的厚爱，特举行降价优惠活动。凡在本店购买药品满1000元者一律七折，满500元者一律八五折。广大顾客欲购从速，机不可失，时不再来。','07-16 14:58','012116'),(10,'为感谢广大顾客的厚爱，特举行降价优惠活动。本店所售所有药品一律买五送一，买八送二。广大顾客欲购从速，机不可失，时不再来。','07-16 15:40','012114'),(11,'为感谢广大顾客的厚爱，特举行降价优惠活动。本店所售药品一律八五折，满1000元者即减100元。广大顾客欲购从速，机不可失，时不再来。','07-16 15:41','012115'),(12,'为感谢广大顾客的厚爱，特举行降价优惠活动。凡在本店购买药品满1000元者送200元优惠券，满500元者送100元优惠券。广大顾客欲购从速，机不可失，时不再来。','07-16 16:08','012116'),(13,'为感谢广大顾客的厚爱，特举行降价优惠活动。本店所售所有非处方类药品一律七五折。广大顾客欲购从速，机不可失，时不再来。','07-17 14:43','012117'),(14,'为感谢广大顾客的厚爱，特举行降价优惠活动。本店所售所有药品凡单价超过50元者一律降价10元。广大顾客欲购从速，机不可失，时不再来。','07-17 14:47','012118');

#
# Source for table "servicer"
#

DROP TABLE IF EXISTS `servicer`;
CREATE TABLE `servicer` (
  `serId` char(10) NOT NULL DEFAULT '',
  `serPassword` varchar(20) NOT NULL DEFAULT '',
  `serName` varchar(10) DEFAULT NULL,
  `serSex` varchar(3) DEFAULT NULL,
  `serAge` varchar(11) DEFAULT '0',
  `medId` char(10) DEFAULT NULL,
  `havSignedAgency` varchar(11) DEFAULT '0',
  `havVisited` varchar(255) DEFAULT NULL,
  `serPhone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`serId`),
  KEY `medId` (`medId`),
  CONSTRAINT `servicer_ibfk_1` FOREIGN KEY (`medId`) REFERENCES `medicine` (`medId`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Data for table "servicer"
#

INSERT INTO `servicer` VALUES ('012111','012111','余力','女','20','H10910003','45','60','13245987475'),('012112','012112','丁宇','男','21','H10940250','3','5','13634820998'),('012113','2','小强','女','22','H20020205','8','13','13034793479'),('012114','012114','于越','男','24','H20110007','15','19','13234094058'),('012115','012115','罗鹏','男','23','H44021351','9','13','15839340976'),('012116','012116','太君','女','19','Z11020056','8','11','18932459295'),('012117','012117','大黄','男','22','Z20013220','14','20','18834508043'),('012118','012118','肥羊','女','25','H10940250','14','40','13248679504');

#
# Source for table "agency"
#

DROP TABLE IF EXISTS `agency`;
CREATE TABLE `agency` (
  `ageId` char(10) NOT NULL DEFAULT '',
  `ageName` varchar(50) DEFAULT NULL,
  `ageAddress` varchar(50) DEFAULT NULL,
  `agePhone` char(11) DEFAULT NULL,
  `ageNumberOfWorker` int(11) DEFAULT NULL,
  `preNumber` varchar(255) DEFAULT NULL,
  `medName` varchar(255) DEFAULT NULL,
  `serId` varchar(255) DEFAULT NULL,
  `datetime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ageId`),
  KEY `agency_ibfk_1` (`serId`),
  CONSTRAINT `agency_ibfk_1` FOREIGN KEY (`serId`) REFERENCES `servicer` (`serId`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Data for table "agency"
#

INSERT INTO `agency` VALUES ('1','华泰药店','郑州市','52781094',100,'100','吗丁啉','012111','2013-7-14'),('10','余家头医院','武汉市','23593499',120,'200','白加黑','012111','2013-7-30'),('11','定点医院','沈阳市','23092359',240,NULL,NULL,NULL,NULL),('12','晨光药店','北京市','14239324',80,'400','白加黑','012112','2013-6-20'),('13','丁羽大药房','荆州市','21423492',95,'1','白加黑','012113','2013－5－6'),('14','老百姓大药房','西安市','11213023',110,'100','板蓝根','012118','2013-6-28'),('15','中心医院','上海市','29473234',150,'88','阿莫西林','012111','2013-5-8'),('17','武汉第一医院','武汉市','98403011',99,'100','白加黑','012113','2013－7－31'),('18','武汉第三医院','武汉市','85946226',98,'500','白加黑','012113','2013－7－31'),('2','武汉铁路医院','武汉市','66148512',96,'66','阿莫西林','012111','2013-5-3'),('3','百姓大药房','天津市','12345678',150,'200','卵磷脂络合碘片','012113','2013-4-16'),('4','同仁堂','北京市','23484596',320,'4','依达拉奉注射液','012114','2013-5-28'),('5','春天药店','上海市','73921023',125,'1','百多邦','012113','2013－6－7'),('6','阳光大药房','成都市','12458956',140,'560','六味地黄丸','012116','2013-7-7'),('7','人民药店','深圳市','87349012',240,'1','江中健胃消食片','012117','2013-7-25'),('8','定于大药房','沈阳市','23478765',120,NULL,NULL,NULL,NULL),('9','解放军总医院','北京市','32352993',230,NULL,NULL,NULL,NULL);

#
# Source for table "preorder"
#

DROP TABLE IF EXISTS `preorder`;
CREATE TABLE `preorder` (
  `serId` char(10) NOT NULL DEFAULT '',
  `ageId` char(10) NOT NULL DEFAULT '',
  `medId` char(10) NOT NULL DEFAULT '',
  `preNumber` varchar(12) DEFAULT NULL,
  `reaNumber` varchar(12) DEFAULT NULL,
  `preTime` date NOT NULL DEFAULT '0000-00-00',
  `preGetGoodsTime` date NOT NULL DEFAULT '0000-00-00',
  PRIMARY KEY (`serId`),
  KEY `ageId` (`ageId`),
  KEY `medId` (`medId`),
  CONSTRAINT `preorder_ibfk-2` FOREIGN KEY (`ageId`) REFERENCES `agency` (`ageId`),
  CONSTRAINT `preorder_ibfk_1` FOREIGN KEY (`serId`) REFERENCES `servicer` (`serId`),
  CONSTRAINT `preorder_ibfk_3` FOREIGN KEY (`medId`) REFERENCES `medicine` (`medId`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Data for table "preorder"
#

INSERT INTO `preorder` VALUES ('012111','1','H10910003','100','80','2013-06-06','2013-07-06'),('012112','12','H10940250','400','360','2013-05-28','2013-06-28'),('012113','3','H20020205','200','150','2013-05-18','2013-06-18'),('012114','4','H20110007','320','260','2013-06-20','2013-07-20'),('012115','5','H44021351','420','360','2013-02-14','2013-04-14'),('012116','6','Z11020056','560','480','2013-06-16','2013-08-16'),('012117','7','Z20013220','240','160','2013-07-12','2013-08-12'),('012118','14','Z20050529','100','80','2013-07-04','2013-08-04');

#
# Source for table "workplan012111"
#

DROP TABLE IF EXISTS `workplan012111`;
CREATE TABLE `workplan012111` (
  `ageName` varchar(50) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `visited` varchar(2) DEFAULT NULL,
  `preorder` varchar(20) DEFAULT NULL,
  `medName` varchar(20) DEFAULT NULL,
  `preNumber` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Data for table "workplan012111"
#

INSERT INTO `workplan012111` VALUES ('华泰药店','2013-7-14','是','是','吗丁啉','100'),('定于大药房','2013-7-25','是','否','null','0'),('解放军总医院','2013-7-25','是','否','null','0'),('余家头医院','2013-7-26','是','否','null','0'),('定点医院','2013-7-30','否','否','null','0'),('余家头医院','2013-7-30','是','是','白加黑','200'),('中心医院','2013-5-8','是','是','阿莫西林','88'),('武汉铁路医院','2013-5-3','是','是','阿莫西林','66'),('丁羽大药房','2013-5-6','是','否','null','0'),('春天药店','2013-6-7','是','否','null','0'),('丁羽大药房','2013－5－6','0','1','白加黑','100'),('春天药店','2013－6－7','0','1','百多邦','500');

#
# Source for table "workplan012112"
#

DROP TABLE IF EXISTS `workplan012112`;
CREATE TABLE `workplan012112` (
  `ageName` varchar(50) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `visited` varchar(2) DEFAULT NULL,
  `preorder` varchar(20) DEFAULT NULL,
  `medName` varchar(20) DEFAULT NULL,
  `preNumber` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Data for table "workplan012112"
#

INSERT INTO `workplan012112` VALUES ('晨光药店','2013-6-20','是','是','白加黑','400');

#
# Source for table "workplan012113"
#

DROP TABLE IF EXISTS `workplan012113`;
CREATE TABLE `workplan012113` (
  `ageName` varchar(50) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `visited` varchar(2) DEFAULT NULL,
  `preorder` varchar(20) DEFAULT NULL,
  `medName` varchar(20) DEFAULT NULL,
  `preNumber` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Data for table "workplan012113"
#

INSERT INTO `workplan012113` VALUES ('百姓大药房','2013-4-16','是','是','卵磷脂络合碘片','200'),('丁羽大药房','2013－5－6','0','1','白加黑','100'),('春天药店','2013－6－7','0','1','百多邦','500'),('丁羽大药房','2013－5－6','是','是','白加黑','100'),('春天药店','2013－6－7','是','是','百多邦','500'),('武汉第一医院','2013－7－31','是','是','白加黑','100'),('武汉第三医院','2013－7－31','是','是','白加黑','500');

#
# Source for table "workplan012114"
#

DROP TABLE IF EXISTS `workplan012114`;
CREATE TABLE `workplan012114` (
  `ageName` varchar(50) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `visited` varchar(2) DEFAULT NULL,
  `preorder` varchar(20) DEFAULT NULL,
  `medName` varchar(20) DEFAULT NULL,
  `preNumber` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Data for table "workplan012114"
#

INSERT INTO `workplan012114` VALUES ('同仁堂','2013-5-28','是','是','依达拉奉注射液','320');

#
# Source for table "workplan012115"
#

DROP TABLE IF EXISTS `workplan012115`;
CREATE TABLE `workplan012115` (
  `ageName` varchar(50) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `visited` varchar(2) DEFAULT NULL,
  `preorder` varchar(20) DEFAULT NULL,
  `medName` varchar(20) DEFAULT NULL,
  `preNumber` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Data for table "workplan012115"
#

INSERT INTO `workplan012115` VALUES ('春天药店','2013-6-24','是','是','阿莫西林','420');

#
# Source for table "workplan012116"
#

DROP TABLE IF EXISTS `workplan012116`;
CREATE TABLE `workplan012116` (
  `ageName` varchar(50) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `visited` varchar(2) DEFAULT NULL,
  `preorder` varchar(20) DEFAULT NULL,
  `medName` varchar(20) DEFAULT NULL,
  `preNumber` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Data for table "workplan012116"
#

INSERT INTO `workplan012116` VALUES ('阳光大药房','2013-7-7','是','是','六味地黄丸','560');

#
# Source for table "workplan012117"
#

DROP TABLE IF EXISTS `workplan012117`;
CREATE TABLE `workplan012117` (
  `ageName` varchar(50) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `visited` varchar(2) DEFAULT NULL,
  `preorder` varchar(20) DEFAULT NULL,
  `medName` varchar(20) DEFAULT NULL,
  `preNumber` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Data for table "workplan012117"
#

INSERT INTO `workplan012117` VALUES ('人民药店','2013-7-25','是','是','江中健胃消食片','240');

#
# Source for table "workplan012118"
#

DROP TABLE IF EXISTS `workplan012118`;
CREATE TABLE `workplan012118` (
  `ageName` varchar(50) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `visited` varchar(2) DEFAULT NULL,
  `preorder` varchar(20) DEFAULT NULL,
  `medName` varchar(20) DEFAULT NULL,
  `preNumber` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Data for table "workplan012118"
#

INSERT INTO `workplan012118` VALUES ('老百姓大药房','2013-6-28','是','是','板蓝根','100');
