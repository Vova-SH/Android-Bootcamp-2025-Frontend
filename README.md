# Android-Bootcamp-2025-Frontend

## Команда: The battle Capybaras

Разработать программный продукт для помощи в осуществлении волонтерской деятельности в клиент-серверной архитектуре.

Клиент - Android Приложение, реализованное на java или kotlin с адаптивной версткой. Мобильное приложение должно предоставлять удобный и интуитивно понятный интерфейс для волонтеров, в котором можно зарегистрироваться в базу волонтеров, просмотреть ближайшие волонтерские центры, отредактировать собственный профиль и посмотреть активных волонтеров в конкретном центре. В случае авторизации с правами администратора, пользователь приложения должен иметь возможность просмотреть список не занятых волонтеров и их контактные данные.

Сервер - микросервис на Spring Boot, работающий с In-memory базой данных H2. Серверное приложение должно предоставлять API необходимый для работы мобильного приложения. Осуществлять все основные манипуляции с данными в СУБД при помощи Spring Data JPA. Данные о всех зарегистрированных волонтерских центрах должны быть предзаполнены в БД при запуске приложения.Создание схемы БД и предзаполнение данными необходимо реализовать с использованием liquibase.

Программный продукт для помощи в осуществлении волонтерской деятельности

User story
1. Как пользователь, я могу зарегистрироваться в приложении чтобы числиться в базе данных волонтеров
2. Как пользователь, я могу авторизоваться, чтобы попасть в базу волонтеров.
3. Как пользователь, я могу изменить свой профиль, чтобы добавить туда свои данные и контактную информацию.
4. Как пользователь, я могу посмотреть список ближайших волонтерских центров.
5. Как пользователь, я могу посмотреть список активных волонтеров в конкретном центре.
6. Как администратор, я могу посмотреть список незанятых волонтеров.
7. Как администратор, я могу увидеть контактные данные конкретного волонтера.
8. Как администратор, я могу указать новый волонтерский центр
9. Как администратор, я могу назначить или снять с пользователя статус администратора
	
Use case
1. Цель: регистрация пользователя.
Участники: волонтер.
Предусловия: подключение к сети
Основной поток: ввод e-mail и пароля. Отправка запроса на сервер, получение токена. Переход на экран входа.
Альтернативный поток: указанная почта не является почтой(вместо vasyapupkin@gamil.com vasyapupkingmailcom), вывод сообщения об ошибке или ошибка подключения к интернету.
2. Цель: авторизация пользователя.
Участники: волонтер.
Предусловия: учетная запись создана.
Основной поток: ввод e-mail и пароля. Отправка запроса на сервер, получение токена. Переход на главный экран.
Альтернативный поток: неверные учетные данные, вывод сообщения об ошибке или ошибка подключения к интернету.
3. Цель: получение списка ближайших волонтерских центров.
Участники: волонтер.
Предусловия: учетная запись создана.
Основной поток: переход на экран списка организаций. Отправка запроса на сервер, получение информации.
Альтернативный поток: ошибка подключения к интернету
4. Цель: выбрать подходящий волонтёрский центр
Участники: волонтер.
Предусловия: учетная запись создана, информация о волонтерском центре загружена 
Основной поток: переход на экран с информацией организации, отправка запроса на сервер, сохранение участника
Альтернативный поток: выход с окна данной организации и просмотр иной или ошибка подключения к интернету
5. Цель: просмотреть личный профиль
Участники: волонтер
Предусловия: произведён вход в приложение.
Основной поток: переход на экран профиля. Отправка запроса на сервер, получение информации о пользователе
Альтернативный поток: ошибка подключения к интернету
6. Цель: изменение информации в профиле пользователя.
Участники: волонтер
Предусловия: учетная запись создана.
Основной поток: переход на экран редактирования профиля. Отправка запроса на сервер, получение информации о пользователе. Ввод обновленной информации, отправка на сервер.
Альтернативный поток: ошибка подключения к интернету
7. Цель: получение информации об участниках организации.
Участники: волонтер
Предусловия: учетная запись создана.
Основной поток: переход на экран организации. Отправка запроса на сервер, получение информации об активных волонтерах.
Альтернативный поток: ошибка подключения к интернету
8. Цель: получение списка волонтеров.
Участники: администратор.
Предусловия: учетная запись создана. Наличие прав администратора.
Основной поток: переход на экран списка волонтеров. Отправка запроса на сервер, получение информации.
Альтернативный поток: ошибка подключения к интернету
9. Цель: получение контактных данных пользователя.
Участники: администратор.
Предусловия: учетная запись создана. Наличие прав администратора.
Основной поток: переход на экран учетной записи пользователя(волонтера). Отправка запроса на сервер, получение информации.
10. Цель: добавление нового волонтерского центра
Участники: администратор 
Предусловие: права администратора
Основной поток: ввод данных волонтерского центра, отправка запроса о добавлении волонтерского центра, сохранение данных
Альтернативный поток: отсутствие прав администратора, ошибка подключения к интернету, неверно введенные данные
11. Цель: выдать права администратора
Участники: администратор
Предусловие: права администратора
Основной поток: администратор заходит в профиль пользователя, назначает его администратор, на сервер отправляется запрос о сохранении статуса администратора
Альтернативный поток: нет прав администратора, ошибка подключения к интернету
12. Цель: снять права администратора
Участники: администратор
Предусловие: права администратора
Основной поток: администратор заходит в профиль пользователя, снимает с него статус администратор, на сервер отправляется запрос о снятии статуса администратора
Альтернативный поток: нет прав администратора, ошибка подключения к интернету




