# Android-Bootcamp-2025-Frontend
Разработать программный продукт для помощи в осуществлении волонтерской деятельности в клиент-серверной архитектуре.

Клиент - Android Приложение, реализованное на java или kotlin с адаптивной версткой. Мобильное приложение должно предоставлять удобный и интуитивно понятный интерфейс для волонтеров, в котором можно зарегистрироваться в базу волонтеров, просмотреть ближайшие волонтерские центры, отредактировать собственный профиль и посмотреть активных волонтеров в конкретном центре. В случае авторизации с правами администратора, пользователь приложения должен иметь возможность просмотреть список не занятых волонтеров и их контактные данные.

Сервер - микросервис на Spring Boot, работающий с In-memory базой данных H2. Серверное приложение должно предоставлять API необходимый для работы мобильного приложения. Осуществлять все основные манипуляции с данными в СУБД при помощи Spring Data JPA. Данные о всех зарегистрированных волонтерских центрах должны быть предзаполнены в БД при запуске приложения.Создание схемы БД и предзаполнение данными необходимо реализовать с использованием liquibase.

## Команда: СамАйти

## User Stories
 1. Регистрация пользователя: Как пользователь, я хочу зарегистрироваться в приложении, чтобы получить доступ к функционалу приложения.
 2. Регистрация администратора: Как администратор, я хочу зарегистрироваться в приложении, чтобы получить доступ к функциям управления волонтерами.
 3. Авторизация пользователя: Как пользователь, я хочу авторизоваться, чтобы получить доступ к своему профилю и персонализированным функциям приложения.
 4. Авторизация администратора: Как администратор, я хочу авторизоваться, чтобы управлять данными о волонтерах и центрах.
 5. Просмотр ближайших волонтерских центров: Как волонтер, я хочу видеть список ближайших волонтерских центров, чтобы выбрать подходящий для участия.
 6. Редактирование профиля: Как пользователь, я хочу редактировать свой профиль, чтобы обновить информацию о себе.
 7. Просмотр активных волонтеров в центре: Как волонтер, я хочу видеть список активных волонтеров в конкретном центре, чтобы связаться с ними или узнать о текущей активности.
 8. Просмотр незанятых волонтеров (администратор): Как администратор, я хочу видеть список незанятых волонтеров и их контактные данные, чтобы назначать их на задачи.
 9. Предзаполнение базы данных волонтерских центров: Как разработчик, я хочу, чтобы база данных автоматически заполнялась информацией о волонтерских центрах при запуске сервера, чтобы ускорить процесс начальной настройки системы.
 10. Интеграция токена авторизации: Как пользователь, я хочу, чтобы мои действия были защищены с помощью авторизационного токена, чтобы никто не мог получить доступ к моим данным без разрешения.
11.  Сохранение пользовательских данных: Как пользователь/администратор, я хочу, чтобы после авторизации мои данные автоматически сохранялись в приложении, чтобы при повторном входе не вводить их заново.

## Use Cases

Use Case 1: Регистрация нового волонтера 
Цель: Регистрация волонтера в базе волонтеров. 
Участники: Волонтер.
Предусловия: Волонтер не зарегистрирован в системе. 
Основной поток: 
1. Волонтер открывает экран регистрации.
2. Вводит имя, фамилию, email, пароль и другие необходимые данные. 
3. Отправляет запрос на сервер для регистрации.
4. Сервер проверяет данные и создает новую учетную запись.
5. Возвращается подтверждение успешной регистрации.
6. Пользователь перенаправляется на экран авторизации. 
Альтернативный поток: 
• Пользователь вводит уже зарегистрированный email. 
1. Сервер возвращает ошибку: “Email уже используется”. 
2. Отображается сообщение об ошибке. 
3. Волонтер может повторить попытку. 

Use Case 2: Авторизация волонтера 
Цель: Авторизация волонтера для получения доступа к функционалу приложения. 
Участники: Волонтер, администратор. 
Предусловия: Учетная запись создана.
Основной поток: 
1. Волонтер открывает экран авторизации. 
2. Вводит email и пароль. 
3. Отправляет запрос на сервер для проверки учетных данных.
4. Сервер проверяет данные и возвращает токен авторизации.
5. Пользователь получает доступ к главному экрану. 
Альтернативный поток: • Волонтер вводит неверные учетные данные. 
1. Сервер возвращает ошибку: “Неверные email или пароль”.
2. Отображается сообщение об ошибке. 

