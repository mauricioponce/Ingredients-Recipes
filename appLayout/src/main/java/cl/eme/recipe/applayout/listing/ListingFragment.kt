package cl.eme.recipe.applayout.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cl.eme.recipe.applayout.databinding.FragmentListingBinding
import cl.eme.recipe.core.domain.Result
import cl.eme.recipe.core.exception.NoConnection
import cl.eme.recipe.listing.presentation.ListingViewModel
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

        init()

        registerListeners()

        return binding.root
    }

    private fun registerListeners() {
        cViewModel.recipes.observe(viewLifecycleOwner){
            adapter.update(it)
        }

        cViewModel.err.observe(viewLifecycleOwner) {
            when(it) {
                is Result.Err.NetworkConnection -> showErrorConnection()
                else -> { showGeneralError() }
            }
        }
    }

    private fun bindAdapter() {
        adapter = RecipeAdapter()
        binding.recipesList.adapter = adapter
    }

    private fun init() {
        cViewModel.getRecipes()
    }

    private fun showGeneralError() {

    }

    private fun showErrorConnection() {

    }

}