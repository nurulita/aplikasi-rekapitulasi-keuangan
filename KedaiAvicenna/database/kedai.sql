-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 25, 2021 at 12:43 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 7.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kedai`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `user_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(32) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `notelp` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `posisi` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`user_id`, `username`, `password`, `nama`, `alamat`, `notelp`, `email`, `posisi`) VALUES
(10001, 'admin', '1234', 'Nurulita', 'Depok', '088213806697', 'nurulita@gmail.com', 'Staff Keuangan'),
(10002, 'raditya', '1235', 'Radit Yudhistira', 'Cilodong', '088213806671', 'aufa@gmail.com', 'Manager'),
(10003, 'afandim', '1237', 'Afandi Muhammad', 'Jakarta Selatan', '088216722102', 'afandi@gmail.com', 'Kasir');

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `id_tenant` varchar(20) NOT NULL,
  `id_menu` varchar(20) NOT NULL,
  `namamenu` varchar(100) NOT NULL,
  `stok` int(11) NOT NULL,
  `jenis` varchar(50) NOT NULL,
  `harga` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`id_tenant`, `id_menu`, `namamenu`, `stok`, `jenis`, `harga`) VALUES
('T0001', 'MK0101', 'Surabi Cokelat Keju', 86, 'Makanan', 9000),
('T0001', 'MK0102', 'Surabi Durian', 20, 'Makanan', 10000),
('T0001', 'MK0103', 'Surabi Cokelat Kacang', 388, 'Makanan', 12000),
('T0002', 'MK0201', 'Nasi Rames', -11, 'Makanan', 18000),
('T0002', 'MK0202', 'Nasi Pecel', 14, 'Makanan', 10000),
('T0003', 'MK0301', 'Pecel Lele Saus Mangga', 194, 'Makanan', 20000),
('T0003', 'MK0302', 'Pecel Lele Asam Manis', 29, 'Makanan', 15000),
('T0004', 'MK0401', 'Fried Chicken Jeletot', 495, 'Makanan', 15000),
('T0004', 'MK0402', 'Fried Chicke Saus Matah', 5, 'Makanan', 18000),
('T0005', 'MK0501', 'Ayam Bakar Madu', 100, 'Makanan', 18000),
('T0005', 'MK0502', 'Ayam Bakar Sambal Ijo', 19, 'Makanan', 20000),
('T0006', 'MK0601', 'Ayam Geprek Sambel Ijo', 175, 'Makanan', 15000),
('T0007', 'MK0701', 'Siomay Bandung', 486, 'Makanan', 15000),
('T0007', 'MK0702', 'Paket Dimsum Mix', 17, 'Makanan', 25000),
('T0009', 'MK0901', 'Pancong Cokelat', 22, 'Makanan', 7000),
('T0009', 'MK0902', 'Pancong Keju Susu', 78, 'Makanan', 8000),
('T0010', 'MK1001', 'Nasi Goreng Seafood', -3, 'Makanan', 25000),
('T0010', 'MK1002', 'Nasi Goreng Kampung', 21, 'Makanan', 20000),
('T0011', 'MK1101', 'Seblak Komplit', 13, 'Makanan', 15000),
('T0012', 'MK1201', 'Ayam Bakar', 90, 'Makanan', 25000),
('T0001', 'MN0101', 'Jus Buah Naga', 25, 'Minuman', 10000),
('T0002', 'MN0201', 'Es Doger', 90, 'Minuman', 10000),
('T0005', 'MN0501', 'Es Cincau', 150, 'Minuman', 10000),
('T0006', 'MN0601', 'Teh Manis Hangat', 145, 'Minuman', 8000),
('T0007', 'MN0701', 'Teh Botol Sosro', 64, 'Minuman', 7000),
('T0008', 'MN0801', 'Kopi Susu Hazelnut', 75, 'Minuman', 15000),
('T0008', 'MN0802', 'Kopi Gula Aren', 26, 'Minuman', 17000),
('T0011', 'MN1101', 'Air Mineral', 95, 'Minuman', 5000);

-- --------------------------------------------------------

--
-- Table structure for table `pemesanan`
--

