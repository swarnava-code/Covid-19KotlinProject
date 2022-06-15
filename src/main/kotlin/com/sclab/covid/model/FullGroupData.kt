package com.sclab.covid.model

import java.time.LocalDate

data class FullGroupData(
    val date: LocalDate,
    val country: String,
    val confirmed: Int,
    val newCases: Int
)
/*
Date	Country/Region	Confirmed	Deaths	Recovered	Active	New cases	New deaths	New recovered	WHO Region
 */