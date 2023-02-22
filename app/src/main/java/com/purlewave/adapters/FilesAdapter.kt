package com.purlewave.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.purlewave.data.models.FileModel
import com.purlewave.data.models.RecordModel
import com.purlewave.databinding.ListFilesRecordsBinding
import com.purlewave.databinding.ListItemsRecordsBinding
import com.purlewave.util.customPattern


class FilesAdapter(
    private val onItemClick: ((item: FileModel) -> Unit)? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<FileModel>() {

        override fun areItemsTheSame(oldItem: FileModel, newItem: FileModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FileModel, newItem: FileModel): Boolean {
            return oldItem.id == newItem.id
        }

    }
    private val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return RecordsViewHolder(
            ListFilesRecordsBinding.inflate(LayoutInflater.from(parent.context), parent, false),
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

    fun submitList(list: List<FileModel>) {
        differ.submitList(list)
    }

    class RecordsViewHolder(
        private val binding: ListFilesRecordsBinding,
        private val onItemClick: ((item: FileModel) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FileModel) = with(binding) {

            root.setOnClickListener {
                onItemClick?.invoke(item)
            }
            name.text = item.name
            description.text = item.location
            date.text = item.uploadedAt?.let { customPattern(it, "dd-MM-yyyy HH:mm") }

        }
    }
}
