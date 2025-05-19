drop database if exists Valorant;
create database Valorant;
use Valorant;

create table Agentes(
    idAgente int auto_increment primary key,
    nombre varchar2(15),
    descripcion varchar2(200),
    genero varchar2(20),
    /* rol varchar2(20), */
    /* habilidad */
);

create table Rol(
    idRol int auto_increment primary key,
    idAgente int,
    nombre varchar2(15),
    posicion varchar2(15);
    idAgente
);

create table Habilidad(
    idHabilidad int auto_increment primary key,
    descripcion  varchar2(200),
    idAgente int,


);
