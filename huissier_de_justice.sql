-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 10 déc. 2021 à 09:55
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
  `num_bon` varchar(10) NOT NULL,
  `nom_notaire` varchar(40) CHARACTER SET utf32 COLLATE utf32_unicode_ci NOT NULL,
  `type_acte` varchar(30) CHARACTER SET utf32 COLLATE utf32_unicode_ci NOT NULL,
  `date` date NOT NULL,
  `created_at` date NOT NULL DEFAULT current_timestamp(),
  `prix` int(11) NOT NULL,
  `somme` int(11) NOT NULL,
  `num` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  `somme` int(11) NOT NULL DEFAULT 4500
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
  `somme` int(11) NOT NULL DEFAULT 4500
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `bon_apercu_parorders`
--

INSERT INTO `bon_apercu_parorders` (`id`, `num_bon`, `prix`, `status`, `date_fin`, `created_at`, `num_order`, `date_order`, `commission`, `somme`) VALUES
(1, '12/21', 4500, '', NULL, '2021-03-21 21:09:31', '234', '2021-03-20', 'محكمة : البليدة', 4500);

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
  `somme` int(11) NOT NULL DEFAULT 4500
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `bon_autres`
--

CREATE TABLE `bon_autres` (
  `id` int(11) NOT NULL,
  `num_bon` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `prix` int(11) NOT NULL,
  `status` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `date_fin` date DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `somme` int(11) NOT NULL DEFAULT 3000
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
  `somme` int(11) NOT NULL DEFAULT 1870
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `bon_excuses`
--

INSERT INTO `bon_excuses` (`id`, `num_bon`, `prix`, `status`, `date_fin`, `created_at`, `date_marquage`, `type`, `somme`) VALUES
(8, '20/21', 0, '', NULL, '2021-12-04 17:45:43', '2021-12-03', 'إعذار', 2000);

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
  `somme` int(11) NOT NULL DEFAULT 1500
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  `type` varchar(25) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `bon_orders`
--

INSERT INTO `bon_orders` (`id`, `num_bon`, `prix`, `status`, `date_fin`, `created_at`, `num_order`, `date_order`, `commission`, `somme`, `type`) VALUES
(6, '19/21', 6780, '', NULL, '2021-11-16 16:01:53', '2359/21', '2021-11-07', 'المحكمة الإدارية : البليدة', 6780, 'أمر إستعجالي');

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
  `somme` int(11) DEFAULT 1500
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `bon_provisions`
--

INSERT INTO `bon_provisions` (`id`, `num_bon`, `prix`, `status`, `date_fin`, `created_at`, `num_indice`, `num_table`, `date`, `commission`, `type`, `spec`, `somme`) VALUES
(22, '1/21', 13560, 'منجزة', '2021-03-10', '2021-10-03 17:02:03', '123/21', '56/21', '2021-03-11', 'مجلس قضاء : البليدة', 'حكم', 'الغرفة المدنية', 13560),
(23, '2/21', 6780, 'تم إرسال رسالة', NULL, '2021-11-01 20:00:47', '23/21', '443/21', '2021-03-11', 'محكمة : البليدة', 'حكم', 'القسم الاجتماعي', 6780),
(24, '3/21', 20340, '', NULL, '2021-10-03 17:02:32', '324/21', '45/21', '2021-03-06', 'محكمة : بوفاريك', 'حكم', 'القسم المدني', 25000),
(25, '4/21', 6780, '', NULL, '2021-10-03 17:02:39', '23/21', '366/21', '2021-03-18', 'محكمة : بوفاريك', 'حكم', 'القسم المدني', 6780),
(28, '16/21', 1500, '', NULL, '2021-09-08 10:44:09', '232/21', '1234/21', '2021-09-24', 'محكمة : حسين داي', 'حكم', 'القسم المدني', 1500);

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
  `somme` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  `somme` int(11) NOT NULL DEFAULT 3000
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `bon_seances`
--

INSERT INTO `bon_seances` (`id`, `num_bon`, `prix`, `status`, `date_fin`, `created_at`, `num_seance`, `type`, `commission`, `date_seance`, `date_report`, `date_report2`, `somme`) VALUES
(53, '8/21', 3000, 'منجزة', '2021-03-16', '2021-03-26 20:48:00', '1234567', 'القسم المدني', 'محكمة : البليدة', '2021-03-27', NULL, NULL, 3000),
(54, '15/21', 6000, 'تم إرسال رسالة', '2021-04-21', '2021-11-01 20:01:13', '554/21', 'القسم المدني', 'مجلس قضاء : البليدة', '2021-04-22', '2021-03-12', NULL, 6000),
(64, '17/21', 0, '', NULL, '2021-09-30 14:01:02', '2353/21', 'القسم المدني', 'محكمة : البليدة', '2021-09-23', NULL, NULL, 3000),
(66, '18/21', 1000, '', NULL, '2021-10-09 10:46:22', '3224/21', 'القسم الاجتماعي', 'محكمة : البليدة', '2021-10-01', '2021-10-09', '2021-10-22', 3000),
(72, '19/21', 3000, '', NULL, '2021-11-19 17:34:04', '1234/21', 'القسم المدني', 'محكمة : البليدة', '2021-11-06', '2021-11-12', NULL, 3000);

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
(236, 'دريوش علي ', 'حي دريوش بوعرفة البليدة', NULL, '19/21');

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
(2, 21),
(3, 21),
(4, 21),
(5, 21),
(6, 21),
(7, 21),
(8, 21),
(9, 21),
(10, 21),
(11, 21),
(12, 21),
(13, 21),
(14, 21),
(15, 21),
(16, 21),
(17, 21),
(18, 21),
(19, 21),
(20, 21);

-- --------------------------------------------------------

--
-- Structure de la table `logs`
--

CREATE TABLE `logs` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `time` timestamp NOT NULL DEFAULT current_timestamp(),
  `operation` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
(114, 'طهاري نور الدين', 'حي مجدوفة قرواو', NULL, NULL, 0, '1/21'),
(115, 'جيجي جعفر', 'حي الحوارش رقم 123 قرواو', NULL, NULL, 0, '1/21'),
(116, 'بلخلفة بلال', 'حي 1000 مسكن أولاد يعيش البليدة', 'تم إرسال رسالة', '2021-02-26', 1, '2/21'),
(117, 'دريوش محمد فريد', 'حي 13 ماي البليدة', 'منجزة', '2021-02-21', 0, '3/21'),
(118, 'بن قولال رمزي وسيم', 'حي 13 ماي البليدة', NULL, NULL, 0, '3/21'),
(120, 'فليسي الياس', 'حي بريان أمام مسجد ابن الأثير بلدية قرواو', NULL, NULL, 0, '4/21'),
(122, 'شسيبلا', 'يبلاتت', NULL, NULL, 0, '10/21'),
(123, 'البيبلات', 'سئيءبؤلات', NULL, NULL, 0, '11/21'),
(124, 'قثفغ', 'منت', NULL, NULL, 0, '13/21'),
(125, 'رلاىرؤ', 'نهتع', NULL, NULL, 0, '14/21'),
(126, 'شلقبيشلشيب', 'سيبلابلي', 'منجزة', '2021-02-16', 0, '8/21'),
(140, 'قويدر الزدام', 'اولاد يعيش', 'تم إرسال رسالة', '2021-04-03', 1, '15/21'),
(141, 'مراد بن ثامر', 'قرواو', NULL, NULL, 0, '9/21'),
(142, 'محمد السعيد بوعلام الله', 'الجزائر', NULL, NULL, 0, '15/21'),
(163, 'سيد أحمد', 'حسين داي', NULL, NULL, 0, '16/21'),
(164, 'شصثبقصثص', 'ثفلاللل', NULL, NULL, 0, '17/21'),
(174, 'فيصل بوبكري', 'حي طريق الحبس البليدة', NULL, NULL, 0, '18/21'),
(175, 'الطيب شيبوب', 'العفرون', NULL, NULL, 0, '18/21'),
(179, 'مراد بن ثامر', 'قرواو البليدة', NULL, NULL, 0, '18/21'),
(193, 'محمدي عدنان', 'حي 1670 مسكن بئر التوتة الجزائر', 'منجزة', '2021-12-01', 0, '19/21'),
(194, 'سيدي عيسى علي', 'مرمان البليدة', NULL, NULL, 0, '19/21');

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
-- Structure de la table `suspension`
--

CREATE TABLE `suspension` (
  `id` int(11) NOT NULL,
  `id_rapport` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `type_rapport` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `id_obligatoire` int(11) NOT NULL,
  `num_lettre` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `date_lettre` date NOT NULL,
  `proc` text COLLATE utf8_unicode_ci NOT NULL,
  `publier` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `suspension`
--

INSERT INTO `suspension` (`id`, `id_rapport`, `type_rapport`, `id_obligatoire`, `num_lettre`, `date_lettre`, `proc`, `publier`) VALUES
(35, '2/21', 'تكليف بالوفاء : محضر تكليف', 116, '234567324', '2021-03-18', '', 1),
(36, '2/21', 'تكليف بالوفاء : محضر تبليغ التكليف', 116, '23456765', '2021-04-17', '', 1),
(37, '2/21', 'تكليف بالوفاء : محضر تبليغ السند', 116, '2345678', '2021-03-13', '', 1),
(38, '15/21', 'تبليغ جلسة', 140, '234567564433', '2021-03-19', '', 1);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `username` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `nom` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` char(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` varchar(5) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
`id` int(11)
,`type` varchar(13)
,`num_bon` varchar(10)
,`dem` varchar(50)
,`nom` varchar(50)
,`date` date
,`status` varchar(20)
,`created_at` datetime
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

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v2`  AS SELECT `b`.`id` AS `id`, trim('تبليغ جلسة') AS `type`, `b`.`num_bon` AS `num_bon`, `d`.`nom` AS `dem`, `o`.`nom` AS `nom`, `o`.`date` AS `date`, `o`.`status` AS `status`, `b`.`created_at` AS `created_at` FROM ((`demandeur` `d` join `obligatoire` `o`) join `bon_seances` `b`) WHERE `o`.`id_bon` = `b`.`num_bon` AND `b`.`num_bon` = `d`.`id_bon` ;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `action`
--
ALTER TABLE `action`
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
-- Index pour la table `identif`
--
ALTER TABLE `identif`
  ADD PRIMARY KEY (`id`,`year`);

--
-- Index pour la table `logs`
--
ALTER TABLE `logs`
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
-- Index pour la table `suspension`
--
ALTER TABLE `suspension`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `action`
--
ALTER TABLE `action`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT pour la table `bon_apercus`
--
ALTER TABLE `bon_apercus`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `bon_apercu_parorders`
--
ALTER TABLE `bon_apercu_parorders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `bon_associations`
--
ALTER TABLE `bon_associations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `bon_autres`
--
ALTER TABLE `bon_autres`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `bon_excuses`
--
ALTER TABLE `bon_excuses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `bon_mandat`
--
ALTER TABLE `bon_mandat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `bon_orders`
--
ALTER TABLE `bon_orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `bon_provisions`
--
ALTER TABLE `bon_provisions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT pour la table `bon_rqst`
--
ALTER TABLE `bon_rqst`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `bon_seances`
--
ALTER TABLE `bon_seances`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=73;

--
-- AUTO_INCREMENT pour la table `demandeur`
--
ALTER TABLE `demandeur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=240;

--
-- AUTO_INCREMENT pour la table `logs`
--
ALTER TABLE `logs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `notification_fidelité`
--
ALTER TABLE `notification_fidelité`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT pour la table `obligatoire`
--
ALTER TABLE `obligatoire`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=198;

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
-- AUTO_INCREMENT pour la table `suspension`
--
ALTER TABLE `suspension`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
