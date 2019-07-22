-- MySQL dump 10.13  Distrib 5.7.10, for Win64 (x86_64)
--
-- Host: localhost    Database: will_parry
-- ------------------------------------------------------
-- Server version	5.7.10-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account_form`
--

DROP TABLE IF EXISTS `account_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_form` (
  `username` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `department` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `privelege` int(11) DEFAULT NULL,
  `number_registered` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `account_payments`
--

DROP TABLE IF EXISTS `account_payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_payments` (
  `id_pay` bigint(20) NOT NULL,
  `des_of_service` varchar(100) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `cost_of_service` bigint(20) NOT NULL,
  `amount_deposited` bigint(20) NOT NULL,
  `receivables` bigint(20) NOT NULL,
  `signed_by` varchar(50) NOT NULL,
  `receipt_no` varchar(100) DEFAULT NULL,
  `date_paid` date NOT NULL,
  PRIMARY KEY (`id_pay`),
  CONSTRAINT `account_payments_ibfk_1` FOREIGN KEY (`id_pay`) REFERENCES `patient_registry` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `account_records`
--

DROP TABLE IF EXISTS `account_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_records` (
  `accountid` bigint(20) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `sexchoice` varchar(50) NOT NULL,
  `dateofbirth` varchar(100) NOT NULL,
  `address` varchar(1000) NOT NULL,
  `stateoforigin` varchar(1000) NOT NULL,
  `languagespoken` varchar(100) DEFAULT NULL,
  `nationality` varchar(100) DEFAULT NULL,
  `department` varchar(100) NOT NULL,
  `specialty` varchar(100) NOT NULL,
  `religion` varchar(100) DEFAULT NULL,
  `previousemp` varchar(1000) DEFAULT NULL,
  `reasonsforlearning` varchar(10000) DEFAULT NULL,
  `dateofemployment` varchar(100) NOT NULL,
  `nextofkin` varchar(100) DEFAULT NULL,
  `ralkin` varchar(100) DEFAULT NULL,
  `phone` varchar(100) NOT NULL,
  `phoneofrel` varchar(100) DEFAULT NULL,
  `privelege` int(11) NOT NULL DEFAULT '100',
  `status` varchar(100) NOT NULL DEFAULT 'enable',
  `password` varchar(100) NOT NULL DEFAULT 'password@1',
  `marital_status` varchar(200) NOT NULL,
  PRIMARY KEY (`accountid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ante_exam_update`
--

DROP TABLE IF EXISTS `ante_exam_update`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ante_exam_update` (
  `patientid` bigint(20) NOT NULL,
  `examdate` date DEFAULT NULL,
  `weight` varchar(100) DEFAULT NULL,
  `dedema` varchar(100) DEFAULT NULL,
  `presentation` varchar(100) DEFAULT NULL,
  `ga_weeks` varchar(100) DEFAULT NULL,
  `bpmm` varchar(100) DEFAULT NULL,
  `sfh` varchar(100) DEFAULT NULL,
  `attitude` varchar(100) DEFAULT NULL,
  `ultrasound` varchar(100) DEFAULT NULL,
  `temp` varchar(100) DEFAULT NULL,
  `bp` varchar(100) DEFAULT NULL,
  `lie` varchar(100) DEFAULT NULL,
  `foetal_heart` varchar(100) DEFAULT NULL,
  `clinical_info` varchar(100) DEFAULT NULL,
  `sign_by` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `antenatal_records`
--

DROP TABLE IF EXISTS `antenatal_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `antenatal_records` (
  `dateofbooking` date DEFAULT NULL,
  `patientid` bigint(20) NOT NULL,
  `surname` varchar(100) DEFAULT NULL,
  `othername` varchar(100) DEFAULT NULL,
  `age` varchar(100) DEFAULT NULL,
  `para` varchar(100) DEFAULT NULL,
  `lcb` varchar(100) DEFAULT NULL,
  `gravida` varchar(100) DEFAULT NULL,
  `special_circum` varchar(100) DEFAULT NULL,
  `antenatal_code` varchar(100) DEFAULT NULL,
  `type_of_delivery` varchar(100) DEFAULT NULL,
  `lmp` date DEFAULT NULL,
  `edd` date DEFAULT NULL,
  `ga_booking` varchar(100) DEFAULT NULL,
  `og_surgical` varchar(100) DEFAULT NULL,
  `menarche` varchar(100) DEFAULT NULL,
  `mens_duration` varchar(100) DEFAULT NULL,
  `prev_midhistroy` varchar(100) DEFAULT NULL,
  `prev_surgery` varchar(100) DEFAULT NULL,
  `labour` varchar(100) DEFAULT NULL,
  `sign_by` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `attendance_roster`
--

DROP TABLE IF EXISTS `attendance_roster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attendance_roster` (
  `id_sn` bigint(20) NOT NULL,
  `surname` varchar(200) NOT NULL,
  `othernames` varchar(200) NOT NULL,
  `date` date NOT NULL,
  `time` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bed_records`
--

DROP TABLE IF EXISTS `bed_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bed_records` (
  `ward` int(11) NOT NULL,
  `bed` int(11) NOT NULL,
  `occupied` varchar(100) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `birth_info`
--

DROP TABLE IF EXISTS `birth_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `birth_info` (
  `baby_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `our_ref` varchar(100) DEFAULT NULL,
  `place_of_birth` varchar(100) NOT NULL,
  `name_of_father` varchar(100) DEFAULT NULL,
  `occupation_of_father` varchar(100) DEFAULT NULL,
  `tribe_of_father` varchar(100) NOT NULL,
  `local_gov_origin` varchar(100) NOT NULL,
  `time_of_delivery` varchar(50) NOT NULL,
  `name_of_mother` varchar(100) NOT NULL,
  `tribe_of_mother` varchar(100) NOT NULL,
  `sex_of_baby` varchar(100) NOT NULL,
  `weight` int(11) DEFAULT NULL,
  `date_of_delivery` date DEFAULT NULL,
  `sign` varchar(100) NOT NULL,
  `perm_address` varchar(100) NOT NULL,
  PRIMARY KEY (`baby_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `caput_records`
--

DROP TABLE IF EXISTS `caput_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `caput_records` (
  `patient_id` bigint(20) NOT NULL,
  `date` date DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  `moulding` varchar(100) DEFAULT NULL,
  `caput` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cervical_records`
--

DROP TABLE IF EXISTS `cervical_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cervical_records` (
  `patient_id` bigint(20) NOT NULL,
  `cervical_dilation` varchar(100) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `comp_rad_type`
--

DROP TABLE IF EXISTS `comp_rad_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comp_rad_type` (
  `priradtype` varchar(100) DEFAULT NULL,
  `radtype` varchar(100) DEFAULT NULL,
  `cost` bigint(20) DEFAULT NULL,
  `pricost` bigint(20) DEFAULT NULL,
  UNIQUE KEY `priradtype` (`priradtype`),
  UNIQUE KEY `radtype` (`radtype`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `company_details`
--

DROP TABLE IF EXISTS `company_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company_details` (
  `company_name` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `fax` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `zip` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `cpname` varchar(100) DEFAULT NULL,
  `cpnumber` varchar(100) DEFAULT NULL,
  `cpmobile` varchar(100) DEFAULT NULL,
  `cpemail` varchar(100) DEFAULT NULL,
  `cpnote` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cost_of_service`
--

DROP TABLE IF EXISTS `cost_of_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cost_of_service` (
  `nameof_service` varchar(100) DEFAULT NULL,
  `hmo` bigint(20) DEFAULT NULL,
  `company_cost` bigint(20) DEFAULT NULL,
  `private_patient` bigint(20) DEFAULT NULL,
  `family_cost` bigint(20) DEFAULT NULL,
  `individual_cost` bigint(20) DEFAULT NULL,
  UNIQUE KEY `nameof_service` (`nameof_service`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `costof_service`
--

DROP TABLE IF EXISTS `costof_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `costof_service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeof_service` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  `AGE` int(11) NOT NULL,
  `ADDRESS` char(25) DEFAULT NULL,
  `SALARY` decimal(18,2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `department_records`
--

DROP TABLE IF EXISTS `department_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department_records` (
  `departmentid` int(11) NOT NULL AUTO_INCREMENT,
  `department` varchar(100) NOT NULL,
  `authority` int(11) DEFAULT NULL,
  PRIMARY KEY (`departmentid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `discharge_patient`
--

DROP TABLE IF EXISTS `discharge_patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discharge_patient` (
  `id_sn` bigint(20) NOT NULL,
  `surname` varchar(200) NOT NULL,
  `othername` varchar(200) NOT NULL,
  `cost_of_service` bigint(20) NOT NULL,
  `amount_deoposited` bigint(20) NOT NULL,
  `receivables` bigint(20) NOT NULL,
  `status` varchar(200) NOT NULL,
  `discharge_status` varchar(200) NOT NULL DEFAULT 'Not Discharged'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `doc_and_patient`
--

DROP TABLE IF EXISTS `doc_and_patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doc_and_patient` (
  `patient_id` bigint(20) NOT NULL,
  `doc_name` varchar(200) NOT NULL,
  `date` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `doc_pres`
--

DROP TABLE IF EXISTS `doc_pres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doc_pres` (
  `patient_id` varchar(100) NOT NULL,
  `prescrip` varchar(100) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `doctor` varchar(100) DEFAULT NULL,
  `surname` varchar(100) DEFAULT NULL,
  `othernames` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dpspecialist`
--

DROP TABLE IF EXISTS `dpspecialist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dpspecialist` (
  `department` varchar(100) NOT NULL,
  `specialist` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `drug_category`
--

DROP TABLE IF EXISTS `drug_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `drug_category` (
  `category` varchar(100) DEFAULT NULL,
  UNIQUE KEY `dcindex` (`category`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `drug_details`
--

DROP TABLE IF EXISTS `drug_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `drug_details` (
  `drug_categroy` varchar(100) DEFAULT NULL,
  `dosage` varchar(100) DEFAULT NULL,
  `drug` varchar(100) DEFAULT NULL,
  `form` varchar(100) DEFAULT NULL,
  `packet_size` bigint(20) DEFAULT NULL,
  `re_orderlimit` bigint(20) DEFAULT NULL,
  `supplier` varchar(100) DEFAULT NULL,
  `unit_price` bigint(20) DEFAULT NULL,
  `cost_price` bigint(20) DEFAULT NULL,
  `manufacturer` varchar(100) DEFAULT NULL,
  `qty_left` bigint(20) DEFAULT NULL,
  `mfg_date` date DEFAULT NULL,
  `exp_date` date DEFAULT NULL,
  `nafdac` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `drug_sales`
--

DROP TABLE IF EXISTS `drug_sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `drug_sales` (
  `patient_id` varchar(100) DEFAULT NULL,
  `patient_type` varchar(100) DEFAULT NULL,
  `drug` varchar(100) DEFAULT NULL,
  `quantity` bigint(20) NOT NULL DEFAULT '0',
  `unit_cost` bigint(20) NOT NULL,
  `selling_cost` bigint(20) DEFAULT NULL,
  `totalcost` bigint(20) NOT NULL,
  `date` date DEFAULT NULL,
  `sign_by` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `exam_diag`
--

DROP TABLE IF EXISTS `exam_diag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam_diag` (
  `patient_id` varchar(100) DEFAULT NULL,
  `resp_system` varchar(100) DEFAULT NULL,
  `abdomenn` varchar(100) DEFAULT NULL,
  `nerveous` varchar(100) DEFAULT NULL,
  `musk` varchar(100) DEFAULT NULL,
  `sign_by` varchar(100) DEFAULT NULL,
  `history` varchar(100) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `features` varchar(100) DEFAULT NULL,
  `examination` varchar(100) DEFAULT NULL,
  `diagnosis` varchar(100) DEFAULT NULL,
  `signed_by` varchar(100) DEFAULT NULL,
  UNIQUE KEY `patient_id` (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fluid_records`
--

DROP TABLE IF EXISTS `fluid_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fluid_records` (
  `patient_id` bigint(20) NOT NULL,
  `fluid` varchar(100) DEFAULT NULL,
  `drug` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `foetal_heart`
--

DROP TABLE IF EXISTS `foetal_heart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `foetal_heart` (
  `patient_id` bigint(20) NOT NULL,
  `foetal_heart_rate` varchar(100) DEFAULT NULL,
  `liquor_time` varchar(100) DEFAULT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hmo_details`
--

DROP TABLE IF EXISTS `hmo_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hmo_details` (
  `hmo_name` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `fax` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `zip` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `cpname` varchar(100) DEFAULT NULL,
  `cpnumber` varchar(100) DEFAULT NULL,
  `cpmobile` varchar(100) DEFAULT NULL,
  `cpemail` varchar(100) DEFAULT NULL,
  `cpnote` varchar(100) DEFAULT NULL,
  UNIQUE KEY `hmo_name` (`hmo_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hmo_group`
--

DROP TABLE IF EXISTS `hmo_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hmo_group` (
  `group_name` varchar(200) NOT NULL,
  `hmo_name` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hmo_patients`
--

DROP TABLE IF EXISTS `hmo_patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hmo_patients` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `othername` varchar(100) DEFAULT NULL,
  `dateofbirth` date NOT NULL,
  `pacategory` varchar(100) DEFAULT NULL,
  `patype` varchar(100) NOT NULL,
  `nationality` varchar(100) DEFAULT NULL,
  `occupation` varchar(100) DEFAULT NULL,
  `addressofkin` varchar(100) DEFAULT NULL,
  `dateofreg` date NOT NULL,
  `nextofkin` varchar(100) DEFAULT NULL,
  `religion` varchar(100) DEFAULT NULL,
  `placeofwork` varchar(100) DEFAULT NULL,
  `sex` varchar(20) NOT NULL,
  `id_sn` bigint(20) NOT NULL,
  `phoneno` varchar(100) DEFAULT NULL,
  `bloodgroup` varchar(50) DEFAULT NULL,
  `SPO2` varchar(100) DEFAULT NULL,
  `image_url` varchar(100) DEFAULT NULL,
  `phoneofkin` varchar(100) DEFAULT NULL,
  `others` varchar(100) DEFAULT NULL,
  `gimage_url` varchar(100) DEFAULT NULL,
  `family_name` varchar(100) DEFAULT NULL,
  `payment_status` varchar(100) DEFAULT NULL,
  `hmo_name` varchar(100) DEFAULT NULL,
  `company_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `uk_patient_registry` (`id_sn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ins_test`
--

DROP TABLE IF EXISTS `ins_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ins_test` (
  `hmo` int(11) DEFAULT NULL,
  `individual` int(11) DEFAULT NULL,
  `company` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lab_patient`
--

DROP TABLE IF EXISTS `lab_patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lab_patient` (
  `id_sn` varchar(200) NOT NULL,
  `surname` varchar(200) DEFAULT NULL,
  `othername` varchar(200) DEFAULT NULL,
  `lab_type` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lab_test_type`
--

DROP TABLE IF EXISTS `lab_test_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lab_test_type` (
  `test_type` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `laboratory`
--

DROP TABLE IF EXISTS `laboratory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `laboratory` (
  `labname` varchar(100) NOT NULL,
  `labtype` varchar(100) NOT NULL,
  `pricost` bigint(20) DEFAULT NULL,
  `companycost` bigint(20) DEFAULT NULL,
  `hmocost` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `laboratory_test`
--

DROP TABLE IF EXISTS `laboratory_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `laboratory_test` (
  `test_type` varchar(100) DEFAULT NULL,
  `test_name` varchar(100) DEFAULT NULL,
  `normal_value` varchar(100) DEFAULT NULL,
  `amount` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `labour_records`
--

DROP TABLE IF EXISTS `labour_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `labour_records` (
  `patient_name` varchar(100) NOT NULL,
  `foetalheartrate` varchar(100) DEFAULT NULL,
  `liquortime` varchar(100) DEFAULT NULL,
  `foetaldate` varchar(100) DEFAULT NULL,
  `cervicaldilation` varchar(100) DEFAULT NULL,
  `foetaltime` varchar(100) DEFAULT NULL,
  `caput` varchar(100) DEFAULT NULL,
  `caputdate` varchar(100) DEFAULT NULL,
  `moulding` varchar(100) DEFAULT NULL,
  `ucmins` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`patient_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `maternal_records`
--

DROP TABLE IF EXISTS `maternal_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `maternal_records` (
  `patient_id` bigint(20) NOT NULL,
  `maternal_id` varchar(50) DEFAULT NULL,
  `matenal_pulse` varchar(50) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `opcl_data`
--

DROP TABLE IF EXISTS `opcl_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `opcl_data` (
  `id_sn` bigint(20) NOT NULL,
  `surname` varchar(200) NOT NULL,
  `othername` varchar(200) NOT NULL,
  `surgery` varchar(200) NOT NULL,
  `data` varchar(5000) NOT NULL,
  `bloodgroup` varchar(200) NOT NULL,
  `sign_by` varchar(200) NOT NULL,
  `age` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `opd_service`
--

DROP TABLE IF EXISTS `opd_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `opd_service` (
  `patient_name` varchar(100) NOT NULL,
  `patientnumber` varchar(100) DEFAULT NULL,
  `complains` varchar(1000) DEFAULT NULL,
  `doctor` varchar(100) DEFAULT NULL,
  `registration` varchar(100) DEFAULT NULL,
  `bpsystolic` varchar(100) DEFAULT NULL,
  `bpdystolic` varchar(100) DEFAULT NULL,
  `temperature` varchar(100) DEFAULT NULL,
  `height` varchar(100) DEFAULT NULL,
  `SPO2` varchar(100) DEFAULT NULL,
  `pulse` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`patient_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `oxytocin_record`
--

DROP TABLE IF EXISTS `oxytocin_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oxytocin_record` (
  `patient_id` bigint(20) NOT NULL,
  `unit` varchar(50) DEFAULT NULL,
  `drops` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `palabtest`
--

DROP TABLE IF EXISTS `palabtest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `palabtest` (
  `patient_id` varchar(100) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `lab_no` varchar(100) DEFAULT NULL,
  `surname` varchar(100) DEFAULT NULL,
  `othername` varchar(100) DEFAULT NULL,
  `sex` varchar(50) DEFAULT NULL,
  `specimen` varchar(100) DEFAULT NULL,
  `request_by` varchar(100) DEFAULT NULL,
  `clinical_info` varchar(1000) DEFAULT NULL,
  `test_type` varchar(100) DEFAULT NULL,
  `test_name` varchar(100) DEFAULT NULL,
  `normal_value` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `patient_records`
--

DROP TABLE IF EXISTS `patient_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_records` (
  `dateofreg` varchar(100) DEFAULT NULL,
  `nationality` varchar(100) DEFAULT NULL,
  `occupation` varchar(100) DEFAULT NULL,
  `nextofkin` varchar(100) DEFAULT NULL,
  `xraynumber` varchar(100) DEFAULT NULL,
  `dob` varchar(100) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `civilstate` varchar(100) DEFAULT NULL,
  `patienttype` varchar(100) DEFAULT NULL,
  `religion` varchar(100) DEFAULT NULL,
  `placeofwork` varchar(100) DEFAULT NULL,
  `addressofkin` varchar(100) DEFAULT NULL,
  `phonenumber` varchar(100) DEFAULT NULL,
  `imageurl` varchar(100) DEFAULT NULL,
  `amount_deposited` bigint(20) DEFAULT NULL,
  `total_cost` bigint(20) DEFAULT NULL,
  `debt` bigint(20) DEFAULT NULL,
  `company_name` varchar(100) DEFAULT NULL,
  `radiology_service` varchar(1000) DEFAULT NULL,
  `laboratory_service` varchar(1000) DEFAULT NULL,
  `pharmacy_service` varchar(1000) DEFAULT NULL,
  `treatment` varchar(1000) DEFAULT NULL,
  `treatment_service` varchar(1000) DEFAULT NULL,
  `consultation_service` varchar(1000) DEFAULT NULL,
  `mortuary` varchar(1000) DEFAULT NULL,
  `HMO_name` varchar(1000) DEFAULT NULL,
  `patient_name` varchar(1000) DEFAULT NULL,
  `SN` varchar(1000) DEFAULT NULL,
  `in_patient` varchar(100) DEFAULT NULL,
  `out_patient` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `patient_registry`
--

DROP TABLE IF EXISTS `patient_registry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_registry` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(200) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `othername` varchar(200) NOT NULL,
  `dateofbirth` date NOT NULL,
  `pacategory` varchar(100) DEFAULT NULL,
  `patype` varchar(100) NOT NULL,
  `nationality` varchar(100) DEFAULT NULL,
  `occupation` varchar(100) DEFAULT NULL,
  `addressofkin` varchar(100) DEFAULT NULL,
  `dateofreg` date NOT NULL,
  `nextofkin` varchar(100) DEFAULT NULL,
  `religion` varchar(100) DEFAULT NULL,
  `placeofwork` varchar(100) DEFAULT NULL,
  `sex` varchar(20) NOT NULL,
  `id_sn` bigint(20) NOT NULL,
  `phoneno` varchar(100) DEFAULT NULL,
  `bloodgroup` varchar(50) DEFAULT NULL,
  `SPO2` varchar(100) DEFAULT NULL,
  `image_url` varchar(100) DEFAULT NULL,
  `phoneofkin` varchar(100) DEFAULT NULL,
  `others` varchar(100) DEFAULT NULL,
  `gimage_url` varchar(100) DEFAULT NULL,
  `family_name` varchar(100) DEFAULT NULL,
  `payment_status` varchar(100) DEFAULT NULL,
  `hmo_name` varchar(100) DEFAULT NULL,
  `company_name` varchar(100) DEFAULT NULL,
  `hmo_group` varchar(200) DEFAULT NULL,
  `age` int(11) NOT NULL,
  `reg_by` varchar(200) NOT NULL,
  `p_doctor` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `uk_patient_registry` (`id_sn`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `patient_schedule`
--

DROP TABLE IF EXISTS `patient_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_schedule` (
  `patient_id` varchar(100) DEFAULT NULL,
  `patient_name` varchar(100) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `purpose` varchar(100) DEFAULT NULL,
  `details` varchar(100) DEFAULT NULL,
  `scheduled_by` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `assign_to` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `payment_table`
--

DROP TABLE IF EXISTS `payment_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_table` (
  `id_sn` bigint(20) NOT NULL,
  `amount_deposited` bigint(20) NOT NULL,
  `payment_type` varchar(200) DEFAULT NULL,
  `receipt_no` bigint(20) NOT NULL,
  `sign_by` varchar(200) DEFAULT NULL,
  `date_v` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pharmacy_sales`
--

DROP TABLE IF EXISTS `pharmacy_sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pharmacy_sales` (
  `total_sales` bigint(20) NOT NULL DEFAULT '100'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `radiological`
--

DROP TABLE IF EXISTS `radiological`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `radiological` (
  `radname` varchar(100) DEFAULT NULL,
  `radtype` varchar(100) DEFAULT NULL,
  `pricost` bigint(20) DEFAULT NULL,
  `companycost` bigint(20) DEFAULT NULL,
  `hmocost` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource` (
  `items` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `service_data`
--

DROP TABLE IF EXISTS `service_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service_data` (
  `c_id` bigint(20) DEFAULT NULL,
  `service_name` varchar(200) DEFAULT NULL,
  `service_type` varchar(200) DEFAULT NULL,
  `service_value` varchar(500) DEFAULT NULL,
  `date_v` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `service_rendered`
--

DROP TABLE IF EXISTS `service_rendered`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service_rendered` (
  `id` bigint(20) NOT NULL,
  `service_type` varchar(100) DEFAULT NULL,
  `cost` bigint(20) NOT NULL,
  `date_v` date DEFAULT NULL,
  `major_type` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `smajor_service`
--

DROP TABLE IF EXISTS `smajor_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `smajor_service` (
  `id` int(11) NOT NULL,
  `family` bigint(20) DEFAULT NULL,
  `individual` bigint(20) DEFAULT NULL,
  `hmo` bigint(20) DEFAULT NULL,
  `company` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `store_item`
--

DROP TABLE IF EXISTS `store_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store_item` (
  `date` date NOT NULL,
  `item_type` varchar(200) NOT NULL,
  `receipt_no` varchar(200) NOT NULL,
  `quantity` bigint(20) NOT NULL,
  `rate` bigint(20) NOT NULL,
  `total` bigint(20) NOT NULL,
  `received_by` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `store_items`
--

DROP TABLE IF EXISTS `store_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store_items` (
  `date` date DEFAULT NULL,
  `store` varchar(100) DEFAULT NULL,
  `supplier` varchar(100) DEFAULT NULL,
  `item_supplied` varchar(100) DEFAULT NULL,
  `received_by` varchar(100) DEFAULT NULL,
  `receipt_no` int(11) DEFAULT NULL,
  `quantity_left` int(11) DEFAULT NULL,
  `rate` int(11) DEFAULT NULL,
  `total` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `store_used`
--

DROP TABLE IF EXISTS `store_used`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store_used` (
  `date` date DEFAULT NULL,
  `item_type` varchar(200) DEFAULT NULL,
  `quantity` bigint(20) NOT NULL,
  `issued_by` varchar(200) NOT NULL,
  `request_by` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `supplier_details`
--

DROP TABLE IF EXISTS `supplier_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier_details` (
  `supplier_name` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `fax` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `pname` varchar(100) DEFAULT NULL,
  `pemail` varchar(100) DEFAULT NULL,
  `pnote` varchar(100) DEFAULT NULL,
  `item_supplied` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `suppliers`
--

DROP TABLE IF EXISTS `suppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `suppliers` (
  `supplier_name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `surgery_patient`
--

DROP TABLE IF EXISTS `surgery_patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `surgery_patient` (
  `id_sn` bigint(20) NOT NULL,
  `surname` varchar(200) NOT NULL,
  `othername` varchar(200) NOT NULL,
  `surgery_type` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `templates`
--

DROP TABLE IF EXISTS `templates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `templates` (
  `service_name` varchar(100) NOT NULL,
  `service_type` varchar(100) NOT NULL,
  `service_container` varchar(100) NOT NULL,
  `lab_or_surgery` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `urine_records`
--

DROP TABLE IF EXISTS `urine_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `urine_records` (
  `patient_Id` bigint(20) NOT NULL,
  `acet` varchar(100) DEFAULT NULL,
  `port` varchar(100) DEFAULT NULL,
  `vol` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `uterine_record`
--

DROP TABLE IF EXISTS `uterine_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uterine_record` (
  `patient_id` bigint(20) NOT NULL,
  `mins` varchar(50) DEFAULT NULL,
  `duration` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vital_signs`
--

DROP TABLE IF EXISTS `vital_signs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vital_signs` (
  `patient_id` varchar(100) DEFAULT NULL,
  `surname` varchar(100) DEFAULT NULL,
  `othernames` varchar(100) DEFAULT NULL,
  `complain` varchar(1000) DEFAULT NULL,
  `doctor` varchar(1000) DEFAULT NULL,
  `respiration` varchar(100) DEFAULT NULL,
  `bp_lic` varchar(100) DEFAULT NULL,
  `time` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `pulse` varchar(100) DEFAULT NULL,
  `sign_by` varchar(100) DEFAULT NULL,
  `bp` varchar(100) DEFAULT NULL,
  `b_group` varchar(100) DEFAULT NULL,
  `temp` varchar(100) DEFAULT NULL,
  `height` varchar(100) DEFAULT NULL,
  `weight` varchar(100) DEFAULT NULL,
  `spo2` varchar(100) DEFAULT NULL,
  `admit` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vol`
--

DROP TABLE IF EXISTS `vol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vol` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ward_and_bed`
--

DROP TABLE IF EXISTS `ward_and_bed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ward_and_bed` (
  `ward` bigint(20) NOT NULL,
  `bed` bigint(20) NOT NULL,
  `status` varchar(200) NOT NULL DEFAULT 'vacant',
  `date` date DEFAULT NULL,
  `patient_id` bigint(20) DEFAULT '0',
  `name` varchar(200) NOT NULL DEFAULT 'None'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ward_bed`
--

DROP TABLE IF EXISTS `ward_bed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ward_bed` (
  `number_of_bed` int(11) NOT NULL,
  `number_of_ward` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-26  1:24:45
