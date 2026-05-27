package com.example.banknoteindentifier.ui.onboard.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.banknoteindentifier.R
import com.example.banknoteindentifier.databinding.FragmentOnBoardFirstBinding
import com.example.banknoteindentifier.ui.onboard.OnBoarViewModel

class OnBoardFirstFragment : Fragment() {
    private var _binding: FragmentOnBoardFirstBinding? = null
    private val binding get() = _binding!!

    private val viewModel: OnBoarViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun initView(){

        binding.clBankNote.setOnClickListener {
            viewModel.selectOption(0)
            updateSelected(0)

        }
        binding.clCoin.setOnClickListener {
            viewModel.selectOption(1)
            updateSelected(1)
        }
        binding.clBoth.setOnClickListener {
            viewModel.selectOption(2)
            updateSelected(2)
        }
    }

    private fun updateSelected(position: Int){

        binding.clBankNote.setBackgroundResource(
            if(position == 0) R.drawable.bg_onboard_selected
            else R.drawable.bg_onboard
        )

        binding.clCoin.setBackgroundResource(
            if(position == 1) R.drawable.bg_onboard_selected
            else R.drawable.bg_onboard
        )

        binding.clBoth.setBackgroundResource(
            if(position == 2) R.drawable.bg_onboard_selected
            else R.drawable.bg_onboard
        )
    }
}