Use Case 3: Регистрация администратора
Цель: Регистрация администратора для получения доступа к функциям управления волонтерами и центрами.
Участники: Администратор.
Предусловия:
1. Администратор не зарегистрирован в системе.
Основной поток:
1. Администратор открывает экран регистрации.
2. Вводит свои данные (имя, email, пароль).
3. Отправляет данные на сервер.
4. Сервер проверяет корректность данных (например, уникальность email).
5. Сервер создает учетную запись администратора и возвращает подтверждение.
6. Приложение уведомляет администратора об успешной регистрации и перенаправляет на экран авторизации.
Альтернативный поток:
• Если введенные данные некорректны (например, email уже используется):
1. Сервер возвращает сообщение об ошибке.
2. Приложение отображает ошибку пользователю.
3. Администратор исправляет данные и повторяет попытку.

Use Case 4: Авторизация администратора
Цель: Авторизация администратора для доступа к функциям управления волонтерами и центрами.
Участники: Администратор.
Предусловия: Учетная запись администратора создана.
Основной поток:
1. Администратор открывает экран авторизации.
2. Вводит email и пароль.
3. Отправляет данные на сервер.
4. Сервер проверяет корректность учетных данных.
5. При успешной авторизации сервер возвращает JWT токен.
6. Приложение сохраняет токен и предоставляет доступ к функциям администратора.
Альтернативный поток:
• Если данные некорректны (например, неверный пароль):
1. Сервер возвращает сообщение об ошибке.
2. Приложение отображает ошибку пользователю.
3. Администратор повторяет попытку.

Use Case 5: Просмотр списка ближайших волонтерских центров
Цель: Отображение списка центров для волонтера. 
Участники: Волонтер. 
Предусловия: Волонтер авторизован. 
Основной поток: 
1. Волонтер открывает раздел с волонтерскими центрами. 
2. Приложение отправляет запрос на сервер для получения списка ближайших центров. 
3. Сервер возвращает данные о центрах. 
4. Пользователь видит список центров с информацией (название, адрес, описание). Альтернативный поток: 
• Нет доступных волонтерских центров. 
1. Сервер возвращает пустой список. 
2. Волонтер видит сообщение: “Центры не найдены”. 

Use Case 6: Редактирование профиля волонтера 
Цель: Позволить волонтеру изменить данные своего профиля. 
Участники: Волонтер. 
Предусловия: Волонтер авторизован. 
Основной поток: 
1. Волонтер открывает экран редактирования профиля. 
2. Вносит изменения (например, обновляет email, имя, номер телефона). 
3. Нажимает “Сохранить изменения”. 
4. Приложение отправляет обновленные данные на сервер. 
5. Сервер обновляет данные в базе. 
6. Волонтер видит подтверждение успешного сохранения изменений. 
Альтернативный поток: 
• Введены некорректные данные (например, неверный формат email). 
1. Сервер возвращает ошибку: “Некорректные данные”. 
2. Волонтер видит сообщение об ошибке и может повторить попытку.

Use Case 7: Просмотр активных волонтеров в центре 
Цель: Показать волонтеру список активных волонтеров в выбранном центре. 
Участники: Волонтер. 
Предусловия: Волонтер авторизован и выбрал волонтерский центр. 
Основной поток:
1. Волонтер открывает информацию о центре.
2. Приложение отправляет запрос на сервер для получения данных об активных волонтерах. 
3. Сервер возвращает список активных волонтеров. 
4. Волонтер видит список с контактными данными волонтеров. 
Альтернативный поток: 
• Нет активных волонтеров в центре. 
1. Сервер возвращает пустой список.
2. Волонтер видит сообщение: “В этом центре пока нет активных волонтеров”. 

Use Case 8: Просмотр списка незанятых волонтеров (администратор)
Цель: Предоставить администратору доступ к списку незанятых волонтеров.
Участники: Администратор. 
Предусловия: Пользователь авторизован с правами администратора. 
Основной поток: 
1. Администратор открывает экран с незанятыми волонтерами.
2. Приложение отправляет запрос на сервер для получения списка. 
3. Сервер возвращает список незанятых волонтеров и их контактные данные.
4. Администратор видит данные и может использовать их для назначения задач.
 Альтернативный поток: 
• Все волонтеры заняты. 
1. Сервер возвращает пустой список.
2. Администратор видит сообщение: “Все волонтеры заняты”.

