@echo off
set SRC_DIR=src
set BIN_DIR=bin
set MAIN_CLASS=main.Application

if not exist %BIN_DIR% (
    mkdir %BIN_DIR%
)

dir /b %BIN_DIR%\*.class >nul 2>&1
if errorlevel 1 (
    echo Compilando el proyecto...
    for /r %SRC_DIR% %%f in (*.java) do (
        javac -d %BIN_DIR% %%f
    )
)

if "%1"=="" (
    java -cp %BIN_DIR% %MAIN_CLASS%
) else (
    java -cp %BIN_DIR% %MAIN_CLASS% %1
)
