-- --------------------------------------------------------
-- 호스트:                          stg-yswa-kr-practice-db-master.mariadb.database.azure.com
-- 서버 버전:                        10.3.23-MariaDB - MariaDB Server
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- s09p31d210 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `s09p31d210` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `s09p31d210`;

-- 테이블 s09p31d210.account 구조 내보내기
CREATE TABLE IF NOT EXISTS `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `language` varchar(255) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `refresh_token` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 s09p31d210.account:~13 rows (대략적) 내보내기
INSERT INTO `account` (`id`, `language`, `nickname`, `password`, `refresh_token`, `role`, `username`) VALUES
	(2, 'python', 'jinugi', '{bcrypt}$2a$10$lHd28AVhIvRSGRJZIyMGjeGtBZQ3iFMBfq3N2UQXXI/HRIWIVjmVS', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE3MDEzMzQ1MjN9.hCEw6-ueDc8a6SnVH_e5shAq4owsGZ8QnNYvwFF6AIoSebvNNy3hDRTXJTXEnW_TgZpcqjZoAQfprt9v9-2xgQ', 'USER', 'jinuk'),
	(5, 'C++', 'asdasd', '{bcrypt}$2a$10$94iEp92IAk5vxi/Mzbmx7epRlKxl9LZbVW/ziaEApPFqWOwpZh4Ou', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE3MDEzMzIwODJ9.EsbvN5eyklKX13eFC3lHQHGiBWxJ5yVO8rwYgAOXMrBTgIx0haM4TzQYQP9bC2D4cQoAsORuzfZb1KNfhPd96g', 'USER', 'test'),
	(6, 'C++', 'test', '{bcrypt}$2a$10$DDmbDx85QgFYEDvU4kmWMOx0yc8ptAy0tpa0fL/atRC/xtyfqYpy.', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE2OTk5NDg0NDV9.3A0fUNHi2fiojQaQPokUaULmmVNxAzjAo6NJdkx3jcBsfsprRknaSsUjz2j2cYJSQpRnI1Md6aHSxon3_1OqaA', 'USER', 'thswnsqo99'),
	(7, 'Java', 'haha', '{bcrypt}$2a$10$rxG9vxspIel.YgYYxpXHauR3xIAsl7fcjnU4z75aIsTr3rC6qRMlC', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE3MDEzMDQwNTh9.-bw99t5Y33yl9konmLrs5s7zICACj4B1nB1tW5L5E79LV1fMZnPqWDnohvr8YM7NcRbdabG9Fs93Ry4zQjMXJw', 'USER', 'wlsdnr214'),
	(8, 'Python', 'hello', '{bcrypt}$2a$10$jh2dABVbcopYeDWAjmadRO3tUjlUvXSudyU0PLcQ0LSaPhLIrSjIS', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE2OTk5NDExODZ9.F_T0_Onq30x22uTuTvFOuO350jgWo3m2fvUga7oGbqdGJYfCpZBlZn8ENChcYpNbLABgBrR41IIltdSDDitTyw', 'USER', 'jinugi'),
	(9, 'Python', '슬기공듀', '{bcrypt}$2a$10$mZL05CJGHEyZ/lIs7IaU2eQ/swV4N9HS23M3ZbE39EQSEvhmI5nCm', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE3MDEzMjg3Nzd9.GwfAdSuAJbQUvqxp4KH119n_FIN81rVxiFYP7KZAdmZTt7DobM_TLeKOTqD6o_SsxPl8IQggygTrHby2oaJWEA', 'USER', 'seulgi'),
	(10, 'C / C++', '준빠이', '{bcrypt}$2a$10$XREVforHWNJlWmijxzHHHeY7oA2jgI8x/sDEsviPkOQ2W5E.vCSQa', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE2OTk5NTIzMzF9.O5CzftJKE2UrVvkeG03DzWQqD4LdUE2e04qYEHtm19G-fIdqAoRN5AmDk8uD74AaMlvrlLsDNJdQ3h5H4hCNRw', 'USER', 'junbai'),
	(11, 'Python', '구미대장', '{bcrypt}$2a$10$VTyM5aRxf256Bf.qUyiP5OP3WXsi00aKb0sqHa0FDBuxZJxGBYhKG', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE3MDAwMzI4NTl9.lWpNqTo6MCssIg2EyH35fIGGgoV1KHYX2a5Fo9OM2o_TpmLeyrjzPiCUJB3j4H21Wgq6_TBurffxEvj9A1RExg', 'USER', 'ssafy'),
	(21, 'Java', 'ssafy', '{bcrypt}$2a$10$uehD/JpUELtuzpYPPlaT1eQv8/WqmDneL0yBOrO5y2zRi6spkSSuC', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE3MDEzMzEzMjR9.ozTIn7Wuz34iL-1S5NZ9jtoULapgpBBCP4mIFpPm5oCJcjgY_HygSLBtJEFIkaON74diGkUOx4kHOKb6pBS8JQ', 'USER', 'ssafy1117'),
	(22, 'Python', 'sumsum', '{bcrypt}$2a$10$.RrejD3J3WF94dlFXYK9z.Et9Z907tAVpMVmNlKNDZM.ih2THI42y', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE3MDEzMzM2Nzh9.vH-JLY4bpc4QXi-2OjvqZha9WhYZOXS1J6F3yoUQFzo-6SOeQJ71-6YJXimdjVoKbptbmClc_s4CC3dWb4MySg', 'USER', 'sumin'),
	(23, 'Python', 'happycat', '{bcrypt}$2a$10$mt8Rh3s3ZK9.T6rjO/ySge.cWjJe3OXcygSMSsZleiSWU4O7TC1eK', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE3MDEzMzMyMDd9.f9fuV9jk3na9XJRYI6h73vkLVyaGFzM0SiCeNeqsi-XwqjsVVA0e-8tUo6HxGtF_nYr79fjzSUqrrGuyXCw3dA', 'USER', 'happy1234'),
	(24, 'Python', '동글동글너구리', '{bcrypt}$2a$10$PALkRe5LNdLaZhqbhBes8eMDDJ48vzKb793UUwip1Q3xSbeUWcHEO', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE3MDEzMzkwODh9.sN0CjaPC9h1pFOuwOh0VHB3k5Ib_3WaTBgeLQVF0RNVPQ_4B2OHvLaPxQbfhJeaFQmnSD6kzjoRgjiBhQ9rVKA', 'USER', 'haeum'),
	(25, 'Java', '명장텐하흐', '{bcrypt}$2a$10$C6/iMiYOM2VoO1LsvXW2d.qvmVo2pApvSxuuBU9nv6ldIcN5U/MsS', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE3MDEzMzM5MDh9.hX0D3SlU9YBw1E5nS0iMarNJ9A6InEH-lv26GMtAMbsmtRFDWlnKvxHTQS42k8Y5tdkMfsObaD9nELuNetmmQQ', 'USER', 'sonny');

-- 테이블 s09p31d210.character 구조 내보내기
CREATE TABLE IF NOT EXISTS `character` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exp` int(11) NOT NULL,
  `gender` char(1) DEFAULT 'M',
  `img_path` varchar(255) DEFAULT NULL,
  `level` int(11) NOT NULL DEFAULT 1,
  `name` varchar(255) DEFAULT NULL,
  `point` int(11) NOT NULL,
  `savepoint` varchar(255) DEFAULT NULL,
  `story_progress` int(11) NOT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK340k49qlwjxblqptlsmblc2bt` (`account_id`),
  CONSTRAINT `FK340k49qlwjxblqptlsmblc2bt` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 s09p31d210.character:~8 rows (대략적) 내보내기
INSERT INTO `character` (`id`, `exp`, `gender`, `img_path`, `level`, `name`, `point`, `savepoint`, `story_progress`, `account_id`) VALUES
	(1, 1, 'M', 'images/character/character_m.png', 1, '구미대장', 1200, 'LobbyMap', 1, 5),
	(2, 2, 'M', 'images/character/character_m.png', 1, 'csdw', 3, 'wdf', 2, 6),
	(27, 6, 'M', 'images/character/character_m.png', 1, '아되나요', 0, 'OfficeMap', 0, 2),
	(32, 1, 'M', 'images/character/character_m.png', 1, '캐릭터이름뭘로하지', 0, 'OfficeMap', 0, 21),
	(33, 0, 'F', 'images/character/character_m.png', 1, '숨숨', 0, 'LobbyMap', 0, 22),
	(34, 0, 'M', 'images/character/character_m.png', 1, '해피해피', 0, 'LobbyMap', 0, 23),
	(35, 0, 'M', 'images/character/character_m.png', 1, '더보기맹구', 0, 'LobbyMap', 0, 25),
	(36, 0, 'F', 'images/character/character_m.png', 1, '신입사원 황하음', 0, 'LobbyMap', 0, 24);

-- 테이블 s09p31d210.inventory 구조 내보내기
CREATE TABLE IF NOT EXISTS `inventory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  `is_equipping` bit(1) NOT NULL,
  `character_id` bigint(20) DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4yw26gc4trtq3cpqi9ubrucen` (`character_id`),
  KEY `FKrflym5lxj6xhmu4ok3ohmun5a` (`item_id`),
  CONSTRAINT `FK4yw26gc4trtq3cpqi9ubrucen` FOREIGN KEY (`character_id`) REFERENCES `character` (`id`),
  CONSTRAINT `FKrflym5lxj6xhmu4ok3ohmun5a` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 s09p31d210.inventory:~4 rows (대략적) 내보내기
INSERT INTO `inventory` (`id`, `count`, `is_equipping`, `character_id`, `item_id`) VALUES
	(1, 1, b'0', 1, 5),
	(2, 1, b'0', 1, 6),
	(3, 1, b'0', 2, 15),
	(4, 1, b'0', 2, 8);

-- 테이블 s09p31d210.item 구조 내보내기
CREATE TABLE IF NOT EXISTS `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `img_path` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `item_type_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_lcsp6a1tpwb8tfywqhrsm2uvg` (`name`),
  KEY `FKh7kyk389qj2m5chaa0njsq601` (`item_type_id`),
  CONSTRAINT `FKh7kyk389qj2m5chaa0njsq601` FOREIGN KEY (`item_type_id`) REFERENCES `item_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 s09p31d210.item:~18 rows (대략적) 내보내기
INSERT INTO `item` (`id`, `description`, `img_path`, `name`, `item_type_id`) VALUES
	(1, '기분이 좋아지는 달달한 초콜릿이다.', 'images/item/food/chocolate.png', '초콜릿', 1),
	(2, '방금 만든 따뜻한 햄버거이다.', 'images/item/food/hamburger.png', '햄버거', 1),
	(3, '치즈가 쭈욱 늘어나는 맛있는 피자다.', 'images/item/food/pizza.png', '피자', 1),
	(4, '톡쏘는 시원함을 주는 콜라다.', 'images/item/food/coke.png', '콜라', 1),
	(5, '마음이 따뜻해지는 뜨거운 커피다.', 'images/item/food/coffee.png', '커피', 1),
	(6, '정신이 번쩍 드는 에너지드링크다.', 'images/item/food/energy-drink.png', '에너지드링크', 1),
	(7, '조금만 만져도 부서질 것 같은 키보드다.', 'images/item/weapon/Keyboard_1.png', '문방구 키보드', 2),
	(8, '깔끔한 디자인을 가진 기계식 키보드다.', 'images/item/weapon/Keyboard_2.png', '기계식 키보드', 2),
	(9, '좋은 반응속도를 가진 게이밍 키보드다.', 'images/item/weapon/Keyboard_3.png', '게이밍 키보드', 2),
	(10, '금방이라도 부서질 것 같은 마우스다.', 'images/item/weapon/Mouse_1.gif', '문방구 마우스', 2),
	(11, '좋은 반응속도를 가진 게이밍 마우스다.', 'images/item/weapon/Mouse_2.gif', '게이밍 마우스', 2),
	(12, '커스텀 특별제작으로 만들어진 마우스다.', 'images/item/weapon/Mouse_3.gif', '한정판 마우스', 2),
	(13, 'LTE 사용이 불가능한 구형 3G 피처폰이다.', 'images/item/weapon/Phone_1.png', '3G 피처폰', 2),
	(14, '버벅임이 심한 보급형 스마트폰이다.', 'images/item/weapon/Phone_2.png', '보급형 스마트폰', 2),
	(15, '빠르고 강력한 성능을 가진 스마트폰이다.', 'images/item/weapon/Phone_3.png', '플래그쉽 스마트폰', 2),
	(16, '언제 선이 끊어질지 모르는 유선 이어폰이다.', 'images/item/helmet/earphone.gif', '유선 이어폰', 2),
	(17, '편리하고 가벼운 무선 이어폰이다.', 'images/item/helmet/wireless_earphones.gif', '무선 이어폰', 2),
	(18, '노이즈 캔슬링 기능이 제공되는 헤드폰이다.', 'images/item/helmet/headphone.png', '헤드폰', 2);

-- 테이블 s09p31d210.item_favorite 구조 내보내기
CREATE TABLE IF NOT EXISTS `item_favorite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` bigint(20) DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfohmbeb80l40is55pq0b36rc5` (`account_id`),
  KEY `FKr5xnkgjna1dtion52i5a77wl6` (`item_id`),
  CONSTRAINT `FKfohmbeb80l40is55pq0b36rc5` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FKr5xnkgjna1dtion52i5a77wl6` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=304 DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 s09p31d210.item_favorite:~6 rows (대략적) 내보내기
INSERT INTO `item_favorite` (`id`, `account_id`, `item_id`) VALUES
	(16, 2, 2),
	(239, 5, 14),
	(270, 5, 15),
	(300, 5, 5),
	(301, 5, 7),
	(302, 5, 8);

-- 테이블 s09p31d210.item_type 구조 내보내기
CREATE TABLE IF NOT EXISTS `item_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1pvqr9hc1t8cgbbhc5434lfff` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 s09p31d210.item_type:~4 rows (대략적) 내보내기
INSERT INTO `item_type` (`id`, `name`) VALUES
	(4, '기타'),
	(1, '소모품'),
	(2, '장비'),
	(3, '퀘스트');

-- 테이블 s09p31d210.level 구조 내보내기
CREATE TABLE IF NOT EXISTS `level` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `atk` int(11) NOT NULL,
  `def` int(11) NOT NULL,
  `hp` int(11) NOT NULL,
  `mp` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `need_exp` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 s09p31d210.level:~10 rows (대략적) 내보내기
INSERT INTO `level` (`id`, `atk`, `def`, `hp`, `mp`, `name`, `need_exp`) VALUES
	(1, 10, 10, 100, 50, '인턴', 100),
	(2, 20, 20, 200, 100, '사원', 200),
	(3, 30, 30, 300, 200, '대리', 300),
	(4, 40, 40, 400, 250, '과장', 400),
	(5, 50, 50, 500, 300, '차장', 500),
	(6, 60, 60, 600, 350, '부장', 600),
	(7, 70, 70, 700, 400, '본부장', 700),
	(8, 80, 80, 800, 450, '이사', 800),
	(9, 90, 90, 900, 500, '전무', 900),
	(10, 100, 100, 1000, 600, '사장', 999999999);

-- 테이블 s09p31d210.monster 구조 내보내기
CREATE TABLE IF NOT EXISTS `monster` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `hp` int(11) NOT NULL,
  `img_path` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2d63i8eekohj7pjaxphrdbpva` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 s09p31d210.monster:~4 rows (대략적) 내보내기
