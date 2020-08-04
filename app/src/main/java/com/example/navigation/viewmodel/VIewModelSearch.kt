package com.example.navigation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.navigation.model.RepoModel
import com.example.navigation.model.RepoResult
import com.example.navigation.repository.Repository

class ViewModelSearch(private val repository: Repository) : ViewModel() {

    private val queryLiveData = MutableLiveData<String>()
    private val repoResult: LiveData<RepoResult> = Transformations.map(queryLiveData) {
        repository.search(it)
    }

    val repos: LiveData<PagedList<RepoModel>> = Transformations.switchMap(repoResult) { it -> it.data }
    val networkErrors: LiveData<String> = Transformations.switchMap(repoResult) { it ->
        it.networkErrors
    }

    /**
     * Search a repository based on a query string.
     */
    fun searchRepo(queryString: String) {
        queryLiveData.postValue(queryString)
    }


    /**
     * Get the last query value.
     */
    fun lastQueryValue(): String? = queryLiveData.value
}