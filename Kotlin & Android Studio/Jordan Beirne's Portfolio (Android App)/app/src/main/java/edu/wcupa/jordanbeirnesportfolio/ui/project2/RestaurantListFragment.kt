package edu.wcupa.jordanbeirnesportfolio.ui.project2

import android.os.Bundle
import android.view.*
import androidx.fragment.app.*
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import edu.wcupa.jordanbeirnesportfolio.databinding.FragmentTrackerBinding
import edu.wcupa.jordanbeirnesportfolio.ui.project2.ui.*
import edu.wcupa.jordanbeirnesportfolio.ui.project2.ui.AppViewModelProvider
import edu.wcupa.jordanbeirnesportfolio.ui.project2.ui.RestaurantListAdapter
import edu.wcupa.jordanbeirnesportfolio.ui.project2.ui.RestaurantListViewModel
import kotlinx.coroutines.launch
import edu.wcupa.jordanbeirnesportfolio.R


class RestaurantListFragment : Fragment() {

    private val viewModel by viewModels<RestaurantListViewModel> {
        AppViewModelProvider.Factory
    }

    private val adapter = RestaurantListAdapter(
        onEdit = {
            val bundle = Bundle()
            bundle.putInt("itemId", it.id.toInt())

            findNavController().navigate(
                R.id.entryDialogFragment,
                bundle
            )
        },
        onDelete = {
            viewModel.deleteRestaurant(it)
        }
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentTrackerBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentTrackerBinding.bind(view)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.entryDialogFragment)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.restaurantsStream.collect {
                    adapter.submitList(it)
                }
            }
        }

    }
}