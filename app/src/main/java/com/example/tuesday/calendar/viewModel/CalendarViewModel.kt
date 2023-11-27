package com.example.tuesday.calendar.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalendarViewModel: ViewModel() {
    val scheduleNum = MutableLiveData<Int>()

    init {

    }
}