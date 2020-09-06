package ru.fevgenson.timetable.features.dictionary.presentation

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import ru.fevgenson.timetable.libraries.database.domain.entities.Lesson

class ListOfLessonsByCategoryViewModel(
    categoryItem: String,
    val categoryName: String,
    private val categoryType: Int
) : ViewModel() {

    private val oldCategoryItem = categoryItem
    val realCategoryItem = MutableLiveData<String>(categoryItem)

    private var oldEmail: String? = null
    val realEmail = MutableLiveData<String>(null)
    private var oldPhone: String? = null
    val realPhone = MutableLiveData<String>(null)
    // TODO добавить переменные для работы со временью

    val lessons: LiveData<List<Lesson>> = getLessonsFromDatabase()

    val isNoItemsTextVisible = Transformations.map(lessons) {
        it.isNullOrEmpty()
    }

    //TODO переписать с базой данных
    private fun getLessonsFromDatabase(): LiveData<List<Lesson>> {
        return liveData(Dispatchers.IO) {
            emit(listOf())
        }
    }
}
