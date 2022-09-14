-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Set 14, 2022 alle 11:58
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
  `id_camera` int(11) NOT NULL,
  `tipo` varchar(20) NOT NULL,
  `posti_letto` int(2) NOT NULL,
  `tariffa` double NOT NULL,
  `stato` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `ospite`
--

CREATE TABLE `ospite` (
  `id_ospite` int(11) NOT NULL,
  `nome` varchar(20) NOT NULL,
  `cognome` varchar(20) NOT NULL,
  `numero_documento` varchar(10) NOT NULL,
  `data_nascita` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `prenotazione`
--

CREATE TABLE `prenotazione` (
  `id_prenotazione` int(11) NOT NULL,
  `check_in` date NOT NULL,
  `check_out` date NOT NULL,
  `prezzo_totale` double NOT NULL,
  `id_nominativo` int(11) NOT NULL,
  `id_camera` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `tariffa`
--

CREATE TABLE `tariffa` (
  `id_tariffa` int(11) NOT NULL,
  `descrizione` varchar(50) DEFAULT NULL,
  `data_inizio` date DEFAULT NULL,
  `data_finale` date DEFAULT NULL,
  `importo` float(6,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `tariffa`
--

INSERT INTO `tariffa` (`id_tariffa`, `descrizione`, `data_inizio`, `data_finale`, `importo`) VALUES
(1, 'Tariffa singola bassa stagione', '2022-10-01', '2023-05-31', 60.00),
(2, 'Tariffa singola alta stagione', '2023-06-01', '2023-09-30', 90.00);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `camera`
--
ALTER TABLE `camera`
  ADD PRIMARY KEY (`id_camera`);

--
-- Indici per le tabelle `ospite`
--
ALTER TABLE `ospite`
  ADD PRIMARY KEY (`id_ospite`);

--
-- Indici per le tabelle `prenotazione`
--
ALTER TABLE `prenotazione`
  ADD PRIMARY KEY (`id_prenotazione`),
  ADD KEY `ospite` (`id_nominativo`),
  ADD KEY `camera` (`id_camera`);

--
-- Indici per le tabelle `tariffa`
--
ALTER TABLE `tariffa`
  ADD PRIMARY KEY (`id_tariffa`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `camera`
--
ALTER TABLE `camera`
  MODIFY `id_camera` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `ospite`
--
ALTER TABLE `ospite`
  MODIFY `id_ospite` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `prenotazione`
--
ALTER TABLE `prenotazione`
  MODIFY `id_prenotazione` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `tariffa`
--
ALTER TABLE `tariffa`
  MODIFY `id_tariffa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `prenotazione`
--
ALTER TABLE `prenotazione`
  ADD CONSTRAINT `camera` FOREIGN KEY (`id_camera`) REFERENCES `camera` (`id_camera`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ospite` FOREIGN KEY (`id_nominativo`) REFERENCES `ospite` (`id_ospite`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
