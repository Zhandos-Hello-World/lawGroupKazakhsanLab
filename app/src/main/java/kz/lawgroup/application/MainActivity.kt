package kz.lawgroup.application

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class MainActivity : AppCompatActivity() {
    var id = "1JCw1eqWGQOETb8mE4f6aUOb5gKxfY0bD2_ZDD4WVxes"
    var api_key = "AIzaSyCfQaccYamykPOVzUisuFYJBoUpBVpZ52A"
    var url = "https://sheets.googleapis.com/v4/spreadsheets/$id/values/FB_%D1%8F%D0%BD%D0%B2?alt=json&key=$api_key";

    lateinit var progressBar:ProgressBar
    lateinit var listView:ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.SHOW_PROGRESS)
        listView = findViewById(R.id.list_view)

        if(Data.getMap.isEmpty()) {
            getDataFrom()
        }
        else {
            installValueToList()
        }

    }
    private fun getDataFrom() {
        val queue = Volley.newRequestQueue(this@MainActivity)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    val entryArray = response.getJSONArray("values")
                    for (i in 0 until entryArray.length()) {
                        val internalValue = entryArray.getJSONArray(i)
                        val temp = mutableListOf<String>()
                        for (j in 1 until internalValue.length()) {
                            temp.add(internalValue.getString(j))
                        }

                        Data.map[internalValue.getString(0)] = temp
                    }
                    installValueToList()
                } catch (e: JSONException) {
                    Toast.makeText(this@MainActivity, "Failed", Toast.LENGTH_LONG).show()
                }
            }) {
        }
        queue.add(jsonObjectRequest)
    }
    private fun installValueToList() {
        progressBar.isGone = true
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,
            android.R.id.text1, Data.getMap.keys.toMutableList())
        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, ShowActivity::class.java)
            intent.putExtra("id", view.findViewById<TextView>(android.R.id.text1).text.toString())
            startActivity(intent)
        }
    }
}