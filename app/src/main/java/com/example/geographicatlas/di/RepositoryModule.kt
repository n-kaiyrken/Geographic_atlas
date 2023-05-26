package com.example.geographicatlas.di

import com.example.geographicatlas.data.GeographicAtlasRepositoryImpl
import com.example.geographicatlas.domain.GeographicAtlasRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindRepository(
        repository: GeographicAtlasRepositoryImpl
    ): GeographicAtlasRepository

}