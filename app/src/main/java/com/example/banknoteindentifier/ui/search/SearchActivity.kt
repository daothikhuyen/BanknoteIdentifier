package com.example.banknoteindentifier.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.banknoteindentifier.R
import com.example.banknoteindentifier.data.domain.entities.BankNote
import com.example.banknoteindentifier.databinding.ActivitySearchBinding
import com.example.banknoteindentifier.ui.detail.DetailActivity
import com.example.banknoteindentifier.ui.search.adpater.SearchAdapter
import com.example.banknoteindentifier.utils.AppConstant
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private val viewModel: SearchViewModel by viewModel()
    private val searchAdapter by lazy {
        SearchAdapter(
            onClickToDetail = ::onClickToDetail
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        onSubmitSearch()
    }

    fun initView() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        viewModel.isLoadingMore.asLiveData().observe(this) {
            binding.layoutLoading.visibility = if (it) View.VISIBLE else View.GONE
        }

        // recycler view
        binding.rvSearchList.adapter = searchAdapter
        binding.rvSearchList.layoutManager = LinearLayoutManager(this)
        viewModel.searchBankNotes.asLiveData().observe(this) {
            val isEmpty = it.isEmpty()

            binding.llNoData.visibility =
                if (isEmpty && !viewModel.isLoadingMore.value) View.VISIBLE else View.GONE
            binding.rvSearchList.visibility = if (isEmpty) View.GONE else View.VISIBLE
            searchAdapter.submitList(it)
        }

        // load more
        binding.rvSearchList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount

                if (lastVisibleItemPosition == totalItemCount - 1 && totalItemCount > 0 && lastVisibleItemPosition >= totalItemCount - 2) {
                    viewModel.onLoadMore()
                }
            }
        })
    }

    fun onSubmitSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(p0: String?): Boolean {
                viewModel.clearSearch()
                viewModel.onSubmitSearch(p0.toString())
                return true
            }

            override fun onQueryTextChange(newtext: String?): Boolean {
                if (newtext.isNullOrBlank()) {
                    viewModel.clearSearch()
                    viewModel.loadData()
                }
                return true
            }
        })
    }

    fun onClickToDetail(item: BankNote) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(AppConstant.BANKNOTE_ID, item.id)
        startActivity(intent)
    }
}