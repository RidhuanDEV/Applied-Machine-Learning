package com.dicoding.asclepius.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.asclepius.data.local.entity.HistoryEntity
import com.dicoding.asclepius.databinding.ItemHistoryBinding
import com.dicoding.asclepius.helper.HistoryDiffCallBack
import java.text.NumberFormat

class HistoryAdapter(private val onDeleteClick: (HistoryEntity) -> Unit) : ListAdapter<HistoryEntity, HistoryAdapter.MyViewHolder>(HistoryDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MyViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: HistoryEntity) {
            binding.tvItemName.text = buildString {
                append(event.label + " ")
                append(
                    NumberFormat.getPercentInstance()
                        .format(event.score).trim()
                )
            }
            Glide.with(binding.imgItemPhoto.context)
                .load(event.imageUri)
                .into(binding.imgItemPhoto)

            binding.deleteButton.setOnClickListener {
                onDeleteClick(event)
            }
        }
    }
}
