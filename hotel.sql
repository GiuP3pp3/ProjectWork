-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Set 26, 2022 alle 11:12
-- Versione del server: 10.4.24-MariaDB
-- Versione PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `camera`
--

CREATE TABLE `camera` (
  `id` int(11) NOT NULL,
  `numero_stanza` varchar(3) NOT NULL,
  `tipo` varchar(20) NOT NULL,
  `posti_letto` int(2) NOT NULL,
  `tariffa` double NOT NULL,
  `descrizione` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `camera`
--

INSERT INTO `camera` (`id`, `numero_stanza`, `tipo`, `posti_letto`, `tariffa`, `descrizione`) VALUES
(1, '101', 'singola', 1, 60, 'nord-vista lago'),
(2, '201', 'singola', 1, 60, 'nord-vista lago'),
(3, '207', 'singola', 1, 60, 'sud-vista montagna'),
(4, '107', 'singola', 1, 60, 'sud-vista montagna'),
(5, '105', 'singola', 1, 60, 'est'),
(6, '102', 'doppia', 2, 80, 'nord-vista lago'),
(7, '202', 'doppia', 2, 80, 'nord-vista lago'),
(8, '106', 'doppia', 2, 80, 'sud-vista montagna'),
(9, '209', 'doppia', 2, 80, 'sud-vista montagna'),
(10, '205', 'doppia', 2, 80, 'est'),
(11, '104', 'tripla', 3, 90, 'nord-vista lago'),
(12, '204', 'tripla', 3, 90, 'nord-vista lago'),
(13, '206', 'tripla', 3, 90, 'sud-vista montagna'),
(14, '208', 'suite', 5, 250, 'nord-vista mare'),
(15, '103', 'suite', 2, 200, 'sud-vista lago');

-- --------------------------------------------------------

--
-- Struttura della tabella `ospite`
--

CREATE TABLE `ospite` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `cognome` varchar(50) NOT NULL,
  `numero_documento` varchar(10) NOT NULL,
  `data_nascita` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `ospite`
--

INSERT INTO `ospite` (`id`, `nome`, `cognome`, `numero_documento`, `data_nascita`) VALUES
(1, 'Ottavio', 'Ferri', 'MQ63929901', '1973-01-06'),
(2, 'Livia', 'Verdi', 'PM58283383', '1971-09-25'),
(3, 'Paola', 'Bianchi', 'IA75433426', '1999-04-21'),
(4, 'Matteo', 'Buoso', 'MB3325671', '1987-05-14'),
(5, 'Livio', 'Cattaneo', 'QF44312062', '1994-04-27'),
(6, 'Gianfranco', 'Lucciano', 'RG92335157', '1972-08-18'),
(7, 'Anna', 'Marino', 'BC8305355', '2000-08-09'),
(8, 'Marta', 'Boni', 'BJ36739575', '1990-12-31');

-- --------------------------------------------------------

--
-- Struttura della tabella `prenotazione`
--

CREATE TABLE `prenotazione` (
  `id` int(11) NOT NULL,
  `check_in` date NOT NULL,
  `check_out` date NOT NULL,
  `numero_ospiti` int(11) NOT NULL,
  `prezzo_totale` double NOT NULL,
  `id_nominativo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `prenotazione`
--

INSERT INTO `prenotazione` (`id`, `check_in`, `check_out`, `numero_ospiti`, `prezzo_totale`, `id_nominativo`) VALUES
(1, '2022-09-28', '2022-10-03', 2, 300, 1),
(2, '2022-09-29', '2022-10-03', 3, 360, 2),
(3, '2022-10-03', '2022-10-09', 2, 480, 3),
(4, '2022-10-04', '2022-10-08', 3, 360, 4),
(5, '2022-09-30', '2022-10-02', 1, 120, 5),
(6, '2022-10-11', '2022-10-16', 2, 400, 6),
(7, '2022-09-30', '2022-10-02', 3, 180, 7),
(8, '2022-10-05', '2022-10-08', 3, 270, 8);

-- --------------------------------------------------------

--
-- Struttura della tabella `prenotazioni_camere`
--

CREATE TABLE `prenotazioni_camere` (
  `id_prenotazione` int(11) NOT NULL,
  `id_camera` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `prenotazioni_camere`
--

INSERT INTO `prenotazioni_camere` (`id_prenotazione`, `id_camera`) VALUES
(2, 12),
(3, 7),
(4, 12),
(5, 1),
(5, 1),
(5, 1),
(6, 8),
(7, 11),
(8, 11),
(1, 2);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `camera`
--
ALTER TABLE `camera`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `numero_stanza` (`numero_stanza`);

--
-- Indici per le tabelle `ospite`
--
ALTER TABLE `ospite`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `prenotazione`
--
ALTER TABLE `prenotazione`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ospite` (`id_nominativo`);

--
-- Indici per le tabelle `prenotazioni_camere`
--
ALTER TABLE `prenotazioni_camere`
  ADD KEY `prenotazione` (`id_prenotazione`),
  ADD KEY `camera` (`id_camera`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `camera`
--
ALTER TABLE `camera`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT per la tabella `ospite`
--
ALTER TABLE `ospite`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT per la tabella `prenotazione`
--
ALTER TABLE `prenotazione`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `prenotazione`
--
ALTER TABLE `prenotazione`
  ADD CONSTRAINT `ospite` FOREIGN KEY (`id_nominativo`) REFERENCES `ospite` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `prenotazioni_camere`
--
ALTER TABLE `prenotazioni_camere`
  ADD CONSTRAINT `camera` FOREIGN KEY (`id_camera`) REFERENCES `camera` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `prenotazione` FOREIGN KEY (`id_prenotazione`) REFERENCES `prenotazione` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
