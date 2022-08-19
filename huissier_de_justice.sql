-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 19 août 2022 à 21:35
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `huissier_de_justice`
--

DELIMITER $$
--
-- Procédures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `log_insert` (IN `user` INT, IN `nomtable` VARCHAR(60), IN `id` VARCHAR(10), IN `details` VARCHAR(30))  BEGIN
INSERT INTO logs (user_id,operation,table_name,id_table,details) VALUES (user, "insert",nomtable,id,details);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `log_login` (IN `user` INT, IN `details` VARCHAR(100))  BEGIN
INSERT INTO logs (user_id,operation,table_name,id_table,details) VALUES (user, "login","#","#",details);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `log_update` (IN `user` INT, IN `nomtable` VARCHAR(60), IN `id` VARCHAR(10), IN `details` VARCHAR(30))  BEGIN
INSERT INTO logs (user_id,operation,table_name,id_table,details) VALUES (user, "update", nomtable , id,details);
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `actes`
--

CREATE TABLE `actes` (
  `num_acte` varchar(30) NOT NULL,
  `date_acte` date NOT NULL,
  `type_acte` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `notaire` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `id` int(11) NOT NULL,
  `num_bon` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `action`
--

CREATE TABLE `action` (
  `id` int(11) NOT NULL,
  `type` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `id_notification` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `date` date NOT NULL,
  `id_oblig` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `action`
--

INSERT INTO `action` (`id`, `type`, `id_notification`, `date`, `id_oblig`) VALUES
(15, 'تنفيذ', '234/21', '2021-03-16', 114),
(17, 'تنفيذ', '234/21', '2021-03-18', 118);

-- --------------------------------------------------------

--
-- Structure de la table `bon_acte`
--

CREATE TABLE `bon_acte` (
  `num_bon` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `nom_notaire` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `type_acte` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `date` date NOT NULL,
  `created_at` date NOT NULL DEFAULT current_timestamp(),
  `prix` int(11) NOT NULL,
  `somme` int(11) NOT NULL,
  `num` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `id` int(11) NOT NULL,
  `last_updated` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `bon_acte`
--

INSERT INTO `bon_acte` (`num_bon`, `nom_notaire`, `type_acte`, `date`, `created_at`, `prix`, `somme`, `num`, `id`, `last_updated`) VALUES
('3/22', 'الاستاذ قويدر محمد', 'عقد بيع', '2021-03-01', '2022-03-25', 1000, 7000, '2/21', 1, 1),
('khfl', 'jghflkj', 'flk', '2022-01-11', '2022-06-28', 2233, 2333, 'lmfhdk', 2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `bon_apercus`
--

CREATE TABLE `bon_apercus` (
  `id` int(11) NOT NULL,
  `num_bon` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `prix` int(11) NOT NULL,
  `status` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `date_fin` date DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `somme` int(11) NOT NULL DEFAULT 4500,
  `last_updated` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `bon_apercus`
--

INSERT INTO `bon_apercus` (`id`, `num_bon`, `prix`, `status`, `date_fin`, `created_at`, `somme`, `last_updated`) VALUES
(3, '5/22', 1500, 'غير منجزة', NULL, '2022-06-29 15:55:18', 4000, 2),
(4, 'dfml', 233, 'hkgh', '2022-02-02', '2022-06-29 15:55:18', 4500, 2);

-- --------------------------------------------------------

--
-- Structure de la table `bon_apercu_parorders`
--

CREATE TABLE `bon_apercu_parorders` (
  `id` int(11) NOT NULL,
  `num_bon` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `prix` int(11) NOT NULL,
  `status` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `date_fin` date DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `num_order` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `date_order` date NOT NULL,
  `commission` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `somme` int(11) NOT NULL DEFAULT 4500,
  `last_updated` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `bon_apercu_parorders`
--

INSERT INTO `bon_apercu_parorders` (`id`, `num_bon`, `prix`, `status`, `date_fin`, `created_at`, `num_order`, `date_order`, `commission`, `somme`, `last_updated`) VALUES
(1, '12/21', 4500, '', '2022-03-29', '2022-06-29 15:55:18', '234', '2021-03-20', 'محكمة : البليدة', 4500, 2),
(2, '6/22', 4500, 'منجزة', '2022-03-30', '2022-06-29 15:55:18', '43/22', '2022-03-16', 'محكمة : البليدة', 4500, 2);

-- --------------------------------------------------------

--
-- Structure de la table `bon_associations`
--

CREATE TABLE `bon_associations` (
  `id` int(11) NOT NULL,
  `num_bon` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `prix` int(11) NOT NULL,
  `status` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `date_fin` date DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `somme` int(11) NOT NULL DEFAULT 4500,
  `last_updated` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `bon_associations`
--

INSERT INTO `bon_associations` (`id`, `num_bon`, `prix`, `status`, `date_fin`, `created_at`, `somme`, `last_updated`) VALUES
(3, '7/22', 5000, 'منجزة', '2022-03-31', '2022-06-29 15:55:18', 7000, 2);

-- --------------------------------------------------------

--
-- Structure de la table `bon_autres`
--

CREATE TABLE `bon_autres` (
  `id` int(11) NOT NULL,
  `num_bon` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `prix` int(11) NOT NULL,
  `status` varchar(15) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'غير منجزة',
  `date_fin` date DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `somme` int(11) NOT NULL DEFAULT 3000,
  `last_updated` tinyint(1) NOT NULL DEFAULT 0,
  `joint_table` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `type_pv` varchar(30) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `bon_autres`
--

INSERT INTO `bon_autres` (`id`, `num_bon`, `prix`, `status`, `date_fin`, `created_at`, `somme`, `last_updated`, `joint_table`, `type_pv`) VALUES
(1, '15/22', 2500, 'غير منجزة', NULL, '2022-08-19 15:00:54', 2500, 0, 'none', 'حساب فردي'),
(3, '16/22', 2000, 'غير منجزة', NULL, '2022-08-19 18:12:09', 1500, 0, 'orders', 'حجز على منقول');

-- --------------------------------------------------------

--
-- Structure de la table `bon_excuses`
--

CREATE TABLE `bon_excuses` (
  `id` int(11) NOT NULL,
  `num_bon` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `prix` int(11) NOT NULL,
  `status` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `date_fin` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `date_marquage` date DEFAULT NULL,
  `type` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `somme` int(11) NOT NULL DEFAULT 1870,
  `last_updated` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `bon_excuses`
--

INSERT INTO `bon_excuses` (`id`, `num_bon`, `prix`, `status`, `date_fin`, `created_at`, `date_marquage`, `type`, `somme`, `last_updated`) VALUES
(8, '20/21', 0, '', NULL, '2022-06-29 15:55:19', '2021-12-03', 'إعذار', 2000, 2),
(9, '2/22', 2000, '', NULL, '2022-07-06 19:20:52', '2022-03-12', 'رد على إعذار', 2000, 1);

-- --------------------------------------------------------

--
-- Structure de la table `bon_mandat`
--

CREATE TABLE `bon_mandat` (
  `num_bon` varchar(10) NOT NULL,
  `prix` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `num_mandat` varchar(20) NOT NULL,
  `type` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `commission` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_nopad_ci NOT NULL,
  `date` date NOT NULL,
  `service` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `created_at` date NOT NULL DEFAULT current_timestamp(),
  `somme` int(11) NOT NULL DEFAULT 1500,
  `last_updated` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `bon_mandat`
--

INSERT INTO `bon_mandat` (`num_bon`, `prix`, `id`, `num_mandat`, `type`, `commission`, `date`, `service`, `created_at`, `somme`, `last_updated`) VALUES
('21/21', 1500, 1, '12453444', 'جوابية', 'مجلس قضاء : البليدة', '2021-12-25', 'أمانة الضبط', '2021-12-14', 1500, 2);

-- --------------------------------------------------------

--
-- Structure de la table `bon_orders`
--

CREATE TABLE `bon_orders` (
  `id` int(11) NOT NULL,
  `num_bon` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `prix` int(11) NOT NULL,
  `status` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `date_fin` date DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `num_order` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `date_order` date NOT NULL,
  `commission` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `somme` int(11) NOT NULL DEFAULT 1500,
  `type` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `last_updated` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `bon_orders`
--

INSERT INTO `bon_orders` (`id`, `num_bon`, `prix`, `status`, `date_fin`, `created_at`, `num_order`, `date_order`, `commission`, `somme`, `type`, `last_updated`) VALUES
(8, '1/22', 2000, '', NULL, '2022-06-29 15:55:19', '12/22', '2022-03-11', 'محكمة : البليدة', 3000, 'أمر إستعجالي', 2),
(9, '8/22', 1500, '', NULL, '2022-06-29 15:55:19', '78/22', '2022-03-31', 'مجلس قضاء : البليدة', 1500, 'أمر إستعجالي', 2);

-- --------------------------------------------------------

--
-- Structure de la table `bon_provisions`
--

CREATE TABLE `bon_provisions` (
  `id` int(11) NOT NULL,
  `num_bon` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `prix` int(11) NOT NULL,
  `status` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `date_fin` date DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `num_indice` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `num_table` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `date` date NOT NULL,
  `commission` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `spec` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `somme` int(11) DEFAULT 1500,
  `last_updated` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `bon_provisions`
--

INSERT INTO `bon_provisions` (`id`, `num_bon`, `prix`, `status`, `date_fin`, `created_at`, `num_indice`, `num_table`, `date`, `commission`, `type`, `spec`, `somme`, `last_updated`) VALUES
(22, '1/21', 13560, 'منجزة', '2021-03-10', '2022-06-29 15:55:19', '123/21', '56/21', '2021-03-11', 'مجلس قضاء : البليدة', 'حكم', 'الغرفة المدنية', 13560, 2),
(23, '2/21', 6780, 'تم إرسال رسالة', NULL, '2022-06-30 08:33:10', '23/21', '443/21', '2021-03-11', 'محكمة : البليدة', 'حكم', 'القسم الاجتماعي', 6780, 1),
(24, '3/21', 20340, '', NULL, '2022-06-29 15:55:19', '324/21', '45/21', '2021-03-06', 'محكمة : بوفاريك', 'حكم', 'القسم المدني', 25000, 2),
(25, '4/21', 6780, '', NULL, '2022-06-29 15:55:19', '23/21', '366/21', '2021-03-18', 'محكمة : بوفاريك', 'حكم', 'القسم المدني', 6780, 2),
(28, '16/21', 1500, '', NULL, '2022-06-29 15:55:19', '232/21', '1234/21', '2021-09-24', 'محكمة : حسين داي', 'حكم', 'القسم المدني', 1500, 2),
(30, '10/22', 3000, '', NULL, '2022-07-06 19:20:52', '665/22', '673/22', '2022-06-23', 'مجلس قضاء : البليدة', 'قرار', 'غرفة شؤون الأسرة', 3000, 1);

-- --------------------------------------------------------

--
-- Structure de la table `bon_rqst`
--

CREATE TABLE `bon_rqst` (
  `id` int(11) NOT NULL,
  `num_bon` varchar(10) NOT NULL,
  `prix` int(11) NOT NULL,
  `num_rqst` varchar(20) NOT NULL,
  `type` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `commission` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_czech_ci NOT NULL,
  `date` date NOT NULL,
  `created_at` date NOT NULL DEFAULT current_timestamp(),
  `somme` int(11) NOT NULL,
  `last_updated` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `bon_rqst`
--

INSERT INTO `bon_rqst` (`id`, `num_bon`, `prix`, `num_rqst`, `type`, `commission`, `date`, `created_at`, `somme`, `last_updated`) VALUES
(1, '4/22', 1000, '1234556', 'إدخال في الخصومة', 'المحكمة الإدارية : البليدة', '2022-03-24', '2022-03-28', 1500, 1);

-- --------------------------------------------------------

--
-- Structure de la table `bon_seances`
--

CREATE TABLE `bon_seances` (
  `id` int(11) NOT NULL,
  `num_bon` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `prix` int(11) NOT NULL,
  `status` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `date_fin` date DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `num_seance` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `commission` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `date_seance` date NOT NULL,
  `date_report` date DEFAULT NULL,
  `date_report2` date DEFAULT NULL,
  `somme` int(11) NOT NULL DEFAULT 3000,
  `last_updated` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `bon_seances`
--

INSERT INTO `bon_seances` (`id`, `num_bon`, `prix`, `status`, `date_fin`, `created_at`, `num_seance`, `type`, `commission`, `date_seance`, `date_report`, `date_report2`, `somme`, `last_updated`) VALUES
(53, '8/21', 3000, 'منجزة', '2021-03-16', '2022-06-29 15:55:20', '1234567', 'القسم المدني', 'محكمة : البليدة', '2021-03-27', '0000-00-00', '0000-00-00', 3000, 2),
(54, '15/21', 7200, 'تم إرسال رسالة', '2021-04-21', '2022-06-30 08:55:46', '786/21', 'القسم المدني', 'مجلس قضاء : البليدة', '2021-04-22', NULL, NULL, 19200, 1),
(64, '17/21', 0, '', NULL, '2022-06-29 15:55:20', '2353/21', 'القسم المدني', 'محكمة : البليدة', '2021-09-23', NULL, NULL, 3000, 2),
(66, '18/21', 1000, '', NULL, '2022-07-06 19:20:52', '3224/21', 'القسم الاجتماعي', 'محكمة : البليدة', '2021-10-01', '2021-10-09', '2021-10-22', 3000, 1),
(72, '19/21', 3000, '', NULL, '2022-06-29 15:55:20', '1234/21', 'القسم المدني', 'محكمة : البليدة', '2021-11-06', '2021-11-12', NULL, 3000, 2),
(73, '9/22', 1500, '', NULL, '2022-07-06 19:24:21', '434/22', 'القسم المدني', 'محكمة : البليدة', '2022-04-01', NULL, NULL, 3000, 1),
(74, '11/22', 3000, '', NULL, '2022-07-16 14:05:07', '1244/21', 'القسم المدني', 'محكمة : البليدة', '2022-07-16', NULL, NULL, 3000, 1),
(75, '12/22', 1000, '', NULL, '2022-07-17 08:17:04', '3466/22', 'القسم الاجتماعي', 'محكمة : البليدة', '2022-07-15', NULL, NULL, 3000, 1);

-- --------------------------------------------------------

--
-- Structure de la table `demandeur`
--

CREATE TABLE `demandeur` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `addr` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type_bon` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_bon` varchar(11) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `demandeur`
--

INSERT INTO `demandeur` (`id`, `nom`, `addr`, `type_bon`, `id_bon`) VALUES
(119, 'طارق شابو', 'مركز بهلي بلدية الصومعة - البليدة', NULL, '2/21'),
(121, 'كربوش عبد الرحمان', 'حي بريان أمام مسجد ابن الأثير بلدية قرواو', NULL, '4/21'),
(126, 'صثقفغا', 'يبلا', NULL, '10/21'),
(127, 'ناتمكلا', 'كمنت', NULL, '11/21'),
(128, 'منتالرب', 'سيبقلفا', NULL, '12/21'),
(129, 'شسيبلات', 'نتاليب', NULL, '13/21'),
(130, 'سيبلاهعت', 'تانتاغ', NULL, '14/21'),
(131, 'حنلةيبكمي', 'كبةىيكمةبكمسي', NULL, '8/21'),
(147, 'رميد محمد عبد الكريم', 'حي 19 جوان البليدة', NULL, '3/21'),
(148, 'محمد علي عبد الرحمان', 'نهج قريتلي مختار قرواو', NULL, '1/21'),
(152, 'الزبير بن عطية', 'دوار اولاد الربيع بلدية بوطي سايح', NULL, '15/21'),
(153, 'قويدر بوعلام', 'بني تامو', NULL, '9/21'),
(207, 'مراد ', 'حسين داي', NULL, '16/21'),
(208, 'أحمد ', 'حسين داي', NULL, '16/21'),
(209, 'صحثهستحصهشس', 'شبشبييس', NULL, '17/21'),
(219, 'حمزة حاج عمار', 'قرواو', NULL, '18/21'),
(220, 'بونجار فيصل', 'بني تامو البليدة', NULL, '18/21'),
(222, 'حمزة حاج عمار', 'قرواو البليدة', NULL, '18/21'),
(236, 'دريوش علي ', 'حي دريوش بوعرفة البليدة', NULL, '19/21'),
(240, 'يوسف بلايلي', 'وهران', NULL, '21/21'),
(241, 'كسنيةبكسنية', 'كسمةيبكمة', NULL, '1/22'),
(242, 'سشيبشيسب', 'كسمةيبكمة', NULL, '2/22'),
(243, 'صشيسبشسي', 'كسمةيبكمة', NULL, '3/22'),
(244, 'مبروكي حسام', 'بوسعادة ولاية المسيلة', NULL, '4/22'),
(247, 'لطفي بوجلال', 'حي النخيل رقم 40 البليدة', NULL, '5/22'),
(248, 'علي عليوات', 'حي الموز رقم 34 البليدة', NULL, '6/22'),
(249, 'محفوظ عليوات', 'حي الموز رقم 34 البليدة', NULL, '7/22'),
(250, 'بشير بشيري', 'حي النخيل البليدة', NULL, '8/22'),
(251, 'عبد القادر عمر', 'حي سيدي عبد القادر البليدة', NULL, '9/22'),
(252, 'محمد السعيد بوعلام الله', 'متوسطة احمد عروة قرواو البليدة', NULL, '10/22'),
(253, 'سمكمنبنةلنك', 'كةيبكةل', NULL, '10/22'),
(254, 'محمد السعيد بوعلام الله', 'متوسطة احمد عروة قرواو البليدة', NULL, '10/22'),
(255, 'لحسن بلال', 'حي تاباينات بوقرة البليدة', NULL, '11/22'),
(256, 'سرير عبد الوهاب', 'نهج قريتلي مختار قرواو البليدة', NULL, '12/22'),
(257, 'حداد فطوم', 'باب الوادي الجزائر', NULL, '13/22'),
(258, 'سعيدي سعيد', 'حي 400 مسكن البليدة', NULL, '14/22'),
(261, 'عدي يونس', 'حي 1400 مسكن أولاد يعيش البليدة', NULL, '16/22'),
(262, 'سيد علي قناز', 'حي قناز مصطفى قرواو البليدة', NULL, '15/22'),
(263, 'سيد علي قناز', 'حي قناز مصطفى قرواو البليدة', NULL, '15/22');

-- --------------------------------------------------------

--
-- Structure de la table `fichiers`
--

CREATE TABLE `fichiers` (
  `type_fichier` varchar(25) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `date_marquage` date NOT NULL,
  `langue` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `num_bon` varchar(10) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `identif`
--

CREATE TABLE `identif` (
  `id` int(11) NOT NULL,
  `year` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `identif`
--

INSERT INTO `identif` (`id`, `year`) VALUES
(1, 21),
(1, 22),
(2, 21),
(2, 22),
(3, 21),
(3, 22),
(4, 21),
(4, 22),
(5, 21),
(5, 22),
(6, 22),
(7, 21),
(7, 22),
(8, 21),
(8, 22),
(9, 21),
(9, 22),
(10, 21),
(10, 22),
(11, 21),
(11, 22),
(12, 21),
(12, 22),
(13, 21),
(13, 22),
(14, 21),
(14, 22),
(15, 21),
(15, 22),
(16, 21),
(16, 22),
(17, 21),
(18, 21),
(19, 21),
(20, 21),
(21, 21);

-- --------------------------------------------------------

--
-- Structure de la table `jugements`
--

CREATE TABLE `jugements` (
  `num_indice` varchar(15) NOT NULL,
  `num_table` varchar(15) NOT NULL,
  `date` date NOT NULL,
  `commission` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `spec` varchar(25) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `id` int(11) NOT NULL,
  `type` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `num_bon` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `letter`
--

CREATE TABLE `letter` (
  `id` int(11) NOT NULL,
  `id_rapport` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `type_rapport` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `id_obligatoire` int(11) NOT NULL,
  `num_lettre` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `date_lettre` date NOT NULL,
  `publier` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `letter`
--

INSERT INTO `letter` (`id`, `id_rapport`, `type_rapport`, `id_obligatoire`, `num_lettre`, `date_lettre`, `publier`) VALUES
(35, '2/21', 'تكليف بالوفاء : محضر تكليف', 116, '234567324', '2021-03-18', 1),
(36, '2/21', 'تكليف بالوفاء : محضر تبليغ التكليف', 116, '23456765', '2021-04-17', 1),
(37, '2/21', 'تكليف بالوفاء : محضر تبليغ السند', 116, '2345678', '2021-03-13', 1),
(78, '16/21', 'تبليغ حكم', 163, 'R00834566709', '2022-04-16', 0),
(80, '4/22', 'تبليغ عريضة', 202, 'R00853487709', '2022-07-06', 0),
(81, '10/22', 'تبليغ حكم/قرار', 210, 'R00876656609', '2022-07-13', 0),
(82, '11/22', 'تكليف بالحضور لجلسة', 212, 'rkfbdkksh', '2022-07-16', 0),
(83, '11/22', 'تكليف بالحضور لجلسة', 212, 'rkfbdkksh', '2022-07-16', 0),
(84, '11/22', 'تكليف بالحضور لجلسة', 212, 'rkfbdkksh', '2022-07-16', 0),
(85, '12/22', 'تكليف بالحضور لجلسة', 213, 'R00898876609', '2022-07-17', 0);

--
-- Déclencheurs `letter`
--
DELIMITER $$
CREATE TRIGGER `update_rapport_bon` AFTER UPDATE ON `letter` FOR EACH ROW BEGIN
IF (SELECT id FROM bon_acte WHERE num_bon = NEW.id_rapport) IS NOT NULL THEN
UPDATE bon_acte SET last_updated = 1 WHERE num_bon=NEW.id_rapport;
ELSEIF (SELECT id FROM bon_apercus WHERE num_bon = NEW.id_rapport) IS NOT NULL THEN
UPDATE bon_apercus SET last_updated = 1 WHERE num_bon=NEW.id_rapport;
ELSEIF (SELECT id FROM bon_apercu_parorders WHERE num_bon = NEW.id_rapport) IS NOT NULL THEN
UPDATE bon_apercu_parorders SET last_updated = 1 WHERE num_bon=NEW.id_rapport;
ELSEIF (SELECT id FROM bon_associations WHERE num_bon = NEW.id_rapport) IS NOT NULL THEN
UPDATE bon_associations SET last_updated = 1 WHERE num_bon=NEW.id_rapport;
ELSEIF (SELECT id FROM bon_autres WHERE num_bon = NEW.id_rapport) IS NOT NULL THEN
UPDATE bon_autres SET last_updated = 1 WHERE num_bon=NEW.id_rapport;
ELSEIF (SELECT id FROM bon_excuses WHERE num_bon = NEW.id_rapport) IS NOT NULL THEN
UPDATE bon_excuses SET last_updated = 1 WHERE num_bon=NEW.id_rapport;
ELSEIF (SELECT id FROM bon_mandat WHERE num_bon = NEW.id_rapport) IS NOT NULL THEN
UPDATE bon_mandat SET last_updated = 1 WHERE num_bon=NEW.id_rapport;
ELSEIF (SELECT id FROM bon_orders WHERE num_bon = NEW.id_rapport) IS NOT NULL THEN
UPDATE bon_orders SET last_updated = 1 WHERE num_bon=NEW.id_rapport;
ELSEIF (SELECT id FROM bon_provisions WHERE num_bon = NEW.id_rapport) IS NOT NULL THEN
UPDATE bon_provisions SET last_updated = 1 WHERE num_bon=NEW.id_rapport;
ELSEIF (SELECT id FROM bon_rqst WHERE num_bon = NEW.id_rapport) IS NOT NULL THEN
UPDATE bon_rqst SET last_updated = 1 WHERE num_bon=NEW.id_rapport;
ELSEIF (SELECT id FROM bon_seances WHERE num_bon = NEW.id_rapport) IS NOT NULL THEN
UPDATE bon_seances SET last_updated = 1 WHERE num_bon=NEW.id_rapport;
END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `logs`
--

CREATE TABLE `logs` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `time` timestamp NOT NULL DEFAULT current_timestamp(),
  `operation` varchar(150) NOT NULL,
  `table_name` varchar(60) NOT NULL,
  `id_table` varchar(10) NOT NULL,
  `details` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `logs`
--

INSERT INTO `logs` (`id`, `user_id`, `time`, `operation`, `table_name`, `id_table`, `details`) VALUES
(1, 12, '2022-06-29 17:55:06', 'hgjh', '', '0', ''),
(2, 1, '2022-06-30 08:55:47', 'update', 'bon_seances', '15/21', ''),
(3, 1, '2022-07-01 22:14:25', 'login', '#', '#', ' '),
(4, 1, '2022-07-02 10:23:22', 'login', '#', '#', ' '),
(5, 1, '2022-07-02 11:31:56', 'login', '#', '#', ' '),
(6, 1, '2022-07-02 11:49:24', 'login', '#', '#', ' '),
(7, 1, '2022-07-02 11:52:18', 'login', '#', '#', ' '),
(8, 1, '2022-07-02 11:53:37', 'login', '#', '#', ' '),
(9, 1, '2022-07-02 12:04:07', 'login', '#', '#', ' '),
(10, 1, '2022-07-02 12:05:00', 'login', '#', '#', ' '),
(11, 1, '2022-07-02 12:06:59', 'login', '#', '#', ' '),
(12, 1, '2022-07-02 12:07:57', 'login', '#', '#', ' '),
(13, 1, '2022-07-02 12:09:05', 'login', '#', '#', ' '),
(14, 1, '2022-07-02 16:37:29', 'login', '#', '#', ' '),
(15, 1, '2022-07-02 17:38:11', 'login', '#', '#', ' '),
(16, 1, '2022-07-02 17:44:26', 'login', '#', '#', ' '),
(17, 1, '2022-07-02 17:54:24', 'login', '#', '#', ' '),
(18, 1, '2022-07-02 19:04:05', 'login', '#', '#', ' '),
(19, 1, '2022-07-02 20:35:24', 'update', 'ogligatoire', '142', ' '),
(20, 1, '2022-07-02 20:35:24', 'update', 'letter', '142', ' '),
(21, 1, '2022-07-02 20:56:59', 'login', '#', '#', ' '),
(22, 1, '2022-07-03 12:34:21', 'login', '#', '#', ' '),
(23, 1, '2022-07-03 12:40:38', 'login', '#', '#', ' '),
(24, 1, '2022-07-03 13:10:35', 'login', '#', '#', ' '),
(25, 1, '2022-07-03 13:28:08', 'login', '#', '#', ' '),
(26, 1, '2022-07-03 13:37:03', 'login', '#', '#', ' '),
(27, 1, '2022-07-03 13:38:38', 'login', '#', '#', ' '),
(28, 1, '2022-07-03 13:41:19', 'login', '#', '#', ' '),
(29, 1, '2022-07-03 13:43:39', 'insert', 'bon_provisions', '10/22', ' '),
(30, 1, '2022-07-03 13:43:39', 'insert', 'demandeur', '252', ' '),
(31, 1, '2022-07-03 13:43:39', 'insert', 'obliatoire', '209', ' '),
(32, 1, '2022-07-03 13:44:38', 'insert', 'demandeur', '253', ' '),
(33, 1, '2022-07-03 13:44:38', 'insert', 'demandeur', '254', ' '),
(34, 1, '2022-07-03 13:44:38', 'insert', 'obliatoire', '210', ' '),
(35, 1, '2022-07-03 13:44:38', 'insert', 'obliatoire', '211', ' '),
(36, 1, '2022-07-03 13:45:25', 'login', '#', '#', ' '),
(37, 1, '2022-07-03 13:47:06', 'login', '#', '#', ' '),
(38, 1, '2022-07-03 13:48:32', 'login', '#', '#', ' '),
(39, 1, '2022-07-03 13:49:46', 'login', '#', '#', ' '),
(40, 1, '2022-07-03 14:02:16', 'login', '#', '#', ' '),
(41, 1, '2022-07-03 14:03:38', 'login', '#', '#', ' '),
(42, 1, '2022-07-03 14:04:50', 'login', '#', '#', ' '),
(43, 1, '2022-07-03 14:11:14', 'login', '#', '#', ' '),
(44, 1, '2022-07-03 14:13:37', 'login', '#', '#', ' '),
(45, 1, '2022-07-03 14:15:05', 'login', '#', '#', ' '),
(46, 1, '2022-07-03 15:00:02', 'login', '#', '#', ' '),
(47, 1, '2022-07-03 15:05:31', 'login', '#', '#', ' '),
(48, 1, '2022-07-03 15:07:16', 'login', '#', '#', ' '),
(49, 1, '2022-07-04 19:41:15', 'login', '#', '#', ' '),
(50, 1, '2022-07-04 19:42:39', 'login', '#', '#', ' '),
(51, 1, '2022-07-04 20:22:52', 'login', '#', '#', ' '),
(52, 1, '2022-07-04 20:23:26', 'login', '#', '#', ' '),
(53, 1, '2022-07-04 20:24:55', 'login', '#', '#', ' '),
(54, 1, '2022-07-04 20:25:12', 'login', '#', '#', ' '),
(55, 1, '2022-07-04 20:41:51', 'login', '#', '#', ' '),
(56, 1, '2022-07-05 18:37:08', 'login', '#', '#', ' '),
(57, 1, '2022-07-05 18:50:18', 'login', '#', '#', ' '),
(58, 1, '2022-07-05 18:50:45', 'login', '#', '#', ' '),
(59, 1, '2022-07-05 18:51:33', 'login', '#', '#', ' '),
(60, 1, '2022-07-05 18:57:29', 'login', '#', '#', ' '),
(61, 1, '2022-07-05 20:08:51', 'login', '#', '#', ' '),
(62, 1, '2022-07-05 21:02:51', 'login', '#', '#', ' '),
(63, 1, '2022-07-05 21:30:47', 'login', '#', '#', ' '),
(64, 1, '2022-07-06 05:39:20', 'login', '#', '#', ' '),
(65, 1, '2022-07-06 05:41:55', 'login', '#', '#', ' '),
(66, 1, '2022-07-06 09:01:29', 'login', '#', '#', ' '),
(67, 1, '2022-07-06 09:06:14', 'login', '#', '#', ' '),
(68, 1, '2022-07-06 10:16:24', 'login', '#', '#', ' '),
(69, 1, '2022-07-06 10:18:11', 'login', '#', '#', ' '),
(70, 1, '2022-07-06 10:22:57', 'login', '#', '#', ' '),
(71, 1, '2022-07-06 16:21:54', 'login', '#', '#', ' '),
(72, 1, '2022-07-06 19:11:17', 'login', '#', '#', ' '),
(73, 1, '2022-07-06 19:22:54', 'update', 'ogligatoire', '202', ' '),
(74, 1, '2022-07-06 19:22:55', 'update', 'letter', '202', ' '),
(75, 1, '2022-07-06 19:24:22', 'update', 'ogligatoire', '208', ' '),
(76, 1, '2022-07-06 19:24:22', 'update', 'letter', '208', ' '),
(77, 1, '2022-07-06 19:26:28', 'update', 'ogligatoire', '208', ' '),
(78, 1, '2022-07-13 20:01:57', 'login', '#', '#', ' '),
(79, 1, '2022-07-13 20:05:13', 'login', '#', '#', ' '),
(80, 1, '2022-07-13 20:08:32', 'update', 'ogligatoire', '210', ' '),
(81, 1, '2022-07-16 13:45:43', 'login', '#', '#', ' '),
(82, 1, '2022-07-16 13:47:21', 'insert', 'bon_rqst', '11/22', ' '),
(83, 1, '2022-07-16 13:47:21', 'insert', 'demandeur', '255', ' '),
(84, 1, '2022-07-16 13:47:21', 'insert', 'obliatoire', '212', ' '),
(85, 1, '2022-07-17 08:09:32', 'login', '#', '#', ' '),
(86, 1, '2022-07-17 08:13:14', 'insert', 'bon_rqst', '12/22', ' '),
(87, 1, '2022-07-17 08:13:14', 'insert', 'demandeur', '256', ' '),
(88, 1, '2022-07-17 08:13:14', 'insert', 'obliatoire', '213', ' '),
(89, 1, '2022-07-17 08:19:49', 'update', 'ogligatoire', '213', ' '),
(90, 1, '2022-07-17 08:25:52', 'login', '#', '#', ' '),
(91, 1, '2022-07-17 08:29:10', 'login', '#', '#', ' '),
(92, 1, '2022-08-04 22:53:23', 'login', '#', '#', ' '),
(93, 1, '2022-08-07 00:05:07', 'login', '#', '#', ' '),
(94, 1, '2022-08-19 14:30:37', 'login', '#', '#', ' '),
(95, 1, '2022-08-19 14:37:17', 'login', '#', '#', ' '),
(96, 1, '2022-08-19 14:40:00', 'login', '#', '#', ' '),
(97, 1, '2022-08-19 14:55:46', 'login', '#', '#', ' '),
(98, 1, '2022-08-19 14:56:55', 'insert', 'demandeur', '257', ' '),
(99, 1, '2022-08-19 14:56:55', 'insert', 'obliatoire', '214', ' '),
(100, 1, '2022-08-19 14:57:28', 'login', '#', '#', ' '),
(101, 1, '2022-08-19 14:58:21', 'insert', 'demandeur', '258', ' '),
(102, 1, '2022-08-19 14:58:21', 'insert', 'obliatoire', '215', ' '),
(103, 1, '2022-08-19 15:00:00', 'login', '#', '#', ' '),
(104, 1, '2022-08-19 15:00:54', 'insert', 'demandeur', '259', ' '),
(105, 1, '2022-08-19 15:00:54', 'insert', 'obliatoire', '216', ' '),
(106, 1, '2022-08-19 15:05:09', 'login', '#', '#', ' '),
(107, 1, '2022-08-19 15:15:58', 'login', '#', '#', ' '),
(108, 1, '2022-08-19 15:31:09', 'login', '#', '#', ' '),
(109, 1, '2022-08-19 15:32:45', 'insert', 'demandeur', '260', ' '),
(110, 1, '2022-08-19 15:32:45', 'insert', 'obliatoire', '217', ' '),
(111, 1, '2022-08-19 15:35:35', 'login', '#', '#', ' '),
(112, 1, '2022-08-19 15:36:59', 'insert', 'demandeur', '261', ' '),
(113, 1, '2022-08-19 15:36:59', 'insert', 'obliatoire', '218', ' '),
(114, 1, '2022-08-19 17:36:37', 'login', '#', '#', ' '),
(115, 1, '2022-08-19 17:37:59', 'login', '#', '#', ' '),
(116, 1, '2022-08-19 18:02:22', 'login', '#', '#', ' '),
(117, 1, '2022-08-19 18:10:52', 'login', '#', '#', ' '),
(118, 1, '2022-08-19 18:11:38', 'insert', 'demandeur', '262', ' '),
(119, 1, '2022-08-19 18:11:38', 'update', 'demandeur', '15/22', ' '),
(120, 1, '2022-08-19 18:12:09', 'insert', 'demandeur', '263', ' '),
(121, 1, '2022-08-19 18:12:09', 'update', 'demandeur', '15/22', ' '),
(122, 1, '2022-08-19 18:16:08', 'login', '#', '#', ' '),
(123, 1, '2022-08-19 18:25:24', 'login', '#', '#', ' '),
(124, 1, '2022-08-19 18:27:07', 'login', '#', '#', ' ');

-- --------------------------------------------------------

--
-- Structure de la table `migrations`
--

CREATE TABLE `migrations` (
  `id` int(10) UNSIGNED NOT NULL,
  `migration` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `migrations`
--

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(1, '2019_12_14_000001_create_personal_access_tokens_table', 1),
(2, '2022_07_01_175522_add_api_token_to_users_table', 1);

-- --------------------------------------------------------

--
-- Structure de la table `notification_fidelité`
--

CREATE TABLE `notification_fidelité` (
  `id_provision` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `num` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `date` date NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `notification_fidelité`
--

INSERT INTO `notification_fidelité` (`id_provision`, `num`, `date`, `id`) VALUES
('2/21', '234/21', '2021-03-11', 19),
('4/21', '45/21', '2021-03-19', 21),
('3/21', '234/21', '2021-04-17', 22),
('1/21', '234/21', '2021-03-11', 23),
('19/21', '465', '2021-11-16', 33);

-- --------------------------------------------------------

--
-- Structure de la table `obligatoire`
--

CREATE TABLE `obligatoire` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `addr` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date` date DEFAULT NULL,
  `en_suspens` tinyint(4) NOT NULL DEFAULT 0,
  `id_bon` varchar(11) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `obligatoire`
--

INSERT INTO `obligatoire` (`id`, `nom`, `addr`, `status`, `date`, `en_suspens`, `id_bon`) VALUES
(114, 'طهاري نور الدين', 'حي مجدوفة قرواو', 'تم التبليغ', '2022-03-27', 0, '1/21'),
(115, 'جيجي جعفر', 'حي الحوارش رقم 123 قرواو', 'تم إشعاره(ا)', '2022-03-27', 0, '1/21'),
(116, 'بلخلفة بلال', 'حي 1000 مسكن أولاد يعيش البليدة', 'تعليق (غير مبلغ)', '2021-02-26', 1, '2/21'),
(117, 'دريوش محمد فريد', 'حي 13 ماي البليدة', 'تم التبليغ', '2021-02-21', 0, '3/21'),
(118, 'بن قولال رمزي وسيم', 'حي 13 ماي البليدة', NULL, NULL, 0, '3/21'),
(120, 'فليسي الياس', 'حي بريان أمام مسجد ابن الأثير بلدية قرواو', NULL, NULL, 0, '4/21'),
(122, 'شسيبلا', 'يبلاتت', NULL, NULL, 0, '10/21'),
(123, 'البيبلات', 'سئيءبؤلات', NULL, NULL, 0, '11/21'),
(124, 'قثفغ', 'منت', NULL, NULL, 0, '13/21'),
(125, 'رلاىرؤ', 'نهتع', NULL, NULL, 0, '14/21'),
(126, 'شلقبيشلشيب', 'سيبلابلي', 'تم التبليغ', '2021-02-16', 0, '8/21'),
(141, 'مراد بن ثامر', 'قرواو', NULL, NULL, 0, '9/21'),
(142, 'محمد السعيد بوعلام الله', 'الجزائر', 'تم إشعاره(ا)', '2022-08-07', 0, '15/21'),
(163, 'سيد أحمد', 'حسين داي', 'تم التبليغ', '2022-04-22', 0, '16/21'),
(164, 'شصثبقصثص', 'ثفلاللل', NULL, NULL, 0, '17/21'),
(174, 'فيصل بوبكري', 'حي طريق الحبس البليدة', 'تم إشعاره(ا)', '2022-07-06', 0, '18/21'),
(175, 'الطيب شيبوب', 'العفرون', 'تم التبليغ', '2022-07-06', 0, '18/21'),
(179, 'مراد بن ثامر', 'قرواو البليدة', 'غير منجزة', NULL, 0, '18/21'),
(193, 'محمدي عدنان', 'حي 1670 مسكن بئر التوتة الجزائر', 'تم التبليغ', '2021-12-01', 0, '19/21'),
(194, 'سيدي عيسى علي', 'مرمان البليدة', NULL, NULL, 0, '19/21'),
(198, 'حسين بد عيادة', 'وادي العلايق', NULL, NULL, 0, '21/21'),
(199, 'شيسمنبناسمني', 'سيبيشسب', NULL, NULL, 0, '1/22'),
(200, 'سيبشسيب', 'سيبيشسب', 'تم التبليغ', '2022-07-06', 0, '2/22'),
(201, '12233', 'سيبيشسب', 'تم إشعاره(ا)', '2022-04-23', 0, '3/22'),
(202, 'محمد علي مام', 'حي بونعامة الجيلالي ع 10 رقم 12 البليدة', 'تم إرسال رسالة', '2022-03-28', 0, '4/22'),
(207, 'عبد الوهاب زبيري', 'حي الموز البليدة', 'تم التبليغ', '2022-03-31', 0, '8/22'),
(208, 'عميمر سيد احمد', 'شارع 11 ديسمبر 1960 البليدة', 'تم التبليغ', '2022-07-07', 0, '9/22'),
(210, 'محمد علي فريدة', 'شارع زدري عبد النور بني تامو البليدة', 'تعليق (غير مبلغ)', '2022-07-06', 0, '10/22'),
(211, 'النائب العام لدى مجلس قضاء البليدة', 'مجلس قضاء البليدة', 'غير منجزة', NULL, 0, '10/22'),
(212, 'بوعمرة على', 'حي الاخوة فتال قرواو البليدة', 'تم إرسال رسالة', NULL, 0, '11/22'),
(213, 'فايز فليسي', 'نهج قناز مصطفى قرواو البليدة', 'تعليق (غير مبلغ)', '2022-07-17', 0, '12/22'),
(214, 'ربراب عميمر', 'حي 200 مسكن اولاد يعيش البليدة', NULL, NULL, 0, '13/22'),
(215, 'حمادي حميدة', 'حي 200 مسكن أولاد يعيش', NULL, NULL, 0, '14/22'),
(216, 'سعيدي سعدية', 'حي 300 مسكن الصومعة البليدة', NULL, NULL, 0, '15/22'),
(218, 'بن قسمية زكرياء', 'شارع فلسطين رقم 24 البليدة', NULL, NULL, 0, '16/22');

--
-- Déclencheurs `obligatoire`
--
DELIMITER $$
CREATE TRIGGER `update_oblig_bon` AFTER UPDATE ON `obligatoire` FOR EACH ROW BEGIN
IF (SELECT id FROM bon_acte WHERE num_bon = NEW.id_bon) IS NOT NULL THEN
UPDATE bon_acte SET last_updated = 1 WHERE num_bon=NEW.id_bon;
ELSEIF (SELECT id FROM bon_apercus WHERE num_bon = NEW.id_bon) IS NOT NULL THEN
UPDATE bon_apercus SET last_updated = 1 WHERE num_bon=NEW.id_bon;
ELSEIF (SELECT id FROM bon_apercu_parorders WHERE num_bon = NEW.id_bon) IS NOT NULL THEN
UPDATE bon_apercu_parorders SET last_updated = 1 WHERE num_bon=NEW.id_bon;
ELSEIF (SELECT id FROM bon_associations WHERE num_bon = NEW.id_bon) IS NOT NULL THEN
UPDATE bon_associations SET last_updated = 1 WHERE num_bon=NEW.id_bon;
ELSEIF (SELECT id FROM bon_autres WHERE num_bon = NEW.id_bon) IS NOT NULL THEN
UPDATE bon_autres SET last_updated = 1 WHERE num_bon=NEW.id_bon;
ELSEIF (SELECT id FROM bon_excuses WHERE num_bon = NEW.id_bon) IS NOT NULL THEN
UPDATE bon_excuses SET last_updated = 1 WHERE num_bon=NEW.id_bon;
ELSEIF (SELECT id FROM bon_mandat WHERE num_bon = NEW.id_bon) IS NOT NULL THEN
UPDATE bon_mandat SET last_updated = 1 WHERE num_bon=NEW.id_bon;
ELSEIF (SELECT id FROM bon_orders WHERE num_bon = NEW.id_bon) IS NOT NULL THEN
UPDATE bon_orders SET last_updated = 1 WHERE num_bon=NEW.id_bon;
ELSEIF (SELECT id FROM bon_provisions WHERE num_bon = NEW.id_bon) IS NOT NULL THEN
UPDATE bon_provisions SET last_updated = 1 WHERE num_bon=NEW.id_bon;
ELSEIF (SELECT id FROM bon_rqst WHERE num_bon = NEW.id_bon) IS NOT NULL THEN
UPDATE bon_rqst SET last_updated = 1 WHERE num_bon=NEW.id_bon;
ELSEIF (SELECT id FROM bon_seances WHERE num_bon = NEW.id_bon) IS NOT NULL THEN
UPDATE bon_seances SET last_updated = 1 WHERE num_bon=NEW.id_bon;
END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `orders`
--

CREATE TABLE `orders` (
  `num_order` varchar(25) NOT NULL,
  `date_order` date NOT NULL,
  `commission` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `spec` varchar(25) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `id` int(11) NOT NULL,
  `type_order` varchar(25) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `num_bon` varchar(8) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `orders`
--

INSERT INTO `orders` (`num_order`, `date_order`, `commission`, `spec`, `id`, `type_order`, `num_bon`) VALUES
('600/22', '2022-08-19', 'محكمة : البليدة', 'القسم العقاري', 1, 'أمر بحجز تنفيذي على منقول', '16/22');

-- --------------------------------------------------------

--
-- Structure de la table `personal_access_tokens`
--

CREATE TABLE `personal_access_tokens` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `tokenable_type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tokenable_id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `abilities` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_used_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `personal_access_tokens`
--

INSERT INTO `personal_access_tokens` (`id`, `tokenable_type`, `tokenable_id`, `name`, `token`, `abilities`, `last_used_at`, `created_at`, `updated_at`) VALUES
(3, 'App\\Models\\User', 1, 'myapptoken', 'f0e6fa25dc9bba081a6ba50ca07cad2609bb742b225a5139f71602ccf8531038', '[\"*\"]', NULL, '2022-07-05 18:03:01', '2022-07-05 18:03:01'),
(4, 'App\\Models\\User', 1, 'myapptoken', '285f2d03fdace9fe29a6864bb54777a0b6710ee3666b36b9fd6f5e044a87daea', '[\"*\"]', NULL, '2022-07-06 04:41:26', '2022-07-06 04:41:26'),
(5, 'App\\Models\\User', 1, 'myapptoken', '67bb36f01d66d3f1f509ffc52796e35de38506252c44585f6ff60d45cc707e4e', '[\"*\"]', NULL, '2022-07-06 04:41:26', '2022-07-06 04:41:26'),
(6, 'App\\Models\\User', 1, 'myapptoken', '3f03033c48319e57de1ddcb40cdfe18367bac6d6ab892fca106bbdd61c1b4dbd', '[\"*\"]', NULL, '2022-07-06 04:41:30', '2022-07-06 04:41:30'),
(7, 'App\\Models\\User', 1, 'myapptoken', '0d25e7830ae1bd4ac2716fe6ff65facdc3b15724c3647e3c97956bcc36577998', '[\"*\"]', NULL, '2022-07-06 04:41:30', '2022-07-06 04:41:30'),
(8, 'App\\Models\\User', 1, 'myapptoken', '103a39ce45f55939639db28b535d62deb22d904558582ff9e8dd3ac47dc28b16', '[\"*\"]', NULL, '2022-07-06 04:41:33', '2022-07-06 04:41:33'),
(9, 'App\\Models\\User', 1, 'myapptoken', '271509897b80d0a85b1cf7005403c001b943b12f3ac9eb495899f565dd83e181', '[\"*\"]', NULL, '2022-07-06 04:41:34', '2022-07-06 04:41:34'),
(10, 'App\\Models\\User', 1, 'myapptoken', 'b70c7a8a9b6a02b05cc2d65acc33c09a8e6a91d1dca02b0619721707abd4ac49', '[\"*\"]', '2022-07-06 04:47:01', '2022-07-06 04:42:30', '2022-07-06 04:47:01'),
(11, 'App\\Models\\User', 1, 'myapptoken', '5efcfe1e3f2cfec76191107bffc40f22c05d34dacdbaa56ee6135ad362b5fcdf', '[\"*\"]', '2022-07-06 04:51:22', '2022-07-06 04:50:50', '2022-07-06 04:51:22'),
(12, 'App\\Models\\User', 1, 'myapptoken', '29d575e3815521f37a5cabdeb26bd0d8f9775762f3b24bb3f261dd20b09806c7', '[\"*\"]', '2022-07-06 05:22:31', '2022-07-06 05:20:01', '2022-07-06 05:22:31'),
(13, 'App\\Models\\User', 1, 'myapptoken', 'f15e89ce506a6e0fa2d340ffa43fea085b50cad0bb45aad7ca8c403d617d485f', '[\"*\"]', NULL, '2022-07-06 05:20:01', '2022-07-06 05:20:01'),
(14, 'App\\Models\\User', 1, 'myapptoken', 'e93599a0bff7a9ac10ab2f2de906c518f9f4afec449277147a118c4b02565215', '[\"*\"]', NULL, '2022-07-06 05:20:02', '2022-07-06 05:20:02'),
(15, 'App\\Models\\User', 1, 'myapptoken', 'b93494ca62ba54ba8812ecc2f7dad3d03d4d663ba3995aa6a87b7dd16cbb1644', '[\"*\"]', NULL, '2022-07-06 05:20:02', '2022-07-06 05:20:02'),
(16, 'App\\Models\\User', 1, 'myapptoken', '91cd89cc627d7a1003c9cba465aea6914f62de825e0c626e9aed6a43b54c950f', '[\"*\"]', NULL, '2022-07-06 05:20:02', '2022-07-06 05:20:02'),
(17, 'App\\Models\\User', 1, 'myapptoken', '3f56d5a8f0f9af3534894aa44e7d105c4c510d011f01d39cd9873e66b56d8ef8', '[\"*\"]', NULL, '2022-07-06 05:20:02', '2022-07-06 05:20:02'),
(18, 'App\\Models\\User', 1, 'myapptoken', '62ba99f24768328c015ff7cd3628bc898b0413f66b91bfa93ad37e0ebe34b672', '[\"*\"]', NULL, '2022-07-06 05:20:02', '2022-07-06 05:20:02'),
(19, 'App\\Models\\User', 1, 'myapptoken', '5bc311bc0a4710e7ca6f51c9dd583c67eaf5accd577f5f366c06da7328736482', '[\"*\"]', NULL, '2022-07-06 05:20:02', '2022-07-06 05:20:02'),
(20, 'App\\Models\\User', 1, 'myapptoken', '06c071205f64a37f2fba468b78914064190a3e46d0423119b34889fba12c50f5', '[\"*\"]', NULL, '2022-07-06 05:20:02', '2022-07-06 05:20:02'),
(21, 'App\\Models\\User', 1, 'myapptoken', '4edaaff954a47ea50a00565e2f993c25b4aea08ce3bb568b2893da844355166b', '[\"*\"]', NULL, '2022-07-06 05:20:02', '2022-07-06 05:20:02'),
(22, 'App\\Models\\User', 1, 'myapptoken', '9d8e157d94f1d702122dffc6ac50cfef5e3b8d183215947fbd1094cc9172a18e', '[\"*\"]', NULL, '2022-07-06 05:20:02', '2022-07-06 05:20:02'),
(23, 'App\\Models\\User', 1, 'myapptoken', '338b0f5a5d6906fc51c804071b9e295cfd65a3a4eb7b1a57fdaf508d8268aa06', '[\"*\"]', NULL, '2022-07-06 05:20:02', '2022-07-06 05:20:02'),
(24, 'App\\Models\\User', 1, 'myapptoken', 'fe426edf3b86e48a53b0daae43f8611cae133508412e16458dd7b378f5081f40', '[\"*\"]', NULL, '2022-07-06 05:20:02', '2022-07-06 05:20:02'),
(25, 'App\\Models\\User', 1, 'myapptoken', '46b1973e0d65fd84202e642b0862826130fb0bc84d785dfd32c6dc395dd08a21', '[\"*\"]', NULL, '2022-07-06 05:20:02', '2022-07-06 05:20:02'),
(26, 'App\\Models\\User', 1, 'myapptoken', '81b97b3589c5cf3165c216354a8139ba6d3f49bbbb8e478ad6325befa8241e92', '[\"*\"]', NULL, '2022-07-06 05:20:02', '2022-07-06 05:20:02'),
(27, 'App\\Models\\User', 1, 'myapptoken', '7781921ea506acbebcfe9eceba43f427e6d2a3f878c2904650f782e9770d5518', '[\"*\"]', NULL, '2022-07-06 05:20:02', '2022-07-06 05:20:02'),
(28, 'App\\Models\\User', 1, 'myapptoken', '42dc615c9b6be94ba25c65731e0c2c333ff50c79bc26a14bcb3c9d588e557a46', '[\"*\"]', NULL, '2022-07-06 05:20:02', '2022-07-06 05:20:02'),
(29, 'App\\Models\\User', 1, 'myapptoken', '98026646e19154b311418eaa32d3cb4ed7fb750b03d1b740383c260db2bae096', '[\"*\"]', NULL, '2022-07-06 15:22:20', '2022-07-06 15:22:20'),
(30, 'App\\Models\\User', 1, 'myapptoken', '9aa1799fca94a1462304e8b87e9265bf76a362f3267fd667804d5a5309197a27', '[\"*\"]', NULL, '2022-07-06 15:22:20', '2022-07-06 15:22:20'),
(31, 'App\\Models\\User', 1, 'myapptoken', 'c5d59af1c587d63b7bc104356ee778c42a454c71a80380fdf0626f9eb92c8389', '[\"*\"]', '2022-07-06 15:25:29', '2022-07-06 15:22:20', '2022-07-06 15:25:29'),
(32, 'App\\Models\\User', 1, 'myapptoken', '248434d1bb6430b863b3e394f372eaaccfcde55cb12225324ba8d5732e9d27c1', '[\"*\"]', NULL, '2022-07-06 15:22:20', '2022-07-06 15:22:20'),
(33, 'App\\Models\\User', 1, 'myapptoken', '18f6d234d208d776e6b203357a5792284dd6633a8d4db0f836c4dd55e5c9aa90', '[\"*\"]', NULL, '2022-07-06 15:22:20', '2022-07-06 15:22:20'),
(34, 'App\\Models\\User', 1, 'myapptoken', '4d3d05c6314d7e8ebf51434bec43bb380b8cc546640d581860a36bfbbd972844', '[\"*\"]', NULL, '2022-07-06 15:22:21', '2022-07-06 15:22:21'),
(35, 'App\\Models\\User', 1, 'myapptoken', '49e4826496372ca26f05eca96316331b77622dbdfd0a311b17ba5c0a720dd3ef', '[\"*\"]', NULL, '2022-07-06 15:22:21', '2022-07-06 15:22:21'),
(36, 'App\\Models\\User', 1, 'myapptoken', '3083b22574a8b7aa70b4d365a10decb768e97f8fbdc4c90133529aaf03afe9b8', '[\"*\"]', NULL, '2022-07-06 15:22:21', '2022-07-06 15:22:21'),
(37, 'App\\Models\\User', 1, 'myapptoken', 'b809b6bde6690e2c6795c82890af43ed2affa154a04d496967c3e60911a4d2c5', '[\"*\"]', '2022-07-06 20:13:55', '2022-07-06 18:11:33', '2022-07-06 20:13:55'),
(38, 'App\\Models\\User', 1, 'myapptoken', 'f93af4d31e8e1953c790ee72be79216584840ac8da05d8eca8e6c0cb686dad5e', '[\"*\"]', '2022-07-13 19:11:08', '2022-07-13 19:02:40', '2022-07-13 19:11:08'),
(39, 'App\\Models\\User', 1, 'myapptoken', '3242b7b92d79a8ea94c57da17f107a5f47d2f23cdc1de7d395ce92b5417766bc', '[\"*\"]', '2022-07-16 13:05:07', '2022-07-13 19:02:42', '2022-07-16 13:05:07'),
(40, 'App\\Models\\User', 1, 'myapptoken', 'b99d6ac1dc078411338b82cd1453ef57c8b2ffacf3cbd07ff912f22472a05f4f', '[\"*\"]', '2022-07-17 07:20:12', '2022-07-17 07:09:59', '2022-07-17 07:20:12'),
(41, 'App\\Models\\User', 1, 'myapptoken', 'fb277865f3e02b6581cc53f27d3e62ca873108ed3378e54031c66e2b72cfdc39', '[\"*\"]', NULL, '2022-07-17 07:41:31', '2022-07-17 07:41:31'),
(42, 'App\\Models\\User', 1, 'myapptoken', '42e54d52ec68395ead2ecffa4f6ff1c5e8b3da57629e698c836f783778a50cdd', '[\"*\"]', NULL, '2022-08-06 23:13:30', '2022-08-06 23:13:30'),
(43, 'App\\Models\\User', 1, 'myapptoken', 'c00e70ae97ee122fb40e41741d9a0d495247566c8d66f4d068f615fe417b4897', '[\"*\"]', NULL, '2022-08-06 23:13:30', '2022-08-06 23:13:30'),
(44, 'App\\Models\\User', 1, 'myapptoken', '92f160debc917d61be70cb7913c715d3ab6e579ef13b28c576d1790ca030ac1d', '[\"*\"]', NULL, '2022-08-06 23:13:30', '2022-08-06 23:13:30'),
(45, 'App\\Models\\User', 1, 'myapptoken', 'b410db201e124118b60fd11440e33fc3d3b041d92ece57f61ff001c769282bad', '[\"*\"]', NULL, '2022-08-06 23:13:30', '2022-08-06 23:13:30'),
(46, 'App\\Models\\User', 1, 'myapptoken', '3be01345a3d8a5d08799b06b75fcdab4f964f91041086ef2be2bb94ebcb2035a', '[\"*\"]', NULL, '2022-08-06 23:13:30', '2022-08-06 23:13:30'),
(47, 'App\\Models\\User', 1, 'myapptoken', '11230bd635f25ab17d3a4620cafe65676fdb628d4e0fff7cf6e9f8757a6cff63', '[\"*\"]', NULL, '2022-08-06 23:13:30', '2022-08-06 23:13:30'),
(48, 'App\\Models\\User', 1, 'myapptoken', '95e334c6aa4a4d4f7ec85aa20f07636bdd3b834fce25864c6a839a133704a92d', '[\"*\"]', NULL, '2022-08-06 23:13:30', '2022-08-06 23:13:30'),
(49, 'App\\Models\\User', 1, 'myapptoken', '3bab2806e3aa808720cd1da8b8c94f8e58cea6bcd9329acd8eab529212ad8347', '[\"*\"]', NULL, '2022-08-06 23:13:30', '2022-08-06 23:13:30'),
(50, 'App\\Models\\User', 1, 'myapptoken', 'd69e302fb6a0cc0bbc99ebd3cf26d69ad2540b1ecc85f0b269c7b50b5baddd9a', '[\"*\"]', NULL, '2022-08-06 23:13:30', '2022-08-06 23:13:30'),
(51, 'App\\Models\\User', 1, 'myapptoken', '02541b958c5c40fb36be7a6d5b5b7e461e49da745a4950438ddde6a00eec0234', '[\"*\"]', NULL, '2022-08-06 23:13:31', '2022-08-06 23:13:31'),
(52, 'App\\Models\\User', 1, 'myapptoken', '58c0dca3cfac7db5a32c908362e16ad06be554440875cc63af706cf10f1bad51', '[\"*\"]', NULL, '2022-08-06 23:13:31', '2022-08-06 23:13:31'),
(53, 'App\\Models\\User', 1, 'myapptoken', '8ad5cc3d1427b3510dcb0b179cb02df7c2f52abcc3bbd0d037126c44c2085b4e', '[\"*\"]', '2022-08-06 23:17:18', '2022-08-06 23:13:31', '2022-08-06 23:17:18');

-- --------------------------------------------------------

--
-- Structure de la table `publish`
--

CREATE TABLE `publish` (
  `id` int(11) NOT NULL,
  `num_bon` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `type_pv` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `id_oblig` int(11) NOT NULL,
  `adressed` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `response` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `type_rqst` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` date NOT NULL DEFAULT current_timestamp(),
  `date_fin_commune` date DEFAULT NULL,
  `date_fin_tribunal` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `publish`
--

INSERT INTO `publish` (`id`, `num_bon`, `type_pv`, `id_oblig`, `adressed`, `response`, `type_rqst`, `created_at`, `date_fin_commune`, `date_fin_tribunal`) VALUES
(4, '2/21', 'تكليف بالوفاء', 116, 'زوجته', 'رفضت التوقيع والاستلام بدله', NULL, '2022-06-30', NULL, NULL),
(5, '10/22', 'تبليغ قرار', 210, 'أمه', 'رفضت التوقيع والاستلام بدله فأشعر برسالة مسجلة', NULL, '2022-07-13', NULL, NULL),
(6, '12/22', 'تبليغ جلسة', 213, 'أمه ', 'رفضت التوقيع والاستلام بدله فأشعر برسالة مسجلة', NULL, '2022-07-17', NULL, NULL);

--
-- Déclencheurs `publish`
--
DELIMITER $$
CREATE TRIGGER `update_publish_bon` AFTER UPDATE ON `publish` FOR EACH ROW BEGIN
IF (SELECT id FROM bon_acte WHERE num_bon = NEW.num_bon) IS NOT NULL THEN
UPDATE bon_acte SET last_updated = 1 WHERE num_bon=NEW.num_bon;
ELSEIF (SELECT id FROM bon_apercus WHERE num_bon = NEW.num_bon) IS NOT NULL THEN
UPDATE bon_apercus SET last_updated = 1 WHERE num_bon=NEW.num_bon;
ELSEIF (SELECT id FROM bon_apercu_parorders WHERE num_bon = NEW.num_bon) IS NOT NULL THEN
UPDATE bon_apercu_parorders SET last_updated = 1 WHERE num_bon=NEW.num_bon;
ELSEIF (SELECT id FROM bon_associations WHERE num_bon = NEW.num_bon) IS NOT NULL THEN
UPDATE bon_associations SET last_updated = 1 WHERE num_bon=NEW.num_bon;
ELSEIF (SELECT id FROM bon_autres WHERE num_bon = NEW.num_bon) IS NOT NULL THEN
UPDATE bon_autres SET last_updated = 1 WHERE num_bon=NEW.num_bon;
ELSEIF (SELECT id FROM bon_excuses WHERE num_bon = NEW.num_bon) IS NOT NULL THEN
UPDATE bon_excuses SET last_updated = 1 WHERE num_bon=NEW.num_bon;
ELSEIF (SELECT id FROM bon_mandat WHERE num_bon = NEW.num_bon) IS NOT NULL THEN
UPDATE bon_mandat SET last_updated = 1 WHERE num_bon=NEW.num_bon;
ELSEIF (SELECT id FROM bon_orders WHERE num_bon = NEW.num_bon) IS NOT NULL THEN
UPDATE bon_orders SET last_updated = 1 WHERE num_bon=NEW.num_bon;
ELSEIF (SELECT id FROM bon_provisions WHERE num_bon = NEW.num_bon) IS NOT NULL THEN
UPDATE bon_provisions SET last_updated = 1 WHERE num_bon=NEW.num_bon;
ELSEIF (SELECT id FROM bon_rqst WHERE num_bon = NEW.num_bon) IS NOT NULL THEN
UPDATE bon_rqst SET last_updated = 1 WHERE num_bon=NEW.num_bon;
ELSEIF (SELECT id FROM bon_seances WHERE num_bon = NEW.num_bon) IS NOT NULL THEN
UPDATE bon_seances SET last_updated = 1 WHERE num_bon=NEW.num_bon;
END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `rapports`
--

CREATE TABLE `rapports` (
  `id` int(11) NOT NULL,
  `id_rapport` int(11) NOT NULL,
  `type_rapport` varchar(20) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `record`
--

CREATE TABLE `record` (
  `id` int(11) NOT NULL,
  `inclus` mediumtext COLLATE utf8_unicode_ci NOT NULL,
  `mesure` mediumtext COLLATE utf8_unicode_ci NOT NULL,
  `id_rapport` int(11) NOT NULL,
  `type_rapport` varchar(20) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `nom` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` char(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `id` int(11) NOT NULL,
  `username` varchar(60) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`nom`, `prenom`, `password`, `address`, `phone`, `type`, `id`, `username`) VALUES
('karim', 'remmide', '$2a$12$7NSLPXT3mulBBdvFkhhSneFegWdCthi8w0/OjqcKmPMxWZgkbwbTS', 'gjdlgj', '025224180', 'admin', 1, 'karim'),
('moh', 'elfi', 'test012', 'sdlfk', '025221448', 'assit', 2, 'moh');

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `v1`
-- (Voir ci-dessous la vue réelle)
--
CREATE TABLE `v1` (
`type` varchar(13)
,`num_bon` varchar(10)
,`dem` varchar(50)
,`nom` varchar(50)
,`date` date
,`status` varchar(20)
,`created_at` datetime
);

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `v2`
-- (Voir ci-dessous la vue réelle)
--
CREATE TABLE `v2` (
`type` varchar(19)
,`num_bon` varchar(10)
,`nom` varchar(50)
,`date` date
,`status` varchar(20)
,`created_at` datetime
,`commission` varchar(40)
,`spec` varchar(30)
);

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `v3`
-- (Voir ci-dessous la vue réelle)
--
CREATE TABLE `v3` (
`type` varchar(10)
,`num_bon` varchar(8)
,`dem` varchar(50)
,`nom` varchar(50)
,`date` date
,`status` varchar(20)
,`created_at` timestamp
);

-- --------------------------------------------------------

--
-- Structure de la vue `v1`
--
DROP TABLE IF EXISTS `v1`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v1`  AS SELECT trim('تبليغ جلسة') AS `type`, `b`.`num_bon` AS `num_bon`, `d`.`nom` AS `dem`, `o`.`nom` AS `nom`, `o`.`date` AS `date`, `o`.`status` AS `status`, `b`.`created_at` AS `created_at` FROM ((`demandeur` `d` join `obligatoire` `o`) join `bon_seances` `b`) WHERE `o`.`id_bon` = `b`.`num_bon` AND `b`.`num_bon` = `d`.`id_bon` AND (`o`.`status` = 'منجزة' OR `o`.`status` = 'تم إرسال رسالة') ;

-- --------------------------------------------------------

--
-- Structure de la vue `v2`
--
DROP TABLE IF EXISTS `v2`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v2`  AS SELECT trim('تكليف بالوفاء/ حكم') AS `type`, `b`.`num_bon` AS `num_bon`, `o`.`nom` AS `nom`, `o`.`date` AS `date`, `o`.`status` AS `status`, `b`.`created_at` AS `created_at`, `b`.`commission` AS `commission`, `b`.`spec` AS `spec` FROM (`obligatoire` `o` join `bon_provisions` `b`) WHERE `o`.`id_bon` = `b`.`num_bon` AND `b`.`num_bon` in (select `notification_fidelité`.`id_provision` from `notification_fidelité`) AND `b`.`type` = 'حكم' ;

-- --------------------------------------------------------

--
-- Structure de la vue `v3`
--
DROP TABLE IF EXISTS `v3`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v3`  AS SELECT trim('تبليغ جلسة') AS `type`, `b`.`num_bon` AS `num_bon`, `d`.`nom` AS `dem`, `o`.`nom` AS `nom`, `o`.`date` AS `date`, `o`.`status` AS `status`, `b`.`created_at` AS `created_at` FROM ((`demandeur` `d` join `obligatoire` `o`) join `bon_seances` `b`) WHERE `o`.`id_bon` = `b`.`num_bon` AND `b`.`num_bon` = `d`.`id_bon` AND (`o`.`status` = 'منجزة' OR `o`.`status` = 'تم إرسال رسالة') ;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `actes`
--
ALTER TABLE `actes`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `action`
--
ALTER TABLE `action`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `bon_acte`
--
ALTER TABLE `bon_acte`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `bon_apercus`
--
ALTER TABLE `bon_apercus`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `bon_apercu_parorders`
--
ALTER TABLE `bon_apercu_parorders`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `bon_associations`
--
ALTER TABLE `bon_associations`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `bon_autres`
--
ALTER TABLE `bon_autres`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `bon_excuses`
--
ALTER TABLE `bon_excuses`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `bon_mandat`
--
ALTER TABLE `bon_mandat`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `bon_orders`
--
ALTER TABLE `bon_orders`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `bon_provisions`
--
ALTER TABLE `bon_provisions`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `bon_rqst`
--
ALTER TABLE `bon_rqst`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `bon_seances`
--
ALTER TABLE `bon_seances`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `demandeur`
--
ALTER TABLE `demandeur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `fichiers`
--
ALTER TABLE `fichiers`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `identif`
--
ALTER TABLE `identif`
  ADD PRIMARY KEY (`id`,`year`);

--
-- Index pour la table `jugements`
--
ALTER TABLE `jugements`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `letter`
--
ALTER TABLE `letter`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `migrations`
--
ALTER TABLE `migrations`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `notification_fidelité`
--
ALTER TABLE `notification_fidelité`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `obligatoire`
--
ALTER TABLE `obligatoire`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `personal_access_tokens`
--
ALTER TABLE `personal_access_tokens`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `personal_access_tokens_token_unique` (`token`),
  ADD KEY `personal_access_tokens_tokenable_type_tokenable_id_index` (`tokenable_type`,`tokenable_id`);

--
-- Index pour la table `publish`
--
ALTER TABLE `publish`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `rapports`
--
ALTER TABLE `rapports`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `record`
--
ALTER TABLE `record`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `actes`
--
ALTER TABLE `actes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `action`
--
ALTER TABLE `action`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT pour la table `bon_acte`
--
ALTER TABLE `bon_acte`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `bon_apercus`
--
ALTER TABLE `bon_apercus`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `bon_apercu_parorders`
--
ALTER TABLE `bon_apercu_parorders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `bon_associations`
--
ALTER TABLE `bon_associations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `bon_autres`
--
ALTER TABLE `bon_autres`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `bon_excuses`
--
ALTER TABLE `bon_excuses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `bon_mandat`
--
ALTER TABLE `bon_mandat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `bon_orders`
--
ALTER TABLE `bon_orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `bon_provisions`
--
ALTER TABLE `bon_provisions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT pour la table `bon_rqst`
--
ALTER TABLE `bon_rqst`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `bon_seances`
--
ALTER TABLE `bon_seances`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;

--
-- AUTO_INCREMENT pour la table `demandeur`
--
ALTER TABLE `demandeur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=264;

--
-- AUTO_INCREMENT pour la table `fichiers`
--
ALTER TABLE `fichiers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `jugements`
--
ALTER TABLE `jugements`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `letter`
--
ALTER TABLE `letter`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=86;

--
-- AUTO_INCREMENT pour la table `logs`
--
ALTER TABLE `logs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=125;

--
-- AUTO_INCREMENT pour la table `migrations`
--
ALTER TABLE `migrations`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `notification_fidelité`
--
ALTER TABLE `notification_fidelité`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT pour la table `obligatoire`
--
ALTER TABLE `obligatoire`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=219;

--
-- AUTO_INCREMENT pour la table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `personal_access_tokens`
--
ALTER TABLE `personal_access_tokens`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT pour la table `publish`
--
ALTER TABLE `publish`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `rapports`
--
ALTER TABLE `rapports`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `record`
--
ALTER TABLE `record`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
