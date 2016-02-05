create table Certificate (id integer not null auto_increment, certKey varchar(150) not null, signImage blob not null, value blob not null, primary key (id))
create table address (id integer not null auto_increment, address1 varchar(45), address2 varchar(45), address3 varchar(45), address4 varchar(45), altname varchar(45), city varchar(45), country varchar(45), created datetime not null, landmark varchar(45), name varchar(60), state varchar(45), updated datetime not null, zipcode varchar(45), primary key (id))
create table brand (id integer not null auto_increment, name varchar(100), primary key (id))
create table doctor (id integer not null auto_increment, created datetime not null, encryptedPassword varchar(300) not null, name varchar(45), qualification varchar(100), regno varchar(45), updated datetime not null, username varchar(45) not null, primary key (id))
create table medicine (id integer not null auto_increment, activeIngredients varchar(150), applicantLicenseNo integer, atc4 varchar(10), dosage_form varchar(15), effective_date datetime, generic_or_originator varchar(255), logistics_fee double precision, manuf_price double precision, medRegNo varchar(50), medicineSchedule varchar(255), nappiCode varchar(20), pack_size double precision, proprietaryName varchar(120), quantity integer, sales_volume integer, sep double precision, status varchar(255), strength double precision, unit varchar(25), unit_price double precision, vat double precision, brand integer, primary key (id))
create table medicinediadnosys (id integer not null auto_increment, created datetime not null, diagnosys varchar(255) not null, doctor tinyblob not null, medicine tinyblob not null, updated datetime not null, primary key (id))
create table patient (id integer not null auto_increment, created datetime not null, name varchar(45) not null, phonenumber varchar(45) not null, uniqueId varchar(100) not null, updated datetime not null, primary key (id))
create table patientaddress (addressid integer not null, patientid integer not null)
create table prescription (id integer not null auto_increment, addressid integer not null, created datetime not null, diagnosys varchar(200), doctorname varchar(45), externaltxnid varchar(40), orderstatus integer not null, patientname varchar(45), phonenumber varchar(15), prescriptionDoc blob, updated datetime not null, doctorid integer, primary key (id))
create table prescriptionrow (id integer not null auto_increment, created datetime not null, doses varchar(150) not null, medicineid integer not null, medicinename varchar(45) not null, numunits integer not null, updated datetime not null, primary key (id))
alter table Certificate add constraint UK_jby4kax6kpg4pxrmd33cx4cgi  unique (certKey)
alter table patient add constraint UK_tirth69t88ckc5xw31mp9gw4v  unique (uniqueId)
alter table medicine add constraint FK_ogqduay39l6sw72297lstiuit foreign key (brand) references brand (id)
alter table patientaddress add constraint FK_t5jxe0c2m947alelis0kcfc6c foreign key (patientid) references patient (id)
alter table patientaddress add constraint FK_qdak7bocpw29kqsl9o8v9npex foreign key (addressid) references address (id)
alter table prescription add constraint FK_r3pljq43cdq91vgado4axau72 foreign key (doctorid) references doctor (id)
