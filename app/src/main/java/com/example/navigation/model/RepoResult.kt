package com.example.navigation.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class RepoResult(
    val data: LiveData<PagedList<RepoModel>>,
    val networkErrors: LiveData<String>
)