INSERT INTO `monster` (`id`, `description`, `hp`, `img_path`, `name`) VALUES
	(1, '잠재적인 위험을 안고 있는 상태인 몬스터', 50, 'images/monster/warn.gif', 'WARN'),
	(2, '오류가 발생했지만, 실행하는 것에는 문제가 없는 몬스터', 100, 'images/monster/error.gif', 'ERROR'),
	(3, '애플리케이션을 중지해야 할 심각한 오류를 가진 몬스터', 200, 'images/monster/fatal.gif', 'FATAL'),
	(4, '요청한 몬스터의 정보를 찾을 수 없습니다.', 1000, 'images/monster/404.gif', '404');

-- 테이블 s09p31d210.monster_favorite 구조 내보내기
CREATE TABLE IF NOT EXISTS `monster_favorite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` bigint(20) DEFAULT NULL,
  `monster_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7albqfx786othh8q0h5dehqr4` (`account_id`),
  KEY `FKt5prthc2xd430fn6w4nyiba2a` (`monster_id`),
  CONSTRAINT `FK7albqfx786othh8q0h5dehqr4` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FKt5prthc2xd430fn6w4nyiba2a` FOREIGN KEY (`monster_id`) REFERENCES `monster` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 s09p31d210.monster_favorite:~4 rows (대략적) 내보내기
INSERT INTO `monster_favorite` (`id`, `account_id`, `monster_id`) VALUES
	(25, 5, 3),
	(46, 24, 3),
	(48, 2, 1),
	(49, 2, 3);

-- 테이블 s09p31d210.quest 구조 내보내기
CREATE TABLE IF NOT EXISTS `quest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `story_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdewevaa8c2mhu5qb52xkib3fn` (`story_id`),
  CONSTRAINT `FKdewevaa8c2mhu5qb52xkib3fn` FOREIGN KEY (`story_id`) REFERENCES `story` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 s09p31d210.quest:~20 rows (대략적) 내보내기
