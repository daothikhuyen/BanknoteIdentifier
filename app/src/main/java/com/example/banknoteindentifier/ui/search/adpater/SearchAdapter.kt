package com.example.banknoteindentifier.ui.search.adpater

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.banknoteindentifier.R
import com.example.banknoteindentifier.data.domain.entities.BankNote
import com.example.banknoteindentifier.databinding.ItemCollectionBinding

class SearchAdapter(private val onClickToDetail : (BankNote) -> Unit) : ListAdapter<BankNote, SearchAdapter.SearchViewHolder>(DiffCallback) {

    inner class SearchViewHolder (private val binding: ItemCollectionBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(item : BankNote){
                binding.constraintLayout.foreground = null
                Glide.with(binding.root.context)
                    .load(item.images.getOrNull(0))
                    .error(R.drawable.img_empty)
                    .placeholder(R.drawable.img_empty)
                    .into(binding.imgMoneyOne)

                Glide.with(binding.root.context)
                    .load(item.images.getOrNull(1))
                    .error(R.drawable.img_empty)
                    .placeholder(R.drawable.img_empty)
                    .into(binding.imgMoneyTwo)

                binding.tvTitle.text = item.title
                binding.tvYear.text = item.features.find { it.title == "Years" }?.value
                    ?: binding.root.context.getString(R.string.not_yet_released)

                binding.root.setOnClickListener {
                    onClickToDetail(item)
                }
            }
    }


    override fun onCreateViewHolder(
        partent: ViewGroup,
        viewType : Int
    ): SearchViewHolder {
        val binding = ItemCollectionBinding.inflate(
            android.view.LayoutInflater.from(partent.context),
            partent,
            false
        )
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<BankNote>() {
            override fun areItemsTheSame(
                oldItem: BankNote,
                newItem: BankNote
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: BankNote,
                newItem: BankNote
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}