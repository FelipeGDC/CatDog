package com.fgdc.catdogapp.ui.dogBreedDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.fgdc.catdogapp.databinding.FragmentDogBreedDetailBinding
import com.fgdc.catdogapp.ui.dogBreedDetail.models.DogBreedDetailView
import com.fgdc.catdogapp.ui.dogBreedsList.DogBreedsListFragment
import com.fgdc.core.utils.functional.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

@AndroidEntryPoint
class DogBreedDetailFragment : Fragment() {

    private var binding: FragmentDogBreedDetailBinding? = null
    private val viewModel: DogBreedDetailViewModel by viewModels()
    private val args: DogBreedDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDogBreedDetailBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        setHasOptionsMenu(true)
        viewModel.getDogBreedImages(args.breedId)
        setBindings(binding!!)
    }

    private fun setBindings(binding: FragmentDogBreedDetailBinding) {
        binding.run {
            val navController = findNavController()
            toolbar.setupWithNavController(navController)
            toolbarLayout.contentScrim
        }
    }

    private fun setObservers() {
        binding?.run {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    launch {
                        viewModel.dogBreedDetail.collect { result ->
                            when (result) {
                                is State.Success -> setBreedDetail(result.data)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun FragmentDogBreedDetailBinding.setBreedDetail(dogBreedDetailView: DogBreedDetailView?) {
        dogBreedDetailView?.let { dog ->
            name.text = dog.name
            breedGroup.text = dog.breedGroup
            bredForValue.text = dog.bredFor
            temperamentValue.text = dog.temperament
            lifeSpanValue.text = dog.lifeSpan
            originValue.text = dog.origin

            carousel.registerLifecycle(viewLifecycleOwner)
            val list = mutableListOf<CarouselItem>()
            list.addAll(dog.images!!.map { CarouselItem(imageUrl = it) })
            carousel.setData(list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        fun newInstance() = DogBreedsListFragment()
    }
}
