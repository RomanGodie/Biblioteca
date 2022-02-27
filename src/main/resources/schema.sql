CREATE TABLE prestamo (
    id INT NOT NULL auto_increment,
    isbn VARCHAR (10) NOT NULL,
    identificacionUsuario VARCHAR (10) NOT NULL,
    tipoUsuario INT NOT NULL,
    fechaPrestamo VARCHAR (50) NOT NULL,
    PRIMARY KEY (id)
);