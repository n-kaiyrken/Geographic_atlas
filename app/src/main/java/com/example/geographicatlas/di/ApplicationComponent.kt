package com.example.geographicatlas.di

import com.example.geographicatlas.presentation.CountriesListFragment
import com.example.geographicatlas.presentation.CountryDetailsFragment
import dagger.Component

@ApplicationScope
@Component(modules = [NetworkModule::class, RepositoryModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(fragment: CountryDetailsFragment)

    fun inject(fragment: CountriesListFragment)

    @Component.Factory
    interface Factory {

        fun create(): ApplicationComponent
    }
}