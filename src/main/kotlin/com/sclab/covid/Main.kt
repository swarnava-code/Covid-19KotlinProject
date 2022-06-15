package com.sclab.covid

import com.sclab.covid.main.MainLogicImpl
import com.sclab.covid.model.CountryLocationData
import com.sclab.covid.model.CountryWiseData
import com.sclab.covid.model.DayWiseData
import com.sclab.covid.model.FullGroupData
import com.sclab.covid.util.CSVUtil
import java.time.LocalDate

fun main() {
    val dayWiseData: List<DayWiseData> = CSVUtil().readDatasetByDayWise()
    val countryWiseData: List<CountryWiseData> = CSVUtil().readDatasetByCountryWise()
    val fullGroupData: List<FullGroupData> = CSVUtil().readFullGroupedDataset();
    val locationData = CSVUtil().readCountriesLocationDataset()

    printDayWiseDatasetLimitLineByGivenNumberOr5(dayWiseData)
    printDatesWhenDeathIsLessThan50(dayWiseData)
    printCountryAndLocationWhoseWHORegionIsSameLikeSelectedCountry(countryWiseData, locationData)
    printConfirmedCasesWithCountryByDateRange(fullGroupData)
    printConfirmedCasesAndNewCases(fullGroupData)
}

fun printDayWiseDatasetLimitLineByGivenNumberOr5(dayWiseData: List<DayWiseData>, lineLimit: Int = 5) {
    var limit = 0
    println("Date \t\t Confirmed")
    for ((date, confirmed, _, _, _, _, _, _, _, _, _, _) in dayWiseData) {
        if (limit < lineLimit) {
            println("$date \t $confirmed")
        }
        limit++
    }
}

fun printDatesWhenDeathIsLessThan50(datasetDayWise: List<DayWiseData>) {
    val datesWhenDeathIsLessThan50 =
        MainLogicImpl().getDatesOfDeathsWhenDeathIsLessThanGivenNumberSortedByDeath(50, datasetDayWise)
    println("\ndates When Death Is More Than 50:(Sorted by death)")
    datesWhenDeathIsLessThan50.forEach { print("$it, ") }
}

fun printCountryAndLocationWhoseWHORegionIsSameLikeSelectedCountry(
    datasetCountryWise: List<CountryWiseData>,
    locationDataset: List<CountryLocationData>
) {
    val countryAndLocationWhoseWHORegionIsSameLikeSelectedCountry =
        MainLogicImpl().getCountriesLocationWhoseWHORegionIsSameToTheSelectedCountry(
            selectedCountry = "India",
            datasetCountryWise,
            locationDataset
        )
    println("\n\nCountry's Location Whose WHO Region Is Same Like India:\n $countryAndLocationWhoseWHORegionIsSameLikeSelectedCountry \n\n")
}

fun printConfirmedCasesWithCountryByDateRange(fullGroupDataset: List<FullGroupData>) {
    val confirmedCasesWithCountryByDateRange =
        MainLogicImpl().getConfirmedCasesWithCountryByDateRange(
            fromDate = LocalDate.parse("2020-01-01"), // yyyy-mm-dd
            toDate = LocalDate.parse("2020-02-01"),
            fullGroupDataset
        )
    println("print Confirmed Cases With Country By Date Range:")
    for ((date, countryWithNewCase) in confirmedCasesWithCountryByDateRange) {
        println("$date = $countryWithNewCase")
    }
}

fun printConfirmedCasesAndNewCases(fullGroupDataset: List<FullGroupData>) {
    val confirmedCasesAndNewCases =
        MainLogicImpl().getConfirmedCasesAndNewCases(
            fromDate = LocalDate.parse("2020-01-01"), // yyyy-mm-dd
            toDate = LocalDate.parse("2020-03-01"),
            forSelectedCountry = "China",
            dataset = fullGroupDataset
        )
    println("\nprintConfirmedCasesAndNewCases - {date=[confirmed, newCases], ..}\n $confirmedCasesAndNewCases")
}
