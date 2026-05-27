package com.example.banknoteindentifier.ui.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.example.banknoteindentifier.R
import com.example.banknoteindentifier.databinding.ActivityMainBinding
import com.example.banknoteindentifier.ui.main.adapter.MainAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewPager()
        setupBottomNavigation()
    }

    fun setUpViewPager(){
        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.adapter = MainAdapter(this)
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.bottomNavView.menu[position].isChecked = true
            }
        })
    }

    private fun setupBottomNavigation(){
        binding.bottomNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.tvHome -> binding.viewPager.currentItem = 0
                R.id.tvCollection -> binding.viewPager.currentItem = 1
            }
            true
        }
    }
}