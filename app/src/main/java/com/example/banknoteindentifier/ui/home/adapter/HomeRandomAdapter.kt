package com.example.banknoteindentifier.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.banknoteindentifier.R
import com.example.banknoteindentifier.data.domain.entities.BankNote
import com.example.banknoteindentifier.databinding.ItemRandomBanknoteBinding

class HomeRandomAdapter(var onClickToDetail: (BankNote) -> Unit) :
    androidx.recyclerview.widget.ListAdapter<BankNote, HomeRandomAdapter.ViewHolder>(
        RandomDiffCallback
    ) {

    inner class ViewHolder(private val binding: ItemRandomBanknoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(bankNote: BankNote) {

            binding.imgMoneyOne.load(bankNote.images.getOrNull(1)) {
                error(R.drawable.img_empty)
                placeholder(R.drawable.img_empty)
            }

            binding.imgMoneyTwo.load(bankNote.images.getOrNull(1)) {
                error(R.drawable.img_empty)
                placeholder(R.drawable.img_empty)
            }

            val firstPrice =
                bankNote.pricing.firstOrNull()?.price?.replace("$", "")?.toDoubleOrNull() ?: 0.0
            val secondPrice =
                bankNote.pricing.lastOrNull()?.price?.replace("$", "")?.toDoubleOrNull() ?: 0.0

            if (firstPrice.toInt() != 0 || secondPrice.toInt() != 0) {
                binding.tvPrice.text = "$ $firstPrice - $secondPrice"
            } else {
                binding.tvPrice.text = "$0"
            }

            binding.tvTitle.text = bankNote.title

            val textYear = bankNote.features.find { it.title == "Years" }?.value
            binding.tvYear.isVisible = textYear != null
            binding.tvYear.text = textYear

            binding.root.setOnClickListener {
                onClickToDetail(bankNote)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ItemRandomBanknoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val bankNote = getItem(position)
        holder.bind(bankNote)

    }

    companion object {
        val RandomDiffCallback = object : DiffUtil.ItemCallback<BankNote>() {

            override fun areItemsTheSame(oldItem: BankNote, newItem: BankNote): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: BankNote, newItem: BankNote
            ): Boolean {
                return oldItem == newItem
            }

        }

    }

}