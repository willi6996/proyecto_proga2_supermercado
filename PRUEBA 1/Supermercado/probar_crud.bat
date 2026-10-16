@echo off
setlocal
cd /d "%~dp0"
call compilar.bat
if errorlevel 1 exit /b 1
java -cp "build/classes;build/test-classes;lib/*" CrudIntegrationTest
if errorlevel 1 exit /b 1
echo Pruebas H2 aprobadas. Esto no verifica Oracle.
