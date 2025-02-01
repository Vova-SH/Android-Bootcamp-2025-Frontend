package ru.sicampus.bootcamp2025

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import kotlinx.coroutines.*
import ru.sicampus.bootcamp2025.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        GlobalScope.launch {
            delay(2000)
            withContext(Dispatchers.Main){
                val intent = Intent(this@SplashActivity, VolunteersActivity::class.java)
                val options = ActivityOptionsCompat.makeCustomAnimation(
                    this@SplashActivity, R.anim.fade_in, R.anim.fade_out
                )
                startActivity(intent, options.toBundle())

                finish()
            }
        }
    }

}