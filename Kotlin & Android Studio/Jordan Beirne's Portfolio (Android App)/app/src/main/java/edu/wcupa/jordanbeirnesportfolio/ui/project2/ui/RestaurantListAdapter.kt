package edu.wcupa.jordanbeirnesportfolio.ui.project2.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import edu.wcupa.jordanbeirnesportfolio.databinding.ListItemBinding
import edu.wcupa.jordanbeirnesportfolio.R
import edu.wcupa.jordanbeirnesportfolio.ui.project2.data.Restaurant

class RestaurantListAdapter(
    private val onEdit: (Restaurant) -> Unit,
    private val onDelete: (Restaurant) -> Unit
) : ListAdapter<Restaurant, RestaurantListAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(
        private val binding: ListItemBinding,
        private val onEdit: (Restaurant) -> Unit,
        private val onDelete: (Restaurant) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(restaurant: Restaurant) {
            binding.name.text = restaurant.name
            binding.description.text = restaurant.description
            binding.ratingBar.rating = restaurant.rating.toFloat()
            binding.cuisine.text = restaurant.cuisine

            val imageRes = when (restaurant.cuisine) {
                "Mexican" -> R.drawable.mexican
                "Chinese" -> R.drawable.chinese
                "Italian" -> R.drawable.italian
                "Sushi" -> R.drawable.sushi
                "American" -> R.drawable.american
                "Indian" -> R.drawable.indian
                "Pizza" -> R.drawable.pizza
                "Other" -> R.drawable.restaurant
                else -> R.drawable.restaurant
            }

            binding.baseImage.setImageResource(imageRes)

            binding.deleteButton.setOnClickListener {
                onDelete(restaurant)
            }

            binding.root.setOnClickListener {
                onEdit(restaurant)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onEdit,
            onDelete
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DiffCallback : DiffUtil.ItemCallback<Restaurant>() {
    override fun areItemsTheSame(old: Restaurant, new: Restaurant) = old.id == new.id
    override fun areContentsTheSame(old: Restaurant, new: Restaurant) = old == new
}