package com.sclab.covid.main

import com.sclab.covid.model.CountryLocationData
import com.sclab.covid.model.CountryWiseData
import com.sclab.covid.model.DayWiseData
import com.sclab.covid.model.FullGroupData
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

interface MainLogic {

    fun getDatesOfDeathsWhenDeathIsLessThanGivenNumberSortedByDeath(death: Int, datasetDayWise: List<DayWiseData>): HashMap<LocalDate, Int>

    fun getCountriesLocationWhoseWHORegionIsSameToTheSelectedCountry(
        selectedCountry: String,
        datasetCountryWise: List<CountryWiseData>,
        datasetCountryLoaction: List<CountryLocationData>
    ): TreeMap<String, String>

    fun getCountriesGotAffectedInRangeOfSelectedDate(from: LocalDate, to: LocalDate)

    fun getConfirmedCasesWithCountryByDateRange(
        fromDate: LocalDate, toDate: LocalDate, dataset: List<FullGroupData>
    ): TreeMap<LocalDate, HashMap<String, Int>?>

    fun getConfirmedCasesAndNewCases(
        fromDate: LocalDate,
        toDate: LocalDate,
        forSelectedCountry: String = "India",
        dataset: List<FullGroupData>
    ): TreeMap<LocalDate, ArrayList<Int>>

}