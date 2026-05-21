package com.example.banknoteindentifier.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowCompat.enableEdgeToEdge
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banknoteindentifier.R
import com.example.banknoteindentifier.databinding.FragmentHomeBinding
import com.example.banknoteindentifier.ui.home.adapter.HomeRandomAdapter
import com.example.banknoteindentifier.ui.home.adapter.HomeRecentAdapter
import kotlinx.coroutines.flow.take
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel : HomeViewModel by viewModel()

    private val HomeRecentAdapter by lazy { HomeRecentAdapter() }
    private val HomeRandomAdapter by lazy { HomeRandomAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewRecycleViewRecent()
        initViewRecycleViewRandom()
    }

    fun initViewRecycleViewRecent(){
        binding.rvRecentBanknote.adapter = HomeRecentAdapter
        binding.rvRecentBanknote.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        viewModel.bankNotes.asLiveData().observe(viewLifecycleOwner){
            HomeRecentAdapter.submitList(it.take(10))
        }
    }

    fun initViewRecycleViewRandom(){
        binding.rvRandomBanknote.adapter = HomeRandomAdapter
        binding.rvRandomBanknote.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        viewModel.bankNotes.asLiveData().observe(viewLifecycleOwner){
            HomeRandomAdapter.submitList(it.shuffled().take(10))
        }
    }
}