package cl.eme.recipe.applayout.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cl.eme.recipe.applayout.databinding.FragmentListingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListingFragment : Fragment() {

    private val cViewModel: ListingViewModel by viewModel()

    private lateinit var binding: FragmentListingBinding

    private lateinit var adapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListingBinding.inflate(layoutInflater)

        bindAdapter()
        setListeners()

        cViewModel.getRecipes()

        return binding.root
    }

    private fun setListeners() {
        cViewModel.recipes.observe(viewLifecycleOwner) {
            adapter.update(it)
        }
    }

    private fun bindAdapter() {
        adapter = RecipeAdapter()
        binding.recipesList.adapter = adapter
    }
}