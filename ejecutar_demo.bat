@echo off
setlocal
cd /d "%~dp0"
if not exist demo mkdir demo
java "-Ddb.url=jdbc:h2:./demo/supermercado;MODE=Oracle" -Ddb.user=sa -Ddb.password= -cp "Supermercado.jar;lib/*" util.InicializarDemo
if errorlevel 1 goto fallo
java "-Ddb.url=jdbc:h2:./demo/supermercado;MODE=Oracle" -Ddb.user=sa -Ddb.password= -cp "Supermercado.jar;lib/*" supermercado.Supermercado
if errorlevel 1 goto fallo
exit /b 0
:fallo
pause
exit /b 1