INSERT INTO `quest` (`id`, `description`, `name`, `story_id`) VALUES
	(1, '드디어 첫 출근을 했다!! 사수님이 알려주신대로 따라가보자.', '사수를 따라가기', 1),
	(2, '정과장님이 시키신 커피 심부름... 회사에 이런 일을 하러 온 건 아니지만 그래도 열심히 하자!!', '커피 만들어 오기', 1),
	(3, '커피 타오기보다는 좀 더 가치있는 일... 이겠지??', '문서 파쇄하기', 1),
	(4, '내려가서 택배를 가져오자!! 아직은 첫 날이니 이런 일을 하는게 당연하지 않을까?', '택배 수령해오기', 1),
	(5, '열정적인 모습을 보여주기 위해!! 하는 김에 잘못 들고온 택배도 올바른 곳으로 가져다 주자!', '택배 배달 가기', 1),
	(6, '드디어 첫 출근을 했다!! 사수님이 알려주신대로 따라가보자.', '사수를 따라가기', 2),
	(7, '정과장님이 시키신 커피 심부름... 회사에 이런 일을 하러 온 건 아니지만 그래도 열심히 하자!!', '커피 만들어 오기', 2),
	(8, '커피 타오기보다는 좀 더 가치있는 일... 이겠지??', '문서 파쇄하기', 2),
	(9, '내려가서 택배를 가져오자!! 아직은 첫 날이니 이런 일을 하는게 당연하지 않을까?', '택배 수령해오기', 2),
	(10, '지하실에는 뭐가 있을까? 한 번만 갔다올까?', '지하실 가보기', 2),
	(11, '사수님은 조금 이상한 사람같다. 난데없이 이런 어두운 곳에서 저런 농담이라니...', '전등 켜기', 3),
	(14, '다른 층에가서 서류 결제를 받아오자!', '서류 결제 받아오기', 5),
	(15, '다른 직원들의 컴퓨터를 수리해주자! 잔심부름보다 이런 작업이 더 낫다!!', '컴퓨터 수리', 5),
	(16, '흠 이 회사에서 다른 정보를 얻을 수 없을까?', '정보 수집(1/3)', 5),
	(17, '흠 이 회사에서 다른 정보를 얻을 수 없을까?', '정보 수집(2/3)', 5),
	(18, '흠 이 회사에서 다른 정보를 얻을 수 없을까?', '정보 수집(3/3)', 5),
	(19, '갑자기 부장님이 몬스터라니?! 부장님을 물리치고 정시퇴근...이 아니라 파일을 지키자!!', '부장님과의 전투', 6),
	(20, '이상해져버린 회사에서 서버실까지 무시하 가자!!', '서버실로', 7),
	(21, '사람들을 위해서, 나를 위해서, 탈출을 위해서!!', '마지막 전투', 8),
	(22, '미니 게임이지만 그 결과는 미니하지 않다... 최선을 다하자!!', '미니게임 on', 8);

