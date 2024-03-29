### Hexlet tests and linter status:
[![Actions Status](https://github.com/sergeloie/java-project-72/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/sergeloie/java-project-72/actions)
[![Java CI with Sonarcloud](https://github.com/sergeloie/java-project-72/actions/workflows/main.yml/badge.svg)](https://github.com/sergeloie/java-project-72/actions/workflows/main.yml)
[![Static Badge](https://img.shields.io/badge/Merged_with-Rultor-red)](https://rultor.com)
<br>
[![Maintainability](https://api.codeclimate.com/v1/badges/40e7d5ab2d1b25bec660/maintainability)](https://codeclimate.com/github/sergeloie/java-project-72/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/40e7d5ab2d1b25bec660/test_coverage)](https://codeclimate.com/github/sergeloie/java-project-72/test_coverage)
<br>
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=sergeloie_java-project-72&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=sergeloie_java-project-72)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=sergeloie_java-project-72&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=sergeloie_java-project-72)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=sergeloie_java-project-72&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=sergeloie_java-project-72)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=sergeloie_java-project-72&metric=bugs)](https://sonarcloud.io/summary/new_code?id=sergeloie_java-project-72)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=sergeloie_java-project-72&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=sergeloie_java-project-72)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=sergeloie_java-project-72&metric=coverage)](https://sonarcloud.io/summary/new_code?id=sergeloie_java-project-72)
<br>
[![Static Badge](https://img.shields.io/badge/Deployed_on_Render-sergeloie--jp72.onrender.com-blue?style=for-the-badge&logo=Render)](https://sergeloie-jp72.onrender.com/)


Представляю вашему вниманию Анализатор Страниц.  
Web-сервис позволяющий собирать и сохранять сведения о сайтах.

Сервис написан на Java с использованием фреймворка Javalin и шаблонизатора JTE.  
В разработке и тестировании используется БД H2, а в производстве - Postgres.  
Для отображения фронтенда применяются стили Bootstrap.

Ознакомится с работой сервиса можно по ссылке https://sergeloie-jp72.onrender.com


Для локального запуска необходимо склонировать репозиторий
```
git clone https://github.com/sergeloie/java-project-72.git
```
Перейти в каталог с приложением
```
cd java-project-72
```
Скомпилировать код
```
make install
```
И запустить
```
make run-dist
```
Открыть в браузере http://localhost:7070