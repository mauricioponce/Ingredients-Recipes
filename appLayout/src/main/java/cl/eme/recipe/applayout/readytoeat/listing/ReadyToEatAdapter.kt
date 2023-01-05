package cl.eme.recipe.applayout.readytoeat.listing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cl.eme.recipe.applayout.databinding.ItemReady2eatBinding


class ReadyToEatAdapter : ListAdapter<ReadyToEatView, ReadyToEatAdapter.ReadyToEatViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReadyToEatViewHolder {
        val itemBinding = ItemReady2eatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReadyToEatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ReadyToEatViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class ReadyToEatViewHolder(private val binding: ItemReady2eatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(readyToEat: ReadyToEatView) {
            binding.tvName.text = readyToEat.name
            binding.tvSince.text = readyToEat.freezeDate
            binding.tvMaxDuration.text = readyToEat.maxDurationInDays
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<ReadyToEatView>() {
        override fun areItemsTheSame(oldItem: ReadyToEatView, newItem: ReadyToEatView) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: ReadyToEatView, newItem: ReadyToEatView) =
            oldItem == newItem
    }
}