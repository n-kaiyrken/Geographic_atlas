package com.example.geographicatlas.domain.usecases

import com.example.geographicatlas.domain.GeographicAtlasRepository
import com.example.geographicatlas.domain.entity.Country
import javax.inject.Inject

class GetCountryByCca2UseCase @Inject constructor(val repository: GeographicAtlasRepository) {

    suspend operator fun invoke(cca2: String): Country {
        return repository.getCountryByCca2(cca2)
    }
}