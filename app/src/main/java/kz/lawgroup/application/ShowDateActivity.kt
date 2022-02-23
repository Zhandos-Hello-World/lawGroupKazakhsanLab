package kz.lawgroup.application

import android.os.Bundle
import android.widget.GridView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ShowDateActivity : AppCompatActivity() {

    lateinit var common:TextView
    lateinit var result:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_date)

        common = findViewById(R.id.common_text)
        result = findViewById(R.id.result)
        val gridView = findViewById<GridView>(R.id.grid_view)
        val list = mutableListOf<CardModel>()
        for (i in Data.map["Значение"] ?: mutableListOf()) {
            if (i != "Итого") {
                list.add(CardModel(i))
            }
        }
        gridView.adapter = CardGVAdapter(this.baseContext, objects = list)
        gridView.setOnItemClickListener { parent, view, position, id ->
           onClick(view.findViewById<TextView>(R.id.date_text).text.toString())
        }

    }

    private fun onClick(value: String) {
        var values = ""
        for (i in Data.map[Data.choose]?.indices?: mutableListOf<Int>().indices) {
            if (Data.map["Значение"]?.get(i) == value) {
                values = Data.map[Data.choose]?.get(i) ?: ""
            }
        }

        common.text = "Итог: ${Data.map[Data.choose]?.get(0)}"
        result.text = Data.choose + ": " +  values
    }
}