Use Case 9: Автозаполнение базы данных
Цель: Автоматически заполнять базу данных предустановленной информацией о волонтерских центрах.
Участники: Разработчик.
Предусловия: Серверное приложение запускается впервые.
Основной поток:
1. При запуске приложения выполняется миграция базы данных с использованием Liquibase.
2. Таблица волонтерских центров заполняется заранее подготовленными данными.
Альтернативный поток:
• Если база данных уже заполнена, миграция не применяется.

 Use Case 10: API для мобильного приложения
Цель: Обеспечить взаимодействие мобильного приложения с сервером.
Участники: Разработчик.
Предусловия: Сервер развернут и доступен для запросов.
Основной поток:
1. Разработчик мобильного приложения делает запросы к серверу через предоставленные эндпоинты.
2. Сервер возвращает необходимые данные или подтверждает выполнение операций.
Альтернативный поток:
• Сервер недоступен или возвращает ошибки, которые обрабатываются клиентским приложением.

Use Case 11: Сохранение данных пользователя
Цель: Обеспечить сохранение данных пользователя в приложении, чтобы он не вводил их заново при повторном входе.
Участники: Пользователь.
Предусловия:
 1. Пользователь авторизован в приложении.
Основной поток:
 1. Пользователь вводит учетные данные и авторизуется в приложении.
 2. Приложение отправляет запрос авторизации на сервер.
 3. Сервер возвращает JWT токен, подтверждающий авторизацию.
 4. Приложение сохраняет токен авторизации локально на устройстве.
 5. Приложение загружает данные пользователя с сервера (например, имя, email, контактные данные).
 6. Приложение сохраняет данные пользователя локально.
 7. При следующем запуске приложения токен авторизации используется для автоматической загрузки данных пользователя и входа в систему без повторного ввода учетных данных.
Альтернативный поток:
• Если данные пользователя не удалось загрузить:
 1. Приложение отображает ошибку о невозможности загрузки данных.
 2. Пользователь повторяет попытку позже.

## Задачи для фронтенд-разработчика 

1. Создать экран регистрации: 
• POST /api/auth/register — регистрация пользователя или администратора.
а. Реализовать форму с полями: имя, фамилия, email, пароль, дополнительные данные. 
b. Добавить валидацию на стороне клиента (проверка формата email). 
c. Реализовать отправку данных на сервер. 
d. Обрабатывать ответы сервера (успех/ошибки). 

2. Создать экран авторизации:
• POST /api/auth/login — авторизация пользователя или администратора.
а. Добавить поля ввода email и пароля. 
b. Реализовать отправку данных на сервер для авторизации.
c. Обрабатывать ошибки (неверные данные, отсутствие соединения с сервером). 
d. Сохранять токен авторизации для дальнейшего использования. 

3. Создать экран списка ближайших волонтерских центров: 
• GET /api/centers — получение списка волонтерских центров.
а. Отображать список центров с их названием, адресом и описанием. 
b. Реализовать адаптивный дизайн для разных экранов. 
c. Реализовать навигацию между фрагментами. 
d. Обрабатывать ситуации, когда список пуст.

4. Создать экран редактирования профиля: 
• GET /api/profile — получение данных профиля.
• PUT /api/profile — обновление данных профиля.
а. Добавить форму с текущими данными пользователя.
b. Реализовать возможность изменения данных (имя, email, телефон и др.). 
c. Реализовать отправку обновленных данных на сервер. 
d. Обрабатывать ошибки сервера и показывать их пользователю.
 
5. Создать экран активных волонтеров в центре: 
• GET /api/centers/{id}/volunteers — получение списка активных волонтеров в конкретном центре.
а. Отображать список активных волонтеров (имя, контактная информация). 
b. Добавить возможность обновления списка (например, при свайпе вверх). 
c. Реализовать обработку пустого списка с уведомлением пользователя. 

6. Создать экран для администратора с незанятыми волонтерами: 
• GET /api/volunteers/unassigned — получение списка незанятых волонтеров.
а. Отображать список незанятых волонтеров с контактной информацией.
b. Обрабатывать пустой список с уведомлением. 

7. Интеграция с API: 
• PATCH /api/centers/{id}/volunteers/{userId} — прикрепить или открепить волонтера к центру.
а. Настроить клиент для выполнения запросов к серверу. 
b. Реализовать использование токена авторизации для каждого запроса. 
c. Обрабатывать ошибки сетевого взаимодействия.
