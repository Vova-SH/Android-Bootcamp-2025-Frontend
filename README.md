# Android-Bootcamp-2025-Frontend
## Команда: Inverse
Разработать программный продукт для помощи в осуществлении волонтерской деятельности в клиент-серверной архитектуре.

Клиент - Android Приложение, реализованное на java или kotlin с адаптивной версткой. Мобильное приложение должно предоставлять удобный и интуитивно понятный интерфейс для волонтеров, в котором можно зарегистрироваться в базу волонтеров, просмотреть ближайшие волонтерские центры, отредактировать собственный профиль и посмотреть активных волонтеров в конкретном центре. В случае авторизации с правами администратора, пользователь приложения должен иметь возможность просмотреть список не занятых волонтеров и их контактные данные.

Сервер - микросервис на Spring Boot, работающий с In-memory базой данных H2. Серверное приложение должно предоставлять API необходимый для работы мобильного приложения. Осуществлять все основные манипуляции с данными в СУБД при помощи Spring Data JPA. Данные о всех зарегистрированных волонтерских центрах должны быть предзаполнены в БД при запуске приложения.Создание схемы БД и предзаполнение данными необходимо реализовать с использованием liquibase.


# **[Дизайн](https://www.figma.com/design/xe1KhnzSbdKVsdFNy4C2ld/Samsung-Bootcamp-Inverse?node-id=1-2&t=v8YPAJRWTwumCVwM-1)**

# **User stories**

