-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-06-2022 a las 20:22:28
-- Versión del servidor: 10.4.19-MariaDB
-- Versión de PHP: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `vitrium`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulos`
--

CREATE TABLE `articulos` (
  `idArticulo` int(6) NOT NULL,
  `categoria` varchar(30) CHARACTER SET utf16 COLLATE utf16_spanish_ci NOT NULL,
  `precio` int(100) NOT NULL,
  `descripcion` varchar(1000) CHARACTER SET utf16 COLLATE utf16_spanish_ci NOT NULL,
  `path` varchar(250) CHARACTER SET utf16 COLLATE utf16_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `idCliente` int(11) NOT NULL,
  `nombre` varchar(50) CHARACTER SET utf16 COLLATE utf16_spanish_ci NOT NULL,
  `apellido1` varchar(50) CHARACTER SET utf16 COLLATE utf16_spanish_ci NOT NULL,
  `apellido2` varchar(50) CHARACTER SET utf16 COLLATE utf16_spanish_ci DEFAULT NULL,
  `provincia` varchar(50) CHARACTER SET utf16 COLLATE utf16_spanish_ci NOT NULL,
  `localidad` varchar(50) CHARACTER SET utf16 COLLATE utf16_spanish_ci NOT NULL,
  `direccion` varchar(100) CHARACTER SET utf16 COLLATE utf16_spanish_ci NOT NULL,
  `codigoPostal` int(5) NOT NULL,
  `email` varchar(50) CHARACTER SET utf16 COLLATE utf16_spanish_ci NOT NULL,
  `contraseña` varchar(20) CHARACTER SET utf16 COLLATE utf16_spanish_ci NOT NULL,
  `clase` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`idCliente`, `nombre`, `apellido1`, `apellido2`, `provincia`, `localidad`, `direccion`, `codigoPostal`, `email`, `contraseña`, `clase`) VALUES
(3, 'paco', 'asdvsdv', '', 'Madrid', 'asd', 'cfhcfhcf', 28922, 'sfzsvzdv@dsvsd.com', '123', 1),
(5, 'malo', 'asdvsdv', '', 'Madrid', 'asd', 'cfhcfhcf', 8123, 'a@a.ca', '123', NULL),
(6, 'malo', 'asdvsdv', '', 'Madrid', 'asd', 'cfhcfhcf', 7123, 'a@a.ca2', '123', NULL),
(7, 'dsvdv', 'sdvsd', '', '', 'asd', 'cfhcfhcf', 28922, 'wer@s.cs', '123', NULL),
(8, 'dsvdv', 'asdvsdv', '', 'undefined', 'asd', 'cfhcfhcf', 28922, 'b@b.bc', '123', NULL),
(10, 'dsvdv', 'asdvsdv', '', 'Madrid', 'asd', 'cfhcfhcf', 28922, 'c@c.cc', '123', NULL),
(12, 'dsvdv', 'asdvsdv', '', 'Madrid', 'asd', 'cfhcfhcf', 28922, 'd@d.dd', 'asd', NULL),
(13, 'dsvdv', 'asdvsdv', '', 'Madrid', 'asd', 'cfhcfhcf', 28922, 'e@ee.ee', '123', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturas`
--

CREATE TABLE `facturas` (
  `idFactura` int(11) NOT NULL,
  `idPedido` int(11) NOT NULL,
  `idCliente` int(11) NOT NULL,
  `idLinea` int(11) NOT NULL,
  `idArticulo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lineapedidos`
--

CREATE TABLE `lineapedidos` (
  `idLinea` int(11) NOT NULL,
  `idPedido` int(11) NOT NULL,
  `idArticulo` int(11) NOT NULL,
  `cantidad` int(4) NOT NULL,
  `gastosEnvio` int(11) NOT NULL,
  `precioFinal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `idPedido` int(11) NOT NULL,
  `idCliente` int(11) NOT NULL,
  `estadoPedido` varchar(30) CHARACTER SET utf16 COLLATE utf16_spanish_ci DEFAULT NULL,
  `fechaPed` date DEFAULT NULL,
  `importe`int(11)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `articulos`
--
ALTER TABLE `articulos`
  ADD PRIMARY KEY (`idArticulo`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`idCliente`),
  ADD UNIQUE KEY `UNIQUE` (`email`);

--
-- Indices de la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD PRIMARY KEY (`idFactura`),
  ADD KEY `fk_articulos_facturas` (`idArticulo`),
  ADD KEY `fk_pedidos_facturas` (`idPedido`),
  ADD KEY `fk_lineaPedidos_facturas` (`idLinea`),
  ADD KEY `fk_clientes_facturas` (`idCliente`);

--
-- Indices de la tabla `lineapedidos`
--
ALTER TABLE `lineapedidos`
  ADD PRIMARY KEY (`idLinea`),
  ADD KEY `fk_articulos_pedidos` (`idArticulo`);

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`idPedido`),
  ADD KEY `fk_clientes_pedidos` (`idCliente`),
  ADD KEY `fk-lineaPedidos_pedidos` (`idLinea`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `articulos`
--
ALTER TABLE `articulos`
  MODIFY `idArticulo` int(6) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `lineapedidos`
--
ALTER TABLE `lineapedidos`
  MODIFY `idLinea` int(11) NOT NULL AUTO_INCREMENT;
  
ALTER TABLE `pedidos`
  MODIFY `idPedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD CONSTRAINT `fk_articulos_facturas` FOREIGN KEY (`idArticulo`) REFERENCES `articulos` (`idArticulo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_clientes_facturas` FOREIGN KEY (`idCliente`) REFERENCES `clientes` (`idCliente`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_lineaPedidos_facturas` FOREIGN KEY (`idLinea`) REFERENCES `lineapedidos` (`idLinea`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_pedidos_facturas` FOREIGN KEY (`idPedido`) REFERENCES `pedidos` (`idPedido`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `lineapedidos`
--
ALTER TABLE `lineapedidos`
  ADD CONSTRAINT `fk_articulos_pedidos` FOREIGN KEY (`idArticulo`) REFERENCES `pedidos` (`idLinea`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `fk-lineaPedidos_pedidos` FOREIGN KEY (`idLinea`) REFERENCES `lineapedidos` (`idLinea`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_clientes_pedidos` FOREIGN KEY (`idCliente`) REFERENCES `clientes` (`idCliente`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
