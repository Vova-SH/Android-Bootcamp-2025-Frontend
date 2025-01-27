# taskListForApp

## Команда: codeHub

## ТЗ

Разработать программный продукт для помощи в осуществлении волонтерской деятельности в клиент-серверной архитектуре.

Клиент - Android Приложение, реализованное на java или kotlin с адаптивной версткой. Мобильное приложение должно предоставлять удобный и интуитивно понятный интерфейс для волонтеров, в котором можно зарегистрироваться в базу волонтеров, просмотреть ближайшие волонтерские центры, отредактировать собственный профиль и посмотреть активных волонтеров в конкретном центре. В случае авторизации с правами администратора, пользователь приложения должен иметь возможность просмотреть список не занятых волонтеров и их контактные данные.

Сервер - микросервис на Spring Boot, работающий с In-memory базой данных H2. Серверное приложение должно предоставлять API необходимый для работы мобильного приложения. Осуществлять все основные манипуляции с данными в СУБД при помощи Spring Data JPA. Данные о всех зарегистрированных волонтерских центрах должны быть предзаполнены в БД при запуске приложения. Создание схемы БД и предзаполнение данными необходимо реализовать с использованием liquibase.

---

## Декомпозиция ТЗ
### User Stories
1. Как **пользователь**, я хочу зарегистрироваться в системе, чтобы находиться в базе волонтеров.
2. Как **пользователь**, я хочу просматривать ближайшие волонтерские центры, чтобы участвовать в волонтерских мероприятиях.
3. Как **пользователь**, я хочу редактировать свой профиль, чтобы поддерживать актуальную информацию о себе.
4. Как **пользователь**, я хочу узнать список волонтеров конкретного центра, чтобы быть осведомленным о волонтерах конкретного центра.
5. Как **администратор**, я хочу просмотреть список не занятых волонтеров, чтобы увидеть их контактные данные.
6. Как **администратор**, я умею удалять профили пользователей, чтобы удалять ненужные аккаунты

