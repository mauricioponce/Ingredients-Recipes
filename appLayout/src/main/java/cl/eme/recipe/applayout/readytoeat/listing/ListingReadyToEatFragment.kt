package cl.eme.recipe.applayout.readytoeat.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import cl.eme.recipe.applayout.R
import cl.eme.recipe.applayout.databinding.FragmentReady2eatListingBinding
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListingReadyToEatFragment : Fragment() {

    private val viewModel: ReadyToEatViewModel by viewModel()

    private lateinit var binding: FragmentReady2eatListingBinding

    private lateinit var adapter: ReadyToEatAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReady2eatListingBinding.inflate(layoutInflater)

        bindAdapter()
        setListeners()

        return binding.root
    }

    private fun setListeners() {
        viewModel.readyToEat.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.floatingActionButton.setOnClickListener {
            navigateToAdd()
        }
    }

    private fun navigateToAdd() {
        NavHostFragment.findNavController(this).navigate(R.id.action_listingReadyToEatFragment_to_newReadyToEatFragment)
    }

    private fun bindAdapter() {
        adapter = ReadyToEatAdapter()
        binding.recipesList.adapter = adapter
    }
}