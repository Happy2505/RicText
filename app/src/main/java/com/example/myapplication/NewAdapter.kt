package com.example.myapplication

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityDescripionsBinding
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.ListItemBinding
import com.example.myapplication.retrofit.Character
import com.squareup.picasso.Picasso

class NewAdapter : RecyclerView.Adapter<NewAdapter.NewAdapterHolder>() {

    var list: List<Character> = emptyList()
//        @SuppressLint("NotifyDataSetChanged")
//        set(newValue){
//            field = newValue
//            notifyDataSetChanged()
//        }
    fun setData(list  : List<Character>){
        this.list = list as ArrayList<Character>
        notifyDataSetChanged()
    }

    class NewAdapterHolder(
        val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewAdapterHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflate,parent,false)
        return NewAdapterHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NewAdapterHolder, position: Int) {
        val character = list[position]
        with(holder.binding){
            Picasso.get().load(character.image).into(logo)
            Name.text = character.name
            species.text = character.species
            gender.text = character.gender
        }
    }
}