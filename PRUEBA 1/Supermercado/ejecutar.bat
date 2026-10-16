@echo off
setlocal
cd /d "%~dp0"
java -cp "Supermercado.jar;lib/*" supermercado.Supermercado
