-- drop table USUARIO.Usuarios_Security;
-- drop table USUARIO.Usuarios_Roles;

-- create table Usuarios_Security
-- (
--    
--     NOMBRE_USUARIO VARCHAR(50) NOT NULL ,    
--     PASSWORD VARCHAR(30) NOT NULL,
--     PRIMARY KEY(NOMBRE_USUARIO)
--    
-- );
-- 
-- create table Usuarios_Roles
-- (
--     NOMBRE_USUARIO VARCHAR(50) NOT NULL,
--     ROL VARCHAR(50) NOT NULL
-- );


-- -------------      USUARIOS      --------------------------------
insert into Entidad (ID,ACTIVO,PASSOWORD,ROL_ID) values ('Pepe','123456');
insert into Usuarios_Security (NOMBRE_USUARIO,PASSWORD) values ('Ana','123456');


-- -------------      ROLES DE USUARIOS      --------------------------------

insert into Usuarios_Roles(NOMBRE_USUARIO,ROL) values ('Pepe','ROLE_ADMIN');
insert into Usuarios_Roles(NOMBRE_USUARIO,ROL) values ('Ana','ROLE_USER');