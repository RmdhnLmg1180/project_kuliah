package com.example.kuliah

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.telecom.Call
import com.kuliah.apidata.data.remote.Post


class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            finish()
        }, 3000)
    }

    @Inject
    lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Dagger injection
        DaggerAppComponent.create().inject(this)

        // Gunakan apiService untuk melakukan request ke API
        val call: Call<List<Post>> = apiService.getPosts()
        call.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                val posts: List<Post>? = response.body()

                // Inisialisasi RecyclerView dan adapter, kemudian set adapter ke RecyclerView
                val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
                recyclerView.layoutManager = LinearLayoutManager(this@HomeActivity)
                recyclerView.adapter = PostAdapter(posts.orEmpty())
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                // Handle error
            }
        })
    }
}

class PostAdapter(orEmpty: Any) {

}
