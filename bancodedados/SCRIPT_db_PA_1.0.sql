-- DROP TABLE Pacientes

-- DROP TABLE Medicos
-- SELECT MD5('julia1') -- "8ec70a26c1ffbba5e35a269286e8cea4"
-- DROP TABLE Pessoas
--create database sanus;
CREATE DATABASE sanus
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'en_US.UTF-8'
       LC_CTYPE = 'en_US.UTF-8'
       CONNECTION LIMIT = -1;


DROP TABLE IF EXISTS Pessoas CASCADE;
CREATE TABLE Pessoas
(
	Codigo_Pessoa	SERIAL PRIMARY KEY,
	Nome_Pessoa	VARCHAR(40) NOT NULL,
	RG_Pessoa	VARCHAR(10),
	CPF_Pessoa	CHAR(11) UNIQUE,
	Data_Nascimento_Pessoa	DATE,
	Email_Pessoa	VARCHAR(80) NOT NULL UNIQUE,
	Senha_Pessoa	VARCHAR(100) NOT NULL,
	Data_Cadastro_Pessoa DATE DEFAULT CURRENT_TIMESTAMP,
	Status_pessoa int default 1 --1 Pendente 2 Ativo 3 Inativo
);
INSERT INTO pessoas (Codigo_Pessoa, Nome_Pessoa, Rg_Pessoa, Cpf_Pessoa, Email_Pessoa, Data_Nascimento_Pessoa, Senha_Pessoa ,Status_Pessoa) VALUES (1, 'adm', '12345678', '33333399983', 'adm@sanus.br', now(), md5('adm'), 2 );
INSERT INTO pessoas (Codigo_Pessoa, Nome_Pessoa, Rg_Pessoa, Cpf_Pessoa, Email_Pessoa, Data_Nascimento_Pessoa, Senha_Pessoa ,Status_Pessoa) VALUES (2, 'paciente', '12345675', '33533374312', 'paciente@sanus.br', now(), md5('paciente'), 2 );
INSERT INTO pessoas (Codigo_Pessoa, Nome_Pessoa, Rg_Pessoa, Cpf_Pessoa, Email_Pessoa, Data_Nascimento_Pessoa, Senha_Pessoa ,Status_Pessoa) VALUES (3, 'medico', '12345673', '33433340214', 'medico@sanus.br', now(), md5('medico'), 2 );
				

DROP TABLE IF EXISTS Pacientes CASCADE;
CREATE TABLE Pacientes(
	Codigo_Paciente	SERIAL PRIMARY KEY,
	Codigo_Pessoa	INT REFERENCES Pessoas(Codigo_Pessoa) UNIQUE
);
INSERT INTO pacientes (Codigo_Paciente, Codigo_Pessoa) VALUES (1, 1);
INSERT INTO pacientes (Codigo_Paciente, Codigo_Pessoa) VALUES (2, 2);


DROP TABLE IF EXISTS Medicos CASCADE;
CREATE TABLE Medicos(
	Codigo_Medico	SERIAL PRIMARY KEY,
	Codigo_Pessoa	INT REFERENCES Pessoas(Codigo_Pessoa) UNIQUE,
	CRM_Medico 	VARCHAR(10) NOT NULL UNIQUE
);
INSERT INTO medicos (Codigo_Medico, Codigo_Pessoa, CRM_Medico) VALUES (3, 3, 'CRM00000');

DROP TABLE IF EXISTS Cirurgias CASCADE;
CREATE TABLE Cirurgias
	(
	Codigo_Cirurgia			SERIAL NOT NULL,
	Nome_Cirurgia			VARCHAR(40) NOT NULL,
	Descricao				VARCHAR(800),
	Data					DATE,
	
	PRIMARY KEY(Codigo_Cirurgia)
	);
	

DROP TABLE IF EXISTS Receitas CASCADE;	
CREATE TABLE Receitas
	(
	Codigo_Receita			SERIAL NOT NULL,
	Descricao_Receita		VARCHAR(100),
	Data					DATE,
	PRIMARY KEY(Codigo_Receita)
	);
				

DROP TABLE IF EXISTS Hospitais CASCADE;				
CREATE TABLE Hospitais
	(
	Codigo_Hospital			SERIAL NOT NULL,
	Nome_Hospital			VARCHAR(40) NOT NULL,	
	PRIMARY KEY(Codigo_Hospital)
	);
				
DROP TABLE IF EXISTS Restricoes CASCADE;				
CREATE TABLE Restricoes
	(
	Codigo_Restricao		SERIAL NOT NULL,
	Descricao_Restricao		VARCHAR(100),
	Tipo_Restricao          VARCHAR(15),
	
	PRIMARY KEY(Codigo_Restricao)
	);
				

