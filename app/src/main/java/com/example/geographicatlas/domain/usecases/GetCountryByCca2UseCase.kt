package com.example.geographicatlas.domain.usecases

import com.example.geographicatlas.domain.GeographicAtlasRepository

class GetCountryByCca2UseCase(val repository: GeographicAtlasRepository) {

    suspend operator fun invoke(cca2: String) {
        repository.getCountryByCca2(cca2)
    }
}