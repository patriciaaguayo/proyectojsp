
create database jsphibernate;
use jsphibernate;

drop database jsphibernate;

CREATE TABLE Usuarios (
    Id_Usuario INT auto_increment primary key,
    Nombre_Usuario VARCHAR(100) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Tipo_Usuario VARCHAR(50) NOT NULL
);

drop table Usuarios;

-- Tabla de Proyectos
CREATE TABLE Proyectos (
    Id_Proyecto INT auto_increment primary key,
    Nombre_Proyecto VARCHAR(255) NOT NULL,
    Estado_Proyecto VARCHAR(50) NOT NULL,
    Descripcion_Proyecto VARCHAR (300),
    Fecha_Inicio_Proyecto DATE NOT NULL,
    Fecha_Fin_Proyecto DATE NOT NULL
);

drop table Proyectos;

-- Tabla de Tareas
CREATE TABLE Tareas (
    Id_Tarea INT auto_increment primary key,
    Descripcion_Tarea VARCHAR (300) NOT NULL,
    Id_Proyecto INT NOT NULL,
    Responsable VARCHAR(36),
    Estado_Tarea VARCHAR(50) NOT NULL,
    Fecha_Inicio_Tarea DATE NOT NULL,
    Fecha_Fin_Tarea DATE,
	FOREIGN KEY (Id_Proyecto) REFERENCES Proyectos(Id_Proyecto) ON DELETE CASCADE
);

drop table Tareas;

-- Inserciones en la tabla Usuarios
INSERT INTO Usuarios (Nombre_Usuario, Password, Tipo_Usuario) VALUES
('admin', 'admin', 'Admin'),
('usuario1', 'pass1', 'User'),
('usuario2', 'pass2', 'User');

-- Inserciones en la tabla Proyectos
INSERT INTO Proyectos (Nombre_Proyecto, Estado_Proyecto, Descripcion_Proyecto, Fecha_Inicio_Proyecto, Fecha_Fin_Proyecto) VALUES
('Proyecto Alpha', 'En curso', 'Gestión de recursos humanos', '2024-01-10', '2024-12-20'),
('Proyecto Beta', 'Terminado', 'Desarrollo de aplicación web', '2024-02-15', '2024-11-25');

-- Inserciones en la tabla Tareas
INSERT INTO Tareas (Descripcion_Tarea, Id_Proyecto, Responsable, Estado_Tarea, Fecha_Inicio_Tarea, Fecha_Fin_Tarea) VALUES
('Diseñar la base de datos', 1, 'Juan Pérez', 'Pendiente', '2024-03-01', '2024-03-15'),
('Implementar backend', 1, 'Ana Gómez', 'Completada', '2024-03-10', '2024-04-01'),
('Diseñar UI/UX', 2, 'Carlos Díaz', 'En progreso', '2024-04-05', '2024-05-10');
