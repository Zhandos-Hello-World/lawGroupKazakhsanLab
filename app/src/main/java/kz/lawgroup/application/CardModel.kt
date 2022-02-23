package kz.lawgroup.application

class CardModel(val date:String) {

    fun getMonth(): String {
        if (!date.contains(Regex("[1-3][1-9].[0-1][0-9]"))) {
            return "Итог"
        }
        return when (date.substring(3).toInt()) {
            1 -> "Январь"
            2 -> "Февраль"
            3 -> "Март"
            4 -> "Апрель"
            5 -> "Май"
            6 -> "Июнь"
            7 -> "Июль"
            8 -> "Август"
            9 -> "Сентябрь"
            10 -> "Октябрь"
            11 -> "Ноябрь"
            else -> "Декабрь"
        }
    }
    val dateDay: String
    get() {
        return date.substring(0, date.indexOf("."))
    }

}