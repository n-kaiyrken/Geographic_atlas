package com.example.geographicatlas.data.api

import com.example.geographicatlas.data.api.dto.CountryDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("v3.1/all")
    suspend fun getAllCountries(): List<CountryDetailsDto>

    @GET("v3.1/alpha/{cca2}")
    suspend fun getCountryByCca2(
        @Path("cca2") cca2: String
    ): List<CountryDetailsDto>

}