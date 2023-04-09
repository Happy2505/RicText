package com.example.myapplication

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.retrofit.Character
import com.squareup.picasso.Picasso

class MyAdapter(private val characters: List<Character>) : BaseAdapter() {

    override fun getCount(): Int = characters.size

    override fun getItem(position: Int): Any = characters[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {


        var inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.list_item, parent, false)


        val character = characters[position]

        val imageView: ImageView = view.findViewById(R.id.logo)
        val name: TextView = view.findViewById(R.id.Name)
        val species: TextView = view.findViewById(R.id.species)
        val gender: TextView = view.findViewById(R.id.gender)

        Picasso.get().load(character.image).into(imageView)
        name.text = character.name
        species.text = character.species
        gender.text = character.gender


        return view
    }
}

