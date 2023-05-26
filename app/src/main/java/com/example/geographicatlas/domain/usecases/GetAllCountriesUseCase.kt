package com.example.geographicatlas.domain.usecases

import com.example.geographicatlas.domain.GeographicAtlasRepository
import com.example.geographicatlas.domain.entity.Country
import javax.inject.Inject

class GetAllCountriesUseCase @Inject constructor(val repository: GeographicAtlasRepository) {

    suspend operator fun invoke(): List<Country> {
        return repository.getAllCountries()
    }
}