package com.example.banknoteindentifier.ui.main.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.banknoteindentifier.ui.collection.CollectionFragment
import com.example.banknoteindentifier.ui.home.HomeFragment

class MainAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> CollectionFragment()
            else -> HomeFragment()
        }
    }
}