### Use Cases
1. **Цель:** Регистрация волонтера
	- **Акторы**: Пользователь
	- **Предусловия**: Учетная запись еще не создана. Пользователь находится на странице регистрации
	- **Основной поток:**
		1. Пользователь вводит в форму "Логин/Email" логин или почту без недопустимых символов
		2. Пользователь нажимает на кнопку "Регистрация"
		3. Данные отправляются на сервер и сохраняются в соответствующие поля в БД
		4. Приложение переходит на страницу с картой
	- **Альтернативный поток:**
		1. Пользователь вводит в поля запрещенные символы (@, [, ], \`, ', ", ~, \_, -, ;, .) или уже существующие данные 
		2. Пользователь нажимает кнопку "Регистрация"
		3. Появляется Toast: "Неверный ввод"

2.  **Цель:** Авторизация волонтера
	- **Акторы**: Пользователь
	- **Предусловия**: Учетная запись создана. Пользователь находится на странице авторизации
	- **Основной поток:**
		1. Пользователь вводит в форму "Логин/Email" логин или почту.
		2. Пользователь нажимает на кнопку "Войти"
		3. Данные проверяются на сервере
		4. Приложение переходит на главный экран
	- **Альтернативный поток:**
		1. Пользователь вводит несуществующие данные
		2. Пользователь нажимает кнопку "Войти"
		3. Toast: "Неверные данные"
	- **Альтернативный поток:**
		1. Пользователь вводит данные и нажимает "Сохранить"
		2. Сеть с сервером отсутствует. Toast: "Ошибка"

3.  **Цель:** Просмотр своего профиля
	- **Акторы**: Пользователь
	- **Предусловия**: Учетная запись создана.
	- **Основной поток:**
		1. Пользователь нажимает на иконку в углу
		2. Приложение получает данные с сервера.
		3. Приложение переходит на страницу профиля
	- **Альтернативный поток.**
		1. Отсутствует сеть с сервером.
		2. Toast: "Ошибка сети

4.  **Цель:** Редактирование профиля пользователя
	- **Акторы**: Пользователь
	- **Предусловия**: Учетная запись создана. Пользователь находится на странице профиля.
	- **Основной поток:**
		1. Пользователь нажимает на кнопку "Редактировать"
		2. Поля с текстом превращаются в поля ввода.
		3. Пользователь вносит изменения в контактные данные или информацию о навыках и предпочтениях и нажимает "Сохранить"
		4. Отправка данных на сервер. Toast: "Сохранено"
	- **Альтернативный поток:**
		1. Пользователь вводит данные и нажимает "Сохранить"
		2. Сеть с сервером отсутствует. Toast: "Ошибка"
	- **Альтернативный поток:**
		 1. Пользователь ввел данные в неправильном формате
		 2. Пользователь нажал "Сохранить"
		 3. Toast: "Неправильный формат"

5.  **Цель:** Удаление своего профиля пользователя
	- **Акторы**: Пользователь
	- **Предусловия**: Учетная запись создана. Пользователь находится на странице своего профиля.
	- **Основной поток:**
		1. Пользователь нажимает на кнопку "Удалить профиль"
		2. Появляется небольшое окно с выбором: "Вы уверены?"
		3. Пользователь выбирает "Да"
		4. Отправка запроса на сервер. Удаление данных с БД.
		5. Перенаправление пользователя на экран авторизации
	- **Альтернативный поток:**
		1. Пользователь выбирает "Нет"
		2. Закрытие появившегося окна
	- **Альтернативный поток:**
		1. Отсутствует сеть с сервером.
		2. Toast: "Ошибка сети"

 6.  **Цель:** Просмотр ближайших волонтерских центров
	 - **Акторы**: Пользователь
	 - **Предусловия**: Учетная запись создана.
	 - **Основной поток**:
        1. Получение местоположения пользователя.
        2. Получение данных волонтерских центров с сервера, сортировка по расстоянию до пользователя.
	 	3. Пользователь находится на карте или выбирает из отсортированного по расстоянию списка волонтерских центров
	 - **Альтернативный поток**:
	 	1. Отсутствует соединение с сервером
	 	2. Вместо списка вывод надписи "Отсутствует соединение с сервером"
	 	3. Попытка получить данные сервера каждые n-секунд
 7.  **Цель:** Поиск волонтерского центра
	 - **Акторы**: Пользователь
	 - **Предусловия**: Учетная запись создана, пользователь просматривает центры списком.
	 - **Основной поток**:
        1. Пользователь нажимает на значок лупы вверху.
        2. Появляется текстовое поле.
        3. Пользователь вводит адресс центра и нажимает найти.
        4. Получение данных с сервера.
        5. Формируется и выводится список найденных центров.
     - **Альтернативный поток**:
	 	1. Отсутствует соединение с сервером
	 	2. Вместо списка вывод надписи "Отсутствует соединение с сервером"
        или
        1. На сервере не найдено ни одного совпадения со введенными данными.
        2. Toast "не найдено"
8.  **Цель:** Просмотр страницы волонтерского центра
	- **Акторы**: Пользователь
	- **Предусловия**: Учетная запись создана. Пользователь нажал на волонтерский центр на карте или в списке ближайших волонтерских центров
	- **Основной поток**:
		1. Загрузка данных волонтерского центра с сервера (без списка волонтеров)
		2. Приложение открывает страницу центра
	- **Альтернативный поток**:
		1. Отсутствует соединение с сервером
		2. Toast: "Отсутствует соединение"

9.  **Цель:** Просмотр списка волонтеров конкретного центра
	- **Акторы**: Пользователь
	- **Предусловия**: Учетная запись создана. Пользователь находится на странице волонтерского центра
	- **Основной поток:**
		1. Пользователь нажимает на кнопку "Список волонтеров"
		2. Получение данных с бд с данными {name} конкретного центра
		3. Приложение открывает список имен волонтеров центра
	- **Альтернативный поток:**
		1. Отсутствует соединение с сервером
		2. Toast: "Отсутствует соединение"

10.  **Цель:** Просмотр списка не занятых волонтеров
	 - **Акторы**: Администратор
	 - **Предусловия**: Учетная запись создана. Пользователь открыл боковую панель
	 - **Основной поток:**
		1. Пользователь нажимает на кнопку "Список не занятых волонтеров"
		2. Получение данных с сервера
		3. Приложение открывает со списком волонтеров и их данными
	 - **Альтернативный поток:**
		1. Отсутствует сеть с сервером.
		2. Toast: "Ошибка сети"

11.  **Цель:** Удаление профиля любого пользователя
	 - **Акторы**: Администратор
	 - **Предусловия**: Учетная запись создана. Администратор находится на странице профиля пользователя
	 - **Основной поток:**
		1. Администратор нажимает на кнопку "Удалить профиль"
		2. Появляется небольшое окно с выбором: "Вы уверены?"
		3. Пользователь выбирает "Да"
		4. Отправка запроса на сервер. Удаление данных с БД.
		5. Перенаправление администратора на экран, с которого он зашел в профиль
	 - **Альтернативный поток:**
		1. Администратор выбирает "Нет"
		2. Закрытие появившегося окна
	 - **Альтернативный поток:**
		1. Отсутствует сеть с сервером.
		2. Toast: "Ошибка сети"

### Задачи frontend
1. Экран регистрации и авторизации
- поле ввода email, пароля, кнопка войти или зарегистрироваться
- связь с сервером, проверка данных на валидность, проверка есть ли пользователь в системе, переход на главный экран
2. Главный экран с картой
- запрос геолокации, связь с сервером для формирования карты
3. Главный экран со списком
- связь с сервером для формирования списка
4. Реализация поиска
- кнопка для поиска
- создание формы для ввода адресса центра
- связь сервером, поиск совпадений
- вывод в списке найденных центров
5. Экран центра
- при клике на центр на карте или в списке, приложение переходит на новый экран со всей информации о центре(информация берется из базы данных)
6. Экран профиля пользователя
- иконка-кнопка в углу экрана
- Связь с сервером, получение данных пользователя
- вывод данных в соответствующие поля
7. Редактирование профиля
- кнопка карандаша в углу экрана профиля
- создание полей ввода с уже имеющейся информацией.
- по кнопке данные отправляются на сервер и обновляются.
8. Удаление профиля
- кнопка удалить аккаунт
- всплывающее подтверждение
- удаление данных на сервере
- переход на экран авторизации
9. Добавление функций администратора

### Ссылка на макет Figma
https://www.figma.com/design/HtG1x05ufXz9Nf81wmreE7/Untitled?node-id=0-1&t=vCOA0STdJoWJF31b-1