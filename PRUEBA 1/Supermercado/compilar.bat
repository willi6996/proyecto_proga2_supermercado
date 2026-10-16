@echo off
setlocal
cd /d "%~dp0"
if not exist build mkdir build
if not exist build\classes mkdir build\classes
if not exist build\test-classes mkdir build\test-classes
(for /r src %%f in (*.java) do @echo "%%f") > build\sources.txt
javac --release 17 -encoding UTF-8 -cp "lib/*" -d build/classes @build/sources.txt
if errorlevel 1 exit /b 1
xcopy /E /I /Y src\icon build\classes\icon >nul
javac --release 17 -encoding UTF-8 -cp "build/classes;lib/*" -d build/test-classes tests/CrudIntegrationTest.java
if errorlevel 1 exit /b 1
echo Compilacion correcta.
