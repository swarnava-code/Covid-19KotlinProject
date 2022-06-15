package com.sclab.covid.util

import com.sclab.covid.resource.Index
import com.sclab.covid.model.*
import com.sclab.covid.resource.Path
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDate
import java.util.ArrayList

class CSVUtil {

    fun readDatasetByDayWise(): List<DayWiseData> {
        val datasetByDayWise: ArrayList<DayWiseData> = arrayListOf()
        val reader = Files.newBufferedReader(Paths.get(Path.CSV.DATASET_DAY_WISE_PATH))
        val csvParser = CSVParser(reader, CSVFormat.DEFAULT)
        var ignoreHeaderRecord = false;
        for (csvRecord in csvParser) {
            if (ignoreHeaderRecord) {
                val recordOfDayWiseData = DayWiseData(
                    date = LocalDate.parse(csvRecord.get(Index.DatasetByDayWise.DATE)),
                    confirmed = csvRecord.get(Index.DatasetByDayWise.CONFIRMED).toInt(),
                    deaths = csvRecord.get(Index.DatasetByDayWise.DEATHS).toInt(),
                    recovered = csvRecord.get(Index.DatasetByDayWise.RECOVERED).toInt(),
                    active = csvRecord.get(Index.DatasetByDayWise.ACTIVE).toInt(),
                    newCases = csvRecord.get(Index.DatasetByDayWise.NEW_CASES).toInt(),
                    newDeaths = csvRecord.get(Index.DatasetByDayWise.NEW_DEATHS).toInt(),
                    newRecovered = csvRecord.get(Index.DatasetByDayWise.NEW_RECOVERED).toInt(),
                    deathsDivideBy100Cases = csvRecord.get(Index.DatasetByDayWise.DEATHS_DIVIDED_BY_100_CASES).toDouble(),
                    recoveredDivideBy100Cases = csvRecord.get(Index.DatasetByDayWise.RECOVERED_DIVIDED_BY_100_CASES).toDouble(),
                    deathsDivideBy100Recovered = csvRecord.get(Index.DatasetByDayWise.DEATHS_DIVIDED_BY_100_RECOVERED).toDouble(),
                    numberOfCountries = csvRecord.get(Index.DatasetByDayWise.NUMBER_OF_COUNTRIES).toInt()
                )
                datasetByDayWise.add(recordOfDayWiseData)
            }
            ignoreHeaderRecord = true
        }
        return datasetByDayWise;
    }

    fun readDatasetByCountryWise(): List<CountryWiseData> {
        val datasetByCountryWise: ArrayList<CountryWiseData> = arrayListOf()
        val reader = Files.newBufferedReader(Paths.get(Path.CSV.DATASET_COUNTRY_WISE_PATH))
        val csvParser = CSVParser(reader, CSVFormat.DEFAULT)
        var ignoreHeaderRecord = false;
        for (csvRecord in csvParser) {
            if (ignoreHeaderRecord) {
                val recordOfCovidDayWise = CountryWiseData(
                    country = csvRecord.get(Index.ReadDatasetByCountryWise.COUNTRY),
                    oneWeekPercentageIncrease = csvRecord.get(Index.ReadDatasetByCountryWise.ONE_WEEK_PERCENTAGE_INCREASE)
                        .toDouble(),
                    whoRegion = csvRecord.get(Index.ReadDatasetByCountryWise.WHO_REGION)
                )
                datasetByCountryWise.add(recordOfCovidDayWise)
            }
            ignoreHeaderRecord = true
        }
        return datasetByCountryWise;
    }

    fun readFullGroupedDataset(): List<FullGroupData> {
        val fullGroupDataset: ArrayList<FullGroupData> = arrayListOf()
        val reader = Files.newBufferedReader(Paths.get(Path.CSV.DATASET_FULL_GROUP_PATH))
        val csvParser = CSVParser(reader, CSVFormat.DEFAULT)
        var ignoreHeaderRecord = false
        for (csvRecord in csvParser) {
            if (ignoreHeaderRecord) {
                val recordOfCovidDayWise = FullGroupData(
                    country = csvRecord.get(Index.ReadFullGroupedDataset.COUNTRY),
                    date = LocalDate.parse(csvRecord.get(Index.ReadFullGroupedDataset.DATE)),
                    confirmed = csvRecord.get(Index.ReadFullGroupedDataset.CONFIRMED).toInt(),
                    newCases = csvRecord.get(Index.ReadFullGroupedDataset.NEW_CASES).toInt()
                )
                fullGroupDataset.add(recordOfCovidDayWise)
            }
            ignoreHeaderRecord = true
        }
        return fullGroupDataset;
    }

    fun readCountriesLocationDataset(): List<CountryLocationData> {
        val locationDataset: ArrayList<CountryLocationData> = arrayListOf()
        val reader = Files.newBufferedReader(Paths.get(Path.CSV.DATASET_LOCATION_PATH))
        val csvParser = CSVParser(reader, CSVFormat.DEFAULT)
        var ignoreHeaderRecord = false;
        for (csvRecord in csvParser) {
            if (ignoreHeaderRecord) {
                val recordsOfCountriesLocation = CountryLocationData(
                    country = csvRecord.get(Index.ReadCountriesLocationDataset.COUNTRY_INDEX),
                    location = "(${csvRecord.get(Index.ReadCountriesLocationDataset.LAT_INDEX)}, ${csvRecord.get(Index.ReadCountriesLocationDataset.LONG_INDEX)})"
                )
                locationDataset.add(recordsOfCountriesLocation)
            }
            ignoreHeaderRecord = true
        }
        return locationDataset;
    }

}