DROP TABLE IF EXISTS Especialidade CASCADE;				
CREATE TABLE Especialidade
	(
	Codigo_Especialidade	SERIAL NOT NULL,
	Nome_Especialidade	VARCHAR(40),
	Descricao_Especialidade	VARCHAR(90),
	PRIMARY KEY(Codigo_Especialidade)
	
	);
	

DROP TABLE IF EXISTS Exames CASCADE;	
CREATE TABLE Exames
	(
	Codigo_Exame			SERIAL NOT NULL,
	Nome_Exame				VARCHAR(40) NOT NULL,
	Descricao_Exame			VARCHAR(100)		,
	Data					DATE,
	PRIMARY KEY(Codigo_Exame)
	);
				
				
DROP TABLE IF EXISTS Medicamentos CASCADE;				
CREATE TABLE Medicamentos
	(
	Codigo_Medicamento	SERIAL NOT NULL,
	Bula_Medicamento	VARCHAR(100)		,
	PRIMARY KEY(Codigo_Medicamento)
	);
	

DROP TABLE IF EXISTS Consultas CASCADE;	
CREATE TABLE Consultas
	(
	 Codigo_Consulta 	SERIAL NOT NULL,
	 Descricao_Consulta VARCHAR(100),
	 Data					DATE,
	 PRIMARY KEY(Codigo_Consulta)
	);
	

DROP TABLE IF EXISTS Paciente_Consulta CASCADE;	
CREATE TABLE Paciente_Consulta
	(
	 Codigo_Paciente 	INT NOT NULL,
	 Codigo_Consulta	INT NOT NULL,
	 FOREIGN KEY(Codigo_Paciente) REFERENCES Pacientes(Codigo_Paciente) ON UPDATE CASCADE ON DELETE CASCADE,
	 FOREIGN KEY(Codigo_Consulta) REFERENCES Consultas(Codigo_Consulta) ON UPDATE CASCADE ON DELETE CASCADE
	);
	

DROP TABLE IF EXISTS Medico_Consulta CASCADE;	
CREATE TABLE Medico_Consulta
	(
	 Codigo_Medico		INT NOT NULL,
	 Codigo_Consulta	INT NOT NULL,
	 FOREIGN KEY(Codigo_Medico) REFERENCES Medicos(Codigo_Medico) ON UPDATE CASCADE ON DELETE CASCADE,
	 FOREIGN KEY(Codigo_Consulta) REFERENCES Consultas(Codigo_Consulta) ON UPDATE CASCADE ON DELETE CASCADE
	);
				
				
DROP TABLE IF EXISTS Paciente_Receita CASCADE;				
CREATE TABLE Paciente_Receita
	(
	Codigo_Paciente			INT NOT NULL,
	Codigo_Receita			INT NOT NULL,
	FOREIGN KEY(Codigo_Paciente) REFERENCES Pacientes(Codigo_Paciente) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY(Codigo_Receita) REFERENCES Receitas(Codigo_Receita) ON UPDATE CASCADE ON DELETE CASCADE
	);			
				

DROP TABLE IF EXISTS Medico_Hospital CASCADE;				
CREATE TABLE Medico_Hospital
	(
	Codigo_Medico			INT NOT NULL,
	Codigo_Hospital			INT NOT NULL,
	FOREIGN KEY(Codigo_Medico) REFERENCES Medicos(Codigo_Medico),	
	FOREIGN KEY(Codigo_Hospital) REFERENCES Hospitais(Codigo_Hospital)
	);
				

DROP TABLE IF EXISTS Cirurgias_Hospitais CASCADE;				
CREATE TABLE Cirurgias_Hospitais
	(
	Codigo_Cirurgia			INT NOT NULL,
	Codigo_Hospital			INT NOT NULL,
	FOREIGN KEY(Codigo_Cirurgia) REFERENCES Cirurgias(Codigo_Cirurgia),	
	FOREIGN KEY(Codigo_Hospital) REFERENCES Hospitais(Codigo_Hospital)
	);
				
DROP TABLE IF EXISTS Medico_Especialidade CASCADE;				
CREATE TABLE Medico_Especialidade
	(
	Codigo_Medico		INT NOT NULL,
	Codigo_Especialidade	INT NOT NULL,
	FOREIGN KEY(Codigo_Medico) REFERENCES Medicos(Codigo_Medico),
	FOREIGN KEY(Codigo_especialidade) REFERENCES Especialidade(Codigo_especialidade)
	);


