package com.example.patientappv06.presentation.features.patients

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patientappv06.domain.models.delete.PatientDeleteResponseModel
import com.example.patientappv06.domain.models.patients.PatientDataModel
import com.example.patientappv06.domain.usecase.delete.DeletePatientUseCase
import com.example.patientappv06.domain.usecase.patients.getPatientsSortedByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientViewModel @Inject constructor(
    private val patientsSortedByNameUseCase: getPatientsSortedByNameUseCase,
    private val patientDeleteUseCase: DeletePatientUseCase
) : ViewModel()  {

    private val _patientMutableStateFlow: MutableStateFlow<List<PatientDataModel>> = MutableStateFlow(emptyList())
    val patientStateFlow = _patientMutableStateFlow.asStateFlow()


    private val _loadingMutableStateFlow:MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadingStateFlow = _loadingMutableStateFlow.asStateFlow()

    private val _errorMutableStateFlow:MutableStateFlow<Exception?> = MutableStateFlow(null)
    val errorStateFlow = _errorMutableStateFlow.asStateFlow()



    private val _deletePatientStateFlow: MutableLiveData<PatientDeleteResponseModel?> = MutableLiveData()
    val deletePatientStateFlow : LiveData<PatientDeleteResponseModel?> = _deletePatientStateFlow

    init {
        getPatients()
    }


    fun getPatients(){
        viewModelScope.launch {
            _loadingMutableStateFlow.emit(true)
            try {
                _patientMutableStateFlow.emit(patientsSortedByNameUseCase())
            }
            catch (e:Exception)
            {
                _errorMutableStateFlow.emit(e)
            }
            _loadingMutableStateFlow.emit(false)

        }
    }
    fun deletePatient(id:String){
        viewModelScope.launch {
            _loadingMutableStateFlow.emit(true)
            try {
                _deletePatientStateFlow.postValue(patientDeleteUseCase(id))
            }
            catch (e:Exception)
            {
                _errorMutableStateFlow.emit(e)
            }
            _loadingMutableStateFlow.emit(false)

        }
    }
}