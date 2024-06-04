-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-06-2024 a las 06:29:03
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

CREATE DATABASE IF NOT EXISTS ecommerce_db;

USE ecommerce_db;


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ecommerce-db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria_producto`
--

CREATE TABLE `categoria_producto` (
  `id_categoria_producto` bigint(20) NOT NULL,
  `nombre_categoria` varchar(60) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categoria_producto`
--

INSERT INTO `categoria_producto` (`id_categoria_producto`, `nombre_categoria`, `descripcion`) VALUES
(1, 'TECNOLOGIA', 'Ecuentra los productos de la ultima tecnolgia y mayor calidad del mercado!'),
(2, 'BELLEZA', 'Encuentra los mejores productos para el ciudado de tu piel y tu belleza!'),
(3, 'HOGAR', 'Tenemos para ti los mejores articulos para tu hogar!'),
(4, 'AUTOMOVIL', 'Tenemos los mejroes accesorios para tu carro, al mejor precio!'),
(5, 'DEPORTE', 'Tenemos los mejores equipamientos para que practiques tus deportes favoritos!');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudad`
--

CREATE TABLE `ciudad` (
  `id_ciudad` bigint(20) NOT NULL,
  `nombre` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id_cliente` bigint(20) NOT NULL,
  `id_usuario` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id_cliente`, `id_usuario`) VALUES
(1, 1),
(6, 2),
(7, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente_frecuente`
--

