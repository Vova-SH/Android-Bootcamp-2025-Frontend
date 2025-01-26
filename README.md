# Android-Bootcamp-2025-Frontend
Разработать программный продукт для помощи в осуществлении волонтерской деятельности в клиент-серверной архитектуре.

Клиент - Android Приложение, реализованное на java или kotlin с адаптивной версткой. Мобильное приложение должно предоставлять удобный и интуитивно понятный интерфейс для волонтеров, в котором можно зарегистрироваться в базу волонтеров, просмотреть ближайшие волонтерские центры, отредактировать собственный профиль и посмотреть активных волонтеров в конкретном центре. В случае авторизации с правами администратора, пользователь приложения должен иметь возможность просмотреть список не занятых волонтеров и их контактные данные.

Сервер - микросервис на Spring Boot, работающий с In-memory базой данных H2. Серверное приложение должно предоставлять API необходимый для работы мобильного приложения. Осуществлять все основные манипуляции с данными в СУБД при помощи Spring Data JPA. Данные о всех зарегистрированных волонтерских центрах должны быть предзаполнены в БД при запуске приложения.Создание схемы БД и предзаполнение данными необходимо реализовать с использованием liquibase.

1. Юзер стори
1.1. Волонтер
Как волонтер, я хочу зарегистрироваться в системе, чтобы участвовать в волонтерских мероприятиях.
Как волонтер, я хочу просмотреть список ближайших волонтерских центров, чтобы выбрать подходящий для участия.
Как волонтер, я хочу редактировать свой профиль, чтобы обновить информацию о себе.
Как волонтер, я хочу видеть активных волонтеров в конкретном центре.
1.2. Администратор
Как администратор, я хочу войти в систему, чтобы получить доступ к управлению данными.
Как администратор, я хочу просмотреть список свободных волонтеров, чтобы находить подходящих для мероприятий.
Как администратор, я хочу видеть контактные данные волонтеров, чтобы связаться с ними при необходимости.
2. Юзер кейсы
2.1. Регистрация волонтера
Основной поток:
Акторы: Волонтер
Предусловия: волонтёр не авторизован.
•	Пользователь выбирает опцию "Регистрация".
•	Пользователь вводит необходимые данные.
•	Пользователь подтверждает регистрацию.
•	Система сохраняет данные в базе данных.
•	Пользователь получает уведомление о успешной регистрации.
Альтернативный поток:
•	Пользователь вводит неверные данные.
•	Сервер возвращает ошибку.
•	Волонтёр видит сообщение о неверных данных.
2.3. Просмотр волонтерских центров
Акторы: Волонтер
Предусловия: волонтёр авторизован.
Основной поток:
•	Пользователь выбирает опцию "Просмотр волонтерских центров".
•	Система отображает список ближайших волонтерских центров.
•	Пользователь может выбрать центр для получения дополнительной информации.
Альтернативный поток:
•	Пользователь получает сообщение о отсутствии ближайших центров.
2.4. Редактирование профиля волонтера
Акторы: Волонтер
Предусловия: волонтёр авторизован.
Основной поток:
•	Пользователь выбирает опцию "Редактировать профиль".
•	Пользователь вносит изменения в информацию.
•	Пользователь подтверждает изменения.
•	Система обновляет данные в базе данных.
Альтернативный поток:
•	Пользователь не подтверждает изменения.
•	В системе остаются те же данные.
2.5. Просмотр активных волонтеров в центре
Предусловия: волонтёр авторизован.
Основной поток:
•	Пользователь выбирает волонтерский центр.
•	Система отображает список активных волонтеров в выбранном центре.
Альтернативный поток:
•	Система выдаёт отсутствие активных волонтеров в выбранном центре.
2.6. Авторизация администратора
Акторы: Администратор
Предусловия: Администратор авторизован.
Основной поток:
•	Администратор выбирает опцию "Вход для администратора".
•	Администратор вводит свои учетные данные.
•	Система проверяет данные и предоставляет доступ к административной панели.
Альтернативный поток:
•	Пользователь вводит неверные данные.
•	Сервер возвращает ошибку.
•	Пользователь видит сообщение о неверных данных.
2.7. Просмотр свободных волонтеров
Акторы: Администратор
Предусловия: Администратор авторизован.
Основной поток:
Администратор выбирает опцию "Просмотр свободных волонтеров".
Система отображает список свободных волонтеров с их контактными данными.
Альтернативный поток:
•	Система выдаёт отсутствие свободных волонтеров.

Задачи для frontend-разработчика:
Реализация вида для волонтеров:
3.1 Реализвать экран регистрации для волонтеров:
•   Поля ввода данных;
•   Отправка данных на сервер;
•   Обработка ошибок(неверные данные, проблемы с сетью и тд.).
3.2 Реализвать экран для просмотра волонтерских центров:
•   Отображение волонтерских центровпо близости местонахождения;
•   Отображение контактной информации центра;
•   Реализовать сортировку центров(по близости, направлениям волонтерства, популярности).
3.3 Реализовать экран профиля волонтера:
•   Отображение контактных данных;
•   Отображение привязки к центру;
•   Реализовать возможность изменения данных профиля волонтером;
3.4 Реализовать экран активных волонтеров в конкретном центре:
•   Отображение списка волонтеров по центру.
Реализация вида для администраторов:
4.1 Реализвать экран регистрации для администраторов:
•   Поля ввода данных;
•   Отправка данных на сервер;
•   Обработка ошибок(неверные данные, проблемы с сетью и тд.).
4.2 Реализовать экран для просмотра контактных данных свободных волонтеров:
•   Отображение списка свободных волонтеров и их контактных данных;
•   Реализовать возможную фильтрацию свободных волонтеров(возраст, опыт, близость, предпочитаемое направление волонтерства).
