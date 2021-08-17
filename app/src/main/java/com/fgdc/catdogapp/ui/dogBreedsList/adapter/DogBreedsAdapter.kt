package com.fgdc.catdogapp.ui.dogBreedsList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fgdc.catdogapp.R
import com.fgdc.catdogapp.databinding.ItemDogBreedBinding
import com.fgdc.catdogapp.ui.dogBreedsList.DogBreedsListFragmentDirections
import com.fgdc.catdogapp.ui.dogBreedsList.models.DogBreedItemView
import com.fgdc.core.utils.extensions.squaredListLoad

class DogBreedsAdapter :
    ListAdapter<DogBreedItemView, DogBreedsAdapter.DogBreedsViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogBreedsViewHolder {
        val binding =
            ItemDogBreedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DogBreedsViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: DogBreedsAdapter.DogBreedsViewHolder,
        position: Int
    ) {
        val dogItem = getItem(position)
        if (dogItem != null) {
            holder.bind(dogItem)
        }
    }

    inner class DogBreedsViewHolder(private val itemBinding: ItemDogBreedBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(dogBreed: DogBreedItemView) {
            itemBinding.apply {
                item.animation = AnimationUtils.loadAnimation(itemView.context, R.anim.down_to_up)
                dogId.text =
                    root.context.getString(R.string.dog_id, dogBreed.id.toString())
                dogName.text = dogBreed.name
                dogImage.squaredListLoad(dogBreed.image.orEmpty(), itemBinding.root.context)
                root.setOnClickListener {
                    itemBinding.root.findNavController()
                        .navigate(DogBreedsListFragmentDirections.actionListToBreedDetail(dogBreed.id))
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<DogBreedItemView>() {

            override fun areItemsTheSame(
                oldDog: DogBreedItemView,
                newDog: DogBreedItemView
            ) = oldDog.id == newDog.id

            override fun areContentsTheSame(
                oldDog: DogBreedItemView,
                newDog: DogBreedItemView
            ) = oldDog == newDog
        }
    }
}
