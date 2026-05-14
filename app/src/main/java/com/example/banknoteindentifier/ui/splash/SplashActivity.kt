package com.example.banknoteindentifier.ui.splash

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.animation.LinearInterpolator
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.banknoteindentifier.databinding.ActivitySplashBinding
import com.example.banknoteindentifier.ui.home.HomeActivity
import com.example.banknoteindentifier.utils.AppConstant

class SplashActivity : AppCompatActivity() {

    private val splashTime = AppConstant.SPLASH_TIME

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        android.os.Handler().postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, splashTime.toLong())
    }
}