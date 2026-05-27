package com.example.banknoteindentifier.ui.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.banknoteindentifier.R
import com.example.banknoteindentifier.data.domain.entities.BankNote
import com.example.banknoteindentifier.databinding.ActivityDetailBinding
import com.example.banknoteindentifier.ui.detail.adapter.DetailAdapter
import com.example.banknoteindentifier.utils.AppConstant
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var itemBankNote: BankNote? = null
    private val featureAdapter by lazy { DetailAdapter() }
    private val viewModel : DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        loadBanknote()

        viewModel.bankNoteDetail.asLiveData().observe(this) { bankNote ->
            bankNote?.let {
                itemBankNote = it
                updateUI(it)
            }
        }

        viewModel.isCollection.asLiveData().observe(this) {
            statusButtonAdd(it)
        }

        binding.btnAddCollection.setOnClickListener {
            viewModel.toggleCollection(itemBankNote ?: return@setOnClickListener)
        }

    }

    private fun statusButtonAdd(isCollection : Boolean){
        if(isCollection){
            binding.btnAddCollection.text = getString(R.string.remove_collection)
            binding.btnAddCollection.setBackgroundResource(R.drawable.bg_button_remove)
            binding.btnAddCollection.setTextColor(getColor(R.color.color_error))
        }else{
            binding.btnAddCollection.text = getString(R.string.add_to_collection)
            binding.btnAddCollection.setBackgroundResource(R.drawable.bg_button_add)
            binding.btnAddCollection.setTextColor(getColor(R.color.color_primary_dark))
        }
    }

    private fun loadBanknote() {
        val id =
            intent.getStringExtra(AppConstant.BANKNOTE_ID)
                ?: return

        viewModel.getBankNoteById(id)
        viewModel.getCollectionById(id)
    }

    private fun updateUI(bankNote: BankNote) {
        Glide.with(binding.root.context)
            .load(bankNote.images.getOrNull(0))
            .error(R.drawable.img_empty)
            .placeholder(R.drawable.img_empty)
            .into(binding.imgMoneyOne)

        Glide.with(binding.root.context)
            .load(bankNote.images.getOrNull(1))
            .error(R.drawable.img_empty)
            .placeholder(R.drawable.img_empty)
            .into(binding.imgMoneyTwo)

        binding.tvTitle.text = bankNote.title

        val firstPrice = bankNote.pricing.firstOrNull()?.price
        val secondPrice = bankNote.pricing.lastOrNull()?.price
        binding.tvPrice.text =
            if (!firstPrice.isNullOrEmpty() && !secondPrice.isNullOrEmpty()) {
                "$firstPrice - $secondPrice"
            } else {
                getString(R.string.not_yet_released)
            }

        binding.rvPhysicalFeatures.apply {
            adapter = featureAdapter
            layoutManager = LinearLayoutManager(this@DetailActivity)
        }
        featureAdapter.submitList(bankNote.features)
    }
}