package com.android.finalfantasy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Response

class MainViewModel ( private val repository: Repository
                      = Repository(ApiClient.apiService) ) : ViewModel() {
    private val _tripleTriadLiveData = MutableLiveData< ScreenState<List<TripleTriadData>?> > ()

    val tripleTriadLiveData : LiveData< ScreenState<List<TripleTriadData>?> > get() = _tripleTriadLiveData

    init {
        getTripleTriad()
    }

    private fun getTripleTriad(){
        val client = repository.getDatas()
        _tripleTriadLiveData.postValue(ScreenState.Loading(null))

        client.enqueue(object : retrofit2.Callback<DataApiResponse> {
            override fun onResponse(
                call: Call<DataApiResponse>,
                response: Response<DataApiResponse>
            ) {
                if (response.isSuccessful) {
                    _tripleTriadLiveData.postValue(ScreenState.Success(response.body()?.result))
                } else {
                    _tripleTriadLiveData.postValue(ScreenState.Error(response.code().toString(), null))
                }
            }
            override fun onFailure(call: Call<DataApiResponse>, t: Throwable) {
                _tripleTriadLiveData.postValue(ScreenState.Error(t.message.toString(), null))
            }
        })
    }
}