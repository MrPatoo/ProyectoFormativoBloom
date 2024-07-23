create table tbPaciente(
uuid VARCHAR2(100) primary KEY,
nombre VARCHAR2(100)not null,
apellido varchar2(100) not null,
edad number not null,
numHabitacion number not null,
numCama number unique,
fechaIngreso varchar2(15) not null,
--fk
enfermedad number not null,
medicamento number not null,

constraint fkEnfermedad
foreign key (enfermedad)
references tbEnfermedad(idEnfermedad),

constraint fkMedicamento
foreign key (medicamento)
references tbMedicamento(idMedicamento)
);

create table tbEnfermedad(
idEnfermedad number GENERATED AS IDENTITY START WITH 1 INCREMENT BY 1 primary key,
nombreEnfermedad varchar(100)
);

create table tbMedicamento(
idMedicamento number GENERATED AS IDENTITY START WITH 1 INCREMENT BY 1 primary key,
nombreMedicamento varchar2(100),
horaAplicacion varchar2(100)
);

drop table tbPaciente
drop table tbMedicamento
drop table tbEnfermedad

--MEDICAMENTO
insert into tbMedicamento(nombreMedicamento, horaaplicacion) values('PARACETAMOL', 'Cada 6 horas');
insert into tbMedicamento(nombreMedicamento, horaaplicacion) values('SUERO', 'Cada 2 horas');
insert into tbMedicamento(nombreMedicamento, horaaplicacion) values('AMBROXOL', 'Cada 8 horas');
insert into tbMedicamento(nombreMedicamento, horaaplicacion) values('SIMETICONA', 'Cada 12 horas');

SELECT * FROM tbMedicamento

--ENFERMEDAD
insert into tbEnfermedad(nombreEnfermedad) values('GRIPE');
insert into tbEnfermedad(nombreEnfermedad) values('DOLOR DE ESTOMAGO');
insert into tbEnfermedad(nombreEnfermedad) values('VOMITOS');
insert into tbEnfermedad(nombreEnfermedad) values('DOLOR DE CABEZA');

SELECT * FROM tbEnfermedad


select * from tbPaciente
commit