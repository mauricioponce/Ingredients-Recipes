package cl.eme.recipe.applayout.listing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cl.eme.recipe.applayout.databinding.ItemRecipeBinding

class RecipeAdapter : ListAdapter<RecipeItemView, RecipeAdapter.RecipeViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemBinding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context))
        return RecipeViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class RecipeViewHolder(private val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: RecipeItemView) {
            binding.tvName.text = recipe.name
            binding.tvPrepTime.text = recipe.prepTime

        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<RecipeItemView>() {

    override fun areItemsTheSame(oldItem: RecipeItemView, newItem: RecipeItemView) =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: RecipeItemView, newItem: RecipeItemView) =
        oldItem == newItem
}