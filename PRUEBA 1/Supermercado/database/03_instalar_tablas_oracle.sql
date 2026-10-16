WHENEVER SQLERROR EXIT SQL.SQLCODE ROLLBACK
-- Conectarse como SUPERMERCADO antes de ejecutar este archivo.
@@02_tablas.sql
SELECT table_name FROM user_tables ORDER BY table_name;
