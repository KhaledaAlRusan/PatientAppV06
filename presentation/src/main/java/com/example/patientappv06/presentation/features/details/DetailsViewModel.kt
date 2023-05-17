package com.example.patientappv06.presentation.features.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patientappv06.domain.models.get.GetPatientResponseModel
import com.example.patientappv06.domain.models.patients.PatientDataModel
import com.example.patientappv06.domain.usecase.get.GetPatientByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPatientByIdUseCase: GetPatientByIdUseCase,
    state:SavedStateHandle
):ViewModel() {
    private val _detailsMutableStateFlow: MutableStateFlow<PatientDataModel?> = MutableStateFlow(null)
    val detailsStateFlow = _detailsMutableStateFlow.asStateFlow()


    private val _loadingMutableStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadingStateFlow = _loadingMutableStateFlow.asStateFlow()

    private val _errorMutableStateFlow: MutableStateFlow<Exception?> = MutableStateFlow(null)
    val errorStateFlow = _errorMutableStateFlow.asStateFlow()
    private val savedStateHandle = state

    init {
        details()
    }

    fun details() {
        val id = savedStateHandle.get<String>("id")?: "-1"
        viewModelScope.launch {
            _loadingMutableStateFlow.emit(true)
            try {
                _detailsMutableStateFlow.emit(getPatientByIdUseCase(id))
            }
            catch (e:Exception){
                _errorMutableStateFlow.emit(e)
            }
            _loadingMutableStateFlow.emit(false)
        }
    }
}