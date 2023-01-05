package cl.eme.recipe.applayout.readytoeat.new

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import cl.eme.recipe.applayout.databinding.FragmentReady2eatAddBinding
import cl.eme.recipe.applayout.readytoeat.listing.ReadyToEatView
import cl.eme.recipe.applayout.readytoeat.listing.ReadyToEatViewModel
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddReadyToEatFragment: Fragment() {

    private val viewModel: ReadyToEatViewModel by viewModel()

    private lateinit var binding: FragmentReady2eatAddBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReady2eatAddBinding.inflate(layoutInflater)

        initSpinners()

        setListeners()

        return binding.root
    }

    private fun setListeners() {
        binding.btAddReadyToEat.setOnClickListener {
            val newReadyToEat = ReadyToEatView(
                binding.textName.editText!!.text.toString(),
                "",
                binding.maxDuration.editableText.toString(),
                binding.textObservation.editText!!.text.toString(),
                binding.quantity.editableText.toString().toInt()

            )
            viewModel.newReadyToEat(newReadyToEat)
        }
    }

    private fun initSpinners() {
        val maxDurationOptions = (1..6).toList()
        binding.maxDuration.setAdapter(ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, maxDurationOptions))

        val quantityOptions = (1..10).toList()
        binding.quantity.setAdapter(ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, quantityOptions))
    }
}