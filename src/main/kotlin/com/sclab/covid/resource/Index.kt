package com.sclab.covid.resource

object Index {

    object ReadFullGroupedDataset {
        const val DATE = 0
        const val COUNTRY = 1
        const val CONFIRMED = 2
        const val NEW_CASES = 6
    }

    object ReadCountriesLocationDataset{
        const val LAT_INDEX = 2
        const val LONG_INDEX = 3
        const val COUNTRY_INDEX = 1
    }

    object DatasetByDayWise {
        const val DATE = 0
        const val CONFIRMED = 1
        const val DEATHS = 2
        const val RECOVERED = 3
        const val ACTIVE = 4
        const val NEW_CASES = 5
        const val NEW_DEATHS = 6
        const val NEW_RECOVERED = 7
        const val DEATHS_DIVIDED_BY_100_CASES = 8
        const val RECOVERED_DIVIDED_BY_100_CASES = 9
        const val DEATHS_DIVIDED_BY_100_RECOVERED = 10
        const val NUMBER_OF_COUNTRIES = 11
    }

    object ReadDatasetByCountryWise {
        const val COUNTRY = 0
        const val WHO_REGION = 14
        const val ONE_WEEK_PERCENTAGE_INCREASE = 13
    }

    object ConfirmedCasesAndNewCases{
        const val CONFIRMED_CASES_INDEX = 0;
        const val NEW_CASES_INDEX = 1;
    }

}