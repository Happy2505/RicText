package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.retrofit.Character
import com.example.myapplication.retrofit.CharacterApi
import com.example.myapplication.retrofit.RetrofitClient
import kotlinx.coroutines.*
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NewAdapter
    private lateinit var сharacterApi: CharacterApi


    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = NewAdapter()

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        сharacterApi = RetrofitClient.сharacterApi

        GlobalScope.launch {
            val characters = сharacterApi.getCharacter()
            runOnUiThread {
                val characters2 = characters.results
                adapter.setData(characters2)
            }
        }


//        binding.MyList.setOnItemClickListener { parent, view, position, id ->
//
//            val i = Intent(this, Descripions::class.java)
//            i.putExtra("id", position + 1)
//            startActivity(i)
//        }
    }
}