-- 테이블 s09p31d210.quest_success 구조 내보내기
CREATE TABLE IF NOT EXISTS `quest_success` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `character_id` bigint(20) DEFAULT NULL,
  `quest_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK44qmoc6ucttv9y9alkn7dyono` (`character_id`),
  KEY `FKdiv648j6sbngnfj6yvj050r35` (`quest_id`),
  CONSTRAINT `FK44qmoc6ucttv9y9alkn7dyono` FOREIGN KEY (`character_id`) REFERENCES `character` (`id`),
  CONSTRAINT `FKdiv648j6sbngnfj6yvj050r35` FOREIGN KEY (`quest_id`) REFERENCES `quest` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 s09p31d210.quest_success:~0 rows (대략적) 내보내기

-- 테이블 s09p31d210.skill 구조 내보내기
CREATE TABLE IF NOT EXISTS `skill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `img_path` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_5ljf2l2h4odhtxrsuohlro4ir` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 s09p31d210.skill:~4 rows (대략적) 내보내기
INSERT INTO `skill` (`id`, `description`, `img_path`, `name`) VALUES
	(1, '분노의 타이핑으로 인해 아드레날린이 분비되어 공격속도와 이동속도가 증가한다.', 'images/skill/angry-typing.png', '앵그리 타이핑'),
	(2, '마우스 광클로 인해 커다란 마우스 포인터가 발사된다.', 'images/skill/big-pointer.png', '빅 포인터'),
	(3, '스마트폰 과다 사용으로 발생한 전자파가 발사된다.', 'images/skill/electric-shock.png', '일렉트릭 쇼크'),
	(4, '응축된 데이터를 에너지로 만들어서 발사한다.', 'images/skill/bigdata-wave.png', '빅데이터 웨이브');

