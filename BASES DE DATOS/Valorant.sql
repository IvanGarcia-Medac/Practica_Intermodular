drop database if exists Valorant;
create database Valorant;
use Valorant;

create table Agentes(
    idAgente int auto_increment primary key,
    idRol int,
    nombre varchar2(15) not null,
    descripcion varchar2(200),
    genero varchar2(20)
    foreign key (idRol) references Agentes(idRol)
);

create table Rol(
    idRol int auto_increment primary key,
    nombre varchar2(15) not null,
    posicion varchar2(15);
);

create table Habilidad(
    idHabilidad int auto_increment primary key,
    descripcion  varchar2(200) not null,
    idAgente int,
    foreign key (idAgente) references Agentes(idAgente)
);

INSERT INTO Agentes (nombre, descripcion, genero) VALUES
('Jett', 'Agente �gil con habilidades de viento', 'Femenino'),
('Sage', 'Apoyo defensivo con habilidades de curaci�n y ralentizaci�n', 'Femenino'),
('Brimstone', 'Especialista en controlar el mapa con humo y ataques a�reos', 'Masculino'
);

INSERT INTO Rol (nombre, posicion, idAgente) VALUES
('Duelista', 'Ataque', 1),
('Sentinel', 'Defensa', 2),
('Smoker', 'Control', 3
);

INSERT INTO Habilidad (descripcion, idAgente) VALUES
('Deslizamiento r�pido hacia adelante', 1),
('Orbe de ralentizaci�n que bloquea el paso', 2),
('Lanzamiento de humo desde el cielo', 3
);


