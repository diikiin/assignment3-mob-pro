package com.dikin.assignment3

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class SharedViewModel: ViewModel() {
    val inputText = MutableStateFlow("")
}