package com.frey.msu.criminalintent

import android.app.LauncherActivity.ListItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.frey.msu.criminalintent.databinding.ListItemCrimeBinding

//class CrimeHolder (
//    val binding: ListItemCrimeBinding
//
//): RecyclerView.ViewHolder(binding.root) {
//    fun bind(crime: Crime) {
//        binding.crimeTitle.text = crime.title
//        binding.crimeDate.text = crime.date.toString()
//
//        binding.root.setOnClickListener {
//            Toast.makeText(
//                binding.root.context,
//                "${crime.title} clicked!",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//    }
//}


class CrimeListAdapter(private val crimes: List<Crime>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // Two view types -- normal for normal, serious for serious.
    private val VIEW_TYPE_NORMAL = 1
    private val VIEW_TYPE_SERIOUS = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        // Checks view type, instantiates normal or serious appropriately

        return when (viewType) {
            VIEW_TYPE_NORMAL -> {
                val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
                NormalCrimeHolder(binding)
            }
            VIEW_TYPE_SERIOUS -> {
                val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
                SeriousCrimeHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid VIEW_TYPE")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val crime = crimes[position]

        when (holder) {
            is NormalCrimeHolder -> holder.bind(crime)
            is SeriousCrimeHolder -> holder.bind(crime)
        }
    }

    override fun getItemCount() = crimes.size

    // Checks crimes, determines if police are required or not, sets variable
    override fun getItemViewType(position: Int): Int {
        val crime = crimes[position]
        return if (crime.requiresPolice) {
            VIEW_TYPE_SERIOUS
        } else {
            VIEW_TYPE_NORMAL
        }
    }

    inner class NormalCrimeHolder(private val binding: ListItemCrimeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(crime: Crime) {
            binding.crimeTitle.text = crime.title
            binding.crimeDate.text = crime.date.toString()
        }
    }

    // Special contact police button
    inner class SeriousCrimeHolder(private val binding: ListItemCrimeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(crime: Crime) {
            binding.crimeTitle.text = crime.title
            binding.crimeDate.text = crime.date.toString()
            binding.contactPoliceButton.visibility = View.VISIBLE // Show the "contact serious" button
        }
    }
}
