package ru.sicampus.bootcamp2025.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.model.Volunteer
import ru.sicampus.bootcamp2025.viewmodel.VolunteerViewModel
import ru.sicampus.bootcamp2025.adapter.VolunteerAdapter

class MainActivity : AppCompatActivity() {

    private val volunteerViewModel: VolunteerViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var volunteerAdapter: VolunteerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        volunteerAdapter = VolunteerAdapter(emptyList())
        recyclerView.adapter = volunteerAdapter

        // Наблюдение за изменениями в списке волонтеров
        volunteerViewModel.volunteers.observe(this) { volunteers ->
            // Обновляем адаптер с новыми данными
            volunteerAdapter.updateVolunteers(volunteers)
        }

        // Загружаем волонтеров
        volunteerViewModel.loadVolunteers()
    }
}