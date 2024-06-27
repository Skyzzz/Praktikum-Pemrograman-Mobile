package com.android.finalfantasy

class Repository ( private val apiService: ApiService) {
    fun getDatas() = apiService.fetchTripleTriad()
}