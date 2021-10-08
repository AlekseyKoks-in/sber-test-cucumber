#language: ru

@all
Функционал: Ипотека

  @mortgageTest
  Сценарий: Расчет ипотеки на готовое жилье и проверка значений полей
    * Закрыть сообщение cookies
    * Выбрать раздел "Ипотека" в главном меню
    * Выбрать раздел "Ипотека на вторичное жильё" в подменю главного меню
    * Перейти к расчету ипотеки
    * Заполнить поля формы:
      | Стоимость недвижимости | 5 180 000 |
      | Первоначальный взнос   | 3 058 000 |
      | Срок кредита           | 30        |
    * Поставить галочку "Молодая семья"
    * Убрать галочку "Страхование жизни и здоровья"
    * Подождать загрузку данных
    * Проверить значение полей
      | Ежемесячный платеж | 17 227    |
      | Сумма кредита      | 2 122 000 |
      | Необходимый доход  | 22 177    |
      | Процентная ставка  | 11        |

