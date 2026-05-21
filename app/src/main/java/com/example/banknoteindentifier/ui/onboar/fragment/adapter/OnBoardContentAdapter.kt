package com.example.banknoteindentifier.ui.onboar.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.banknoteindentifier.R
import com.example.banknoteindentifier.data.domain.entities.BankNote
import com.example.banknoteindentifier.data.domain.entities.OnboardingOption
import com.example.banknoteindentifier.databinding.ItemOnboarCategoryBinding

class OnBoardContentAdapter(var onClickOption : (OnboardingOption) -> Unit) :
    ListAdapter<OnboardingOption, OnBoardContentAdapter.BoarViewHolder>(MenuDiffCallback) {
    inner class BoarViewHolder(private val binding: ItemOnboarCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: OnboardingOption) {
            binding.imageView.setImageResource(item.imgRes)
            binding.tvTitle.text = item.title

            binding.tvReview.isVisible = !item.description.isEmpty()
            binding.tvReview.text = item.description
            binding.cvOption.setOnClickListener {
                onClickOption(item)
            }
        }

        fun setStateItem(item: OnboardingOption) {
            val color = if (item.isSelected) {
                ContextCompat.getColor(binding.root.context, R.color.color_primary)
            } else {
                ContextCompat.getColor(binding.root.context, android.R.color.darker_gray)
            }
            binding.cvOption.strokeColor = color
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoarViewHolder {
        val binding =
            ItemOnboarCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BoarViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BoarViewHolder,
        position: Int
    ) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onBindViewHolder(holder: BoarViewHolder, position: Int, payloads: List<Any?>) {
        super.onBindViewHolder(holder, position, payloads)
        if (payloads.isEmpty()) {
            val item = getItem(position)
            holder.bind(item)
        } else {
            val payload = payloads[0] as? Boolean ?: false
            if (payload) {
                val item = getItem(position)
                holder.setStateItem(item)
            }
        }
    }

    companion object {
        val MenuDiffCallback = object : DiffUtil.ItemCallback<OnboardingOption>() {

            override fun areItemsTheSame(
                oldItem: OnboardingOption,
                newItem: OnboardingOption
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: OnboardingOption,
                newItem: OnboardingOption
            ): Boolean {
                return oldItem == newItem
            }

            override fun getChangePayload(
                oldItem: OnboardingOption,
                newItem: OnboardingOption
            ): Any? {
                return oldItem.isSelected != newItem.isSelected
            }
        }
    }

}