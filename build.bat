@echo off
REM call lib.bat

REM v - verbose
REM c - create jar
REM f - explict jar file
REM 0 - no compression

set Napp=jquick_test.jar
set Nman=man.txt
set Fout=out\
set Fpkg=pkg\
set Fraw=raw\
set Froot=src\
set Fsrc=src\com\cid\jquick_test\
set Flib=lib\
set Fcl=compile_list.txt
set Fos=output.txt
set Fpoj=W:\projects\

echo BATCH BUILD PROJECT %Fapp%

IF NOT EXIST %Fout% mkdir %Fout%
IF NOT EXIST %Fraw% mkdir %Fraw%
IF NOT EXIST %Fsrc% mkdir %Fsrc%
IF NOT EXIST %Flib% mkdir %Flib%

dir /a-D /S /B %Froot% > %Fcl%

set MODULE_A=%Fpoj%jquick\%Fout%
set MODULE_B=%Fpoj%dbot\%Fout%

set CLASSPATH=%MODULE_A%;%MODULE_B%

javac -g -cp %CLASSPATH% -d %Fout% @%Fcl%
REM v
REM
jar cfm0 %Fpkg%%Napp% %Fpkg%%Nman% -C %MODULE_A% . -C %MODULE_B% . -C %Fout% . -C %Fraw% .

jar tf %Fpkg%%Napp% > %Fos%
java -jar %Fpkg%%Napp%
