package com.example.banknoteindentifier.ui.onboard.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.banknoteindentifier.ui.onboard.fragment.OnBoardFirstFragment
import com.example.banknoteindentifier.ui.onboard.fragment.OnBoardSecondFragment
import com.example.banknoteindentifier.ui.onboard.fragment.OnBoardThirdFragment

class OnBoarAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 3
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnBoardFirstFragment()
            1 -> OnBoardSecondFragment()
            2 -> OnBoardThirdFragment()
            else -> OnBoardFirstFragment()
        }
    }


}