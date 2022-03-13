DROP DATABASE IF EXISTS HGshop;
CREATE DATABASE HGshop;
USE HGshop;

DROP user IF EXISTS 'hgShop'@'localhost';
CREATE USER 'hgShop'@'localhost' IDENTIFIED BY 'adminadmin';
GRANT ALL ON storage.* TO 'hgShop'@'localhost';


create table prodotto(
id_prodotto int not null auto_increment,
nome varchar(255) not null,
descrizione varchar(255),
prezzo float not null,
quantita int not null,
foto blob,
categoria varchar(255) not null,
primary key(id_prodotto)
);

insert into prodotto(nome, descrizione, prezzo, quantita,categoria) values("bacchetta di Sambuco", "bacchetta di silente",  23.50 ,10, "bacchette");

create table carta(
cod_carta int not null,
inestatario varchar(255)not null,
scadenza varchar(5) not null,
cvv int not null,
primary key(cod_carta)
);

create table utente(
email varchar(255) not null,
pass varchar(255) not null,
cognome varchar(255) not null,
nome varchar(255) not null,
d_nascita date not null,
l_nascita varchar(255) not null,
telefono int not null,
indirizzo varchar(255) not null,
tipo varchar(255) not null,
primary key(email)

);




create table ordine(
id_ordine int not null auto_increment,
importo float not null,
data_ordine date  not null,
ind_consegna varchar(255) not null,
email varchar(255) not null,
cod_carta int not null,

primary key(id_ordine), 
foreign key(email)references utente(email)
on delete cascade
on update cascade,
foreign key(cod_carta) references carta(cod_carta)
on delete cascade
on update cascade
);

create table include(
id_ordine int not null,
id_prodotto int not null,
email varchar(255) not null,
foreign key(id_ordine)references ordine(id_ordine)
on delete cascade
on update cascade,
foreign key(id_prodotto)references prodotto(id_prodotto)
on delete cascade
on update cascade,
foreign key(email)references utente(email)
on delete cascade
on update cascade
);


