@echo off

setlocal

for /F %%i in ('git status --porcelain') do (
    echo Перед релизом требуется зафиксировать все правки!
    goto done
)

set target=app
set verctl=verctl-2.0.exe

echo Переключение версии

%verctl% -release -file %target%\version.txt

if errorlevel 1 (
    echo Ошибка при переключение версии
    echo Установите утилиту %verctl%, которую можно загрузить по адресу:
    echo http://git.srv.gelicon.biz:3000/utils/verctl/src/master/%verctl%
    goto done
)

set /P release=<%target%\version.txt

for /F %%i in ('%verctl% -level -text "%release%"') do set verlen=%%i

if %verlen% GTR 3 goto :end_build_switch

echo Переключение номера сборки

%verctl% -release -file %target%\build.txt

if errorlevel 1 (
    echo Ошибка при переключение номера сборки
    goto done
)

:end_build_switch

echo Добавление файлов

git add -A .

if errorlevel 1 (
    echo Ошибка при добавлении файлов
    goto done
)

echo Коммит файлов

git commit -m "Релиз версии %release%"

if errorlevel 1 (
  echo Ошибка при коммите файлов
  goto done
)

echo Добавление метки

git tag -a "v%release%" -m "Выпуск %release%"

if errorlevel 1 (
  echo Ошибка при добавлении метки
  goto done
)

echo Переключение на рабочую версию

%verctl% -last -file %target%\version.txt

if errorlevel 1 (
    echo Ошибка при переключении на рабочую версию
    goto done
)

set /P work=<%target%\version.txt

git add -A .

if errorlevel 1 (
    echo Ошибка при добавлении файлов
    goto done
)

echo Коммит файлов

git commit -m "Переключение на рабочую версию %work%"

if errorlevel 1 (
  echo Ошибка при коммите файлов
  goto done
)

echo Отправка коммитов на сервер

git push origin HEAD

if errorlevel 1 (
    echo Ошибка при отправке коммитов на сервер
    goto done
)

echo Отправка меток на сервер

git push origin --tags

if errorlevel 1 (
    echo Ошибка при отправке меток на сервер
    goto done
)

echo ***************************************
echo Выпуск версии [%release%] успешно завершен!
echo ***************************************

:done
pause
