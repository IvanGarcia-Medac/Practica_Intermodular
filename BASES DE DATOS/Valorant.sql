-- 1. Borrado previo de las tablas si existen
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Habilidad CASCADE CONSTRAINTS';
    EXECUTE IMMEDIATE 'DROP TABLE Rol CASCADE CONSTRAINTS';
    EXECUTE IMMEDIATE 'DROP TABLE Agentes CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

-- 2. Creación de tabla Agentes
CREATE TABLE Agentes (
    idAgente INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(50) NOT NULL,
    descripcion VARCHAR2(200),
    genero VARCHAR2(20) CHECK (genero IN ('Masculino', 'Femenino', 'Otro')),
    idRol int,
    CONSTRAINT fk_agente_rol FOREIGN KEY (idRol) REFERENCES Rol(idRol)
);

-- 3. Creación de tabla Rol
CREATE TABLE Rol (
    idRol INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(15) NOT NULL CHECK (nombre IN ('Duelista', 'Sentinel', 'Smoker')),
    posicion VARCHAR2(15)
);

-- 4. Creación de tabla Habilidad
CREATE TABLE Habilidad (
    idHabilidad INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    descripcion VARCHAR2(200) NOT NULL,
    idAgente INT,
    CONSTRAINT fk_agente_habilidad FOREIGN KEY (idAgente) REFERENCES Agentes(idAgente)
);

-- 5. Inserción de datos

-- Agentes
INSERT INTO Agentes (nombre, descripcion, genero) VALUES
('Jett', 'Agente ágil con habilidades de viento', 'Femenino');
INSERT INTO Agentes (nombre, descripcion, genero) VALUES
('Sage', 'Apoyo defensivo con habilidades de curación y ralentización', 'Femenino');
INSERT INTO Agentes (nombre, descripcion, genero) VALUES
('Brimstone', 'Especialista en controlar el mapa con humo y ataques aéreos', 'Masculino');

-- Roles
INSERT INTO Rol (nombre, posicion) VALUES ('Duelista', 'Ataque');
INSERT INTO Rol (nombre, posicion) VALUES ('Sentinel', 'Defensa');
INSERT INTO Rol (nombre, posicion) VALUES ('Smoker', 'Control');

-- Habilidades
INSERT INTO Habilidad (descripcion, idAgente) VALUES ('Deslizamiento rápido hacia adelante', 1);
INSERT INTO Habilidad (descripcion, idAgente) VALUES ('Orbe de ralentización que bloquea el paso', 2);
INSERT INTO Habilidad (descripcion, idAgente) VALUES ('Lanzamiento de humo desde el cielo', 3);

select * from Rol;
select * from Habilidad;
select * from Agentes;
