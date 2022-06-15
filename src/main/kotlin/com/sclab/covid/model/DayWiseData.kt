package com.sclab.covid.model

import java.time.LocalDate

data class DayWiseData(
    val date: LocalDate,
    val confirmed: Int,
    val deaths: Int,
    val recovered: Int,
    val active: Int,
    val newCases: Int,
    val newDeaths: Int,
    val newRecovered: Int,
    val deathsDivideBy100Cases: Double,
    val recoveredDivideBy100Cases: Double,
    val deathsDivideBy100Recovered: Double,
    val numberOfCountries: Int
)