CREATE TABLE `pemesanan` (
  `no_pemesanan` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `id_tmp` int(11) NOT NULL,
  `tglpesan` date NOT NULL,
  `id_pemesanan` varchar(20) NOT NULL,
  `id_tenant` varchar(20) NOT NULL,
  `namatenant` varchar(50) NOT NULL,
  `no_meja` varchar(10) NOT NULL,
  `id_menu` varchar(20) NOT NULL,
  `namamenu` varchar(50) NOT NULL,
  `hargasatuan` double NOT NULL,
  `jumlahpesan` double NOT NULL,
  `subtotal` double NOT NULL,
  `total` double NOT NULL,
  `bayar` double NOT NULL,
  `kembalian` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pemesanan`
--

INSERT INTO `pemesanan` (`no_pemesanan`, `user_id`, `id_tmp`, `tglpesan`, `id_pemesanan`, `id_tenant`, `namatenant`, `no_meja`, `id_menu`, `namamenu`, `hargasatuan`, `jumlahpesan`, `subtotal`, `total`, `bayar`, `kembalian`) VALUES
(0, 10001, 3, '2021-07-17', 'P0065', 'T0006', 'Jagoan Geprek', '1', 'MK0601', 'Ayam Geprek Sambel Ijo', 15000, 4, 60000, 135000, 0, 0),
(1, 10001, 1, '2021-05-03', 'P0001', 'T0001', 'Surabi Misima', '1', 'MK0101', 'Surabi Cokelat Keju', 9000, 10, 90000, 630000, 650000, 20000),
(2, 10001, 2, '2021-05-03', 'P0001', 'T0002', 'Delicy MM', '1', 'MK0201', 'Nasi Rames', 18000, 30, 540000, 630000, 650000, 20000),
(3, 10001, 1, '2021-05-03', 'P0002', 'T0003', 'Pondok Lele Bang Ali', '2', 'MK0301', 'Pecel Lele Saus Mangga', 20000, 5, 100000, 100000, 0, 0),
(4, 10001, 1, '2021-05-03', 'P0003', 'T0001', 'Surabi Misima', '3', 'MK0101', 'Surabi Cokelat Keju', 9000, 2, 18000, 93000, 0, 0),
(5, 10001, 2, '2021-05-03', 'P0003', 'T0006', 'Jagoan Geprek', '4', 'MK0601', 'Ayam Geprek Sambel Ijo', 15000, 5, 75000, 93000, 0, 0),
(6, 10001, 1, '2021-05-03', 'P0004', 'T0004', 'Chick En Nin', '3', 'MK0401', 'Fried Chicken Jeletot', 15000, 3, 45000, 45000, 0, 0),
(7, 10001, 1, '2021-05-03', 'P0005', 'T0005', 'Athar Kitchen', '2', 'MK0501', 'Ayam Bakar Madu', 18000, 4, 72000, 72000, 0, 0),
(8, 10001, 1, '2021-05-06', 'P0006', 'T0008', 'Kopi Pertiga', '10', 'MN0801', 'Kopi Susu Hazelnut', 15000, 1, 15000, 15000, 0, 0),
(9, 10001, 1, '2021-05-06', 'P0007', 'T0009', 'Suke Pancong', '2', 'MK0901', 'Pancong Cokelat', 7000, 20, 140000, 140000, 0, 0),
(10, 10001, 1, '2021-05-06', 'P0008', 'T0001', 'Surabi Misima', '3', 'MK0101', 'Surabi Cokelat Keju', 9000, 2, 18000, 18000, 0, 0),
(11, 10001, 1, '2021-05-06', 'P0009', 'T0002', 'Delicy MM', '4', 'MK0201', 'Nasi Rames', 18000, 4, 72000, 204000, 0, 0),
(12, 10001, 2, '2021-05-06', 'P0009', 'T0002', 'Delicy MM', '4', 'MK0201', 'Nasi Rames', 18000, 4, 72000, 204000, 0, 0),
(13, 10001, 3, '2021-05-06', 'P0009', 'T0004', 'Chick En Nin', '4', 'MK0401', 'Fried Chicken Jeletot', 15000, 4, 60000, 204000, 0, 0),
(14, 10001, 1, '2021-05-06', 'P0010', 'T0005', 'Athar Kitchen', '4', 'MK0501', 'Ayam Bakar Madu', 18000, 2, 36000, 411000, 0, 0),
(15, 10001, 1, '2021-05-06', 'P0010', 'T0010', 'Warung Parahiyangan', '4', 'MK1001', 'Nasi Goreng Seafood', 25000, 10, 250000, 411000, 0, 0),
(16, 10001, 0, '2021-05-06', 'P0010', 'T0007', 'Dimsum 202', '4', 'MK0702', 'Paket Dimsum Mix', 25000, 5, 125000, 411000, 0, 0),
(17, 10001, 0, '2021-05-11', 'P0011', 'T0009', 'Suke Pancong', '9', 'MK0901', 'Pancong Cokelat', 7000, 7, 49000, 253000, 0, 0),
(18, 10001, 0, '2021-05-11', 'P0011', 'T0008', 'Kopi Pertiga', '9', 'MN0802', 'Kopi Gula Aren', 17000, 12, 204000, 253000, 0, 0),
(19, 10001, 0, '2021-05-11', 'P0012', 'T0005', 'Athar Kitchen', '12', 'MK0501', 'Ayam Bakar Madu', 18000, 5, 90000, 165000, 0, 0),
(20, 10001, 0, '2021-05-11', 'P0012', 'T0004', 'Chick En Nin', '13', 'MK0401', 'Fried Chicken Jeletot', 15000, 5, 75000, 165000, 0, 0),
(21, 10001, 0, '2021-05-11', 'P0013', 'T0007', 'Dimsum 202', '12', 'MK0701', 'Siomay Bandung', 15000, 12, 180000, 216000, 220000, 4000),
(22, 10001, 0, '2021-05-11', 'P0013', 'T0005', 'Athar Kitchen', '19', 'MK0501', 'Ayam Bakar Madu', 18000, 2, 36000, 216000, 220000, 4000),
(23, 10001, 0, '2021-05-11', 'P0014', 'T0010', 'Warung Parahiyangan', '12', 'MK1001', 'Nasi Goreng Seafood', 25000, 12, 300000, 360000, 0, 0),
(24, 10001, 0, '2021-05-11', 'P0014', 'T0011', 'Kedai Raka', '14', 'MN1101', 'Air Mineral', 5000, 12, 60000, 360000, 0, 0),
(25, 10001, 0, '2021-05-11', 'P0015', 'T0008', 'Kopi Pertiga', '7', 'MN0801', 'Kopi Susu Hazelnut', 15000, 6, 90000, 90000, 0, 0),
(26, 10001, 0, '2021-05-14', 'P0016', 'T0001', 'Surabi Misima', '8', 'MK0101', 'Surabi Cokelat Keju', 9000, 6, 54000, 54000, 0, 0),
(27, 10001, 0, '2021-05-14', 'P0017', 'T0006', 'Jagoan Geprek', '9', 'MK0601', 'Ayam Geprek Sambel Ijo', 15000, 8, 120000, 200000, 0, 0),
(28, 10001, 0, '2021-05-14', 'P0017', 'T0009', 'Suke Pancong', '9', 'MK0902', 'Pancong Keju Susu', 8000, 10, 80000, 200000, 0, 0),
(29, 10001, 0, '2021-05-14', 'P0018', 'T0005', 'Athar Kitchen', '10', 'MK0501', 'Ayam Bakar Madu', 18000, 12, 216000, 216000, 0, 0),
(30, 10001, 0, '2021-05-14', 'P0019', 'T0001', 'Surabi Misima', '10', 'MK0101', 'Surabi Cokelat Keju', 9000, 12, 108000, 208000, 0, 0),
(31, 10001, 0, '2021-05-14', 'P0019', 'T0002', 'Delicy MM', '10', 'MN0201', 'Es Doger', 10000, 10, 100000, 208000, 0, 0),
(32, 10001, 0, '2021-05-14', 'P0020', 'T0003', 'Pondok Lele Bang Ali', '1', 'MK0301', 'Pecel Lele Saus Mangga', 20000, 12, 240000, 456000, 0, 0),
(33, 10001, 0, '2021-05-14', 'P0020', 'T0004', 'Chick En Nin', '1', 'MK0402', 'Fried Chicke Saus Matah', 18000, 12, 216000, 456000, 0, 0),
(34, 10001, 0, '2021-05-18', 'P0021', 'T0004', 'Chick En Nin', '16', 'MK0401', 'Fried Chicken Jeletot', 15000, 5, 75000, 105000, 0, 0),
(35, 10001, 0, '2021-05-18', 'P0021', 'T0006', 'Jagoan Geprek', '24', 'MK0601', 'Ayam Geprek Sambel Ijo', 15000, 2, 30000, 105000, 0, 0),
(36, 10001, 0, '2021-05-18', 'P0022', 'T0008', 'Kopi Pertiga', '14', 'MN0801', 'Kopi Susu Hazelnut', 15000, 12, 180000, 180000, 0, 0),
(37, 10001, 0, '2021-05-18', 'P0023', 'T0011', 'Kedai Raka', '12', 'MK1101', 'Seblak Komplit', 15000, 5, 75000, 175000, 0, 0),
(38, 10001, 0, '2021-05-18', 'P0023', 'T0010', 'Warung Parahiyangan', '12', 'MK1002', 'Nasi Goreng Kampung', 20000, 5, 100000, 175000, 0, 0),
(39, 10001, 0, '2021-05-18', 'P0024', 'T0004', 'Chick En Nin', '6', 'MK0402', 'Fried Chicke Saus Matah', 18000, 12, 216000, 366000, 0, 0),
(40, 10001, 0, '2021-05-18', 'P0024', 'T0004', 'Chick En Nin', '6', 'MK0401', 'Fried Chicken Jeletot', 15000, 10, 150000, 366000, 0, 0),
(41, 10001, 0, '2021-05-18', 'P0025', 'T0002', 'Delicy MM', '7', 'MK0201', 'Nasi Rames', 18000, 10, 180000, 180000, 0, 0),
(42, 10001, 0, '2021-05-20', 'P0026', 'T0006', 'Jagoan Geprek', '9', 'MK0601', 'Ayam Geprek Sambel Ijo', 15000, 5, 75000, 165000, 0, 0),
(43, 10001, 0, '2021-05-20', 'P0026', 'T0007', 'Dimsum 202', '9', 'MK0701', 'Siomay Bandung', 15000, 6, 90000, 165000, 0, 0),
(44, 10001, 0, '2021-05-20', 'P0027', 'T0001', 'Surabi Misima', '1', 'MK0101', 'Surabi Cokelat Keju', 9000, 12, 108000, 108000, 0, 0),
(45, 10001, 0, '2021-05-20', 'P0028', 'T0009', 'Suke Pancong', '2', 'MK0901', 'Pancong Cokelat', 7000, 12, 84000, 84000, 0, 0),
(46, 10001, 0, '2021-05-20', 'P0029', 'T0003', 'Pondok Lele Bang Ali', '12', 'MK0301', 'Pecel Lele Saus Mangga', 20000, 2, 40000, 40000, 0, 0),
(47, 10001, 0, '2021-05-20', 'P0030', 'T0005', 'Athar Kitchen', '12', 'MK0501', 'Ayam Bakar Madu', 18000, 4, 72000, 72000, 0, 0),
(48, 10001, 0, '2021-05-24', 'P0031', 'T0001', 'Surabi Misima', '4', 'MK0101', 'Surabi Cokelat Keju', 9000, 12, 108000, 108000, 0, 0),
(49, 10001, 0, '2021-05-24', 'P0032', 'T0002', 'Delicy MM', '2', 'MK0202', 'Nasi Pecel', 10000, 12, 120000, 420000, 0, 0),
(50, 10001, 0, '2021-05-24', 'P0032', 'T0004', 'Chick En Nin', '4', 'MK0401', 'Fried Chicken Jeletot', 15000, 2, 30000, 420000, 0, 0),
(51, 10001, 0, '2021-05-24', 'P0032', 'T0005', 'Athar Kitchen', '4', 'MK0501', 'Ayam Bakar Madu', 18000, 15, 270000, 420000, 0, 0),
(52, 10001, 0, '2021-05-24', 'P0033', 'T0003', 'Pondok Lele Bang Ali', '5', 'MK0302', 'Pecel Lele Asam Manis', 15000, 6, 90000, 90000, 0, 0),
(53, 10001, 0, '2021-05-24', 'P0034', 'T0007', 'Dimsum 202', '7', 'MK0701', 'Siomay Bandung', 15000, 2, 30000, 183000, 0, 0),
(54, 10001, 0, '2021-05-24', 'P0034', 'T0009', 'Suke Pancong', '7', 'MK0901', 'Pancong Cokelat', 7000, 4, 28000, 183000, 0, 0),
(55, 10001, 0, '2021-05-24', 'P0034', 'T0010', 'Warung Parahiyangan', '7', 'MK1001', 'Nasi Goreng Seafood', 25000, 5, 125000, 183000, 0, 0),
(56, 10001, 0, '2021-05-24', 'P0035', 'T0011', 'Kedai Raka', '5', 'MN1101', 'Air Mineral', 5000, 6, 30000, 210000, 0, 0),
(57, 10001, 0, '2021-05-24', 'P0035', 'T0008', 'Kopi Pertiga', '5', 'MN0801', 'Kopi Susu Hazelnut', 15000, 12, 180000, 210000, 0, 0),
(58, 10001, 0, '2021-05-27', 'P0036', 'T0001', 'Surabi Misima', '12', 'MK0101', 'Surabi Cokelat Keju', 9000, 10, 90000, 285000, 0, 0),
(59, 10001, 0, '2021-05-27', 'P0036', 'T0002', 'Delicy MM', '12', 'MK0202', 'Nasi Pecel', 10000, 12, 120000, 285000, 0, 0),
(60, 10001, 0, '2021-05-27', 'P0036', 'T0003', 'Pondok Lele Bang Ali', '12', 'MK0302', 'Pecel Lele Asam Manis', 15000, 5, 75000, 285000, 0, 0),
(61, 10001, 0, '2021-05-27', 'P0037', 'T0006', 'Jagoan Geprek', '8', 'MK0601', 'Ayam Geprek Sambel Ijo', 15000, 12, 180000, 360000, 0, 0),
(62, 10001, 0, '2021-05-27', 'P0037', 'T0006', 'Jagoan Geprek', '8', 'MN0601', 'Teh Manis Hangat', 8000, 12, 96000, 360000, 0, 0),
(63, 10001, 0, '2021-05-27', 'P0037', 'T0009', 'Suke Pancong', '8', 'MK0901', 'Pancong Cokelat', 7000, 12, 84000, 360000, 0, 0),
(64, 10001, 0, '2021-05-27', 'P0038', 'T0008', 'Kopi Pertiga', '15', 'MN0801', 'Kopi Susu Hazelnut', 15000, 2, 30000, 30000, 0, 0),
(65, 10001, 0, '2021-05-27', 'P0039', 'T0007', 'Dimsum 202', '6', 'MK0702', 'Paket Dimsum Mix', 25000, 16, 400000, 512000, 0, 0),
(66, 10001, 0, '2021-05-27', 'P0039', 'T0007', 'Dimsum 202', '6', 'MN0701', 'Teh Botol Sosro', 7000, 16, 112000, 512000, 0, 0),
(67, 10001, 0, '2021-05-27', 'P0040', 'T0010', 'Warung Parahiyangan', '17', 'MK1001', 'Nasi Goreng Seafood', 25000, 1, 25000, 25000, 0, 0),
(68, 10001, 0, '2021-06-11', 'P0041', 'T0011', 'Kedai Raka', '18', 'MK1101', 'Seblak Komplit', 15000, 15, 225000, 350000, 0, 0),
(69, 10001, 0, '2021-06-11', 'P0041', 'T0010', 'Warung Parahiyangan', '18', 'MK1001', 'Nasi Goreng Seafood', 25000, 5, 125000, 350000, 0, 0),
(70, 10001, 0, '2021-06-11', 'P0042', 'T0005', 'Athar Kitchen', '4', 'MK0501', 'Ayam Bakar Madu', 18000, 12, 216000, 216000, 0, 0),
(71, 10001, 0, '2021-06-11', 'P0043', 'T0002', 'Delicy MM', '6', 'MK0201', 'Nasi Rames', 18000, 1, 18000, 33000, 0, 0),
(72, 10001, 0, '2021-06-11', 'P0043', 'T0007', 'Dimsum 202', '6', 'MK0701', 'Siomay Bandung', 15000, 1, 15000, 33000, 0, 0),
(73, 10001, 0, '2021-06-11', 'P0044', 'T0009', 'Suke Pancong', '12', 'MK0901', 'Pancong Cokelat', 7000, 7, 49000, 154000, 0, 0),
(74, 10001, 0, '2021-06-11', 'P0044', 'T0008', 'Kopi Pertiga', '12', 'MN0801', 'Kopi Susu Hazelnut', 15000, 7, 105000, 154000, 0, 0),
(75, 10001, 0, '2021-06-11', 'P0045', 'T0004', 'Chick En Nin', '10', 'MK0401', 'Fried Chicken Jeletot', 15000, 12, 180000, 330000, 0, 0),
(76, 10001, 0, '2021-06-11', 'P0045', 'T0001', 'Surabi Misima', '10', 'MK0102', 'Surabi Durian', 10000, 15, 150000, 330000, 0, 0),
(77, 10001, 0, '2021-06-11', 'P0046', 'T0001', 'Surabi Misima', '5', 'MK0101', 'Surabi Cokelat Keju', 9000, 5, 45000, 45000, 0, 0),
(78, 10001, 0, '2021-06-11', 'P0047', 'T0006', 'Jagoan Geprek', '20', 'MK0601', 'Ayam Geprek Sambel Ijo', 15000, 12, 180000, 180000, 0, 0),
(79, 10001, 0, '2021-06-11', 'P0048', 'T0005', 'Athar Kitchen', '5', 'MK0501', 'Ayam Bakar Madu', 18000, 13, 234000, 374000, 0, 0),
(80, 10001, 0, '2021-06-11', 'P0048', 'T0010', 'Warung Parahiyangan', '5', 'MK1002', 'Nasi Goreng Kampung', 20000, 7, 140000, 374000, 0, 0),
(81, 10001, 0, '2021-06-11', 'P0049', 'T0007', 'Dimsum 202', '9', 'MK0701', 'Siomay Bandung', 15000, 7, 105000, 180000, 0, 0),
(82, 10001, 0, '2021-06-11', 'P0049', 'T0004', 'Chick En Nin', '9', 'MK0401', 'Fried Chicken Jeletot', 15000, 5, 75000, 180000, 0, 0),
(83, 10001, 0, '2021-06-11', 'P0050', 'T0003', 'Pondok Lele Bang Ali', '9', 'MK0301', 'Pecel Lele Saus Mangga', 20000, 10, 200000, 290000, 0, 0),
(84, 10001, 0, '2021-06-11', 'P0050', 'T0005', 'Athar Kitchen', '8', 'MK0501', 'Ayam Bakar Madu', 18000, 5, 90000, 290000, 0, 0),
(85, 10001, 1, '2021-06-20', 'P0051', 'T0001', 'Surabi Misima', '2', 'MK0102', 'Surabi Durian', 10000, 5, 50000, 100000, 100000, 0),
(86, 10001, 2, '2021-06-20', 'P0051', 'T0002', 'Delicy MM', '2', 'MK0202', 'Nasi Pecel', 10000, 5, 50000, 100000, 100000, 0),
(87, 10001, 1, '2021-06-20', 'P0052', 'T0003', 'Pondok Lele Bang Ali', '3', 'MK0301', 'Pecel Lele Saus Mangga', 20000, 12, 240000, 420000, 500000, 80000),
(88, 10001, 2, '2021-06-20', 'P0052', 'T0004', 'Chick En Nin', '6', 'MK0401', 'Fried Chicken Jeletot', 15000, 12, 180000, 420000, 500000, 80000),
(89, 10001, 1, '2021-06-20', 'P0053', 'T0003', 'Pondok Lele Bang Ali', '1', 'MK0301', 'Pecel Lele Saus Mangga', 20000, 4, 80000, 170000, 0, 0),
(90, 10001, 2, '2021-06-20', 'P0053', 'T0005', 'Athar Kitchen', '1', 'MK0501', 'Ayam Bakar Madu', 18000, 5, 90000, 170000, 0, 0),
(91, 10001, 1, '2021-06-20', 'P0054', 'T0007', 'Dimsum 202', '5', 'MK0701', 'Siomay Bandung', 15000, 10, 150000, 378000, 400000, 22000),
(92, 10001, 2, '2021-06-20', 'P0054', 'T0006', 'Jagoan Geprek', '5', 'MK0601', 'Ayam Geprek Sambel Ijo', 15000, 12, 180000, 378000, 400000, 22000),
(93, 10001, 3, '2021-06-20', 'P0054', 'T0006', 'Jagoan Geprek', '5', 'MN0601', 'Teh Manis Hangat', 8000, 6, 48000, 378000, 400000, 22000),
(94, 10001, 1, '2021-06-20', 'P0055', 'T0007', 'Dimsum 202', '11', 'MK0701', 'Siomay Bandung', 15000, 10, 150000, 370000, 370000, 0),
(95, 10001, 2, '2021-06-20', 'P0055', 'T0005', 'Athar Kitchen', '11', 'MK0502', 'Ayam Bakar Sambal Ijo', 20000, 11, 220000, 370000, 370000, 0),
(96, 10001, 1, '2021-06-20', 'P0056', 'T0004', 'Chick En Nin', '12', 'MK0401', 'Fried Chicken Jeletot', 15000, 12, 180000, 180000, 200000, 20000),
(97, 10001, 1, '2021-06-20', 'P0057', 'T0004', 'Chick En Nin', '3', 'MK0401', 'Fried Chicken Jeletot', 15000, 5, 75000, 75000, 0, 0),
(98, 10001, 1, '2021-06-20', 'P0058', 'T0003', 'Pondok Lele Bang Ali', '6', 'MK0301', 'Pecel Lele Saus Mangga', 20000, 5, 100000, 100000, 100000, 0),
(99, 10001, 1, '2021-06-20', 'P0059', 'T0004', 'Chick En Nin', '7', 'MK0401', 'Fried Chicken Jeletot', 15000, 4, 60000, 485000, 0, 0),
(100, 10001, 2, '2021-06-20', 'P0059', 'T0012', 'Ayam Bakar Pak Raden', '7', 'MK1201', 'Ayam Bakar', 25000, 5, 125000, 485000, 0, 0),
(101, 10001, 3, '2021-06-20', 'P0059', 'T0011', 'Kedai Raka', '7', 'MK1101', 'Seblak Komplit', 15000, 10, 150000, 485000, 0, 0),
(102, 10001, 4, '2021-06-20', 'P0059', 'T0008', 'Kopi Pertiga', '7', 'MN0801', 'Kopi Susu Hazelnut', 15000, 10, 150000, 485000, 0, 0),
(103, 10001, 1, '2021-06-20', 'P0060', 'T0005', 'Athar Kitchen', '9', 'MK0501', 'Ayam Bakar Madu', 18000, 12, 216000, 311000, 315000, 4000),
(104, 10001, 2, '2021-06-20', 'P0060', 'T0009', 'Suke Pancong', '9', 'MK0901', 'Pancong Cokelat', 7000, 5, 35000, 311000, 315000, 4000),
(105, 10001, 3, '2021-06-20', 'P0060', 'T0010', 'Warung Parahiyangan', '9', 'MK1002', 'Nasi Goreng Kampung', 20000, 3, 60000, 311000, 315000, 4000),
(106, 10001, 1, '2021-06-20', 'P0061', 'T0003', 'Pondok Lele Bang Ali', '12', 'MK0301', 'Pecel Lele Saus Mangga', 20000, 5, 100000, 370000, 400000, 30000),
(107, 10001, 2, '2021-06-20', 'P0061', 'T0011', 'Kedai Raka', '12', 'MK1101', 'Seblak Komplit', 15000, 4, 60000, 370000, 400000, 30000),
(108, 10001, 3, '2021-06-20', 'P0061', 'T0010', 'Warung Parahiyangan', '12', 'MK1001', 'Nasi Goreng Seafood', 25000, 5, 125000, 370000, 400000, 30000),
(109, 10001, 4, '2021-06-20', 'P0061', 'T0008', 'Kopi Pertiga', '12', 'MN0802', 'Kopi Gula Aren', 17000, 5, 85000, 370000, 400000, 30000),
(110, 10001, 1, '2021-07-01', 'P0062', 'T0006', 'Jagoan Geprek', '4', 'MK0601', 'Ayam Geprek Sambel Ijo', 15000, 10, 150000, 1251000, 1300000, 49000),
(111, 10001, 2, '2021-07-01', 'P0062', 'T0003', 'Pondok Lele Bang Ali', '4', 'MK0301', 'Pecel Lele Saus Mangga', 20000, 4, 80000, 1251000, 1300000, 49000),
(112, 10001, 3, '2021-07-01', 'P0062', 'T0010', 'Warung Parahiyangan', '4', 'MK1001', 'Nasi Goreng Seafood', 25000, 12, 300000, 1251000, 1300000, 49000),
(113, 10001, 4, '2021-07-01', 'P0062', 'T0011', 'Kedai Raka', '4', 'MK1101', 'Seblak Komplit', 15000, 12, 180000, 1251000, 1300000, 49000),
(114, 10001, 5, '2021-07-01', 'P0062', 'T0012', 'Ayam Bakar Pak Raden', '4', 'MK1201', 'Ayam Bakar', 25000, 4, 100000, 1251000, 1300000, 49000),
(115, 10001, 6, '2021-07-01', 'P0062', 'T0009', 'Suke Pancong', '4', 'MK0901', 'Pancong Cokelat', 7000, 12, 84000, 1251000, 1300000, 49000),
(116, 10001, 7, '2021-07-01', 'P0062', 'T0008', 'Kopi Pertiga', '4', 'MN0801', 'Kopi Susu Hazelnut', 15000, 14, 210000, 1251000, 1300000, 49000),
(117, 10001, 8, '2021-07-01', 'P0062', 'T0008', 'Kopi Pertiga', '4', 'MN0801', 'Kopi Susu Hazelnut', 15000, 4, 60000, 1251000, 1300000, 49000),
(118, 10001, 9, '2021-07-01', 'P0062', 'T0006', 'Jagoan Geprek', '4', 'MK0601', 'Ayam Geprek Sambel Ijo', 15000, 1, 15000, 1251000, 1300000, 49000),
(119, 10001, 10, '2021-07-01', 'P0062', 'T0005', 'Athar Kitchen', '4', 'MK0501', 'Ayam Bakar Madu', 18000, 4, 72000, 1251000, 1300000, 49000),
(120, 10001, 1, '2021-07-01', 'P0063', 'T0002', 'Delicy MM', '1', 'MK0201', 'Nasi Rames', 18000, 4, 72000, 162000, 165000, 3000),
(121, 10001, 2, '2021-07-01', 'P0063', 'T0002', 'Delicy MM', '1', 'MK0201', 'Nasi Rames', 18000, 5, 90000, 162000, 165000, 3000),
(122, 10001, 1, '2021-07-01', 'P0064', 'T0011', 'Kedai Raka', '12', 'MK1101', 'Seblak Komplit', 15000, 12, 180000, 305000, 305000, 0),
(123, 10001, 2, '2021-07-01', 'P0064', 'T0010', 'Warung Parahiyangan', '5', 'MK1001', 'Nasi Goreng Seafood', 25000, 5, 125000, 305000, 305000, 0),
(124, 10001, 2, '2021-07-01', 'P0065', 'T0008', 'Kopi Pertiga', '1', 'MN0801', 'Kopi Susu Hazelnut', 15000, 5, 75000, 135000, 150000, 15000),
(126, 10001, 1, '2021-07-03', 'P0066', 'T0002', 'Delicy MM', '4', 'MK0201', 'Nasi Rames', 18000, 4, 72000, 197000, 200000, 3000),
(127, 10001, 2, '2021-07-03', 'P0066', 'T0010', 'Warung Parahiyangan', '5', 'MK1001', 'Nasi Goreng Seafood', 25000, 5, 125000, 197000, 200000, 3000),
(128, 10001, 1, '2021-07-03', 'P0067', 'T0004', 'Chick En Nin', '5', 'MK0401', 'Fried Chicken Jeletot', 15000, 3, 45000, 45000, 50000, 5000),
(129, 10001, 1, '2021-07-03', 'P0068', 'T0001', 'Surabi Misima', '5', 'MK0101', 'Surabi Cokelat Keju', 9000, 5, 45000, 105000, 110000, 5000),
(130, 10001, 2, '2021-07-03', 'P0068', 'T0004', 'Chick En Nin', '5', 'MK0401', 'Fried Chicken Jeletot', 15000, 4, 60000, 105000, 110000, 5000),
(131, 10001, 1, '2021-07-08', 'P0069', 'T0012', 'Ayam Bakar Pak Raden', '7', 'MK1201', 'Ayam Bakar', 25000, 5, 125000, 230000, 250000, 20000),
(132, 10001, 2, '2021-07-08', 'P0069', 'T0011', 'Kedai Raka', '7', 'MK1101', 'Seblak Komplit', 15000, 7, 105000, 230000, 250000, 20000),
(133, 10001, 1, '2021-07-08', 'P0070', 'T0010', 'Warung Parahiyangan', '5', 'MK1001', 'Nasi Goreng Seafood', 25000, 6, 150000, 225000, 225000, 0),
(134, 10001, 2, '2021-07-08', 'P0070', 'T0004', 'Chick En Nin', '5', 'MK0401', 'Fried Chicken Jeletot', 15000, 5, 75000, 225000, 225000, 0),
(135, 10001, 1, '2021-07-08', 'P0071', 'T0009', 'Suke Pancong', '8', 'MK0901', 'Pancong Cokelat', 7000, 7, 49000, 154000, 155000, 1000),
(136, 10001, 2, '2021-07-08', 'P0071', 'T0008', 'Kopi Pertiga', '8', 'MN0801', 'Kopi Susu Hazelnut', 15000, 7, 105000, 154000, 155000, 1000),
(137, 10001, 1, '2021-07-08', 'P0072', 'T0007', 'Dimsum 202', '9', 'MK0701', 'Siomay Bandung', 15000, 10, 150000, 375000, 400000, 25000),
(138, 10001, 2, '2021-07-08', 'P0072', 'T0006', 'Jagoan Geprek', '9', 'MK0601', 'Ayam Geprek Sambel Ijo', 15000, 15, 225000, 375000, 400000, 25000),
(139, 10001, 1, '2021-07-08', 'P0073', 'T0005', 'Athar Kitchen', '2', 'MK0501', 'Ayam Bakar Madu', 18000, 5, 90000, 165000, 200000, 35000),
(140, 10001, 2, '2021-07-08', 'P0073', 'T0004', 'Chick En Nin', '2', 'MK0401', 'Fried Chicken Jeletot', 15000, 5, 75000, 165000, 200000, 35000),
(141, 10001, 1, '2021-07-12', 'P0074', 'T0001', 'Surabi Misima', '10', 'MK0101', 'Surabi Cokelat Keju', 9000, 5, 45000, 1275000, 1300000, 25000),
(142, 10001, 2, '2021-07-12', 'P0074', 'T0002', 'Delicy MM', '10', 'MK0201', 'Nasi Rames', 18000, 5, 90000, 1275000, 1300000, 25000),
(143, 10001, 3, '2021-07-12', 'P0074', 'T0003', 'Pondok Lele Bang Ali', '10', 'MK0301', 'Pecel Lele Saus Mangga', 20000, 2, 40000, 1275000, 1300000, 25000),
(144, 10001, 4, '2021-07-12', 'P0074', 'T0003', 'Pondok Lele Bang Ali', '10', 'MK0301', 'Pecel Lele Saus Mangga', 20000, 5, 100000, 1275000, 1300000, 25000),
(145, 10001, 5, '2021-07-12', 'P0074', 'T0004', 'Chick En Nin', '10', 'MK0402', 'Fried Chicke Saus Matah', 18000, 20, 360000, 1275000, 1300000, 25000),
(146, 10001, 6, '2021-07-12', 'P0074', 'T0007', 'Dimsum 202', '10', 'MK0701', 'Siomay Bandung', 15000, 5, 75000, 1275000, 1300000, 25000),
(147, 10001, 7, '2021-07-12', 'P0074', 'T0012', 'Ayam Bakar Pak Raden', '10', 'MK1201', 'Ayam Bakar', 25000, 4, 100000, 1275000, 1300000, 25000),
(148, 10001, 8, '2021-07-12', 'P0074', 'T0002', 'Delicy MM', '10', 'MK0201', 'Nasi Rames', 18000, 5, 90000, 1275000, 1300000, 25000),
(149, 10001, 9, '2021-07-12', 'P0074', 'T0006', 'Jagoan Geprek', '10', 'MK0601', 'Ayam Geprek Sambel Ijo', 15000, 5, 75000, 1275000, 1300000, 25000),
(150, 10001, 10, '2021-07-12', 'P0074', 'T0008', 'Kopi Pertiga', '10', 'MN0801', 'Kopi Susu Hazelnut', 15000, 20, 300000, 1275000, 1300000, 25000),
(151, 10001, 1, '2021-07-17', 'P0075', 'T0001', 'Surabi Misima', '6', 'MK0101', 'Surabi Cokelat Keju', 9000, 12, 108000, 408000, 410000, 2000),
(152, 10001, 2, '2021-07-17', 'P0075', 'T0009', 'Suke Pancong', '6', 'MK0901', 'Pancong Cokelat', 7000, 5, 35000, 408000, 410000, 2000),
(153, 10001, 3, '2021-07-17', 'P0075', 'T0011', 'Kedai Raka', '6', 'MK1101', 'Seblak Komplit', 15000, 12, 180000, 408000, 410000, 2000),
(154, 10001, 4, '2021-07-17', 'P0075', 'T0007', 'Dimsum 202', '5', 'MK0701', 'Siomay Bandung', 15000, 4, 60000, 408000, 410000, 2000),
(155, 10001, 5, '2021-07-17', 'P0075', 'T0011', 'Kedai Raka', '5', 'MN1101', 'Air Mineral', 5000, 5, 25000, 408000, 410000, 2000),
(156, 10001, 1, '2021-07-19', 'P0076', 'T0012', 'Ayam Bakar Pak Raden', '10', 'MK1201', 'Ayam Bakar', 25000, 4, 100000, 830000, 1000000, 170000),
(157, 10001, 2, '2021-07-19', 'P0076', 'T0010', 'Warung Parahiyangan', '10', 'MK1002', 'Nasi Goreng Kampung', 20000, 14, 280000, 830000, 1000000, 170000),
(158, 10001, 3, '2021-07-19', 'P0076', 'T0009', 'Suke Pancong', '11', 'MK0902', 'Pancong Keju Susu', 8000, 20, 160000, 830000, 1000000, 170000),
(159, 10001, 4, '2021-07-19', 'P0076', 'T0009', 'Suke Pancong', '11', 'MK0901', 'Pancong Cokelat', 7000, 5, 35000, 830000, 1000000, 170000),
(160, 10001, 5, '2021-07-19', 'P0076', 'T0008', 'Kopi Pertiga', '11', 'MN0802', 'Kopi Gula Aren', 17000, 15, 255000, 830000, 1000000, 170000),
(161, 10001, 1, '2021-07-23', 'P0077', 'T0007', 'Dimsum 202', '8', 'MK0702', 'Paket Dimsum Mix', 25000, 12, 300000, 1001000, 1005000, 4000),
(162, 10001, 2, '2021-07-23', 'P0077', 'T0008', 'Kopi Pertiga', '8', 'MN0801', 'Kopi Susu Hazelnut', 15000, 5, 75000, 1001000, 1005000, 4000),
(163, 10001, 3, '2021-07-23', 'P0077', 'T0004', 'Chick En Nin', '8', 'MK0402', 'Fried Chicke Saus Matah', 18000, 7, 126000, 1001000, 1005000, 4000),
(164, 10001, 4, '2021-07-23', 'P0077', 'T0006', 'Jagoan Geprek', '8', 'MN0601', 'Teh Manis Hangat', 8000, 25, 200000, 1001000, 1005000, 4000),
(165, 10001, 5, '2021-07-23', 'P0077', 'T0002', 'Delicy MM', '8', 'MK0202', 'Nasi Pecel', 10000, 30, 300000, 1001000, 1005000, 4000);

-- --------------------------------------------------------

--
-- Table structure for table `pengeluaran`
--

CREATE TABLE `pengeluaran` (
  `id_pengeluaran` varchar(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `keterangan` varchar(100) NOT NULL,
  `jumlah` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pengeluaran`
--

INSERT INTO `pengeluaran` (`id_pengeluaran`, `user_id`, `tanggal`, `keterangan`, `jumlah`) VALUES
('K0001', 10001, '2021-05-13', 'Pembayaran Wifi', 100000),
('K0002', 10001, '2021-05-20', 'THR Tukang Parkir', 70000),
('K0003', 10001, '2021-06-09', 'Iuran Sampah', 50000),
('K0004', 0, '2021-06-09', 'Pembelian Paket Data', 75000),
('K0005', 10001, '2021-06-09', 'Pembelian Kantung Plastik', 20000),
('K0006', 10001, '2021-06-09', 'Pembayaran Wifi', 20000),
('K0007', 10001, '2021-06-09', 'Makan Karyawan', 100000),
('K0008', 10001, '2021-06-20', 'Snack Rapat', 50000),
('K0009', 10001, '2021-07-13', 'Pembayaran Wifi', 100000),
('K0010', 0, '2021-07-16', 'Snack Rapat Manajerial', 50000);

-- --------------------------------------------------------

--
-- Table structure for table `rekapitulasi_bulanan`
--

CREATE TABLE `rekapitulasi_bulanan` (
  `id_rekapbulanan` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `tanggalrekap` date NOT NULL,
  `totalpendapatan` double NOT NULL,
  `totalpengeluaran` double NOT NULL,
  `keuntungan` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rekapitulasi_bulanan`
--

INSERT INTO `rekapitulasi_bulanan` (`id_rekapbulanan`, `user_id`, `tanggalrekap`, `totalpendapatan`, `totalpengeluaran`, `keuntungan`) VALUES
(1, 10001, '2020-05-31', 382700, 170000, 212700),
(2, 10001, '2021-03-31', 8000000, 100000, 7900000),
(3, 10001, '2021-04-30', 5500000, 300000, 5200000),
(4, 10001, '2021-05-31', 766900, 170000, 596900);

-- --------------------------------------------------------

--
-- Table structure for table `rekapitulasi_harian`
--

CREATE TABLE `rekapitulasi_harian` (
  `id_rekapharian` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `id_tenant` varchar(20) NOT NULL,
  `namatenant` varchar(50) NOT NULL,
  `pendapatankotor` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rekapitulasi_harian`
--

INSERT INTO `rekapitulasi_harian` (`id_rekapharian`, `user_id`, `tanggal`, `id_tenant`, `namatenant`, `pendapatankotor`) VALUES
(1, 10001, '2021-05-03', 'T0001', 'Surabi Misima', 108000),
(2, 10001, '2021-05-03', 'T0002', 'Delicy MM', 540000),
(3, 10001, '2021-05-03', 'T0003', 'Pondok Lele Bang Ali', 100000),
(4, 10001, '2021-05-03', 'T0004', 'Chick En Nin', 45000),
(5, 10001, '2021-05-03', 'T0005', 'Athar Kitchen', 72000),
(6, 10001, '2021-05-03', 'T0006', 'Jagoan Geprek', 75000),
(7, 10001, '2021-05-06', 'T0001', 'Surabi Misima', 18000),
(8, 10001, '2021-05-06', 'T0002', 'Delicy MM', 144000),
(9, 10001, '2021-05-06', 'T0004', 'Chick En Nin', 60000),
(10, 10001, '2021-05-06', 'T0005', 'Athar Kitchen', 36000),
(11, 10001, '2021-05-06', 'T0007', 'Dimsum 202', 125000),
(12, 10001, '2021-05-06', 'T0008', 'Kopi Pertiga', 15000),
(13, 10001, '2021-05-06', 'T0009', 'Suke Pancong', 140000),
(14, 10001, '2021-05-06', 'T0010', 'Warung Parahiyangan', 250000),
(15, 10001, '2021-05-11', 'T0004', 'Chick En Nin', 75000),
(16, 10001, '2021-05-11', 'T0005', 'Athar Kitchen', 126000),
(17, 10001, '2021-05-11', 'T0007', 'Dimsum 202', 180000),
(18, 10001, '2021-05-11', 'T0008', 'Kopi Pertiga', 294000),
(19, 10001, '2021-05-11', 'T0009', 'Suke Pancong', 49000),
(20, 10001, '2021-05-11', 'T0010', 'Warung Parahiyangan', 300000),
(21, 10001, '2021-05-11', 'T0011', 'Kedai Raka', 60000),
(22, 10001, '2021-05-14', 'T0001', 'Surabi Misima', 162000),
(23, 10001, '2021-05-14', 'T0002', 'Delicy MM', 100000),
(24, 10001, '2021-05-14', 'T0003', 'Pondok Lele Bang Ali', 240000),
(25, 10001, '2021-05-14', 'T0004', 'Chick En Nin', 216000),
(26, 10001, '2021-05-14', 'T0005', 'Athar Kitchen', 216000),
(27, 10001, '2021-05-14', 'T0006', 'Jagoan Geprek', 120000),
(28, 10001, '2021-05-14', 'T0009', 'Suke Pancong', 80000),
(29, 10001, '2021-05-18', 'T0002', 'Delicy MM', 180000),
(30, 10001, '2021-05-18', 'T0004', 'Chick En Nin', 441000),
(31, 10001, '2021-05-18', 'T0006', 'Jagoan Geprek', 30000),
(32, 10001, '2021-05-18', 'T0008', 'Kopi Pertiga', 180000),
(33, 10001, '2021-05-18', 'T0010', 'Warung Parahiyangan', 100000),
(34, 10001, '2021-05-18', 'T0011', 'Kedai Raka', 75000),
(35, 10001, '2021-05-20', 'T0001', 'Surabi Misima', 108000),
(36, 10001, '2021-05-20', 'T0003', 'Pondok Lele Bang Ali', 40000),
(37, 10001, '2021-05-20', 'T0005', 'Athar Kitchen', 72000),
(38, 10001, '2021-05-20', 'T0006', 'Jagoan Geprek', 75000),
(39, 10001, '2021-05-20', 'T0007', 'Dimsum 202', 90000),
(40, 10001, '2021-05-20', 'T0009', 'Suke Pancong', 84000),
(41, 10001, '2021-05-24', 'T0001', 'Surabi Misima', 108000),
(42, 10001, '2021-05-24', 'T0003', 'Pondok Lele Bang Ali', 90000),
(43, 10001, '2021-05-24', 'T0002', 'Delicy MM', 120000),
(45, 10001, '2021-05-24', 'T0004', 'Chick En Nin', 30000),
(46, 10001, '2021-05-24', 'T0005', 'Athar Kitchen', 270000),
(47, 10001, '2021-05-24', 'T0007', 'Dimsum 202', 30000),
(48, 10001, '2021-05-24', 'T0008', 'Kopi Pertiga', 180000),
(49, 10001, '2021-05-24', 'T0009', 'Suke Pancong', 28000),
(50, 10001, '2021-05-24', 'T0010', 'Warung Parahiyangan', 125000),
(51, 10001, '2021-05-24', 'T0011', 'Kedai Raka', 30000),
(52, 10001, '2021-05-27', 'T0001', 'Surabi Misima', 90000),
(53, 10001, '2021-05-27', 'T0002', 'Delicy MM', 120000),
(54, 10001, '2021-05-27', 'T0003', 'Pondok Lele Bang Ali', 75000),
(55, 10001, '2021-05-27', 'T0006', 'Jagoan Geprek', 276000),
(56, 10001, '2021-05-27', 'T0007', 'Dimsum 202', 512000),
(57, 10001, '2021-05-27', 'T0008', 'Kopi Pertiga', 30000),
(58, 10001, '2021-05-27', 'T0009', 'Suke Pancong', 84000),
(59, 10001, '2021-05-27', 'T0010', 'Warung Parahiyangan', 25000),
(60, 10001, '2021-07-01', 'T0002', 'Delicy MM', 162000),
(61, 10001, '2021-07-01', 'T0003', 'Pondok Lele Bang Ali', 80000),
(62, 10001, '2021-07-01', 'T0005', 'Athar Kitchen', 72000),
(63, 10001, '2021-07-01', 'T0006', 'Jagoan Geprek', 165000),
(64, 10001, '2021-07-01', 'T0008', 'Kopi Pertiga', 345000),
(65, 10001, '2021-07-01', 'T0009', 'Suke Pancong', 84000),
(66, 10001, '2021-07-01', 'T0010', 'Warung Parahiyangan', 425000),
(68, 10001, '2021-07-01', 'T0011', 'Kedai Raka', 360000),
(69, 10001, '2021-07-01', 'T0012', 'Ayam Bakar Pak Raden', 100000),
(71, 10001, '2021-07-03', 'T0001', 'Surabi Misima', 45000),
(72, 10001, '2021-07-03', 'T0002', 'Delicy MM', 72000),
(73, 10001, '2021-07-03', 'T0004', 'Chick En Nin', 105000),
(74, 10001, '2021-07-03', 'T0010', 'Warung Parahiyangan', 125000),
(75, 10001, '2021-07-08', 'T0004', 'Chick En Nin', 150000),
(76, 10001, '2021-07-08', 'T0005', 'Athar Kitchen', 90000),
(77, 10001, '2021-07-08', 'T0006', 'Jagoan Geprek', 225000),
(78, 10001, '2021-07-08', 'T0007', 'Dimsum 202', 150000),
(79, 10001, '2021-07-08', 'T0008', 'Kopi Pertiga', 105000),
(80, 10001, '2021-07-08', 'T0009', 'Suke Pancong', 49000),
(81, 10001, '2021-07-08', 'T0010', 'Warung Parahiyangan', 150000),
(82, 10001, '2021-07-08', 'T0011', 'Kedai Raka', 105000),
(83, 10001, '2021-07-08', 'T0012', 'Ayam Bakar Pak Raden', 125000),
(84, 10001, '2021-07-12', 'T0001', 'Surabi Misima', 45000),
(85, 10001, '2021-07-12', 'T0002', 'Delicy MM', 180000),
(86, 10001, '2021-07-12', 'T0003', 'Pondok Lele Bang Ali', 140000),
(87, 10001, '2021-07-12', 'T0004', 'Chick En Nin', 360000),
(88, 10001, '2021-07-12', 'T0006', 'Jagoan Geprek', 75000),
(89, 10001, '2021-07-12', 'T0007', 'Dimsum 202', 75000),
(90, 10001, '2021-07-12', 'T0008', 'Kopi Pertiga', 300000),
(91, 10001, '2021-07-12', 'T0012', 'Ayam Bakar Pak Raden', 100000),
(92, 10001, '2021-07-17', 'T0001', 'Surabi Misima', 108000),
(93, 10001, '2021-07-17', 'T0006', 'Jagoan Geprek', 60000),
(94, 10001, '2021-07-17', 'T0007', 'Dimsum 202', 60000),
(95, 10001, '2021-07-17', 'T0009', 'Suke Pancong', 35000),
(96, 10001, '2021-07-17', 'T0011', 'Kedai Raka', 205000),
(97, 10001, '2021-07-19', 'T0008', 'Kopi Pertiga', 255000),
(98, 10001, '2021-07-19', 'T0009', 'Suke Pancong', 195000),
(99, 10001, '2021-07-19', 'T0010', 'Warung Parahiyangan', 280000),
(100, 10001, '2021-07-19', 'T0012', 'Ayam Bakar Pak Raden', 100000),
(101, 10001, '2021-07-23', 'T0002', 'Delicy MM', 300000),
(102, 10001, '2021-07-23', 'T0004', 'Chick En Nin', 126000),
(103, 10001, '2021-07-23', 'T0006', 'Jagoan Geprek', 200000),
(104, 10001, '2021-07-23', 'T0007', 'Dimsum 202', 300000),
(105, 10001, '2021-07-23', 'T0008', 'Kopi Pertiga', 75000);

-- --------------------------------------------------------

--
-- Table structure for table `rekapitulasi_mingguan`
--

CREATE TABLE `rekapitulasi_mingguan` (
  `id_rekapmingguan` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `tanggalmulai` date NOT NULL,
  `tanggalakhir` date NOT NULL,
  `id_tenant` varchar(20) NOT NULL,
  `namatenant` varchar(50) NOT NULL,
  `pendapatankotor` double NOT NULL,
  `potongan` double NOT NULL,
  `pendapatankedai` double NOT NULL,
  `pendapatanbersih` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rekapitulasi_mingguan`
--

INSERT INTO `rekapitulasi_mingguan` (`id_rekapmingguan`, `user_id`, `tanggalmulai`, `tanggalakhir`, `id_tenant`, `namatenant`, `pendapatankotor`, `potongan`, `pendapatankedai`, `pendapatanbersih`) VALUES
(8, 10001, '2021-05-01', '2021-05-07', 'T0002', 'Delicy MM', 684000, 0.1, 68400, 615600),
(9, 10001, '2021-05-01', '2021-05-07', 'T0003', 'Pondok Lele Bang Ali', 100000, 0.1, 10000, 90000),
(10, 10001, '2021-05-01', '2021-05-07', 'T0004', 'Chick En Nin', 105000, 0.1, 10500, 94500),
(11, 10001, '2021-05-01', '2021-05-07', 'T0005', 'Athar Kitchen', 108000, 0.1, 10800, 97200),
(12, 10001, '2021-05-01', '2021-05-07', 'T0006', 'Jagoan Geprek', 75000, 0.1, 7500, 67500),
(13, 10001, '2021-05-01', '2021-05-07', 'T0007', 'Dimsum 202', 125000, 0.1, 12500, 112500),
(14, 10001, '2021-05-01', '2021-05-07', 'T0008', 'Kopi Pertiga', 15000, 0.1, 1500, 13500),
(15, 10001, '2021-05-01', '2021-05-07', 'T0009', 'Suke Pancong', 140000, 0.1, 14000, 126000),
(16, 10001, '2021-05-01', '2021-05-07', 'T0010', 'Warung Parahiyangan', 250000, 0.1, 25000, 225000),
(17, 10001, '2021-05-01', '2021-05-07', '', '-', 0, 0, 50000, 0),
(18, 10001, '2021-05-08', '2021-05-14', 'T0001', 'Surabi Misima', 162000, 0.1, 12600, 113400),
(19, 10001, '2021-05-08', '2021-05-14', 'T0001', 'Surabi Misima', 162000, 0.1, 16200, 145800),
(20, 10001, '2021-05-08', '2021-05-14', 'T0002', 'Delicy MM', 100000, 0.1, 10000, 90000),
(21, 10001, '2021-05-08', '2021-05-14', 'T0003', 'Pondok Lele Bang Ali', 240000, 0.1, 24000, 216000),
(22, 10001, '2021-05-08', '2021-05-14', 'T0004', 'Chick En Nin', 291000, 0.1, 29100, 261900),
(23, 10001, '2021-05-08', '2021-05-14', 'T0005', 'Athar Kitchen', 342000, 0.1, 34200, 307800),
(24, 10001, '2021-05-08', '2021-05-14', 'T0006', 'Jagoan Geprek', 120000, 0.1, 12000, 108000),
(25, 10001, '2021-05-08', '2021-05-14', 'T0007', 'Dimsum 202', 180000, 0.1, 18000, 162000),
(26, 10001, '2021-05-08', '2021-05-14', 'T0008', 'Kopi Pertiga', 294000, 0.1, 29400, 264600),
(27, 10001, '2021-05-08', '2021-05-14', 'T0009', 'Suke Pancong', 129000, 0.1, 12900, 116100),
(28, 10001, '2021-05-08', '2021-05-14', 'T0010', 'Warung Parahiyangan', 300000, 0.1, 30000, 270000),
(29, 10001, '2021-05-08', '2021-05-14', 'T0011', 'Kedai Raka', 60000, 0.1, 6000, 54000),
(30, 10001, '2021-05-08', '2021-05-14', 'T0011', '-', 0, 0, 100000, 0),
(31, 10001, '2021-05-22', '2021-05-28', 'T0001', 'Surabi Misima', 198000, 0.1, 19800, 178200),
(32, 10001, '2021-05-22', '2021-05-28', 'T0002', 'Delicy MM', 240000, 0.1, 24000, 216000),
(33, 10001, '2021-05-22', '2021-05-28', 'T0003', 'Pondok Lele Bang Ali', 165000, 0.1, 16500, 148500),
(34, 10001, '2021-05-22', '2021-05-28', 'T0004', 'Chick En Nin', 30000, 0.1, 3000, 27000),
(35, 10001, '2021-05-22', '2021-05-28', 'T0005', 'Athar Kitchen', 270000, 0.1, 27000, 243000),
(36, 10001, '2021-05-22', '2021-05-28', 'T0006', 'Jagoan Geprek', 276000, 0.1, 27600, 248400),
(37, 10001, '2021-05-22', '2021-05-28', 'T0007', 'Dimsum 202', 542000, 0.1, 54200, 487800),
(38, 10001, '2021-05-22', '2021-05-28', 'T0008', 'Kopi Pertiga', 210000, 0.1, 21000, 189000),
(39, 10001, '2021-05-22', '2021-05-28', 'T0009', 'Suke Pancong', 112000, 0.1, 11200, 100800),
(40, 10001, '2021-05-22', '2021-05-28', 'T0010', 'Warung Parahiyangan', 150000, 0.1, 15000, 135000),
(41, 10001, '2021-05-22', '2021-05-28', 'T0011', 'Kedai Raka', 30000, 0.1, 3000, 27000),
(42, 10001, '2021-07-01', '2021-07-03', 'T0001', 'Surabi Misima', 45000, 0.1, 4500, 40500),
(43, 10001, '2021-07-01', '2021-07-03', 'T0002', 'Delicy MM', 234000, 0.1, 23400, 210600),
(44, 10001, '2021-07-01', '2021-07-03', 'T0003', 'Pondok Lele Bang Ali', 80000, 0.1, 8000, 72000),
(45, 10001, '2021-07-01', '2021-07-03', 'T0004', 'Chick En Nin', 105000, 0.1, 10500, 94500),
(46, 10001, '2021-07-01', '2021-07-03', 'T0005', 'Athar Kitchen', 72000, 0.1, 7200, 64800),
(47, 10001, '2021-07-01', '2021-07-03', 'T0006', 'Jagoan Geprek', 165000, 0.1, 16500, 148500),
(48, 10001, '2021-07-01', '2021-07-03', 'T0008', 'Kopi Pertiga', 345000, 0.1, 34500, 310500),
(49, 10001, '2021-07-01', '2021-07-03', 'T0009', 'Suke Pancong', 84000, 0.1, 8400, 75600),
(50, 10001, '2021-07-01', '2021-07-03', 'T0010', 'Warung Parahiyangan', 550000, 0.1, 55000, 495000),
(51, 10001, '2021-07-01', '2021-07-03', 'T0011', 'Kedai Raka', 360000, 0.1, 36000, 324000),
(52, 10001, '2021-07-01', '2021-07-03', 'T0012', 'Ayam Bakar Pak Raden', 100000, 0.1, 10000, 90000),
(53, 10001, '2021-07-04', '2021-07-10', 'T0004', 'Chick En Nin', 150000, 0.1, 15000, 135000),
(54, 10001, '2021-07-04', '2021-07-10', 'T0005', 'Athar Kitchen', 90000, 0.1, 9000, 81000),
(55, 10001, '2021-07-04', '2021-07-10', 'T0006', 'Jagoan Geprek', 225000, 0.1, 22500, 202500),
(56, 10001, '2021-07-04', '2021-07-10', 'T0006', 'Jagoan Geprek', 225000, 0.1, 22500, 202500),
(57, 10001, '2021-07-04', '2021-07-10', 'T0007', 'Dimsum 202', 150000, 0.1, 15000, 135000),
(58, 10001, '2021-07-04', '2021-07-10', 'T0008', 'Kopi Pertiga', 105000, 0.1, 10500, 94500),
(59, 10001, '2021-07-04', '2021-07-10', 'T0009', 'Suke Pancong', 49000, 0.1, 4900, 44100),
(60, 10001, '2021-07-04', '2021-07-10', 'T0010', 'Warung Parahiyangan', 150000, 0.1, 15000, 135000),
(61, 10001, '2021-07-04', '2021-07-10', 'T0011', 'Kedai Raka', 105000, 0.1, 10500, 94500),
(62, 10001, '2021-07-04', '2021-07-10', 'T0012', 'Ayam Bakar Pak Raden', 125000, 0.1, 12500, 112500),
(63, 10001, '2021-07-11', '2021-07-17', 'T0001', 'Surabi Misima', 153000, 0.1, 15300, 137700),
(64, 10001, '2021-07-11', '2021-07-17', 'T0002', 'Delicy MM', 180000, 0.1, 18000, 162000),
(65, 10001, '2021-07-11', '2021-07-17', 'T0003', 'Pondok Lele Bang Ali', 140000, 0.1, 14000, 126000),
(66, 10001, '2021-07-11', '2021-07-17', 'T0004', 'Chick En Nin', 360000, 0.1, 36000, 324000),
(67, 10001, '2021-07-11', '2021-07-17', 'T0006', 'Jagoan Geprek', 135000, 0.1, 13500, 121500),
(68, 10001, '2021-07-11', '2021-07-17', 'T0007', 'Dimsum 202', 135000, 0.1, 13500, 121500),
(69, 10001, '2021-07-11', '2021-07-17', 'T0008', 'Kopi Pertiga', 300000, 0.1, 30000, 270000),
(70, 10001, '2021-07-11', '2021-07-17', 'T0009', 'Suke Pancong', 35000, 0.1, 3500, 31500),
(71, 10001, '2021-07-11', '2021-07-17', 'T0011', 'Kedai Raka', 205000, 0.1, 20500, 184500),
(72, 10001, '2021-07-11', '2021-07-17', 'T0012', 'Ayam Bakar Pak Raden', 100000, 0.1, 10000, 90000),
(73, 10001, '2021-07-11', '2021-07-17', '', '-', 0, 0, 50000, 0),
(74, 10001, '2021-07-11', '2021-07-17', '', '-', 0, 0, 0, 0),
(75, 10001, '2021-07-18', '2021-07-24', 'T0002', 'Delicy MM', 300000, 0.1, 30000, 270000),
(76, 10001, '2021-07-18', '2021-07-24', 'T0004', 'Chick En Nin', 126000, 0.1, 12600, 113400),
(77, 10001, '2021-07-18', '2021-07-24', 'T0006', 'Jagoan Geprek', 200000, 0.1, 20000, 180000),
(78, 10001, '2021-07-18', '2021-07-24', 'T0007', 'Dimsum 202', 300000, 0.1, 30000, 270000),
(79, 10001, '2021-07-18', '2021-07-24', 'T0008', 'Kopi Pertiga', 330000, 0.1, 33000, 297000),
(80, 10001, '2021-07-18', '2021-07-24', 'T0009', 'Suke Pancong', 195000, 0.1, 19500, 175500),
(81, 10001, '2021-07-18', '2021-07-24', 'T0010', 'Warung Parahiyangan', 280000, 0.1, 28000, 252000),
(82, 10001, '2021-07-18', '2021-07-24', 'T0012', 'Ayam Bakar Pak Raden', 100000, 0.1, 10000, 90000),
(83, 10001, '2021-07-18', '2021-07-24', '', '-', 0, 0, 50000, 0),
(84, 10001, '2021-07-18', '2021-07-24', '', '-', 0, 0, 50000, 0),
(85, 10001, '2021-07-18', '2021-07-24', '', '-', 0, 0, 0, 0),
(86, 10001, '2021-07-18', '2021-07-24', '', '-', 0, 0, 50000, 0),
(94, 10001, '2021-07-25', '2021-07-31', 'T0001', 'Surabi Misima', 252000, 0.1, 25200, 226800);

-- --------------------------------------------------------

--
-- Table structure for table `reservasi_tempat`
--

CREATE TABLE `reservasi_tempat` (
  `id_reservasi` varchar(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  `acara` varchar(100) NOT NULL,
  `atasnama` varchar(100) NOT NULL,
  `notelp` varchar(20) NOT NULL,
  `tanggal` date NOT NULL,
  `waktu` varchar(20) NOT NULL,
  `jumlahorg` int(11) NOT NULL,
  `biayatambahan` double NOT NULL,
  `letak` varchar(50) NOT NULL,
  `catatan` varchar(100) NOT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `reservasi_tempat`
--

INSERT INTO `reservasi_tempat` (`id_reservasi`, `user_id`, `acara`, `atasnama`, `notelp`, `tanggal`, `waktu`, `jumlahorg`, `biayatambahan`, `letak`, `catatan`, `status`) VALUES
('R0001', 10001, 'Family Gathering', 'Suprianto', '081728112892', '2021-05-06', '10.00-17.00', 100, 50000, 'Lantai I dan II', 'Pesan Nasi Box 100', 'Booked'),
('R0002', 10001, 'Kompetisi Mobile Legend', 'Hendri Setiawan', '085672727710', '2021-05-09', '10.00-13.00', 200, 100000, 'Lantai I dan II', '-', 'Booked'),
('R0003', 10001, 'Resepsi Pernikahan', 'Reza Julianto', '08176257719', '2021-05-21', '13.00-16.00', 100, 50000, 'Lantai I dan II', 'Order Tenant Chick en Nin, Suke Pancong, Warung Parahiyangan', 'Booked'),
('R0004', 10001, 'Family Gathering', 'Adit Julianto Wibowo', '08127928812', '2021-06-26', '10.00-13.00', 150, 50000, 'Lantai I dan II', '-', 'Booked'),
('R0005', 10001, 'Family Gathering SMPN 6 Depok', 'Rangga', '088127381191', '2021-07-17', '14.00-17.00', 150, 50000, 'Lantai I dan II', '-', 'Booked'),
('R0006', 10001, 'Kompetisi Fire Fire ', 'Kwardy Raditya', '08172884921', '2021-07-18', '10.00-15.00', 100, 50000, 'Lantai I dan II', 'Pesan Minuman Teh Botol 100', 'Booked'),
('R0007', 10001, 'Makan Bersama Panti Asuhan Assalam', 'Yudhi Anugrah', '08671728813', '2021-07-12', '10.00-13.00', 50, 0, 'Lantai II', 'Pesan Nasi Kotak 70', 'Booked'),
('R0008', 10001, 'Resepsi Pernikahan Adi dan Ratih', 'Adi Gilang', '088183927712', '2021-07-24', '10.00-15.00', 100, 50000, 'Lantai I dan II', 'Booking semua tenant', 'Booked'),
('R0009', 10001, 'Family Gathering', 'Hilman', '0881293739271', '2021-07-22', '10.00-14.00', 30, 0, 'Lantai I', '', 'Booked'),
('R0010', 10001, 'Family Gathering', 'Tommi Mulya', '0812438829117', '2021-07-23', '19.00-21.00', 100, 50000, 'Lantai I dan II', '', 'Booked');

-- --------------------------------------------------------

--
-- Table structure for table `temporary_pemesanan`
--

CREATE TABLE `temporary_pemesanan` (
  `id_tmp` int(11) NOT NULL,
  `id_pemesanan` varchar(20) NOT NULL,
  `no_meja` varchar(10) NOT NULL,
  `id_menu` varchar(20) NOT NULL,
  `namamenu` varchar(50) NOT NULL,
  `hargasatuan` double NOT NULL,
  `jumlahpesan` double NOT NULL,
  `subtotal` double NOT NULL,
  `id_tenant` varchar(20) NOT NULL,
  `namatenant` varchar(50) NOT NULL,
  `bayar` double NOT NULL,
  `kembalian` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `temporary_pemesanan`
--

INSERT INTO `temporary_pemesanan` (`id_tmp`, `id_pemesanan`, `no_meja`, `id_menu`, `namamenu`, `hargasatuan`, `jumlahpesan`, `subtotal`, `id_tenant`, `namatenant`, `bayar`, `kembalian`) VALUES
(1, 'P0083', '2', 'MK0101', 'Surabi Cokelat Keju', 9000, 5, 45000, 'T0001', 'Surabi Misima', 0, 0),
(2, 'P0083', '5', 'MK0201', 'Nasi Rames', 18000, 5, 90000, 'T0002', 'Delicy MM', 0, 0),
(3, 'P0083', '2', 'MK0301', 'Pecel Lele Saus Mangga', 20000, 5, 100000, 'T0003', 'Pondok Lele Bang Ali', 0, 0),
(4, 'P0083', '2', 'MK0401', 'Fried Chicken Jeletot', 15000, 5, 75000, 'T0004', 'Chick En Nin', 0, 0),
(5, 'P0083', '2', 'MK0501', 'Ayam Bakar Madu', 18000, 5, 90000, 'T0005', 'Athar Kitchen', 0, 0),
(6, 'P0083', '2', 'MK0601', 'Ayam Geprek Sambel Ijo', 15000, 5, 75000, 'T0006', 'Jagoan Geprek', 0, 0),
(7, 'P0083', '2', 'MK0901', 'Pancong Cokelat', 7000, 5, 35000, 'T0009', 'Suke Pancong', 0, 0),
(8, 'P0083', '2', 'MK1101', 'Seblak Komplit', 15000, 5, 75000, 'T0011', 'Kedai Raka', 0, 0),
(9, 'P0083', '2', 'MK0901', 'Pancong Cokelat', 7000, 5, 35000, 'T0009', 'Suke Pancong', 0, 0),
(10, 'P0083', '2', 'MK1001', 'Nasi Goreng Seafood', 25000, 5, 125000, 'T0010', 'Warung Parahiyangan', 0, 0);

--
-- Triggers `temporary_pemesanan`
--
DELIMITER $$
CREATE TRIGGER `batal` AFTER DELETE ON `temporary_pemesanan` FOR EACH ROW BEGIN
UPDATE menu SET stok = stok + OLD.jumlahpesan
WHERE id_menu = OLD.id_menu;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `beli` AFTER INSERT ON `temporary_pemesanan` FOR EACH ROW BEGIN 
UPDATE menu SET stok = stok - new.jumlahpesan 
WHERE id_menu = new.`id_menu`; 
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tenant`
--

CREATE TABLE `tenant` (
  `id_tenant` varchar(32) NOT NULL,
  `namatenant` varchar(100) NOT NULL,
  `penanggungjawab` varchar(100) NOT NULL,
  `notlp` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tenant`
--

INSERT INTO `tenant` (`id_tenant`, `namatenant`, `penanggungjawab`, `notlp`) VALUES
('T0001', 'Surabi Misima', 'Hendi R', '085718330822'),
('T0002', 'Delicy MM', 'Audi M', '085718328821'),
('T0003', 'Pondok Lele Bang Ali', 'Ali Suryo', '081572839917'),
('T0004', 'Chick En Nin', 'Ratih Setiawati', '081562897721'),
('T0005', 'Athar Kitchen', 'Athar', '089652771827'),
('T0006', 'Jagoan Geprek', 'Ali Muslih Wahyudin', '087881927719'),
('T0007', 'Dimsum 202', 'Ari Hermawan', '081287290056'),
('T0008', 'Kopi Pertiga', 'Nur Setiawan', '088281992811'),
('T0009', 'Suke Pancong', 'Hari Jumantov', '081726682271'),
('T0010', 'Warung Parahiyangan', 'Asep Komarudin', '0813289180992'),
('T0011', 'Kedai Raka', 'Raka Budi Prakoso', '085672281192'),
('T0012', 'Ayam Bakar Pak Raden', 'Radit', '08816247721');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`id_menu`);

--
-- Indexes for table `pemesanan`
--
ALTER TABLE `pemesanan`
  ADD PRIMARY KEY (`no_pemesanan`);

--
-- Indexes for table `pengeluaran`
--
ALTER TABLE `pengeluaran`
  ADD PRIMARY KEY (`id_pengeluaran`);

--
-- Indexes for table `rekapitulasi_bulanan`
--
ALTER TABLE `rekapitulasi_bulanan`
  ADD PRIMARY KEY (`id_rekapbulanan`);

--
-- Indexes for table `rekapitulasi_harian`
--
ALTER TABLE `rekapitulasi_harian`
  ADD PRIMARY KEY (`id_rekapharian`);

--
-- Indexes for table `rekapitulasi_mingguan`
--
ALTER TABLE `rekapitulasi_mingguan`
  ADD PRIMARY KEY (`id_rekapmingguan`);

--
-- Indexes for table `reservasi_tempat`
--
ALTER TABLE `reservasi_tempat`
  ADD PRIMARY KEY (`id_reservasi`);

--
-- Indexes for table `temporary_pemesanan`
--
ALTER TABLE `temporary_pemesanan`
  ADD PRIMARY KEY (`id_tmp`);

--
-- Indexes for table `tenant`
--
ALTER TABLE `tenant`
  ADD PRIMARY KEY (`id_tenant`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pemesanan`
--
ALTER TABLE `pemesanan`
  MODIFY `no_pemesanan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=194;

--
-- AUTO_INCREMENT for table `rekapitulasi_bulanan`
--
ALTER TABLE `rekapitulasi_bulanan`
  MODIFY `id_rekapbulanan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `rekapitulasi_harian`
--
ALTER TABLE `rekapitulasi_harian`
  MODIFY `id_rekapharian` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=127;

--
-- AUTO_INCREMENT for table `rekapitulasi_mingguan`
--
ALTER TABLE `rekapitulasi_mingguan`
  MODIFY `id_rekapmingguan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=105;

--
-- AUTO_INCREMENT for table `temporary_pemesanan`
--
ALTER TABLE `temporary_pemesanan`
  MODIFY `id_tmp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
