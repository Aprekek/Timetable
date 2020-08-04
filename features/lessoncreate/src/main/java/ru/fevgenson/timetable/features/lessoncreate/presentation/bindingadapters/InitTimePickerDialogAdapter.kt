package ru.fevgenson.timetable.features.lessoncreate.presentation.bindingadapters

import android.app.TimePickerDialog
import android.widget.Button
import android.widget.TimePicker
import androidx.databinding.BindingAdapter
import ru.fevgenson.timetable.features.lessoncreate.R

//Изменить для работы с viewModel
@BindingAdapter("invokeTimePickerDialog")
fun Button.invokeTimePickerDialog(invokeTimePickerDialog: Boolean) {
    setOnClickListener {
        val listener = TimePickerDialog.OnTimeSetListener { _: TimePicker, ours: Int, min: Int ->
            val oursStr = if (ours < 10) "0$ours" else ours.toString()
            val minStr = if (min < 10) "0$min" else min.toString()
            this.text = context.getString(R.string.lesson_create_button_time_set)
                .format(oursStr, minStr)
        }
        //Исправить (Получать данные из конструктора)
        TimePickerDialog(context, listener, 0, 0, true).show()
    }
}