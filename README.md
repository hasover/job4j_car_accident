# job4j_car_accident
[![Build Status](https://app.travis-ci.com/hasover/job4j_car_accident.svg?branch=master)](https://app.travis-ci.com/hasover/job4j_car_accident)

* [Описание](#описание)
* [Технологии](#технологии)
* [Функционал](#функционал)
* [Контакты](#контакты)

## Описание
 Приложение автонарушители. Пользователь добавляет описание автонарушения, адрес, тип и статьи нарушения.

## Технологии
* Spring (MVC, ORM, Data, Security)
* JSP
* PostgreSQL
* JDBC
* Hibernate
* Maven
* Travis CI
* Tomcat

## Функционал

### 1. Регистрация.
![alt text](https://github.com/hasover/job4j_car_accident/blob/master/db/reg.PNG)

### 2. Авторизация.
![alt text](https://github.com/hasover/job4j_car_accident/blob/master/images/auth.PNG)

### 3. Создание и редактирование инцидента.
![alt text](https://github.com/hasover/job4j_car_accident/blob/master/images/index.PNG)
![alt text](https://github.com/hasover/job4j_car_accident/blob/master/images/create.PNG)
![alt text](https://github.com/hasover/job4j_car_accident/blob/master/images/index2.PNG)

## Сборка приложения
- Для сборки приложения на вашем компьютере должны быть установлены:
    - JDK 14+
    - Maven
    - PostgreSQL
    - Tomcat
- Укажите настройки для подключения к БД в файле `src/main/resources/app.properties`
- Выполните скрипты `db/schema.sql` и `db/security.schema.sql`
- Выполните команду `mvn package`
- Файл `target/job4j_car_accident-1.0-SNAPSHOT.war` скопируйте в webapp tomcat, переименуйте в car_accident.war

Приложение будет доступно по адресу: http://localhost:8080/car_accident

## Контакты
telegram: @hasover

