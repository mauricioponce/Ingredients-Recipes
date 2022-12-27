package cl.eme.recipe.applayout.readytoeat.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cl.eme.recipe.applayout.databinding.FragmentReady2eatListingBinding
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
    }

    private fun bindAdapter() {
        adapter = ReadyToEatAdapter()
        binding.recipesList.adapter = adapter
    }
}