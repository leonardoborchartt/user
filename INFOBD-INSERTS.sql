
 create table user (
     id bigint not null auto_increment,
      name varchar(255),
      username varchar(255),
      email varchar(255),
      phone varchar(255),
      gender varchar(255),
      birthday varchar(255),
      primary key (id)
  );
insert into user(id,name,username,email,phone,gender, birthday) values (1,'João Silva','jsilva','js@gmail.com','5544223311','M', '1990-10-05');
insert into user(id,name,username,email,phone,gender, birthday) values (2,'Maria Antunes','mantunes','ma@gmail.com','5544229988','F', '1985-03-11');
insert into user(id,name,username,email,phone,gender, birthday) values (3,'Carlos Fernandes','cfernades','cf@gmail.com','5544224488','M', '1999-02-08');


 
  select * from user;
