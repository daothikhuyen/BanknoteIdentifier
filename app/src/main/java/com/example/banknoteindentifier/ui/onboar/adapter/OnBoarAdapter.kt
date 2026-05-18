package com.example.banknoteindentifier.ui.onboar.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.banknoteindentifier.ui.onboar.fragment.OnBoarContentFragment

class OnBoarAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 3
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnBoarContentFragment.newInstance(0)
            1 -> OnBoarContentFragment.newInstance(1)
            2 -> OnBoarContentFragment.newInstance(2)
            else -> OnBoarContentFragment.newInstance(0)
        }
    }


}