## Задачи для Frontend разработчика

#### [ ] Регистрация пользователя в системе

1. [ ] Создать страницу регистрации
    
2. [ ] Реализовать отправку запроса о регистрации на сервер(Registration())

3. [ ] Обработать данные, полученные от сервера

4. [ ] Вывести ошибки при наличии

5. [ ] Вернуть пользователя на окно входа(goToLogin())


#### [ ] Авторизация пользователя в системе

1. [ ] Создать окно входа в аккаунт

2. [ ] Реализовать отправку запроса на сервер(Login())

3. [ ] Обработать полученные данные от сервера

4. [ ] Обработка ошибок при наличии
	- Указан невалидный login/password
	- Внутренняя ошибка сервера
	- Возвращение ошибки в запросе
5. [ ] Произвести вход в аккаунт

#### [ ] Создание основного окна приложения
1.  [ ] Реализовать смену окон 
	-Карта
	-Профиль
	-Список волонтерских центров

##### [ ] Создать карту с волонтерскими центрами
1. [ ] Сделать интерактивную карту
2. [ ] Реализовать отображение положения пользователя на данной карте 3. (getClientGeo())
4. [ ]Отправить положение игрока на сервер (sentGeo())
5. [ ] Написать логику маркеров волонтёрский центров
a. [ ] Получить данные положения волонтерских центров(getCenterGeo)
b. [ ] При наличии ошибок обработать их и сообщить об ошибке сервера
c. [ ] Прописать логику отображения и расположения маркера по координатам (localizationCenters())
d. [ ] Добавить функцию перехода на страницу волонтерского центра


#### Страница волонтерских центров

1. [ ] Создать саму страницу волонтерских центров
2. [ ] Обработать и загрузить информацию и изображения волонтерских центров (loadCenterInfo())
3. [ ] Создать функцию вступления в данный центр (JoinToCenter())

#### Личный профиль пользователя

1. [ ] Создать страницу профиля пользователя
2. [ ] Получить данные пользователя(getClientInfo())
3. [ ] При наличии ошибок обработать их и оповестить о них
- Ошибка серера
- Нет подключения к интернету
4. [ ] Заполнить поля с информацией(loadClientInfo())

#### Изменение данных пользователя

[ ] Создать переход на страницу изменения данных
[ ] Создать поля для ввода измененных данных
[ ] Отправить запрос на сервер о смене данных (sendEditedInfo())
[ ] При наличии ошибок обработать их и оповестить о них пользователя

- Введены некорректные данные
	- Ошибка сервера
- Ошибка подключения к интернету

    

#### Получение информации об участниках организации

[ ] Создать на странице волонтерского профиля переход на подстраницу со списком участников
[ ] Отобразить список участников(loadCenterInfo())
#### Получение списка волонтеров (требуются права администратора)

1. [ ] Создать в профиле администратора переход на страницу списка волонтёров
2. [ ] Реализовать сортировку по статусу занятости волонтёров (sortByStatus(statusType status))
    
#### Получение контактных данных пользователям (требуются права администратора)

1. [ ] Реализовать возможность открыть страницу пользователя
2. [ ] Загрузить данные выбранного пользователя(getClientInfo(int id))
3. [ ] При наличии обработать ошибки и оповестить о них пользователя
- Недостаточно прав
	- Ошибка сервера
- Ошибка подключения к интернету
4. [ ] Отобразить данные пользователя (loadClientInfo)

#### Добавление нового волонтерского центра (требуются права администратора)

1.  [ ] Создать страницу ввода данных волонтерского центра
- Название
- Адрес
- Информация о волонтерском центре
- Загрузка фотографий волонтерского центра
2. [ ] Выполнить загрузку данных на сервер(addCenter())
3. [ ] При наличии обработать и оповестить об ошибке , вернуть к заполнению
- Недостаточно прав
- Введены некорректные данные
	- Ошибка сервера
- Ошибка подключения к интернету
4. [ ] При отсутствии ошибок выйти на главный экран

	
#### Назначение нового администратора (требуются права администратора)

1. [ ] Создать кнопку в профилях пользователей “назначить администратором”
2. [ ] Отправить запрос о назначении администратора на сервер(newAdmin())
3. [ ] При наличии ошибок обработать их и сообщить о них
- Недостаточно прав для совершения действия
- Нет подключения к интернету


#### Снятие прав администратора (требуются права администратора)

[ ] Создать кнопку в профилях пользователей “Снять администратора”
[ ] Отправить запрос о снятии администратора на сервер(deleteAdmin())
[ ] При наличии ошибок обработать их и сообщить о них
- Недостаточно прав для совершения действия
- Нет подключения к интернету




#### App UI/UX maket: https://www.figma.com/design/oGRluA21qfzD8f7vAkSZVP/Samsung-Bootcamp-2025?node-id=0-1&t=TbH518QiGHsnPm96-1
