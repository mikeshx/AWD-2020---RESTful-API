-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Ago 05, 2020 alle 15:30
-- Versione del server: 10.4.13-MariaDB
-- Versione PHP: 7.2.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `guida_tv`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `canale`
--

CREATE TABLE `canale` (
  `id_canale` int(11) NOT NULL,
  `nome` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `login`
--

CREATE TABLE `login` (
  `username` varchar(100) NOT NULL,
  `password` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `login`
--

INSERT INTO `login` (`username`, `password`) VALUES
('admin', '$2a$12$gEmD00lOmqTCwoECsZsFWui4SLPf5bcCqFwFUw9LcwDahLQYclLze');

-- --------------------------------------------------------

--
-- Struttura della tabella `palinsesto`
--

CREATE TABLE `palinsesto` (
  `id_palinsesto` int(11) NOT NULL,
  `id_canale` int(11) NOT NULL,
  `id_programma` varchar(500) NOT NULL,
  `ora_inizio` time NOT NULL,
  `ora_fine` time NOT NULL,
  `fascia_oraria` varchar(120) NOT NULL,
  `giorno_messa_in_onda` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `programma`
--

CREATE TABLE `programma` (
  `id_programma` int(11) NOT NULL,
  `titolo` varchar(500) NOT NULL,
  `descrizione` varchar(500) NOT NULL,
  `genere` int(255) NOT NULL,
  `scheda_approfondimento` varchar(800) DEFAULT NULL,
  `is_serie` tinyint(1) NOT NULL,
  `num_stagione_serie` int(11) DEFAULT NULL,
  `num_episodio_serie` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Informazioni relative a un singolo programma';

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `canale`
--
ALTER TABLE `canale`
  ADD PRIMARY KEY (`id_canale`);

--
-- Indici per le tabelle `login`
--
ALTER TABLE `login`
  ADD UNIQUE KEY `username` (`username`);

--
-- Indici per le tabelle `palinsesto`
--
ALTER TABLE `palinsesto`
  ADD PRIMARY KEY (`id_palinsesto`);

--
-- Indici per le tabelle `programma`
--
ALTER TABLE `programma`
  ADD PRIMARY KEY (`id_programma`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `canale`
--
ALTER TABLE `canale`
  MODIFY `id_canale` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `palinsesto`
--
ALTER TABLE `palinsesto`
  MODIFY `id_palinsesto` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `programma`
--
ALTER TABLE `programma`
  MODIFY `id_programma` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
