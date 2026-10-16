-- Ejecutar como SYSTEM conectado a FREEPDB1, no a CDB$ROOT.
-- Para una instalacion nueva. No elimina ni modifica usuarios existentes.
WHENEVER SQLERROR EXIT SQL.SQLCODE
SET VERIFY OFF
ACCEPT clave CHAR PROMPT 'Clave nueva para SUPERMERCADO (letras y numeros): ' HIDE
CREATE USER supermercado IDENTIFIED BY "&clave" DEFAULT TABLESPACE users QUOTA 100M ON users;
GRANT CREATE SESSION, CREATE TABLE TO supermercado;
UNDEFINE clave
