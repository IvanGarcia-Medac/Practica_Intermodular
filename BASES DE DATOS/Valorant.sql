-- 1. Borrar base de datos si existe y crearla
DROP DATABASE IF EXISTS Valorant;
CREATE DATABASE Valorant;
USE Valorant;

-- 2. Tabla Rol
CREATE TABLE Rol (
    idRol INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(15) NOT NULL CHECK (nombre IN ('Duelista', 'Sentinel', 'Smoker')),
    posicion VARCHAR(15)
);

-- 3. Tabla Agentes
CREATE TABLE Agentes (
    idAgente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(200),
    genero VARCHAR(20) CHECK (genero IN ('Masculino', 'Femenino', 'Otro')),
    idRol INT,
    FOREIGN KEY (idRol) REFERENCES Rol(idRol)
);

-- 4. Tabla Habilidad (con FK a Agente)
CREATE TABLE Habilidad (
    idHabilidad INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(200) NOT NULL,
    idAgente INT,
    FOREIGN KEY (idAgente) REFERENCES Agentes(idAgente)
);

-- 5. Insertar roles
INSERT INTO Rol (nombre, posicion) VALUES 
('Duelista', 'Ataque'),
('Sentinel', 'Defensa'),
('Smoker', 'Control');

-- 6. Insertar agentes
INSERT INTO Agentes (nombre, descripcion, genero, idRol) VALUES
('Jett', 'Agente ágil con habilidades de viento', 'Femenino', 1),
('Sage', 'Apoyo defensivo con habilidades de curación y ralentización', 'Femenino', 2),
('Brimstone', 'Especialista en controlar el mapa con humo y ataques aéreos', 'Masculino', 3);

-- 7. Insertar habilidades (ahora sí se puede referenciar a Agentes por id)
INSERT INTO Habilidad (descripcion, idAgente) VALUES
('Deslizamiento rápido hacia adelante', 1),
('Orbe de ralentización que bloquea el paso', 2),
('Lanzamiento de humo desde el cielo', 3);