DROP TABLE IF EXISTS Paciente_Exames CASCADE;
CREATE TABLE Paciente_Exames
	(
	Codigo_Paciente			INT NOT NULL,
	Codigo_Exame			INT NOT NULL,
	FOREIGN KEY(Codigo_Paciente) REFERENCES Pacientes(Codigo_Paciente) ON UPDATE CASCADE ON DELETE CASCADE,	
	FOREIGN KEY(Codigo_Exame) REFERENCES Exames(Codigo_Exame) ON UPDATE CASCADE ON DELETE CASCADE
	);
				

DROP TABLE IF EXISTS Paciente_Hospital CASCADE;				
CREATE TABLE Paciente_Hospital
	(
	Codigo_Paciente			INT NOT NULL,
	Codigo_Hospital			INT NOT NULL,
	FOREIGN KEY(Codigo_Paciente) REFERENCES Pacientes(Codigo_Paciente),	
	FOREIGN KEY(Codigo_Hospital) REFERENCES Hospitais(Codigo_Hospital)
	);
				

DROP TABLE IF EXISTS Medico_Exames CASCADE;				
CREATE TABLE Medico_Exames
	(
	Codigo_Medico			INT NOT NULL,
	Codigo_Exame			INT NOT NULL,
	FOREIGN KEY(Codigo_Medico) REFERENCES Medicos(Codigo_Medico) ON UPDATE CASCADE ON DELETE CASCADE,	
	FOREIGN KEY(Codigo_Exame) REFERENCES Exames(Codigo_Exame) ON UPDATE CASCADE ON DELETE CASCADE
	);
				
				
DROP TABLE IF EXISTS Medico_Cirurgia CASCADE;				
CREATE TABLE Medico_Cirurgia
	(
	Codigo_Medico			INT NOT NULL,
	Codigo_Cirurgia			INT NOT NULL,
	FOREIGN KEY(Codigo_Medico) REFERENCES Medicos(Codigo_Medico) ON UPDATE CASCADE ON DELETE CASCADE,	
	FOREIGN KEY(Codigo_Cirurgia) REFERENCES Cirurgias(Codigo_Cirurgia) ON UPDATE CASCADE ON DELETE CASCADE
	);
					

DROP TABLE IF EXISTS Receitas_Medicamentos CASCADE;					
CREATE TABLE Receitas_Medicamentos
	(
	Codigo_Receita			INT NOT NULL,
	Codigo_Medicamento		INT NOT NULL,
	FOREIGN KEY(Codigo_Receita) REFERENCES Receitas(Codigo_Receita) ON UPDATE CASCADE ON DELETE CASCADE,	
	FOREIGN KEY(Codigo_Medicamento) REFERENCES Medicamentos(Codigo_Medicamento) ON UPDATE CASCADE ON DELETE CASCADE
	);
				

DROP TABLE IF EXISTS Paciente_Cirurgia CASCADE;				
CREATE TABLE Paciente_Cirurgia
	(
	Codigo_Paciente			INT NOT NULL,
	Codigo_Cirurgia			INT NOT NULL,
	
	FOREIGN KEY(Codigo_Paciente) REFERENCES Pacientes(Codigo_Paciente) ON UPDATE CASCADE ON DELETE CASCADE,	
	FOREIGN KEY(Codigo_Cirurgia) REFERENCES Cirurgias(Codigo_Cirurgia) ON UPDATE CASCADE ON DELETE CASCADE
	);


DROP TABLE IF EXISTS Paciente_Restricao CASCADE;
CREATE TABLE Paciente_Restricao
(
	Codigo_Paciente 		INT NOT NULL,
	Codigo_Restricao		INT NOT NULL,
	FOREIGN KEY(Codigo_Paciente) REFERENCES Pacientes(Codigo_Paciente) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY(Codigo_Restricao) REFERENCES Restricoes(Codigo_Restricao) ON UPDATE CASCADE ON DELETE CASCADE

);
	

DROP TABLE IF EXISTS Medico_Receita CASCADE;	
CREATE TABLE Medico_Receita
	(
	Codigo_Medico			INT NOT NULL,
	Codigo_Receita			INT NOT NULL,
	FOREIGN KEY(Codigo_Medico) REFERENCES Medicos(Codigo_Medico) ON UPDATE CASCADE ON DELETE CASCADE,	
	FOREIGN KEY(Codigo_Receita) REFERENCES Receitas(Codigo_Receita) ON UPDATE CASCADE ON DELETE CASCADE
	);
				