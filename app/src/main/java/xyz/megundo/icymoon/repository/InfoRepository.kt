package xyz.megundo.icymoon.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.megundo.icymoon.api.ApiResponse
import xyz.megundo.icymoon.api.InfoApiService
import xyz.megundo.icymoon.data.Information

class InfoRepository(val apiService: InfoApiService) {
    fun postData(): LiveData<ApiResponse> {
        val data = MutableLiveData<ApiResponse>()
        val call = apiService.getResponse()
        call.enqueue(object : Callback<Information> {
            override fun onFailure(call: Call<Information>?, t: Throwable?) {
                data.postValue(t?.let { ApiResponse(it) })

            }
            override fun onResponse(call: Call<Information>, response: Response<Information>) {
                if (response.body() != null) {
                    data.postValue(ApiResponse(response.body()!!))
                }

            }

        })
        return data

    }

}