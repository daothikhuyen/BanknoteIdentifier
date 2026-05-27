package com.example.banknoteindentifier.ui.collection

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banknoteindentifier.R
import com.example.banknoteindentifier.data.domain.entities.BankNote
import com.example.banknoteindentifier.databinding.FragmentCollectionBinding
import com.example.banknoteindentifier.ui.collection.adapter.CollectionAdapter
import com.example.banknoteindentifier.ui.detail.DetailActivity
import com.example.banknoteindentifier.utils.AppConstant
import org.koin.androidx.viewmodel.ext.android.viewModel

class CollectionFragment : Fragment() {
    private var _binding: FragmentCollectionBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CollectionViewModel by viewModel()
    private val collectionAdapter by lazy {
        CollectionAdapter(
            onClickToDetail = ::onClickToDetail
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        onSubmitSearch()
    }

    fun initView(){
        binding.rvCollection.adapter = collectionAdapter
        binding.rvCollection.layoutManager = LinearLayoutManager(context)
        viewModel.collections.asLiveData().observe(viewLifecycleOwner) {
            collectionAdapter.submitList(it)
        }

        binding.searchView.apply {
            val closeButton = findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)
            closeButton.setImageResource(R.drawable.ic_close_35dp)
            closeButton.elevation = 12f
            val params =closeButton.layoutParams as ViewGroup.MarginLayoutParams
            params.topMargin = (2 * resources.displayMetrics.density).toInt()
            closeButton.layoutParams = params
        }
    }

    fun onSubmitSearch(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(keyword: String?): Boolean {
                viewModel.onSubmitSearch(keyword.toString())
                return true
            }

            override fun onQueryTextChange(newtext: String?): Boolean {
                if (newtext.isNullOrBlank()) {
                    viewModel.getCollections()
                }
                return true
            }
        })
    }

    fun onClickToDetail(item: BankNote) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(AppConstant.BANKNOTE_ID, item.id)
        startActivity(intent)
    }

}