CREATE TABLE `cliente_frecuente` (
  `estado` bit(1) NOT NULL,
  `id_cliente` bigint(20) NOT NULL,
  `id_cliente_frecuente` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente_frecuente`
--

INSERT INTO `cliente_frecuente` (`estado`, `id_cliente`, `id_cliente_frecuente`) VALUES
(b'1', 1, 4),
(b'1', 6, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `descuentos`
--

CREATE TABLE `descuentos` (
  `estado` bit(1) NOT NULL,
  `hora_fin` time(6) DEFAULT NULL,
  `hora_inicio` time(6) DEFAULT NULL,
  `porcentaje_descuento` double NOT NULL,
  `fecha_fin` datetime(6) DEFAULT NULL,
  `fecha_inicio` datetime(6) DEFAULT NULL,
  `id_descuento` bigint(20) NOT NULL,
  `id_tipo_descuento` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `descuentos`
--

INSERT INTO `descuentos` (`estado`, `hora_fin`, `hora_inicio`, `porcentaje_descuento`, `fecha_fin`, `fecha_inicio`, `id_descuento`, `id_tipo_descuento`) VALUES
(b'1', NULL, NULL, 1, NULL, NULL, 1, 1),
(b'1', '03:00:00.000000', '01:00:00.000000', 0.1, NULL, NULL, 2, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_orden`
--

CREATE TABLE `detalle_orden` (
  `cantidad` int(11) NOT NULL,
  `subtotal` double NOT NULL,
  `id_detalle_orden` bigint(20) NOT NULL,
  `id_orden` bigint(20) NOT NULL,
  `id_producto` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalle_orden`
--

INSERT INTO `detalle_orden` (`cantidad`, `subtotal`, `id_detalle_orden`, `id_orden`, `id_producto`) VALUES
(1, 10000, 2, 1, 2),
(5, 15400, 3, 2, 1),
(10, 15400, 4, 2, 1),
(78, 15400, 5, 3, 1),
(95, 1500, 6, 4, 1),
(7, 15400, 7, 5, 2),
(71, 480000, 8, 5, 2),
(8, 157451, 9, 10, 2),
(9, 15400, 10, 11, 2),
(5, 15400, 11, 8, 3),
(5, 15400, 12, 7, 3),
(5, 15400, 13, 9, 3),
(5, 15400, 14, 7, 4),
(5, 15400, 15, 15, 4),
(5, 15400, 16, 7, 5),
(5, 15400, 17, 8, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direccion`
--

CREATE TABLE `direccion` (
  `id_cliente` bigint(20) NOT NULL,
  `id_direccion` bigint(20) NOT NULL,
  `id_localidad` bigint(20) NOT NULL,
  `direccion` varchar(70) NOT NULL,
  `detalle` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `localidad`
--

CREATE TABLE `localidad` (
  `id_ciudad` bigint(20) NOT NULL,
  `id_localidad` bigint(20) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `orden`
--

CREATE TABLE `orden` (
  `total` double NOT NULL,
  `fecha_venta` datetime(6) DEFAULT NULL,
  `id_cliente` bigint(20) NOT NULL,
  `id_descuento` bigint(20) NOT NULL,
  `id_orden` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `orden`
--

INSERT INTO `orden` (`total`, `fecha_venta`, `id_cliente`, `id_descuento`, `id_orden`) VALUES
(800000, '2024-06-03 18:23:04.000000', 1, 1, 1),
(10000, '2024-06-03 18:23:04.000000', 1, 1, 2),
(10000, '2024-06-03 18:23:04.000000', 1, 1, 3),
(10000, '2024-06-03 18:23:04.000000', 1, 1, 4),
(10000, '2024-06-03 18:23:04.000000', 1, 1, 5),
(10000, '2024-06-03 18:23:04.000000', 1, 1, 6),
(10000, '2024-06-03 18:23:04.000000', 1, 1, 7),
(10000, '2024-06-03 18:23:04.000000', 1, 1, 8),
(80000, '2024-06-03 18:23:04.000000', 6, 1, 9),
(80000, '2024-06-03 18:23:04.000000', 6, 1, 10),
(80000, '2024-06-03 18:23:04.000000', 6, 1, 11),
(80000, '2024-06-03 18:23:04.000000', 6, 1, 12),
(80000, '2024-06-03 18:23:04.000000', 6, 1, 13),
(80000, '2024-06-03 18:23:04.000000', 6, 1, 14),
(80000, '2024-06-03 18:23:04.000000', 6, 1, 15);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `perfil`
--

CREATE TABLE `perfil` (
  `id_perfil` bigint(20) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `perfil`
--

INSERT INTO `perfil` (`id_perfil`, `descripcion`, `nombre`) VALUES
(1, 'Este perfil tiene acceso a toda la aplicacion', 'ROLE_ADMINISTRADOR'),
(2, 'unicamente tiene acceso a productos y categoria productos', 'ROLE_VENDEDOR'),
(3, 'El cliente podra registrar sus ordenes, consultar productos y consultar personas y usuarios', 'ROLE_CLIENTE'),
(4, 'Podra ver los reportes creados para analisar', 'ROLE_ANALISTA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `id_persona` bigint(20) NOT NULL,
  `id_tipo_documento` bigint(20) NOT NULL,
  `apellido` varchar(60) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `numero_documento` varchar(60) NOT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`fecha_creacion`, `id_persona`, `id_tipo_documento`, `apellido`, `nombre`, `numero_documento`, `correo`, `telefono`) VALUES
('2024-06-03 17:48:06.000000', 2, 1, 'Pelaez', 'Juan', '1003678423', 'juanpe@gmail.com', '3124597846'),
('2024-06-03 17:49:37.000000', 3, 1, 'Alvarez Avila', 'Andres Felipe', '1003691117', 'andres@gmail.com', '3124597846'),
('2024-06-03 22:27:44.000000', 4, 1, 'Pava Julio', 'Juan Sebastian', '1003678424', 'pavajuan@gmail.com', '3001574360');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `estado` bit(1) NOT NULL,
  `precio` double NOT NULL,
  `stock` int(11) NOT NULL,
  `id_categoria_producto` bigint(20) NOT NULL,
  `id_producto` bigint(20) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`estado`, `precio`, `stock`, `id_categoria_producto`, `id_producto`, `descripcion`, `nombre`) VALUES
(b'1', 150000, 74, 1, 1, 'Disfruta de miles de horas de juego sin interrupcion con nuestro Mouse Logitech Sr5000', 'Mouse Logitech Sr5000'),
(b'1', 10000, 500, 2, 2, 'Luce mas sensual con nuestro labial rosa pasion', 'Labial dama rosa'),
(b'1', 800000, 42, 3, 3, 'Lleva este centro de mesa hermoso para tu sala', 'Centro de mesa'),
(b'1', 2000000, 50, 4, 4, 'Lleva las mejores luces les para que no sufras cuando conduces de noche', 'Luces led(Farolas)'),
(b'0', 180000, 150, 5, 5, 'Practica tu deporte con los mejores guantes calidad-precio del mercado', 'Guantes de Boxeo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `id_rol` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_descuento`
--

CREATE TABLE `tipo_descuento` (
  `id_tipo_descuento` bigint(20) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipo_descuento`
--

INSERT INTO `tipo_descuento` (`id_tipo_descuento`, `descripcion`, `nombre`) VALUES
(1, 'No aplica ningun descuento', 'NO APLICA'),
(2, 'Si el cliente realiza una compra entre las 1AM y las 3AM, tendra un descuento increible', 'POR HORA DE COMPRA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_documento`
--

CREATE TABLE `tipo_documento` (
  `sigla` varchar(4) DEFAULT NULL,
  `id_tipo_documento` bigint(20) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipo_documento`
--

INSERT INTO `tipo_documento` (`sigla`, `id_tipo_documento`, `nombre`) VALUES
('CC', 1, 'CEDULA DE CIUDADANIA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `estado` bit(1) NOT NULL,
  `id_persona` bigint(20) NOT NULL,
  `id_usuario` bigint(20) NOT NULL,
  `nombre_usuario` varchar(60) NOT NULL,
  `contrasenia` varchar(255) NOT NULL,
  `foto_usuario` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`estado`, `id_persona`, `id_usuario`, `nombre_usuario`, `contrasenia`, `foto_usuario`) VALUES
(b'1', 2, 1, '1003678423_usuario', '$2a$10$GalARBfCdQ2XPubFP4BsbeMkRGdTifLPsWDEe2petnUBZzIaUsLCy', NULL),
(b'1', 3, 2, '1003691117_usuario', '$2a$10$xw2QKrS48qT5CAhCiozbH.QKCO4W6/PLx5SI0kkWDBqvHVhRVo3z6', NULL),
(b'1', 4, 3, '1003678424_usuario', '$2a$10$raxz0mw9jqWyvcgTPoH01eDJLrjFtddY/Cflu9O2.bMKXTMxhj3N6', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_perfil`
--

CREATE TABLE `usuario_perfil` (
  `id_perfil` bigint(20) NOT NULL,
  `id_usuario` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario_perfil`
--

INSERT INTO `usuario_perfil` (`id_perfil`, `id_usuario`) VALUES
(1, 1),
(2, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria_producto`
--
ALTER TABLE `categoria_producto`
  ADD PRIMARY KEY (`id_categoria_producto`);

--
-- Indices de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  ADD PRIMARY KEY (`id_ciudad`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`),
  ADD UNIQUE KEY `UKm1e6bw9cg5aydhk0rlylo5oom` (`id_usuario`);

--
-- Indices de la tabla `cliente_frecuente`
--
ALTER TABLE `cliente_frecuente`
  ADD PRIMARY KEY (`id_cliente_frecuente`),
  ADD UNIQUE KEY `UKsm9qtwuiy91dhilysyg65obn2` (`id_cliente`);

--
-- Indices de la tabla `descuentos`
--
ALTER TABLE `descuentos`
  ADD PRIMARY KEY (`id_descuento`),
  ADD KEY `FKroyemhr3521a9sry5qa21ix4l` (`id_tipo_descuento`);

--
-- Indices de la tabla `detalle_orden`
--
ALTER TABLE `detalle_orden`
  ADD PRIMARY KEY (`id_detalle_orden`),
  ADD KEY `FKlsw0bpcxtrh4dy417wl6tbum0` (`id_orden`),
  ADD KEY `FK4xw68lo39jrn8bfe0c1nepxnj` (`id_producto`);

--
-- Indices de la tabla `direccion`
--
ALTER TABLE `direccion`
  ADD PRIMARY KEY (`id_direccion`),
  ADD KEY `FKriivr3713wjwr2f9xg6p2ifh3` (`id_cliente`),
  ADD KEY `FK72554eomm2mv8j929lryf1hfh` (`id_localidad`);

--
-- Indices de la tabla `localidad`
--
ALTER TABLE `localidad`
  ADD PRIMARY KEY (`id_localidad`),
  ADD KEY `FKf000iedcrd7huc92juxxw82bm` (`id_ciudad`);

--
-- Indices de la tabla `orden`
--
ALTER TABLE `orden`
  ADD PRIMARY KEY (`id_orden`),
  ADD KEY `FK2q1npk79pf5vd9n5ot07drnb4` (`id_cliente`),
  ADD KEY `FKt07p06a0cnc1ekdcidu5etpgy` (`id_descuento`);

--
-- Indices de la tabla `perfil`
--
ALTER TABLE `perfil`
  ADD PRIMARY KEY (`id_perfil`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id_persona`),
  ADD KEY `FKi1djd3oysnrlgn00kho3gdlfj` (`id_tipo_documento`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id_producto`),
  ADD KEY `FKagmjtusivfg5vykqrlr58n510` (`id_categoria_producto`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id_rol`);

--
-- Indices de la tabla `tipo_descuento`
--
ALTER TABLE `tipo_descuento`
  ADD PRIMARY KEY (`id_tipo_descuento`);

--
-- Indices de la tabla `tipo_documento`
--
ALTER TABLE `tipo_documento`
  ADD PRIMARY KEY (`id_tipo_documento`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `UK33gathdlc33wn52w45op1r397` (`id_persona`);

--
-- Indices de la tabla `usuario_perfil`
--
ALTER TABLE `usuario_perfil`
  ADD KEY `FK3cxlmf5q4y8mllkos025n9px8` (`id_perfil`),
  ADD KEY `FK2qe6tjawyl6ojl32uhbwllldh` (`id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria_producto`
--
ALTER TABLE `categoria_producto`
  MODIFY `id_categoria_producto` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  MODIFY `id_ciudad` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_cliente` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `cliente_frecuente`
--
ALTER TABLE `cliente_frecuente`
  MODIFY `id_cliente_frecuente` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `descuentos`
--
ALTER TABLE `descuentos`
  MODIFY `id_descuento` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `detalle_orden`
--
ALTER TABLE `detalle_orden`
  MODIFY `id_detalle_orden` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `direccion`
--
ALTER TABLE `direccion`
  MODIFY `id_direccion` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `localidad`
--
ALTER TABLE `localidad`
  MODIFY `id_localidad` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `orden`
--
ALTER TABLE `orden`
  MODIFY `id_orden` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `perfil`
--
ALTER TABLE `perfil`
  MODIFY `id_perfil` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `id_persona` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id_producto` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id_rol` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipo_descuento`
--
ALTER TABLE `tipo_descuento`
  MODIFY `id_tipo_descuento` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tipo_documento`
--
ALTER TABLE `tipo_documento`
  MODIFY `id_tipo_documento` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `FKetx0tojxf5yevxcyt6qb526x5` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `cliente_frecuente`
--
ALTER TABLE `cliente_frecuente`
  ADD CONSTRAINT `FKiqjdsakmab39lanmgakbygwdi` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`);

--
-- Filtros para la tabla `descuentos`
--
ALTER TABLE `descuentos`
  ADD CONSTRAINT `FKroyemhr3521a9sry5qa21ix4l` FOREIGN KEY (`id_tipo_descuento`) REFERENCES `tipo_descuento` (`id_tipo_descuento`);

--
-- Filtros para la tabla `detalle_orden`
--
ALTER TABLE `detalle_orden`
  ADD CONSTRAINT `FK4xw68lo39jrn8bfe0c1nepxnj` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`),
  ADD CONSTRAINT `FKlsw0bpcxtrh4dy417wl6tbum0` FOREIGN KEY (`id_orden`) REFERENCES `orden` (`id_orden`);

--
-- Filtros para la tabla `direccion`
--
ALTER TABLE `direccion`
  ADD CONSTRAINT `FK72554eomm2mv8j929lryf1hfh` FOREIGN KEY (`id_localidad`) REFERENCES `localidad` (`id_localidad`),
  ADD CONSTRAINT `FKriivr3713wjwr2f9xg6p2ifh3` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`);

--
-- Filtros para la tabla `localidad`
--
ALTER TABLE `localidad`
  ADD CONSTRAINT `FKf000iedcrd7huc92juxxw82bm` FOREIGN KEY (`id_ciudad`) REFERENCES `ciudad` (`id_ciudad`);

--
-- Filtros para la tabla `orden`
--
ALTER TABLE `orden`
  ADD CONSTRAINT `FK2q1npk79pf5vd9n5ot07drnb4` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
  ADD CONSTRAINT `FKt07p06a0cnc1ekdcidu5etpgy` FOREIGN KEY (`id_descuento`) REFERENCES `descuentos` (`id_descuento`);

--
-- Filtros para la tabla `persona`
--
ALTER TABLE `persona`
  ADD CONSTRAINT `FKi1djd3oysnrlgn00kho3gdlfj` FOREIGN KEY (`id_tipo_documento`) REFERENCES `tipo_documento` (`id_tipo_documento`);

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `FKagmjtusivfg5vykqrlr58n510` FOREIGN KEY (`id_categoria_producto`) REFERENCES `categoria_producto` (`id_categoria_producto`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `FKagix3q8yqktlyj3yp1sn0mcd9` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`);

--
-- Filtros para la tabla `usuario_perfil`
--
ALTER TABLE `usuario_perfil`
  ADD CONSTRAINT `FK2qe6tjawyl6ojl32uhbwllldh` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `FK3cxlmf5q4y8mllkos025n9px8` FOREIGN KEY (`id_perfil`) REFERENCES `perfil` (`id_perfil`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
