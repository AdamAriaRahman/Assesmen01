package org.d3if3083.assesmen01.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.d3if3083.assesmen01.database.MbtiDao
import org.d3if3083.assesmen01.model.Mbti


class MainViewModel(dao: MbtiDao) : ViewModel() {

    val data: StateFlow<List<Mbti>> = dao.getMbti().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )
}