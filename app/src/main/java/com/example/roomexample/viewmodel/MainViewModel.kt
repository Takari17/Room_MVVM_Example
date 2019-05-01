package com.example.roomexample.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.roomexample.data.Repository
import com.example.roomexample.data.local.Data
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.*

/*
Ensure you subscribe on a background thread o_-
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Repository(application)
    private val compositeDisposable = CompositeDisposable() // A much cleaner approach for disposing multiple disposables at once :D
    val dbData = MutableLiveData<List<Data>>() // Observed by activity


    fun listenToDbData() {
        compositeDisposable += repository.listenToDbData()
            .doOnError { Log.d("myLog", "Failed trying to listen to the data base D:") }
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onNext = { data -> dbData.postValue(data) },
                onError = {}
            )
    }

    fun insert(data: Data) {
        compositeDisposable += repository.insert(data)
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    fun deleteAllData() {
        compositeDisposable += repository.deleteAllData()
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear() // must dispose or else you'll have a memory leak!
    }
}