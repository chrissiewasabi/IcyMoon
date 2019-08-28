package xyz.megundo.icymoon.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import xyz.megundo.icymoon.data.Information
import xyz.megundo.icymoon.repository.RepositoryProvider


class MainViewModel : ViewModel() {
    val infoLiveData = MutableLiveData<Information>()

    fun getResponseFromServer(data: Information): MutableLiveData<Information> {
        val repository = RepositoryProvider.provideRepository()
        infoLiveData.postValue(repository.postData().value)
        return infoLiveData
    }
}
