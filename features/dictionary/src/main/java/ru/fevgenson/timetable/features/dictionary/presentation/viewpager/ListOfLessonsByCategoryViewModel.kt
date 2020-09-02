package ru.fevgenson.timetable.features.dictionary.presentation.viewpager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import ru.fevgenson.timetable.libraries.database.domain.entities.Lesson

class ListOfLessonsByCategoryViewModel(
    categoryItem: String,
    private val categoryType: Int
) : ViewModel() {

    private val oldCategoryItem = categoryItem
    val realCategoryItem = MutableLiveData<String>(categoryItem)

    private var oldEmail: String? = null
    val realEmail = MutableLiveData<String>(null)
    private var oldPhone: String? = null
    val realPhone = MutableLiveData<String>(null)

    val lessons: LiveData<List<Lesson>> = getLessonsFromDatabase()

    //TODO переписать с базой данных
    private fun getLessonsFromDatabase(): LiveData<List<Lesson>> {
        return liveData(Dispatchers.IO) {
            emit(listOf())
        }
    }
}
