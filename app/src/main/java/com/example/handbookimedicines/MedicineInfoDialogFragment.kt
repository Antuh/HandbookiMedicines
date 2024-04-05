package com.example.handbookimedicines

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.squareup.picasso.Picasso

class MedicineInfoDialogFragment(private val medicineName: String, private val medicineDescription: String, private val imageUrl: String) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val dialogView = inflater.inflate(R.layout.activity_medicine_info_dialog_fragment, null)

            // Находим и устанавливаем значения для текстовых полей в диалоговом окне
            val textViewMedicineName: TextView = dialogView.findViewById(R.id.textViewMedicineName)
            val textViewMedicineDescription: TextView = dialogView.findViewById(R.id.textViewMedicineDescription)
            textViewMedicineName.text = medicineName
            textViewMedicineDescription.text = medicineDescription

            // Загружаем изображение с помощью Picasso
            val imageViewMedicine: ImageView = dialogView.findViewById(R.id.imageViewMedicine)
            Picasso.get().load(imageUrl).into(imageViewMedicine)

            builder.setView(dialogView)
                .setTitle("Medicine Info")
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}


