DROP DATABASE IF EXISTS Valorant;
CREATE DATABASE Valorant;
USE Valorant;

-- Tabla Rol
CREATE TABLE Rol (
    idRol INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(15) NOT NULL CHECK (nombre IN ('Duelista', 'Sentinel', 'Smoker')),
    posicion VARCHAR(15)
);

-- Tabla Agentes
CREATE TABLE Agentes (
    idAgente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(200),
    genero VARCHAR(20) CHECK (genero IN ('Masculino', 'Femenino', 'Otro')),
    idRol INT,
    fecha_creacion DATETIME,
    num_habilidades INT DEFAULT 0,
    FOREIGN KEY (idRol) REFERENCES Rol(idRol)
);

-- Tabla Habilidad
CREATE TABLE Habilidad (
    idHabilidad INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(200) NOT NULL
);

-- Tabla intermedia Agente_Habilidad
CREATE TABLE Agente_Habilidad (
    idAgente INT,
    idHabilidad INT,
    PRIMARY KEY (idAgente, idHabilidad),
    FOREIGN KEY (idAgente) REFERENCES Agentes(idAgente) ON DELETE CASCADE,
    FOREIGN KEY (idHabilidad) REFERENCES Habilidad(idHabilidad) ON DELETE CASCADE
);

-- Insertar roles
INSERT INTO Rol (nombre, posicion) VALUES 
('Duelista', 'Ataque'),
('Sentinel', 'Defensa'),
('Smoker', 'Control');

-- Insertar agentes
INSERT INTO Agentes (nombre, descripcion, genero, idRol) VALUES
('Jett', 'Agente ágil con habilidades de viento', 'Femenino', 1),
('Sage', 'Apoyo defensivo con habilidades de curación y ralentización', 'Femenino', 2),
('Brimstone', 'Especialista en controlar el mapa con humo y ataques aéreos', 'Masculino', 3);

-- Insertar habilidades
INSERT INTO Habilidad (descripcion) VALUES
('Deslizamiento rápido hacia adelante'),
('Orbe de ralentización que bloquea el paso'),
('Lanzamiento de humo desde el cielo');

-- Asignar habilidades a agentes (esto activa los triggers)
INSERT INTO Agente_Habilidad (idAgente, idHabilidad) VALUES
(1, 1),
(2, 2),
(3, 3);

-- Trigger para la fecha de inserción
DELIMITER //
CREATE TRIGGER fecha_creacion_agentes
BEFORE INSERT ON Agentes
FOR EACH ROW
BEGIN
    SET NEW.fecha_creacion = NOW();
END;
//
DELIMITER ;

-- Trigger para la actualizacion de habilidades
DELIMITER //
CREATE TRIGGER sumar_habilidad
AFTER INSERT ON Agente_Habilidad
FOR EACH ROW
BEGIN
    UPDATE Agentes
    SET num_habilidades = num_habilidades + 1
    WHERE idAgente = NEW.idAgente;
END;
//
DELIMITER ;

INSERT INTO Agente_Habilidad (idAgente, idHabilidad) VALUES (2, 1);
