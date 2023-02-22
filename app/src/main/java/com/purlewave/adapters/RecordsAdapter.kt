package com.purlewave.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.purlewave.data.models.RecordModel
import com.purlewave.databinding.ListItemsRecordsBinding
import com.purlewave.util.customPattern


class RecordsAdapter(
    private val onItemClick: ((item: RecordModel) -> Unit)? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<RecordModel>() {

        override fun areItemsTheSame(oldItem: RecordModel, newItem: RecordModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RecordModel, newItem: RecordModel): Boolean {
            return oldItem.id == newItem.id
        }

    }
    private val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return RecordsViewHolder(
            ListItemsRecordsBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClick
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RecordsViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<RecordModel>) {
        differ.submitList(list)
    }

    class RecordsViewHolder(
        private val binding: ListItemsRecordsBinding,
        private val onItemClick: ((item: RecordModel) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecordModel) = with(binding) {

            root.setOnClickListener {
                onItemClick?.invoke(item)
            }
            name.text = item.name
            description.text = item.description
            date.text = customPattern(item.updatedAt, "dd-MM-yyyy HH:mm")

        }
    }
}
