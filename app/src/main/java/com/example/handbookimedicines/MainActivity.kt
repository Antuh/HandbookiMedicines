package com.example.handbookimedicines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var medicineAdapter: MedicineListAdapter
    private lateinit var medicineList: List<Medicine>
    private lateinit var medicineRecyclerView: RecyclerView // Объявление переменной

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        medicineRecyclerView = findViewById(R.id.medicineRecyclerView) // Инициализация переменной

        // Создаем базу данных
        val dbHelper = MedicineDatabaseHelper(this)

        // Вставляем некоторые тестовые данные
        dbHelper.insertMedicine("Магний B6", "Для поддержания сосудов сердца", "https://vseglisty.ru/wp-content/uploads/e/5/b/e5bdcbe4f2095cd832b8de7522cd971c.jpg")
        dbHelper.insertMedicine("Антигрипин", "От простуды", "https://vseglisty.ru/wp-content/uploads/e/5/b/e5bdcbe4f2095cd832b8de7522cd971c.jpg")
        dbHelper.insertMedicine("Кетопрофен", "От ушибов и садин", "https://vseglisty.ru/wp-content/uploads/e/5/b/e5bdcbe4f2095cd832b8de7522cd971c.jpg")

        // Получаем список препаратов из базы данных
        //medicineList = dbHelper.getAllMedicines()
        // Получаем список препаратов из базы данных и сортируем его по наименованию
        medicineList = dbHelper.getAllMedicines().sortedBy { it.name }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        // Создаем адаптер для RecyclerView
        medicineAdapter = MedicineListAdapter(medicineList) { medicine ->
            // Отображаем информацию о препарате в диалоговом окне
            val dialogFragment = MedicineInfoDialogFragment(medicine.name, medicine.description, medicine.imageUrl)
            dialogFragment.show(supportFragmentManager, "MedicineInfoDialog")
        }

        // Устанавливаем менеджер макета и адаптер для RecyclerView
        medicineRecyclerView.layoutManager = LinearLayoutManager(this)
        medicineRecyclerView.adapter = medicineAdapter
    }
}
