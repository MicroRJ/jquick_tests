@echo off
REM v - verbose 
REM c - create jar
REM f - explict jar file 
REM 0 - no compression 

set Fout=out\ 
set Fpkg=pkg\
set Fraw=raw\
set Fsrc=src\com\cid\jquick_test\
set Flib=lib\
set Fext=E:\Desktop\modules\jquick\

set Napp=jquick_test.jar 
set Nman=man.txt
mkdir %Fout%
mkdir %Fpkg%
mkdir %Fsrc%
mkdir %Flib%

set Cjava=%Fsrc%TestRunner.java %Fsrc%R.java %Fsrc%AOO.java %Fsrc%AOF.java %Fsrc%Objects.java %Fsrc%Test.java %Fsrc%Testable.java %Fsrc%Models.java

javac -cp %Fext%%Fout% -d %Fout% %Cjava%
jar cfm0 %Fpkg%%Napp% %Fpkg%%Nman% -C %Fext%%Fout% . -C %Fout% . -C %Fraw% .
jar tf %Fpkg%%Napp%
java -jar %Fpkg%%Napp%
