package com.example.handbookimedicines

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MedicineListAdapter(private val medicines: List<Medicine>, private val itemClickListener: (Medicine) -> Unit) :
    RecyclerView.Adapter<MedicineListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_medicine, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val medicine = medicines[position]
        holder.bind(medicine)
    }

    override fun getItemCount(): Int {
        return medicines.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val medicineName: TextView = itemView.findViewById(R.id.medicineName)
        private val medicineImage: ImageView = itemView.findViewById(R.id.medicineImage)

        fun bind(medicine: Medicine) {
            medicineName.text = medicine.name
            Picasso.get().load(medicine.imageUrl).into(medicineImage)
            itemView.setOnClickListener { itemClickListener.invoke(medicine) }
        }
    }
}


