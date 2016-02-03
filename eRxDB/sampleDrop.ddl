alter table medicine drop foreign key FK_gf9g7vr7ejteftsteqol5lgp
alter table medicinecontent drop foreign key FK_is7ncp342pncufxamch7s30np
alter table medicinecontent drop foreign key FK_dghdklvs92axosnfixdk7c3qi
alter table patientaddress drop foreign key FK_t5jxe0c2m947alelis0kcfc6c
alter table patientaddress drop foreign key FK_qdak7bocpw29kqsl9o8v9npex
alter table prescription drop foreign key FK_r3pljq43cdq91vgado4axau72
drop table if exists address
drop table if exists brand
drop table if exists doctor
drop table if exists medicine
drop table if exists medicinecontent
drop table if exists molecule
drop table if exists patient
drop table if exists patientaddress
drop table if exists prescription
drop table if exists prescriptionrow
