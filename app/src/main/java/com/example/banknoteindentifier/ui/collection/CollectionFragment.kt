package com.example.banknoteindentifier.ui.collection

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        binding.rvCollection.adapter = collectionAdapter
        binding.rvCollection.layoutManager = LinearLayoutManager(context)
        viewModel.collections.asLiveData().observe(viewLifecycleOwner) {
            collectionAdapter.submitList(it)
        }
    }

    fun onClickToDetail(item: BankNote) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(AppConstant.BANKNOTE_DETAIL, item)
        startActivity(intent)
    }

}