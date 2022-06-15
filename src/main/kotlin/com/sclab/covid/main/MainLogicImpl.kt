package com.sclab.covid.main

import com.sclab.covid.model.CountryLocationData
import com.sclab.covid.model.CountryWiseData
import com.sclab.covid.model.DayWiseData
import com.sclab.covid.model.FullGroupData
import com.sclab.covid.resource.Index
import java.time.LocalDate
import java.util.TreeMap

class MainLogicImpl : MainLogic {

    override fun getDatesOfDeathsWhenDeathIsLessThanGivenNumberSortedByDeath(
        death: Int,
        datasetDayWise: List<DayWiseData>
    ): HashMap<LocalDate, Int> {
        val datesWhenDeathIsMoreThanX: HashMap<LocalDate, Int> = hashMapOf()
        for (entry in datasetDayWise) {
            if (entry.deaths < death) {
                datesWhenDeathIsMoreThanX[entry.date] = entry.deaths
            }
        }
        val datesWhenDeathIsMoreThanXSortedByDeath =
            datesWhenDeathIsMoreThanX.toList().sortedBy { (_, value) -> value }.toMap() // sortedByDescending{} for desc
        return datesWhenDeathIsMoreThanXSortedByDeath as HashMap<LocalDate, Int>
    }

    override fun getCountriesLocationWhoseWHORegionIsSameToTheSelectedCountry(
        selectedCountry: String,
        datasetCountryWise: List<CountryWiseData>,
        datasetCountryLoaction: List<CountryLocationData>
    ): TreeMap<String, String> {
        var countries: ArrayList<String> = arrayListOf()
        var WHORegionOfSelectedCountry = "";
        for (entry in datasetCountryWise) {
            if (entry.country == selectedCountry) {
                WHORegionOfSelectedCountry = entry.whoRegion
                break
            }
        }
        for (entry in datasetCountryWise) {
            if (entry.whoRegion == WHORegionOfSelectedCountry) {
                countries.add(entry.country)
            }
        }

        val allCountryAndLocation: TreeMap<String, String> = TreeMap()
        val selectedCountryAndLocation: TreeMap<String, String> = TreeMap()

        for (record in datasetCountryLoaction) {
            if (!allCountryAndLocation.containsKey(record.country)) {
                allCountryAndLocation[record.country] = record.location
            }
        }

        for (country in countries) {
            val location = allCountryAndLocation[country]
            if (location != null) {
                selectedCountryAndLocation[country] = location
            }
        }

        return selectedCountryAndLocation
    }

    override fun getCountriesGotAffectedInRangeOfSelectedDate(from: LocalDate, to: LocalDate) {
        TODO("Not yet implemented")
    }

    override fun getConfirmedCasesWithCountryByDateRange(
        fromDate: LocalDate,
        toDate: LocalDate,
        dataset: List<FullGroupData>
    ): TreeMap<LocalDate, HashMap<String, Int>?> {
        var confirmedCasesWithCountry: TreeMap<LocalDate, HashMap<String, Int>?> = TreeMap()
        for (entry in dataset) {
            if (entry.date.isAfter(fromDate) && entry.date.isBefore(toDate) && entry.confirmed > 0) {
                if (confirmedCasesWithCountry.containsKey(entry.date)) {
                    var hashMap: HashMap<String, Int>? = confirmedCasesWithCountry[entry.date]
                    hashMap?.put(entry.country, entry.confirmed)
                    confirmedCasesWithCountry[entry.date] = hashMap
                } else {
                    var hashMap = HashMap<String, Int>()
                    hashMap.put(entry.country, entry.confirmed)
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