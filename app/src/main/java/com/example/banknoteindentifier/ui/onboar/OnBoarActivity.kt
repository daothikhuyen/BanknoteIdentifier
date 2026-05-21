package com.example.banknoteindentifier.ui.onboar

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.viewpager2.widget.ViewPager2
import com.example.banknoteindentifier.databinding.ActivityOnBoarBinding
import com.example.banknoteindentifier.ui.main.MainActivity
import com.example.banknoteindentifier.ui.onboar.adapter.OnBoarAdapter
import androidx.core.content.edit
import com.example.banknoteindentifier.utils.AppConstant

class OnBoarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoarBinding
    private val viewModel: OnBoarViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityOnBoarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        val adapter = OnBoarAdapter(this)
        binding.viewPager.adapter = adapter
        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                val progress = ((position + 1) * 100) / adapter.itemCount
                binding.progressBar.setProgress(progress, true)
            }
        })

        viewModel.isNextEnable.asLiveData().observe(this){
            binding.btnNext.isEnabled = it
        }

        binding.btnNext.setOnClickListener {
            val nextPage = binding.viewPager.currentItem + 1
            if(nextPage < adapter.itemCount){
                binding.viewPager.setCurrentItem(nextPage, true)
                viewModel.checkNextEnable(nextPage)
            }else{
                val sharedPrefKey = getSharedPreferences(AppConstant.APP_SHARED_PREFERENCES, MODE_PRIVATE)
                sharedPrefKey.edit { putBoolean(AppConstant.IS_FIRST_TIME, false) }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}