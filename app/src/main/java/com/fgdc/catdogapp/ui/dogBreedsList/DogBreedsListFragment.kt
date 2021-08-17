package com.fgdc.catdogapp.ui.dogBreedsList

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.fgdc.catdogapp.R
import com.fgdc.catdogapp.databinding.BottomSheetFilterDialogLayoutBinding
import com.fgdc.catdogapp.databinding.FragmentDogBreedsListBinding
import com.fgdc.catdogapp.ui.dogBreedsList.adapter.DogBreedsAdapter
import com.fgdc.core.utils.functional.State
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DogBreedsListFragment : Fragment() {

    private var binding: FragmentDogBreedsListBinding? = null
    private val viewModel: DogBreedsListViewModel by viewModels()
    private lateinit var adapter: DogBreedsAdapter
    private lateinit var breedsAdapter: ArrayAdapter<String>
    private var lastOptionSelected = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDogBreedsListBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = DogBreedsAdapter()
        setBindings(binding!!)
        setObservers()
        setHasOptionsMenu(true)
        if (adapter.itemCount == 0) {
            viewModel.getDogBreeds()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        createFilterFunction(menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setBindings(binding: FragmentDogBreedsListBinding) {
        binding.rvBreeds.adapter = adapter
        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }

    private fun setObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.dogBreeds.collect { result ->
                        when (result) {
                            is State.ErrorNoConnection -> {
                                binding?.apply {
                                    rvBreeds.visibility = View.GONE
                                    emptyView.visibility = View.VISIBLE
                                    errorMessage.text = result.message
                                    tryAgainBtn.setOnClickListener {
                                        viewModel.getDogBreeds()
                                    }
                                }
                            }
                            is State.Success -> {
                                binding?.apply {
                                    emptyView.visibility = View.GONE
                                    rvBreeds.visibility = View.VISIBLE
                                    adapter.submitList(result.data)
                                }
                            }
                        }
                    }
                }
                launch {
                    viewModel.dogBreedsNames.collect { result ->
                        breedsAdapter = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_spinner_dropdown_item,
                            result
                        )
                    }
                }
                launch {
                    viewModel.loading.collect {
                        when (it) {
                            true -> binding?.loadingResults?.visibility = View.VISIBLE
                            false -> binding?.loadingResults?.visibility = View.INVISIBLE
                        }
                    }
                }
            }
        }
    }

    private fun createFilterFunction(menu: Menu) {
        val filterItem = menu.findItem(R.id.filter)
        filterItem.setOnMenuItemClickListener {
            showBottomSheetDialog()
            true
        }
    }

    private fun showBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val inflater = LayoutInflater.from(requireContext())
        val bottomSheetDialogBinding =
            BottomSheetFilterDialogLayoutBinding.inflate(inflater, binding?.root, false)
        bottomSheetDialogBinding.filterDialogOptions.adapter = breedsAdapter
        bottomSheetDialogBinding.filterDialogOptions.setSelection(lastOptionSelected)
        bottomSheetDialogBinding.btnFilterDialogAccept.setOnClickListener {
            viewModel.filterBreeds(bottomSheetDialogBinding.filterDialogOptions.selectedItem as String?)
            lastOptionSelected = bottomSheetDialogBinding.filterDialogOptions.selectedItemPosition
            bottomSheetDialog.dismiss()
        }
        bottomSheetDialog.setContentView(bottomSheetDialogBinding.root)
        bottomSheetDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        fun newInstance() = DogBreedsListFragment()
    }
}
