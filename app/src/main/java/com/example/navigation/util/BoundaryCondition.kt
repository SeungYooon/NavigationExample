package com.example.navigation.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.navigation.api.GithubService
import com.example.navigation.api.searchRepos
import com.example.navigation.model.RepoModel

class BoundaryCondition(
    private val query: String,
    private val service: GithubService,
    private val cache: LocalCache
) : PagedList.BoundaryCallback<RepoModel>() {

    private var lastRequestedPage = 1

    private val _networkErrors = MutableLiveData<String>()

    // LiveData of network errors.
    val networkErrors: LiveData<String>
        get() = _networkErrors

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    override fun onZeroItemsLoaded() {
        requestAndSaveData(query)
    }

    override fun onItemAtEndLoaded(itemAtEnd: RepoModel) {
        requestAndSaveData(query)
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }

    private fun requestAndSaveData(query: String) {
        if (isRequestInProgress) return

        isRequestInProgress = true
        searchRepos(service, query, lastRequestedPage, NETWORK_PAGE_SIZE, { repos ->
            cache.insert(repos) {
                lastRequestedPage++
                isRequestInProgress = false
            }
        }, { error ->
            isRequestInProgress = false
        })
    }
}
