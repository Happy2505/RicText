package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.retrofit.CharacterApi
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NewAdapter

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = NewAdapter()

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        val retrofit = Retrofit.Builder().baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val characterApi = retrofit.create(CharacterApi::class.java)

        GlobalScope.launch {
            val characters = characterApi.getCharacter()
            runOnUiThread {
                val characters2 = characters.results
                adapter.setData(characters2)
//                characters2.let {
//                    val adapter = NewAdapter()
//                    adapter.list = characters2
//                }
            }
        }




//        val list = binding.MyList

//        val retrofit = Retrofit.Builder().baseUrl("https://rickandmortyapi.com/api/")
//            .addConverterFactory(GsonConverterFactory.create()).build()
//        val characterApi = retrofit.create(CharacterApi::class.java)
//
//        GlobalScope.launch {
//            val characters = characterApi.getCharacter()
//            runOnUiThread {
//                val characters2 = characters.results
//                characters2.let {
//                    val adapter = MyAdapter(it)
//                    list.adapter = adapter
//                }
//            }
//        }
//        binding.MyList.setOnItemClickListener { parent, view, position, id ->
//
//            val i = Intent(this, Descripions::class.java)
//            i.putExtra("id", position + 1)
//            startActivity(i)
//        }
    }
}
