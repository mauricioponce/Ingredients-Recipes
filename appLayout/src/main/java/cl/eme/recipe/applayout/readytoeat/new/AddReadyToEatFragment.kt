package cl.eme.recipe.applayout.readytoeat.new

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import cl.eme.recipe.applayout.databinding.FragmentReady2eatAddBinding

class AddReadyToEatFragment: Fragment() {

    private lateinit var binding: FragmentReady2eatAddBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReady2eatAddBinding.inflate(layoutInflater)

        initSpinners()

        return binding.root
    }

    private fun initSpinners() {
        val maxDurationOptions = (1..6).toList()
        binding.maxDuration.setAdapter(ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, maxDurationOptions))

        val quantityOptions = (1..10).toList()
        binding.quantity.setAdapter(ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, quantityOptions))
    }
}