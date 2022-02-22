package kz.lawgroup.application

object Data {
    val map = mutableMapOf<String, MutableList<String>>()

    var getMap = mutableMapOf<String, MutableList<String>>()
    get() {
       for (i in map.keys) {
            if (i != "Значение") {
                field[i] = map[i]?: mutableListOf()
            }
       }
        return field
    }
}