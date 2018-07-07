@echo off
set APP_HOME=%~f0
echo %APP_HOME%
set APP_HOME=%APP_HOME:\bin\service.bat=%
echo %APP_HOME%
:::::::::: set classpath
set CLASSPATH=%CLASSPATH%;%APP_HOME%\conf
set CLASSPATH=%CLASSPATH%;%APP_HOME%\lib\*
echo %CLASSPATH%
java -classpath %CLASSPATH% com.sixliu.credit.product.StartUp
pause