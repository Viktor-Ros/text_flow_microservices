# Text flow microservices - приложение для обмена текстами

![Картинка](resources/pic1.png)

### Навигация
* [Используемые технологии](#title1)
* [Инструкция по запуску](#title2)
* [Инструкция по тестированию](#title3)
* [Логика работы приложения](#title4)
* [Компоненты приложения](#title5)
* [Базовый функционал](#title6)
* [Схема базы данных](#title7)
* [Описание REST запросов](#title8)
* [REST запросы](#title9)


### <a id="title1">Используемые технологии:</a>

* Java 11
* Spring Boot 2.7.0
* Spring JPA 2.7.0
* Spring Cloud 3.1.1
* PostgreSQL 13
* JUnit 5
* Spring Cloud Eureka
* Spring Cloud Gateway
* Spring Cloud OpenFeign
* Spring AOP
* REST API
* Maven PMD
* Микросервисная архитектура

### <a id="title2">Инструкция по запуску:</a>

*  В каждом файле ***application.properties*** сервисов: ***employee_service***, ***story_service***, ***subscription_service*** указать параметры существующей БД или создать новую с параметрами:
    - host = localhost
    - port = 5432
    - user - bestuser
    - password - bestuser
    - name - text_flow_db
*  Запустить все сервисы из классов ****App***
*  При первом запуске сервиса ***employee_service*** в БД создаются таблицы и начальные тестовые данные
*  Также запросы для создания таблиц и стартовых данных лежат в ***resources/sql/data.sql***


### <a id="title3">Инструкция по тестированию:</a>

*  Тесты настроены на работу с начальными тестовыми данными(класс ***TestData***), которые генерируются из файла ***data.sql***
*  В сервисах ***employee_service*** и ***story_service*** тесты, которые требуют запущенных сервисов ***subscription_service*** и ***eureka_service***, помечены @Disabled
*  В сервисе ***text_flow_service*** для корректной работы тестов необходимо запустить все сервисы
*  Тесты прогоняются при сборке с помощью консольной команды: ***mvn clean install***
*  Если тесты при сборке не нужны, то используем консольную команду: ***mvn clean install -DskipTests=true***


### <a id="title4">Логика работы приложения:</a>

Приложение позволяет пользователям писать тексты, а также подписываться на других авторов, чтобы просматривать их тексты

### <a id="title5">Компоненты приложения:</a>

* ***base_service*** - сервис для описания базовой сущности и операций с ней - ***[подробнее](#title6)***
* ***employee_service*** - сервис для работы с пользователями
* ***story_service*** - сервис для работы с текстами
* ***subscription_service*** - сервис для работы с подписками
* ***eureka_service*** - сервис для объединения и совместной работы остальных сервисов приложения
* ***text_flow_service*** - сервис для взаимодействия приложения с клиентом

* Story - текст автора
* Subscription - подписка на автора

### <a id="title6">Базовый функционал:</a>

Служит для сокращения кода и унификации функционала, представлен в сервисе ***base_service***

 * #### BaseEntity - основа каждой сущности, содержит поля: 
    - id - идентификатор
    - creationDate - дата создания
    - modifyDate - дата изменения
     
 * #### BaseDao - базовый функционал для работы на уровне БД:
    - save() - сохранение сущности
    - deleteById() - удаление сущности по идентификатору
    - getById() - получение сущности по идентификатору 
    - getAllList() - получение списка всех сущностей 

 * #### BaseService - базовый функционал для работы на уровне бизнес-логики:
    - save() - сохранение сущности
    - deleteById() - удаление сущности по идентификатору
    - getById() - получение сущности по идентификатору
    - getAllList() - получение списка всех сущностей

 * #### BaseController - базовый функционал для работы на уровне REST API:
    - POST - "" - создание сущности
    - PUT - "" - изменение сущности
    - DELETE - "/{id}" - удаление сущности по идентификатору
    - GET - "/{id}" - получение сущности по идентификатору
    - GET - "" - получение списка всех сущностей
    

### <a id="title7">Схема базы данных(файл data.sql):</a>

 * #### base - дефолтные поля любой сущности - не таблица!!!
    - id - идентификатор
    - creation_date - дата создания
    - modify_date - дата изменения

 * #### employee - таблица пользователей
    - name - имя пользователя
     
 * #### story - таблица текстов
    - text_value - текст
    - author_id - идентификатор автора - references employee.id

 * #### subscription - таблица подписок
    - writer_id - идентификатор писателя - references employee.id
    - subscriber_id - идентификатор подписчика - references employee.id

### <a id="title8">Описание REST запросов сервисов:</a>

 * #### Сервис ***employee_service*** - действия с пользователями
   - POST /employee - сохранение пользователя
   - PUT /employee - изменение пользователя
   - DELETE /employee/{id} - удаление пользователя по id
   - GET /employee/{id} - получение пользователя по id
   - GET /employee - получение списка всех пользователей
   - GET /employee/subscriber_list_by_writer_id/{writer_id} - получения списка подписчиков по id писателя
   - GET /employee/writer_list_by_subscriber_id/{subscriber_id} - получение списка писателей по id подписчика

* #### Сервис ***story_service*** - действия с текстами
    - POST /story - сохранение текста
    - PUT /story - изменение текста
    - DELETE /story/{id} - удаление текста по id
    - GET /story/{id} - получение текста по id
    - GET /story - получение списка всех текстов
    - GET /story/story_list_by_author_id/{author_id} - получения списка текстов по id автора
    - GET /story/story_list_by_subscriber_id/{subscriber_id} - получение списка текстов по id подписчика

* #### Сервис ***subscription_service*** - действия с подписками
    - POST /subscription - сохранение подписки
    - PUT /subscription - изменение подписки
    - DELETE /subscription/{id} - удаление подписки по id
    - GET /subscription/{id} - получение подписки по id
    - GET /subscription - получение списка всех подписок
    - GET /subscription/subscriber_id_list_by_writer_id/{writer_id} - получения списка id подписчиков по id писателя
    - GET /writer_id_list_by_subscriber_id/{subscriber_id} - получение списка id писателей по id подписчика

### <a id="title9">REST запросы для Postman:</a>

* Примеры REST запросов для Postman ***[лежат тут](resources/postman/text_flow_microservices.postman_collection.json)***
