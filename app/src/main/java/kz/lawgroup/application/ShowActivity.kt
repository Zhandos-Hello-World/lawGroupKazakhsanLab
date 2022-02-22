package kz.lawgroup.application

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ShowActivity:AppCompatActivity() {
    lateinit var value: String
    lateinit var nameView: TextView
    lateinit var resultView: TextView
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_value_show)

        nameView = findViewById(R.id.name)
        resultView = findViewById(R.id.output)
        listView = findViewById(R.id.list)

        val intent = intent
        if (intent.hasExtra("id")) {
            value = intent.getStringExtra("id").toString()
            nameView.text = value
            resultView.text = "Итого: " + Data.map[value]?.get(0)

            val list = mutableListOf<String>()
            for (i in 2 until Data.map.size) {
                list.add(Data.map["Значение"]?.get(i) + " --- " + Data.map[value]?.get(i))
            }

            listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, list)
        }
        else {
            value = "Ошибка"
        }

    }
}