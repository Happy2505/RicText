package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.databinding.ActivityDescripionsBinding
import com.example.myapplication.retrofit.CharacterApi
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Descripions : AppCompatActivity() {
    lateinit var binding: ActivityDescripionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescripionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("id", 0)


        val retrofit = Retrofit.Builder().baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val characterApi = retrofit.create(CharacterApi::class.java)

        GlobalScope.launch {
            val characters = characterApi.getCharacterID(id)
            var epis = characters.episode[0].split("/").last()
            if (characters.episode.size > 1) {
                val list1 = characters.episode[0].split("/")
                val list2 = characters.episode[characters.episode.size - 1].split("/")
                epis = "${list1.last()} - ${list2.last()}"
            }
            runOnUiThread {
                if (characters != null) {
                    binding.textView2.text = characters.name
                    binding.textView4.text = characters.gender
                    binding.textView6.text = characters.status
                    binding.textView8.text = characters.location.name
                    binding.textView10.text = epis
                    Picasso.get().load(characters.image).into(binding.imageView)

                } else {
                    Log.d("ayush: ", characters.toString())
                }
            }
        }
    }
}