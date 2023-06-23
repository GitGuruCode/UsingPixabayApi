package com.example.andpro3api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.andpro3api.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    var i:Int=0
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getMemeData()
        binding.btnsubmit.setOnClickListener{
            getMemeData()
        }
    }

    private fun getMemeData() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://pixabay.com/api/?key=37656185-3cfca5c8f2549e998b227e9fe"
      if(i==20)i=0
// Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                // Display the first 500 characters of the response string.
               val  responsJson=JSONObject(response)
                val hits = responsJson.getJSONArray("hits")
                    val hit = hits.getJSONObject(i)


                binding.title.text=hit.getString("views")
                binding.author.text=hit.getString("tags")
               Glide.with(this).load(hit.getString("previewURL")).into(binding.imageView);
                i++
                Log.e("@@@@", "getMemeData:" + response.toString())
            },
            { it-> Toast.makeText(this,it?.localizedMessage.toString(),Toast.LENGTH_SHORT).show()})

    // Add the request to the RequestQueue.
        // b7635a807d1b22a9c57d09983855ae62
        queue.add(stringRequest)
    }
}