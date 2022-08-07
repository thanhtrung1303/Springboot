-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: db
-- Thời gian đã tạo: Th8 07, 2022 lúc 08:35 AM
-- Phiên bản máy phục vụ: 8.0.29
-- Phiên bản PHP: 8.0.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `db-employer`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `applicant`
--

CREATE TABLE `applicant` (
  `id` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `job_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `applicant`
--

INSERT INTO `applicant` (`id`, `email`, `name`, `phone`, `job_id`) VALUES
('1', 'a@gmail.com', 'Nguyen van A', '092110912', '1'),
('2', 'b@gmail.com', 'Nguyen van b', '092110912', '2'),
('3', 'c@gmail.com', 'Nguyen van c', '092110912', '3');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `applicant_skill`
--

CREATE TABLE `applicant_skill` (
  `applicant_id` varchar(255) NOT NULL,
  `skill_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `applicant_skill`
--

INSERT INTO `applicant_skill` (`applicant_id`, `skill_id`) VALUES
('2', '2'),
('2', '1'),
('3', '4'),
('3', '2'),
('1', '2'),
('1', '3'),
('1', '4');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `employer`
--

CREATE TABLE `employer` (
  `id` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `logo_path` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `employer`
--

INSERT INTO `employer` (`id`, `email`, `logo_path`, `name`, `website`) VALUES
('employer1', 'fpt@gmail.com', 'fpt.png', 'FPT', 'https://fpt.com.vn'),
('employer2', 'vng@gmail.com', 'vng.png', 'VNG', 'https://vng.com.vn'),
('employer3', 'viettel@gmail.com', 'viettel.png', 'Viettel', 'https://vietteltelecom.vn');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `job`
--

CREATE TABLE `job` (
  `id` varchar(255) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `employer_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `job`
--

INSERT INTO `job` (`id`, `city`, `created_at`, `description`, `title`, `updated_at`, `employer_id`) VALUES
('1', 'Ho Chi Minh', '2022-08-07 14:08:05', 'backend', 'Lap trinh java', '2022-08-07 02:10:41', 'employer1'),
('2', 'Hai Phong', '2022-08-01 02:14:36', 'fullstack', 'Lap trinh fullstack', '2022-08-07 02:10:41', 'employer2'),
('3', 'Ha Noi', '2022-08-01 02:14:36', 'fontend', 'Lap trinh web', '2022-08-07 02:10:41', 'employer3');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `skill`
--

CREATE TABLE `skill` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `skill`
--

INSERT INTO `skill` (`id`, `name`) VALUES
('1', 'Java'),
('2', 'Csharp'),
('3', 'AWS'),
('4', 'SQL');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `applicant`
--
ALTER TABLE `applicant`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKt74cl2p3amxj0ukd6ngwdryvl` (`job_id`);

--
-- Chỉ mục cho bảng `applicant_skill`
--
ALTER TABLE `applicant_skill`
  ADD KEY `FKpnw48l4hsc6exjh61cc2dtb9k` (`skill_id`),
  ADD KEY `FKmjyemkwlx4cl17lm7udq7vgae` (`applicant_id`);

--
-- Chỉ mục cho bảng `employer`
--
ALTER TABLE `employer`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `job`
--
ALTER TABLE `job`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKnjbsumt44u8xps6yg5f16ynig` (`employer_id`);

--
-- Chỉ mục cho bảng `skill`
--
ALTER TABLE `skill`
  ADD PRIMARY KEY (`id`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `applicant`
--
ALTER TABLE `applicant`
  ADD CONSTRAINT `FKt74cl2p3amxj0ukd6ngwdryvl` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`);

--
-- Các ràng buộc cho bảng `applicant_skill`
--
ALTER TABLE `applicant_skill`
  ADD CONSTRAINT `FKmjyemkwlx4cl17lm7udq7vgae` FOREIGN KEY (`applicant_id`) REFERENCES `applicant` (`id`),
  ADD CONSTRAINT `FKpnw48l4hsc6exjh61cc2dtb9k` FOREIGN KEY (`skill_id`) REFERENCES `skill` (`id`);

--
-- Các ràng buộc cho bảng `job`
--
ALTER TABLE `job`
  ADD CONSTRAINT `FKnjbsumt44u8xps6yg5f16ynig` FOREIGN KEY (`employer_id`) REFERENCES `employer` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
