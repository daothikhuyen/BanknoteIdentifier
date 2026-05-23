package com.example.banknoteindentifier.ui.collection.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.banknoteindentifier.R
import com.example.banknoteindentifier.data.domain.entities.BankNote
import com.example.banknoteindentifier.databinding.ItemCollectionBinding
import com.example.banknoteindentifier.databinding.ItemPhysicalFeaturesBinding

class CollectionAdapter(private val onClickToDetail : (BankNote) -> Unit) :
    ListAdapter<BankNote, CollectionAdapter.CollectionViewHolder>(CollectionDiffUtil) {

    inner class CollectionViewHolder(private val binding: ItemCollectionBinding) :
        RecyclerView.ViewHolder(binding.root){
            fun bind(item : BankNote){
                binding.imgMoneyOne.load(item.images.firstOrNull()) {
                    error(R.drawable.img_empty)
                    placeholder(R.drawable.img_empty)
                }

                binding.imgMoneyTwo.load(item.images.lastOrNull()) {
                    error(R.drawable.img_empty)
                    placeholder(R.drawable.img_empty)
                }

                binding.tvTitle.text = item.title
                binding.tvYear.text = item.features.find { it.title == "Years" }?.value
                    ?: binding.root.context.getString(R.string.not_yet_released)

                binding.root.setOnClickListener {
                    onClickToDetail(item)
                }
            }
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CollectionViewHolder {
       val binding = ItemCollectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CollectionViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CollectionViewHolder,
        position: Int
    ) {
        val item = getItem(position)
        holder.bind(item)
    }
    companion object {
        val CollectionDiffUtil = object : DiffUtil.ItemCallback<BankNote>() {
            override fun areItemsTheSame(
                oldItem: BankNote, newItem: BankNote
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