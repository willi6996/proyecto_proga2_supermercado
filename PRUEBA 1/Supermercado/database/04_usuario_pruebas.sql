-- Ejecutar como SYSTEM en FREEPDB1. Usuario aislado; no usar datos reales.
WHENEVER SQLERROR EXIT SQL.SQLCODE
SET VERIFY OFF
ACCEPT clave CHAR PROMPT 'Clave para SUPERMERCADO_TEST (letras y numeros): ' HIDE
CREATE USER supermercado_test IDENTIFIED BY "&clave" DEFAULT TABLESPACE users QUOTA 100M ON users;
GRANT CREATE SESSION, CREATE TABLE TO supermercado_test;
UNDEFINE clave
-- NO ejecutar 02_tablas.sql aqui: CrudIntegrationTest crea sus propias tablas.
