package edu.wcupa.jordanbeirnesportfolio.ui.project2

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import edu.wcupa.jordanbeirnesportfolio.ui.project2.ui.RestaurantEntryViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import edu.wcupa.jordanbeirnesportfolio.databinding.FragmentEntryDialogBinding
import edu.wcupa.jordanbeirnesportfolio.ui.project2.ui.AppViewModelProvider

class EntryDialogFragment : BottomSheetDialogFragment() {

    private val viewModel by viewModels<RestaurantEntryViewModel> {
        AppViewModelProvider.Factory
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentEntryDialogBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentEntryDialogBinding.bind(view)
        view.setBackgroundColor(android.graphics.Color.parseColor("#E6D6FF"))
        view.setPadding(24, 24, 24, 24)
        val itemId = arguments?.getLong("itemId") ?: 0

        val cuisines = listOf(
            "American",
            "Mexican",
            "Chinese",
            "Italian",
            "Japanese",
            "Indian",
            "Pizza",
            "Other"
        )

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            cuisines
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.cuisineSpinner.adapter = adapter

        binding.saveButton.setOnClickListener {
            val selectedCuisine = binding.cuisineSpinner.selectedItem.toString()

            viewModel.saveRestaurant(
                itemId,
                binding.name.text.toString(),
                binding.description.text.toString(),
                selectedCuisine,
                binding.ratingBar.rating.toInt()
            )
            findNavController().popBackStack()
        }



    }
}