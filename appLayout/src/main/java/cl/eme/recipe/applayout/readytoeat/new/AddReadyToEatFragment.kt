package cl.eme.recipe.applayout.readytoeat.new

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import cl.eme.recipe.applayout.R
import cl.eme.recipe.applayout.databinding.FragmentReady2eatAddBinding
import cl.eme.recipe.applayout.readytoeat.listing.ReadyToEatView
import cl.eme.recipe.applayout.readytoeat.listing.ReadyToEatViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

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
                binding.textDate.text.toString(),
                binding.maxDuration.editableText.toString(),
                binding.textObservation.editText!!.text.toString(),
                binding.quantity.editableText.toString().toInt()
            )
            viewModel.newReadyToEat(newReadyToEat)
        }

        viewModel.newRecipe.observe(viewLifecycleOwner) {
            it?.let {
                navigateBack()
            }
        }
    }

    private fun navigateBack() {
        NavHostFragment.findNavController(this).popBackStack()
    }

    private fun initSpinners() {
        val maxDurationOptions = (1..6).toList()
        binding.maxDuration.setAdapter(ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, maxDurationOptions))

        val quantityOptions = (1..10).toList()
        binding.quantity.setAdapter(ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, quantityOptions))

        binding.materialButton.setOnClickListener {
            showDatePicker()
        }

        binding.textDate.text = getCurrentDateAsString()
    }

    private fun getCurrentDateAsString(): String {
        val calendarNow = getCalendar()
        return calendarNow.toStringDate()
    }

    private fun showDatePicker() {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(
                    MaterialDatePicker.todayInUtcMilliseconds()
                )
                .build()

        datePicker.addOnPositiveButtonClickListener { selectedMillis ->
            val calendarNow = getCalendar()
            calendarNow.timeInMillis = selectedMillis
            binding.textDate.text = calendarNow.toStringDate()
        }

        parentFragmentManager.let { it1 -> datePicker.show(it1, "freeze_date") }
    }

    private fun getCalendar(): Calendar =
        Calendar.getInstance(TimeZone.getDefault())
}

private fun Calendar.toStringDate(): String = SimpleDateFormat("dd-MM-yyyy").format(this.time)