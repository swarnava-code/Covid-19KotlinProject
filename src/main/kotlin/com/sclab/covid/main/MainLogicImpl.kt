package com.sclab.covid.main

import com.sclab.covid.model.CountryLocationData
import com.sclab.covid.model.CountryWiseData
import com.sclab.covid.model.DayWiseData
import com.sclab.covid.model.FullGroupData
import com.sclab.covid.resource.Index
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MainLogicImpl : MainLogic {

    override fun getDatesAndDeathsInRangeOfGivenDeathCountSortedByDeath(
        fromDeathCount: Int,
        toDeathCount: Int,
        datasetDayWise: List<DayWiseData>
    ): HashMap<LocalDate, Int> {
        val dateAndDeath: Map<LocalDate, Int> = datasetDayWise.associateBy({ it.date }, { it.deaths }) // list to map
        val datesWhenDeathIsMoreThanXSortedByDeath =
            dateAndDeath.toList()
                .filter { (it.second > fromDeathCount) && (it.second < toDeathCount) }
                .sortedBy { it.second }.toMap()
        return datesWhenDeathIsMoreThanXSortedByDeath as HashMap<LocalDate, Int>
    }

    override fun getCountriesLocationWhoseWHORegionIsSameToTheSelectedCountry(
        selectedCountry: String,
        datasetCountryWise: List<CountryWiseData>,
        datasetCountryLocation: List<CountryLocationData>
    ): SortedMap<String, String?> {
        val countries: ArrayList<String> = arrayListOf()
        val whoRegionOfSelectedCountry = datasetCountryWise.first { it.country == selectedCountry }.whoRegion
        datasetCountryWise.map {
            if (it.whoRegion in whoRegionOfSelectedCountry) {
                countries.add(it.country)
            }
        }
        val allCountryAndLocation = datasetCountryLocation.associateBy({ it.country }, { it.location }).toSortedMap()
        return countries.associateBy({ it }, { allCountryAndLocation[it] }).toSortedMap()
    }

    override fun getCountriesGotAffectedInRangeOfSelectedDate(from: LocalDate, to: LocalDate) {
        TODO("Not yet implemented")
    }

    override fun getConfirmedCasesWithCountryByDateRange(
        fromDate: LocalDate,
        toDate: LocalDate,
        dataset: List<FullGroupData>
    ): TreeMap<LocalDate, HashMap<String, Int>?> {
        val confirmedCasesWithCountry: TreeMap<LocalDate, HashMap<String, Int>?> = TreeMap()

        for (entry in dataset) {
            if (entry.date.isAfter(fromDate) && entry.date.isBefore(toDate) && entry.confirmed > 0) {
                if (confirmedCasesWithCountry.containsKey(entry.date)) {
                    val hashMap: HashMap<String, Int>? = confirmedCasesWithCountry[entry.date]
                    hashMap?.put(entry.country, entry.confirmed)
                    confirmedCasesWithCountry[entry.date] = hashMap
                } else {
                    val hashMap = HashMap<String, Int>()
                    hashMap[entry.country] = entry.confirmed
                    confirmedCasesWithCountry[entry.date] = hashMap
                }
            }
        }
        return confirmedCasesWithCountry
    }

    override fun getConfirmedCasesAndNewCases(
        fromDate: LocalDate,
        toDate: LocalDate,
        forSelectedCountry: String,
        dataset: List<FullGroupData>
    ): TreeMap<LocalDate, ArrayList<Int>> {
        val confirmedCasesAndNewCasesByDate: TreeMap<LocalDate, ArrayList<Int>> = TreeMap()
        for (entry in dataset) {
            if (entry.date.isAfter(fromDate) &&
                entry.date.isBefore(toDate) &&
                entry.country == forSelectedCountry &&
                (entry.confirmed > 0 || entry.newCases > 0)
            ) {
                if (confirmedCasesAndNewCasesByDate.containsKey(entry.date)) {
                    val confirmedCasesAndNewCases: ArrayList<Int>? = confirmedCasesAndNewCasesByDate[entry.date]
                    if (confirmedCasesAndNewCases != null) {
                        val confirmedCase =
                            confirmedCasesAndNewCases[Index.ConfirmedCasesAndNewCases.CONFIRMED_CASES_INDEX] + entry.confirmed
                        val newCase =
                            confirmedCasesAndNewCases[Index.ConfirmedCasesAndNewCases.NEW_CASES_INDEX] + entry.newCases
                        confirmedCasesAndNewCases.removeAt(Index.ConfirmedCasesAndNewCases.NEW_CASES_INDEX)
                        confirmedCasesAndNewCases.removeAt(Index.ConfirmedCasesAndNewCases.CONFIRMED_CASES_INDEX)
                        confirmedCasesAndNewCases[Index.ConfirmedCasesAndNewCases.CONFIRMED_CASES_INDEX] = confirmedCase
                        confirmedCasesAndNewCases[Index.ConfirmedCasesAndNewCases.NEW_CASES_INDEX] = newCase
                    }
                    if (confirmedCasesAndNewCases != null) {
                        confirmedCasesAndNewCasesByDate[entry.date] = confirmedCasesAndNewCases
                    }
                } else {
                    val confirmedCasesAndNewCases: ArrayList<Int> = ArrayList()
                    confirmedCasesAndNewCases.add(0, entry.confirmed)
                    confirmedCasesAndNewCases.add(1, entry.newCases)
                    confirmedCasesAndNewCasesByDate[entry.date] = confirmedCasesAndNewCases
                }
            }
        }
        return confirmedCasesAndNewCasesByDate
    }

}