Прим.: *с более оформленной версией советуем ознакомиться [здесь](https://docs.google.com/document/d/1CVCk9VR4h4Bymbz0GxjlXOlqCCWtppHFymjkHAL3WTM/edit?usp=sharing)*
* ## **Регистрация / авторизация**

    * Как волонтер, я хочу иметь возможность зарегистрироваться, чтобы иметь доступ к приложению
    * Как волонтер, я хочу иметь возможность войти в аккаунт при первом запуске, если я уже был зарегистрирован, чтобы продолжить пользоваться своим аккаунтом
    * Как волонтер, я не хочу вводить данные повторно после авторизации, чтобы не тратить время на ввод данных

* ## **Просмотр центров**

    * Как волонтер, я хочу видеть список ближайших доступных центров, чтобы понять, где рядом может потребоваться моя помощь
    * Как волонтер, я хочу видеть список волонтеров, принимающих участие в конкретном центре, чтобы узнать, есть ли там мои друзья

* ## **Редактирование профиля**

    * Как волонтер, я хочу редактировать свой профиль, чтобы другие волонтеры могли легко меня узнавать
    * Как администратор, я хочу иметь возможность модерировать профили волонтеров, чтобы избежать неприемлемого контента на платформе

* ## **Администрирование центров**

    * Как администратор, я хочу иметь возможность добавления центра, чтобы волонтеры могли принять участие в моих мероприятиях
    * Как администратор, я хочу иметь возможность редактировать информацию о центре, чтобы волонтеры имели актуальную информацию о нем

* ## **Администрирование волонтеров**

    * Как администратор, я хочу видеть список незанятых волонтеров, а также их контактные данные, чтобы связаться с ними по поводу участия в мероприятиях
    * Как администратор, я хочу иметь возможность изменять статус занятости волонтера, а также прикреплять его к определенному центру и откреплять от него, чтобы в центрах был актуальный список волонтеров и сами волонтеры могли видеть прикрепленный к ним центр

* ## **Безопасность**

    * Как волонтер, я хочу быть уверенным в конфиденциальности вводимых мною при регистрации данных, чтобы избежать утечки персональных данных
    * Как администратор, я хочу быть уверенным в настройках безопасности приложения, чтобы избежать неавторизованного доступа


# **Use Cases**

Прим.: *Здесь и далее пользователь – администратор / волонтер*

## **Запуск приложения / сервера**

### **`AutoCompleteDatabaseUseCase`**

* Цель: автозаполнение данных в базе на основе внешнего источника или шаблонов
* Акторы: система (сервер)
* Предусловие: источник данных доступен, таблица существует
* Триггер: запрос на автозаполнение данных
* Основной сценарий: получен запрос с указанием источника данных, таблицы и критериев, система проверяет данные на корректность (существование таблицы, доступность источника), если все данные верны, система заполняет таблицу, система отправляет подтверждение об успешном завершении автозаполнения
* Альтернативный сценарий:
    * Ошибка данных: некорректные данные (ошибка в таблице или источнике) — система возвращает ошибку
    * Ошибка сервера: проблемы на сервере — система сообщает об ошибке на сервере

### **`LaunchApiUseCase`**

* Цель: инициализировать необходимые репозитории, а также API
* Акторы: клиент (приложение)
* Предусловие: приложение установлено корректно
* Триггер: запуск приложения
* Основной сценарий: отправка запроса на сервер на получение репозиториев, инициализация API и основных сущностей, запуск приложения
* Альтернативный сценарий:
    * приложение было запущено корректно, остановка приложения
    * отсутствует подключение к интернету, сообщаем пользователю, ожидание подключения

## **Регистрация / Авторизация**

### **`RegisterNewAccountUseCase`**

* Цель: новый аккаунт добавлен в БД на сервере
* Акторы: пользователь
* Предусловие: аккаунт не был зарегистрирован / предыдущая сессия была завершена
* Триггер: открытие приложения
* Основной сценарий: пользователь указал данные для регистрации (почта / логин / номер телефона и пароль), сервер подтвердил регистрацию, запуск `AuthorizeUseCase`
* Альтернативный сценарий:
    * пользователь ввел некорректные данные, сервер ответил ошибкой, ошибка отображается на экране, пользователь не допущен к использованию приложения
    * пользователь выбрал опцию войти, запуск `LoginAccountUseCase`

### **`LoginAccountUseCase`**

* Цель: получить доступ к приложению, используя существующую учетную запись
* Акторы: пользователь
* Предусловие: учетная запись была ранее создана
* Триггер: нажатие кнопки войти в `RegisterNewAccountUseCase` / неудачный вход при попытке использования сохраненных данных
* Основной сценарий: пользователь указал данные для входа (почта / логин / номер телефона и пароль), сервер подтвердил вход, пользователь авторизован
* Альтернативный сценарий:
    * пользователь ввел некорректные данные, сервер ответил ошибку, ошибка отображается на экране, пользователь не авторизован
    * пользователь выбрал опцию войти, запуск `RegisterNewAccountUseCase`

### **`AuthorizeAccountUseCase`**

* Цель: пользователь авторизован по сохраненным данным для входа
* Акторы: пользователь
* Предусловие: нет
* Триггер: при каждом запуске приложения
* Основной сценарий: сохраненные данные отправлены на сервер, сервер подтвердил вход, пользователь авторизован
* Альтернативный сценарий:
    * сохраненные данные более невалидны (аккаунт не существует, пароль был изменен), запуск `LoginAccountUseCase`, с отправкой сообщения пользователю о том, что данные более не валидны
    * Нет доступа к интернету, отображаем ошибку

## **Просмотр центров**

### **`GetNearestAvailableCentersUseCase`**

* Цель: просмотреть ближайшие активные центры
* Акторы: пользователь
* Предусловия: пользователь авторизован
* Триггер: пользователь открыл экран с отображением ближайших доступных центров
* Основной сценарий: клиент (приложение) отправило запрос на получение ближайших доступных центров, сервер ответил успешно, данные отобразились на экране
* Альтернативный сценарий: произошла ошибка во время отправки / получения данных, данные не отобразились на экране, на экране вывелась ошибка

### **`GetVolunteersListInCenterUseCase`**

* Цель: просмотреть активных волонтеров в выбранном центре
* Акторы: пользователь
* Предусловия: пользователь должен быть авторизован
* Триггер: нажатие на кнопку центра в списке ближайших доступных центров
* Основной сценарий: клиент (приложение) отправило запрос на получение активных волонтеров в конкретном центре, сервер ответил успешно, данные отобразились на экране
* Альтернативный сценарий: произошла ошибка во время отправки / получения данных, данные не отобразились на экране, на экране вывелась ошибка


## **Администрирование волонтеров**

### **`GetListOfUnemployedVolunteersUseCase`**

* Цель: получить список незанятых волонтеров и их контактных данных  
  Акторы: администратор
* Предусловия: пользователь должен быть авторизован с правами администратора
* Триггер: пользователь (администратор) выбрал соответствующую опцию в приложении
* Основной сценарий: пользователь (администратор) запрашивает список незанятых волонтеров и их контактные данные, сервер отвечает успешно, отображаем информацию
* Альтернативный сценарий: во время отправки / получения данных произошла ошибка, сервер ответил ошибкой, не отображаем информацию, отображаем сообщение об ошибке

### **`SetVolunteerCenterUseCase`**

* Цель: прикрепить волонтера к центру или открепить от него  
  Акторы: администратор, волонтер
* Предусловия: пользователь должен быть авторизован с правами администратора
* Триггер: пользователь (администратор) выбрал соответствующую опцию в приложении
* Основной сценарий: пользователь (администратор) после согласования с волонтером прикрепляет его к выбранному центру, отправляет запрос на сервер, сервер ответил успешно, волонтер видит у себя прикрепленный центр, а другие пользователи видят его в списке активных волонтеров выбранного центра
* Альтернативный сценарий: во время отправки / получения данных произошла ошибка, сервер ответил ошибкой, не изменяем статус занятости, отображаем сообщение об ошибке

## **Редактирование профиля**

### **`UpdateAccountProfileUseCase`**

* Цель: отредактировать профиль пользователя
* Акторы: пользователь
* Предусловия: пользователь должен быть авторизован
* Триггер: пользователь нажал кнопку редактировать профиль на экране просмотра профиля
* Основной сценарий: пользователь ввел корректные данные, данные отправлены на сервер для дальнейшей модерации, отображаем сообщение об успешной отправке данных, а также о статусе заявки
* Альтернативный сценарий: пользователь ввел невалидные данные, данные не отправляются на сервер, отображаем сообщение об ошибке

###  **`FetchUpdatedProfilesUseCase`**

* Цель: получить все заявки на изменение профиля
* Акторы: администратор
* Предусловия: пользователь должен быть авторизован с правами администратора
* Триггер: переход на экран модерации
* Основной сценарий: пользователь (администратор) запрашивает список аккаунтов с измененными профилями, сервер отвечает успешно, пользователь получает возможность модерировать и фиксировать изменения
* Альтернативный сценарий: во время отправки / получения данных произошла ошибка, не отображаем данные, вводим ошибку на экран

### **`CommitOrDenyChangedProfileUseCase`**

* Цель: одобрить или отклонить заявку на изменение профиля  
  Акторы: администратор, волонтер
* Предусловия: пользователь должен быть авторизован с правами администратора, аккаунт с измененным профилем должен быть среди аккаунтов из `FetchUpdatedProfilesUseCase`
* Триггер: пользователь (администратор) после проверки решил, что данный профиль НЕ удовлетворяет регламенту платформу и не принимает изменения
* Основной сценарий: пользователь (администратор) отправляет запрос на сервер на изменение профиля данного аккаунта , сервер отвечает успешно, профиль данного аккаунта изменен, меняем статус заявки на `“accepted”`
* Альтернативный сценарий:
    * пользователь (администратор) отправляет запрос на сервер на отказ для изменений профиля данного аккаунта, сервер отвечает успешно, профиль данного аккаунта изменен, меняем статус заявки на `“denied”`
    * Альтернативный сценарий: во время выполнения отправки / подтверждения запросы произошла ошибка, не изменяем профиль, выводим сообщение об ошибке


## **Администрирование центров**

### **`AddCenterUseCase`**

* Цель: добавить новый центр  
  Акторы: администратор
* Предусловия: пользователь должен быть авторизован с правами администратора
* Триггер: пользователь (администратор) выбрал соответствующий пункт в меню
* Основной сценарий: пользователь (администратор) вводит корректные данные для регистрации нового центра, сервер отвечает успешно, центр добавлен и отображается у волонтеров
* Альтернативный сценарий: пользователь (администратор) вводит некорректные данные для регистрации нового центра, сервер отвечает ошибкой, выводим на экран ошибку, центр не создается и не отображается у волонтеров

### **`UpdateCenterInfoUseCase`**

* Цель: обновить информацию о центре  
  Акторы: администратор
* Предусловия: пользователь должен быть авторизован с правами администратора
* Триггер: пользователь (администратор) выбрал опцию редактировать в меню информации о центре
* Основной сценарий: пользователь (администратор) вводит корректные данные, сервер отвечает успешно, данные обновлены, обновленный центр виден всем волонтерам
* Альтернативный сценарий: пользователь (администратор) вводит некорректные данные, сервер отвечает ошибкой, данные не будут обновлены, волонтеры продолжат видеть старые данные, выводим пользователю (администратору) сообщение об ошибке


# **Frontend tasks**

## Запуск приложения
### `LaunchApiUseCase`
* Data-cлой:
    - [ ] Реализовать получение API и репозиториев для дальнейшего использования
* Domain-слой:
    - [ ] Реализовать UseCase, в рамках которого, на основе возвращаемого значения из data-слоя, выбирается одна из трёх следующий стратегий:
        1. Если данные были успешно получены и сущности были инициализированы, запускаем приложение
        2. Если отсутствуют необходимые модули, останавливаем приложение
        3. Если отсутствует подключение к сети, сообщаем об этом через UI-слой и ожидаем подключения

* UI-слой:
    - [ ] Реализовать отправку сообщения об отсутствии подключения к интернету



## **Регистрация / авторизация**

### **`AuthorizeAccountUseCase`**

* Data-слой:
    * Endpoint **GET** /api/users/authorize
    - [ ] Реализовать репозиторий для общения с сервером и кэшем, для сущностей пользователей (AccountDto), добавить метод для входа пользователя с имеющимися данными
* Domain-слой:
    - [ ] Реализовать UseCase, в рамках которого, на основе возвращаемого значения из data-слоя, выбирается одна из трёх следующий стратегий:
        1. Если авторизация подтверждена, запускаем приложение с правами пользователя, указанных в DTO
        2. Если аккаунт вовсе не был зарегистрирован, т.е. приложение запущено в первый раз, запустить `RegisterNewAccountUseCase`
        3. Если имеющиеся данные оказались не валидны, то запускаем `LoginAccountUseCase` с указанием невалидности текущих данных
* UI-слой:
    - [ ] Реализовать splashscreen, видимый до тех пор, пока domain-слой не примет решение о дальнейшей стратегии


### **`RegisterNewAccountUseCase`**

* Data-слой:
    - Endpoint **POST** /api/users/register
    - [ ] Добавить в репозиторий метод для создания нового аккаунта,
* Domain-слой:
    - [ ] Реализовать UseCase, в рамках которого, на основе ответа репозитория и выбора пользователя, выбирается одна из следующих стратегий:
        1. Если сервер ответил успешно, сохраняем данные для ввода, запускаем `AuthorizeAccountUseCase`
        2. Если сервер ответил ошибкой, то передаем эту ошибку в UI слой
        3. Если пользователь выбрал опцию войти, запускаем `LoginAccountUseCase`
* UI-слой:
    - [ ] Реализовать фрагмент для регистрации пользователя, с полями: логин, пароль, почта, номер телефон, пол, возраст, а также опцией для входа в аккаунт
    - [ ] Реализовать класс ViewModel’и для возможности общения domain-слоя и UI-слоя
    - [ ] Сверстать разметку для фрагмента

### **`LoginAccountUseCase`**

* Data-слой:
    * Endpoint **POST** /api/users/login
* Domain-слой:
    - [ ] Реализовать UseCase, в рамках которого, на основе ответа от репозитория и выбора пользователя, выбирается одна из следующих стратегий:
        1. Если вход успешен, запуск `AuthoriseAccountUseCase`
        2. Если сервер ответил ошибкой, сообщаем об этом пользователю через UI-слой
        3. Если пользователь выбрал опцию зарегистрироваться, запускаем `RegisterNewAccountUseCase`
* UI-слой:
    - [ ] Реализовать фрагмент, в котором будет два поля: логин и пароль, а также опцию для регистрации аккаунта
    - [ ] Реализовать ViewModel для передачи данных для входа в UseCase
    - [ ] В случае неудачного входа, отображаем ошибку


## **Просмотр центров**

### **`GetNearestAvilableCentersUseCase`**

* Data-слой:
    * Endpoint **GET** /api/centers/nearest
    - [ ] Реализовать репозиторий для общения с сервером и кэшем, для сущностей пользователей (CenterDto), добавить метод для получения ближайших доступных центров
* Domain-слой:
    - [ ] Реализовать UseCase, в рамках которого, на основе ответа от репозитория, выбирается одна из следующих стратегий:
        1. Если данные пришли, передаем их в UI-слой
        2. Если сервер ответил ошибкой, передаем ее в UI-слой
* UI-слой:
    - [ ] Реализовать фрагмент со списком ближайших доступных центров, данные поставляются из VIewModel’и
    - [ ] Реализовать ViewModel с данными, поставляемыми domain-слоем
    - [ ] Сверстать разметку со списком и полем для отображения ошибки

### **`GetVolunteersListInCenterUseCase`**

* Data-слой:
    - Endpoint **GET** /api/centers/{centerId}/volunteers
    - [ ] Добавить метод для получения списка волонтеров в конкретном центре
* Domain-слой:
    - [ ] Реализовать UseCase, в рамках которого, на основе ответа от репозитория, выбирается одна из следующих стратегий:
        1. Если данные пришли, передаем их в UI-слой
        2. Если сервер ответил ошибкой, передаем ее в UI-слой
* UI-слой:
    - [ ] Реализовать фрагмент для отображения списка волонтеров в конкретном центре
    - [ ] Реализовать ViewModel для получения данных из domain-слоя
    - [ ] Сверстать разметку со списком и полем для ошибки


## **Администрирование волонтеров**

### **`GetListOfUnemployedVolunteersUseCase`**
* Data-слой:
    * Endpoint **GET** /api/admin/volunteers/unemployed
    - [ ] Добавить в репозиторий метод для получения всех волонтеров с их контактными данными
* Domain-слой:
    - [ ] Реализовать UseCase, в рамках которого, на основе ответа от репозитория, выбирается одна из следующих стратегий:
        1. Если сервер ответил успешно, передаем данные в UI-слой
        2. Если сервер ответил ошибкой, передаем ее в UI-слой
* UI-слой:
    - [ ] Реализовать фрагмент для отображения всех волонтеров и их контактных данных, а также с возможностью фильтрации по их занятости
    - [ ] Реализовать ViewModel для получения данных из domain-слоя
    - [ ] Сверстать разметку со списком и полем для ошибки

### **`SetVolunteersCenterUseCase`**

* Data-слой:
    - [ ] Endpoint **PUT** /api/users/profile
* Domain-слой:
    - [ ] Реализовать UseCase, в рамках которого, на основе ответа от репозитория и выбора пользователя (администратора), выбирается одна из следующих стратегий:
        1. Если сервер ответил успешно на внесение изменений, то передаем сообщение об этом в UI-слой
        2. Если сервер ответил ошибкой, передаем ее в UI-слой
* UI-слой:
    - [ ] Реализовать фрагмент для изменения статуса занятости у волонтера
    - [ ] Реализовать ViewModel для передачи данных в domain-слой и получения статуса из него
    - [ ] Сверстать верстку, содержащую поле ввода для центра и поле с ошибкой

## **Изменение профиля**

### **`UpdateAccountProfileUseCase`**

* Data-слой:
    - Endpoint **PUT** /api/users/profile
    - [ ] Реализовать репозиторий данных о пользователях (ProfileDto) и добавить метод для обновления информации о профиле
* Domain-слой:
    - [ ] Реализовать UseCase, в рамках которого, на основе ответа от репозитория и ввода пользователя, выбирается одна из следующих стратегий:
        1. Если ввод пользователя корректный и сервер успешно ответил, обновляем статус заявки
        2. Если пользователь ввел данные некорректно, просим ввести их заново, не отправляем данные на сервер
        3. Если сервер ответил ошибкой, передаем ее в UI-слой
* UI-слой:
    - [ ] Реализовать фрагмент для просмотра и изменения информации профиля пользователя
    - [ ] Реализовать ViewModel для поставки данных в domain-слой, а также получения статуса отправки из него
    - [ ] Сверстать разметку с полями: имя, фамилия, дата рождения, bio, ссылки, фото профиля, – и полем для ошибки

### **`FetchUpdatedProfilesUseCase`**

* Data слой:
    * Endpoint **GET** /api/admin/profiles/updated
    - [ ] Реализовать репозиторий для хранения заявок на изменения профиля (ChangedProfileDto) и добавить метод для получениях всех заявок
* Domain-слой:
    - [ ] Реализовать UseCase, в рамках которого, на основе ответа от репозитория, выбирается одна из следующих стратегий:
        1. Если данные пришли, передаем их в UI-слой
        2. Если сервер ответил ошибкой, передаем ее в UI-слой
* UI-слой:
    - [ ] Реализовать фрагмент для отображения всех заявок на изменение профиля
    - [ ] Реализовать VIewModel для получения данных из domain-слоя
    - [ ] Сверстать разметку для отображения всех заявок на изменения профилей со списком и полем для ошибки

### **`CommitOrDenyChangedProfileUseCase`**

* Data-слой:
    * Endpoint **POST** /api/admin/profiles/{profileId}/update
    - [ ] Добавить в репозиторий метод для сохранения изменений в профиле и для отклонения заявки на сохранение изменений
* Domain-слой:
    - [ ] Реализовать UseCase, в рамках которого, на основе ответа от репозитория и ввода пользователя, выбирается одна из следующих стратегий:
        1. Если администратор посчитал, что измененный профиль соответствует требованиям платформы, то отправляем на сервер обновленный профиль, обновляем статус заявки
        2. Если администратор посчитал, что измененный профиль не соответствует требованиям платформы, то не отправляем на сервер обновленный профиль, обновляем статус заявки
        3. Если сервер в момент отправки данных ответил ошибкой, то передаем ее в UI-слой, статус заявки не меняем
* UI-слой:
    - [ ] Реализовать фрагмент с просмотром заявки
    - [ ] Реализовать ViewModel для получения данных из domain-слоя
    - [ ] Сверстать разметку для отображения измененной заявки и полем для ошибки

## **Администрирование центров**
### **`AddCenterUseCase`**

* Data-слой:
    * Endpoint **POST** /api/admin/centers
    - [ ] Добавить в репозиторий метод для добавления нового центра
* Domain-слой:
    - [ ] Реализовать UseCase, в рамках которого, на основе ответа от репозитория и ввода пользователя, выбирается одна из следующих стратегий:
        1. Если ввод данных корректен, и сервер ответил успешно, то передаем в UI-слой сообщение об успешном создании центра
        2. Если ввод данных некорректен, или сервер ответил ошибкой, то передаем в UI-слой ошибку
* UI-слой:
    - [ ] Реализовать фрагмент для создания нового центра
    - [ ] Реализовать ViewModel для передачи данных в domain-слой и получения статуса из него
    - [ ] Сверстать разметку для ввода данных нового центра с полями: название, адрес, контактный номер, кол-во человек

## **`UpdateCenterInfoUseCase`**

* Data-слой:
    * Endpoint **PUT** /api/admin/centers/{centerId}
    - [ ] Добавить в репозиторий метод для обновления данных существующего центра
* Domain-слой:
    - [ ] Реализовать UseCase, в рамках которого, на основе ответа от репозитория и ввода пользователя, выбирается одна из следующих стратегий:
        1. Если ввод данных корректен, и сервер ответил успешно, то передаем в UI-слой сообщение об успешном изменении данных центра
        2. Если ввод данных некорректен, или сервер ответил ошибкой, то передаем в UI-слой ошибку
* UI-слой:
    - [ ] Реализовать фрагмент для редактирования информации существующего центра
    - [ ] Реализовать ViewModel для передачи данных в domain-слой и получения статуса из него
    - [ ] Сверстать разметку для ввода новых данных центра с полями: название, адрес, контактный номер, кол-во человек

