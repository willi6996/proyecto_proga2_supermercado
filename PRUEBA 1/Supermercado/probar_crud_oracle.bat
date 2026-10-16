@echo off
setlocal
cd /d "%~dp0"
if not exist database-test.properties (
 echo Copie database-test.properties.example como database-test.properties y configure la clave.
 exit /b 1
)
call compilar.bat
if errorlevel 1 exit /b 1
java "-Ddb.config=database-test.properties" "-Ddb.url=jdbc:oracle:thin:@localhost:1521/freepdb1" "-Ddb.user=SUPERMERCADO_TEST" -cp "build/classes;build/test-classes;lib/*" CrudIntegrationTest --oracle
if errorlevel 1 exit /b 1
echo Pruebas Oracle aprobadas.
