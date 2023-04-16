package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.net.wifi.p2p.WifiP2pManager.ActionListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityDescripionsBinding
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.ListItemBinding
import com.example.myapplication.retrofit.Character
import com.squareup.picasso.Picasso

interface CharacterListener {
    fun onCharacterDetail(character: Character)
}

class NewAdapter : RecyclerView.Adapter<NewAdapter.NewAdapterHolder>(), View.OnClickListener {

    var list: List<Character> = emptyList()

    //        @SuppressLint("NotifyDataSetChanged")
//        set(newValue){
//            field = newValue
//            notifyDataSetChanged()
//        }
    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Character>) {
        this.list = list as ArrayList<Character>
        notifyDataSetChanged()
    }

    class NewAdapterHolder(
        val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewAdapterHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflate, parent, false)
        binding.root.setOnClickListener(this)
        return NewAdapterHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NewAdapterHolder, position: Int) {
        val character = list[position]
        with(holder.binding) {
            holder.itemView.tag = character
            Picasso.get().load(character.image).into(logo)
            Name.text = character.name
            species.text = character.species
            gender.text = character.gender
        }
    }

    override fun onClick(v: View) {
        val cha: Character = v.tag as Character
        val i = Intent(v.context, Descripions::class.java)
        i.putExtra("id", cha.id)
        v.context.startActivity(i)
    }
}