-- 테이블 s09p31d210.skill_favorite 구조 내보내기
CREATE TABLE IF NOT EXISTS `skill_favorite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` bigint(20) DEFAULT NULL,
  `skill_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9ar7hswkfl4r6it99qj298phi` (`account_id`),
  KEY `FK442iilamvjlp5fv27y2qbw9ap` (`skill_id`),
  CONSTRAINT `FK442iilamvjlp5fv27y2qbw9ap` FOREIGN KEY (`skill_id`) REFERENCES `skill` (`id`),
  CONSTRAINT `FK9ar7hswkfl4r6it99qj298phi` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 s09p31d210.skill_favorite:~6 rows (대략적) 내보내기
INSERT INTO `skill_favorite` (`id`, `account_id`, `skill_id`) VALUES
	(1, 2, 1),
	(47, 5, 1),
	(48, 5, 4),
	(49, 5, 2),
	(51, 24, 3),
	(52, 2, 3);

-- 테이블 s09p31d210.story 구조 내보내기
CREATE TABLE IF NOT EXISTS `story` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 s09p31d210.story:~9 rows (대략적) 내보내기
INSERT INTO `story` (`id`, `description`, `name`) VALUES
	(1, '튜토리얼 챕터', '챕터 1 - 1'),
	(2, '새로운 시작', '챕터 2 - 1'),
	(3, '충격적인 지하실의 풍경', '챕터 2 - 2'),
	(4, '이 세계의 비밀', '챕터 2 - 3'),
	(5, '혼자만의 비밀', '챕터 2 - 4'),
	(6, '무엇이 올바른 길일까', '챕터 2 - 5'),
	(7, '서버실을 찾아서', '챕터 2 - 6'),
	(8, '밖으로', '챕터 2 - 7'),
	(9, '에필로그 - 해피엔딩', '챕터 2 - 8');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
