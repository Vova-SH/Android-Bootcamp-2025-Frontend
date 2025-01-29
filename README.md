# Android-Bootcamp-2025-Frontend
Разработать программный продукт для помощи в осуществлении волонтерской деятельности в клиент-серверной архитектуре.

Клиент - Android Приложение, реализованное на java или kotlin с адаптивной версткой. Мобильное приложение должно предоставлять удобный и интуитивно понятный интерфейс для волонтеров, в котором можно зарегистрироваться в базу волонтеров, просмотреть ближайшие волонтерские центры, отредактировать собственный профиль и посмотреть активных волонтеров в конкретном центре. В случае авторизации с правами администратора, пользователь приложения должен иметь возможность просмотреть список не занятых волонтеров и их контактные данные.

Сервер - микросервис на Spring Boot, работающий с In-memory базой данных H2. Серверное приложение должно предоставлять API необходимый для работы мобильного приложения. Осуществлять все основные манипуляции с данными в СУБД при помощи Spring Data JPA. Данные о всех зарегистрированных волонтерских центрах должны быть предзаполнены в БД при запуске приложения.Создание схемы БД и предзаполнение данными необходимо реализовать с использованием liquibase.

## Команда: Нет минусов, одни си плюс плюсы
# 
## User Stories
# Волонтёр
1)Я хочу иметь возможность созддать в системе новый аккаунт  
2)Я хочу иметь возможность войти в ствой аккаунт по почте и паролю  
3)Пользуясь картой в приложении, я хочу просматривать ближайшие волонтёрские центры  
4)Я хочу иметь возможность связаться с админом или оставитьь ему сообщение в системе  
5)Я хочу иметь возможность редактировать профиль при необходимости  

# Админ
1)Я хочу видеть список волонтёров своего города и иметь возможность запрашивать более подробную информацию о каждом  
2)Я хочу иметь возможность оставить сообщение для юзера при необходимости  
# 
## User Cases
## Пользователь
# 1)Цель: Создание аккаунта
# Участники: Волонтёр
# Основной поток: 
* Создание аккаунта  по имени, фамилии, дате рождения, email, телефону, паролю, повторному вводу пароля и ввод/выбор города  
* Проверка правильности формата введённых данных  
* Отправка запроса на сервер  
* Получение и сохранение токена  
* Переход в основную активность  
# Альтернативный поток
При незаполненности полей или неправильном их заполнении вывод того, что сделано не правильно в Toast 
# 
# 2)Цель: Вход в приложение
# Участники: Волонтёр
# Основной поток: 
* Ввод пользователем емайла и пароля  
* Отправка запроса на сервер  
* Получение и сохранение токена  
* Переход в основную активность  
# Альтернативный поток
* При незаполненности полей вывод того, что сделано не правильно в Toast  
* При отсутствии такого пользователя в базе данных сервера вывод сообщения об этом  
# 
# 3)Цель: Просмотр волонтёрских центров на карте
# Участники: Волонтёр
# Основной поток: 
* Отправка положения пользователя на сервер  
* Получение координат ближайших волонтёрских центров  
* Отображение их на карте  
# Альтернативный поток
* Запрос на получения доступа к геолокации пользователя/  
* Отправка с сервера всех волонтёрских центров города пользователя   
* Отображение их на карте  
#
# 4)Цель: Связь с админом
# Участники: Волонтёр
# Основной поток: 
* Запрос на сервер о всех админов этого города  
* Выбор какому админу в этом городе написать  
* Написание текста  
* Отправка сообщения через сервер админу  
# Альтернативный поток
* Превышенно количество сообщений в минуту, вывод сообщения об этом пользователю  
# 
# 5)Цель: Редактирование профиля
# Участники: Волонтёр
# Основной поток: 
* Заполнение полей с новыми данными, нажатие на кнопку отправить  
* Отправка запроса на сервер  
* Вывод пользователю сообщения об успешном изменении   
# Альтернативный поток
* При незаполненности полей или неправильном их заполнении вывод того, что сделано не правильно в Toast  
# 
## Админ
# 1)Цель: Запрос всех пользователей города админа
# Участники: Волонтёр
# Основной поток: 
* При открытии активности запрос данных о всех пользователях текущего региона админа  
* Выбор интересующего пользователя, нажатие на поле с данными с ним(имя, фамиллия, электронная почта)  
* Запрос на сервер о всех данных кроме пароля этого пользователя
* Вывод данных пришедших с сервера админу  
#
# 2)Цель: Связь с пользователем
# Участники: Волонтёр
# Основной поток: 
* Запрос на сервер о всех пользователях этого города  
* Выбор какому пользователю в этом городе написать  
* Написание текста  
* Отправка сообщения через сервер пользователю  
#
## Задачи
## Основные
1)Проработать взаимодействие пользователя с активностью входа, создания аккаунта, смены данных аккаунта  
2)Сделать разные и удобные для работы главные активности пользователя и админа, наполненные разным(по ролям) и интуитивно понятным содержанием   
3)Реализовать работу с картой  
4)Сделать связь с сервером и убрать возможность отправки пользователем на сервер не корректно введённых данных  
5)Сделать систему токенов(получение, сохранение, работа с ними)  
6)Обработка ошибок и исключений  

## Не основные
1)Добавить возможность переписки поьзователя и админа в формате оставления уведомления в приложении  
2)Сделать ленту событий текущего города (собрания, праздники, мероприятия)  
3)Добавить возможность ставить пользовательские метки на карте и делится ими друг с другом (админ<->пользователь)  

Ссылка на оформление приложения:  
https://www.figma.com/design/lueMF8h77WokfL7mz8rgoR/Untitled?m=auto&t=dma0ByQPv3Wo5Ji7-6
