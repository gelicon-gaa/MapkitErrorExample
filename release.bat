@echo off

setlocal

for /F %%i in ('git status --porcelain') do (
    echo ��। ५���� �ॡ���� ��䨪�஢��� �� �ࠢ��!
    goto done
)

set target=app
set verctl=verctl-2.0.exe

echo ��४��祭�� ���ᨨ

%verctl% -release -file %target%\version.txt

if errorlevel 1 (
    echo �訡�� �� ��४��祭�� ���ᨨ
    echo ��⠭���� �⨫��� %verctl%, ������ ����� ����㧨�� �� �����:
    echo http://git.srv.gelicon.biz:3000/utils/verctl/src/master/%verctl%
    goto done
)

set /P release=<%target%\version.txt

for /F %%i in ('%verctl% -level -text "%release%"') do set verlen=%%i

if %verlen% GTR 3 goto :end_build_switch

echo ��४��祭�� ����� ᡮન

%verctl% -release -file %target%\build.txt

if errorlevel 1 (
    echo �訡�� �� ��४��祭�� ����� ᡮન
    goto done
)

:end_build_switch

echo ���������� 䠩���

git add -A .

if errorlevel 1 (
    echo �訡�� �� ���������� 䠩���
    goto done
)

echo ������ 䠩���

git commit -m "����� ���ᨨ %release%"

if errorlevel 1 (
  echo �訡�� �� ������ 䠩���
  goto done
)

echo ���������� ��⪨

git tag -a "v%release%" -m "���� %release%"

if errorlevel 1 (
  echo �訡�� �� ���������� ��⪨
  goto done
)

echo ��४��祭�� �� ࠡ���� �����

%verctl% -last -file %target%\version.txt

if errorlevel 1 (
    echo �訡�� �� ��४��祭�� �� ࠡ���� �����
    goto done
)

set /P work=<%target%\version.txt

git add -A .

if errorlevel 1 (
    echo �訡�� �� ���������� 䠩���
    goto done
)

echo ������ 䠩���

git commit -m "��४��祭�� �� ࠡ���� ����� %work%"

if errorlevel 1 (
  echo �訡�� �� ������ 䠩���
  goto done
)

echo ��ࠢ�� �����⮢ �� �ࢥ�

git push origin HEAD

if errorlevel 1 (
    echo �訡�� �� ��ࠢ�� �����⮢ �� �ࢥ�
    goto done
)

echo ��ࠢ�� ��⮪ �� �ࢥ�

git push origin --tags

if errorlevel 1 (
    echo �訡�� �� ��ࠢ�� ��⮪ �� �ࢥ�
    goto done
)

echo ***************************************
echo ���� ���ᨨ [%release%] �ᯥ譮 �����襭!
echo ***************************************

:done
pause
