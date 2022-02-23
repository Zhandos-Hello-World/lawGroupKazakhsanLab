package kz.lawgroup.application

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*

class ShowActivity:AppCompatActivity() {
    lateinit var value: String
    lateinit var nameView: TextView
    lateinit var resultView: TextView
    lateinit var barChart: BarChart
    lateinit var lineChart:LineChart
    lateinit var dateButton: Button

    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_value_show)

        dateButton = findViewById(R.id.date_btn)
        lineChart = findViewById(R.id.line_output)
        barChart = findViewById(R.id.bar_output)
        nameView = findViewById(R.id.name)
        resultView = findViewById(R.id.output)

        value = Data.choose
        nameView.text = value
        resultView.text = "Итого: " + Data.map[value]?.get(0)

        val barEntries = mutableListOf<BarEntry>()
        val lineEntries = mutableListOf<Entry>()

        var x = 1f
        for (i in Data.map[value]?: mutableListOf()) {
            if (i != "Значение" && x != 1f) {
                var temp = ""
                for (l in i) {
                    if (l in '0'..'9') {
                        temp += l
                    }
                }
                index++
                barEntries.add(BarEntry(x, temp.toFloat()))
                lineEntries.add(Entry(x, temp.toFloat()))
            }
            x += 1f
        }


        val lineDataSet = LineDataSet(lineEntries, value)
        val barDataSet = BarDataSet(barEntries, value)



        lineChart.data = LineData(lineDataSet)
        lineChart.isDragEnabled = true
        lineChart.isScaleXEnabled = true
        lineChart.isScaleYEnabled = true
        lineChart.setTouchEnabled(true)


        barChart.data = BarData(barDataSet)
        barChart.setTouchEnabled(true)
        barChart.isDragEnabled = true
        barChart.isScaleXEnabled = true
        barChart.isScaleYEnabled = true


        dateButton.setOnClickListener {
            val intent = Intent(this, ShowDateActivity::class.java)
            startActivity(intent)
        }

    }

}