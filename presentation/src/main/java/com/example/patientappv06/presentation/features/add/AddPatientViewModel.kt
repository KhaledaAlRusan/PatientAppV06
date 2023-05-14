package com.example.patientappv06.presentation.features.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patientappv06.domain.models.add.AddPatientModel
import com.example.patientappv06.domain.models.add.AddPatientResponseModel
import com.example.patientappv06.domain.models.patients.PatientDataModel
import com.example.patientappv06.domain.usecase.add.AddPatientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPatientViewModel @Inject constructor(
    private val addPatientUseCase: AddPatientUseCase,
):ViewModel() {

    private val _addPatientsMutableStateFlow: MutableStateFlow<AddPatientResponseModel?> = MutableStateFlow(null)
    val addPatientsStateFlow = _addPatientsMutableStateFlow.asStateFlow()


    private val _loadingMutableStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadingStateFlow = _loadingMutableStateFlow.asStateFlow()

    private val _errorMutableStateFlow: MutableStateFlow<Exception?> = MutableStateFlow(null)
    val errorStateFlow = _errorMutableStateFlow.asStateFlow()


    fun addPatient(addPatientModel: AddPatientModel){
        viewModelScope.launch {
            _loadingMutableStateFlow.emit(true)
            try {
                _addPatientsMutableStateFlow.emit(addPatientUseCase(addPatientModel))
            }
            catch (e:Exception)
            {
                _errorMutableStateFlow.emit(e)
            }
            _loadingMutableStateFlow.emit(false)
        }
    }

}