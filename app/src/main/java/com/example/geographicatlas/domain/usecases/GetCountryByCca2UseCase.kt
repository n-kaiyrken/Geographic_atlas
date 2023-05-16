package com.example.geographicatlas.domain.usecases

import com.example.geographicatlas.domain.GeographicAtlasRepository
import com.example.geographicatlas.domain.entity.Country

class GetCountryByCca2UseCase(val repository: GeographicAtlasRepository) {

    suspend operator fun invoke(cca2: String): Country {
        return repository.getCountryByCca2(cca2)
    }
}