# BankBot
## О боте
### BankBot - это бот на платформе телеграма, который может предоставить инфомацию о курсе валют Центробанка от 01.07.1992 по сегоднешний день. Также бот может вывести курс валют в 5 самых выгодных банках в регионах РФ с население больше 500.000 человек.
## Список городов
####
1. Москва
2. Санкт-Петербург
3. Новосибирск
4. Екатеринбург
5. Казань
6. Нижний Новгород
7. Челябинск
8. Самара
9. Омск
10. Ростов
11. Уфа
12. Красноярск
13. Воронеж
14. Пермь
15. Волгоград
16. Краснодар
17. Саратов
18. Тюмень
19. Тольятти
20. Ижевск
21. Барнаул
22. Ульяновск
23. Иркутск
24. Хабаровск
25. Ярославль
26. Владивосток
27. Махачкала
28. Томск
29. Оренбург
29. Кемерово
30. Новокузнецк
31. Рязань
32. Набережные челны
33. Астрахань
34. Пенза
35. Киров
36. Липецк
## Список валют
####
1. Доллар
2. Евро
3. Фунт стерлингов
4. Юань
5. Йена
## Сайты с которых бролась информация
####
* http://www.cbr.ru/
* https://ru.myfin.by/
## Создание бота
#### 
1. Находим телеграм-бота `@BotFather`
2. Пишем ему `/newbot` для создания нового бота
3. Следуем инструкциям и получает токен бота
4. Чтобы добавить бота в групповой чат пишем боту `/setjoingroups`
5. Чтобы бот мог реагировать на все сообщения в групповом чате пишет боту `/setprivacy` и установливаем статус `DISABLED`
## Установка
1. Скачайте данный репозиторий
2. Откройте файл `Bot.java`, в методах `getBotToken()` и `getBotUsername()` напишите ваш токен и назнание бота.
3. Для запуска, введите в командную строку следующее `java src.main.java.Bot`
## Библиотеки с Maven зависимостями
* org.telegram
* junit
* org.jsoup
* com.google.code.gson
