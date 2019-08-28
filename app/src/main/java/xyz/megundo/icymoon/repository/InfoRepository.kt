package xyz.megundo.icymoon.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.megundo.icymoon.api.InfoApiService
import xyz.megundo.icymoon.data.Information

class InfoRepository(val apiService: InfoApiService) {
    fun postData(): LiveData<Information> {
        val data = MutableLiveData<Information>()
        val call = apiService.getResponse()
        call.enqueue(object : Callback<Information> {
            override fun onFailure(call: Call<Information>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<Information>, response: Response<Information>) {
                data.postValue(response.body())
            }

        })
        return data

    }

}