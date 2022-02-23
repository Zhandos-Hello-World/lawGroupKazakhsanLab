package kz.lawgroup.application

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CardGVAdapter(context: Context, resource: Int = 0, objects: MutableList<CardModel>) :
    ArrayAdapter<CardModel>(context, resource, objects) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).
        inflate(R.layout.view_card, parent,false)


        val cardModel = getItem(position)
        val btnDate = view.findViewById<TextView>(R.id.date_text)
        btnDate.text = cardModel?.date

        return view
    }
}