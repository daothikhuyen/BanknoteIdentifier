package com.example.banknoteindentifier.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.banknoteindentifier.databinding.ActivitySplashBinding
import com.example.banknoteindentifier.ui.home.HomeActivity
import com.example.banknoteindentifier.ui.onboar.OnBoarActivity
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

        val sharedPrefKey = getSharedPreferences(AppConstant.APP_SHARED_PREFERENCES, MODE_PRIVATE)
        val isFirstTime = sharedPrefKey.getBoolean(AppConstant.IS_FIRST_TIME, true)

        android.os.Handler().postDelayed({
            if(isFirstTime){
                startActivity(Intent(this, OnBoarActivity::class.java))
                finish()
            }else{
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }

        }, splashTime.toLong())
    }
}