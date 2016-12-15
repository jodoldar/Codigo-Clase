DROP TABLE p2_Asignatura CASCADE CONSTRAINTS;
DROP TABLE p2_Departamento CASCADE CONSTRAINTS;
DROP TABLE p2_Docencia CASCADE CONSTRAINTS;
DROP TABLE p2_Profesor CASCADE CONSTRAINTS;

CREATE TABLE p2_Departamento(
  cod_dep CHAR(4) CONSTRAINT pk_departamento PRIMARY KEY DEFERRABLE,
  nombre VARCHAR2(50) NOT NULL,
  telefono CHAR(8),
  director CHAR(9) 
);

CREATE TABLE p2_Asignatura(
  cod_asg CHAR(5) CONSTRAINT pk_asignatura PRIMARY KEY DEFERRABLE,
  nombre VARCHAR2(50) NOT NULL UNIQUE,
  semestre CHAR(2) NOT NULL CHECK(semestre IN('1A','1B','2A','2B','3A','3B','4A','4B')),
  cod_dep CHAR(4) NOT NULL CONSTRAINT fk_asignatura_departamento REFERENCES p2_Departamento(cod_dep) DEFERRABLE,
  teoria FLOAT NOT NULL,
  practicas FLOAT NOT NULL,
  CHECK(teoria>=practicas)
);

CREATE TABLE p2_Profesor(
  dni CHAR(9) CONSTRAINT pk_profesor PRIMARY KEY DEFERRABLE,
  nombre VARCHAR2(80) NOT NULL,
  telefono CHAR(8),
  cod_dep CHAR(4) CONSTRAINT fk_profesor_departamento REFERENCES p2_Departamento(cod_dep),
  provincia CHAR(25),
  edad NUMBER
);

CREATE TABLE p2_Docencia(
  dni CHAR(9) CONSTRAINT fk_docencia_profesor REFERENCES p2_Profesor(dni),
  cod_asg CHAR(5) CONSTRAINT fk_docencia_profesor2 REFERENCES p2_Asignatura(cod_asg),
  gteo NUMBER NOT NULL,
  gpra NUMBER NOT NULL,
  CONSTRAINT pk_docencia PRIMARY KEY (dni,cod_asg) DEFERRABLE 
);

ALTER TABLE p2_Departamento ADD CONSTRAINT fk_departamento_profesor FOREIGN KEY("DIRECTOR") REFERENCES "P2_PROFESOR"("DNI");

alter table "JODOLDAR"."P2_PROFESOR" add constraint RG1 check(dni IN P2_DOCENCIA("DNI")) ENABLE;





