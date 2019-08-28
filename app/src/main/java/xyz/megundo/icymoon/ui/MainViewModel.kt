package xyz.megundo.icymoon.ui

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import xyz.megundo.icymoon.api.ApiResponse
import xyz.megundo.icymoon.data.Information
import xyz.megundo.icymoon.repository.RepositoryProvider


class MainViewModel : ViewModel() {
    val infoLiveData = MediatorLiveData<ApiResponse>()

    fun getResponseFromServer(data: Information): MutableLiveData<ApiResponse> {
        val repository = RepositoryProvider.provideRepository()
        infoLiveData.addSource(
            repository.postData()
        ) { apiResponse -> infoLiveData.setValue(apiResponse) }


        return infoLiveData
    }
}
