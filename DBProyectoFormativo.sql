create table tbPaciente(
uuid VARCHAR2(100) primary KEY,
nombre VARCHAR2(100),
apellido varchar2(100),
edad number,
numHabitacion number,
numCama number,
fechaIngreso date,
--fk
enfermedad number,
medicamento number,

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
insert into tbMedicamento(nombreMedicamento, horaaplicacion) values('PARACETAMOL', 'Cada 6 horas')
insert into tbMedicamento(nombreMedicamento, horaaplicacion) values('SUERO', 'Cada 2 horas')
insert into tbMedicamento(nombreMedicamento, horaaplicacion) values('AMBROXOL', 'Cada 8 horas')
insert into tbMedicamento(nombreMedicamento, horaaplicacion) values('SIMETICONA', 'Cada 12 horas')

SELECT * FROM TBMEDICAMENTO

--ENFERMEDAD
insert into tbEnfermedad(nombreEnfermedad) values('GRIPE')
insert into tbEnfermedad(nombreEnfermedad) values('DOLOR DE ESTOMAGO')
insert into tbEnfermedad(nombreEnfermedad) values('VOMITOS')
insert into tbEnfermedad(nombreEnfermedad) values('DOLOR DE CABEZA')

SELECT * FROM TBENFERMEDAD

