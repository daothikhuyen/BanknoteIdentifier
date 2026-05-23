package com.example.banknoteindentifier.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.banknoteindentifier.data.domain.entities.Features
import com.example.banknoteindentifier.databinding.ItemPhysicalFeaturesBinding

class DetailAdapter : ListAdapter<Features, DetailAdapter.DetailViewHolder>(DetailDiffCallback) {

    inner class DetailViewHolder(private val binding: ItemPhysicalFeaturesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(feature: Features) {
            binding.tvType.text = feature.title
            binding.tvValue.text = feature.value
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailViewHolder {
        val binding =
            ItemPhysicalFeaturesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: DetailViewHolder,
        position: Int
    ) {
        val feature = getItem(position)
        holder.bind(feature)
        if (position == itemCount - 1) {
            holder.itemView.setPadding(0, 0, 0, 0)
        }
    }

    companion object {
        val DetailDiffCallback = object : DiffUtil.ItemCallback<Features>() {

            override fun areItemsTheSame(oldItem: Features, newItem: Features): Boolean {
                return oldItem.value == newItem.value
            }

            override fun areContentsTheSame(
                oldItem: Features, newItem: Features
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}