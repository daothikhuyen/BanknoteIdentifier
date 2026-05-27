package com.example.banknoteindentifier.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.WindowCompat.enableEdgeToEdge
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banknoteindentifier.R
import com.example.banknoteindentifier.data.domain.entities.BankNote
import com.example.banknoteindentifier.databinding.FragmentHomeBinding
import com.example.banknoteindentifier.ui.detail.DetailActivity
import com.example.banknoteindentifier.ui.home.adapter.HomeRandomAdapter
import com.example.banknoteindentifier.ui.home.adapter.HomeRecentAdapter
import com.example.banknoteindentifier.ui.search.SearchActivity
import com.example.banknoteindentifier.utils.AppConstant
import kotlinx.coroutines.flow.take
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModel()

    private val HomeRecentAdapter by lazy {
        HomeRecentAdapter(
            onClickToDetail = ::onClickToDetail
        )
    }
    private val HomeRandomAdapter by lazy {
        HomeRandomAdapter(
            onClickToDetail = ::onClickToDetail
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewRecycleViewRecent()
        initViewRecycleViewRandom()
    }

    fun initView() {

        binding.searchView.apply {
            val searchEditText = findViewById<EditText>(androidx.appcompat.R.id.search_src_text)

            searchEditText.isCursorVisible = false
            searchEditText.isFocusable = false
            searchEditText.isFocusableInTouchMode = false

            val openSearch = {
                startActivity(Intent(requireContext(), SearchActivity::class.java))
            }

            setOnClickListener { openSearch() }
            searchEditText.setOnClickListener { openSearch() }
        }
    }

    fun initViewRecycleViewRecent() {
        binding.rvRecentBanknote.adapter = HomeRecentAdapter
        binding.rvRecentBanknote.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        viewModel.recentBankNotes.asLiveData().observe(viewLifecycleOwner) {
            HomeRecentAdapter.submitList(it)
        }
    }

    fun initViewRecycleViewRandom() {
        binding.rvRandomBanknote.adapter = HomeRandomAdapter
        binding.rvRandomBanknote.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        viewModel.randomBankNotes.asLiveData().observe(viewLifecycleOwner) {
            HomeRandomAdapter.submitList(it)
        }
    }

    fun onClickToDetail(item: BankNote) {
        val intent = Intent(requireContext(), DetailActivity::class.java)
        intent.putExtra(AppConstant.BANKNOTE_ID, item.id)
        startActivity(intent)
    }
}