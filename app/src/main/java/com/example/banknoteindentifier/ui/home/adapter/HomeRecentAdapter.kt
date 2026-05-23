package com.example.banknoteindentifier.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.banknoteindentifier.R
import com.example.banknoteindentifier.data.domain.entities.BankNote
import com.example.banknoteindentifier.databinding.ItemRecentBanknoteBinding

class HomeRecentAdapter(var onClickToDetail: (BankNote) -> Unit) :
    ListAdapter<BankNote, HomeRecentAdapter.RecentViewHolder>(HomeDiffCallback) {

    inner class RecentViewHolder(private val binding: ItemRecentBanknoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(bankNote: BankNote) {
            binding.imgMoneyOne.load(bankNote.images.getOrNull(1)) {
                error(R.drawable.img_empty)
                placeholder(R.drawable.img_empty)
            }

            binding.imgMoneyTwo.load(bankNote.images.getOrNull(1)) {
                error(R.drawable.img_empty)
                placeholder(R.drawable.img_empty)
            }
            val price = bankNote.pricing.firstOrNull()?.price?.replace("$", "")?.toDoubleOrNull()
            binding.tvPrice.text = if (price != null) "$price" else "0"
            binding.tvCountry.text = bankNote.countryRegion

            binding.root.setOnClickListener {
                onClickToDetail(bankNote)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentViewHolder {
        val binding =
            ItemRecentBanknoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RecentViewHolder,
        position: Int
    ) {
        val bankNote = getItem(position)
        holder.bind(bankNote)
    }


    companion object {
        val HomeDiffCallback = object : DiffUtil.ItemCallback<BankNote>() {

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