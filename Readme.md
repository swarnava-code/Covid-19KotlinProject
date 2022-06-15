
[COVID-19 Dataset source](https://www.kaggle.com/datasets/imdevskp/corona-virus-report)

[Run main()](src/main/kotlin/com/sclab/covid/Main.kt)

### Output:

printDayWiseDatasetLimitLineByGivenNumberOr5(dayWiseData)
```
Date 		 Confirmed
2020-01-22 	 555
2020-01-23 	 654
2020-01-24 	 941
2020-01-25 	 1434
2020-01-26 	 2118
```

printDatesWhenDeathIsLessThan50(dayWiseData)
```
dates When Death Is More Than 50:(Sorted by death)
2020-01-22=17, 2020-01-23=18, 2020-01-24=26, 2020-01-25=42, 
```

printCountryAndLocationWhoseWHORegionIsSameLikeSelectedCountry(countryWiseData, locationData)
```
Country's Location Whose WHO Region Is Same Like India:
 {Bangladesh=(23.685, 90.3563), Bhutan=(27.5142, 90.4336), Burma=(21.9162, 95.956), India=(20.593684, 78.96288), Indonesia=(-0.7893, 113.9213), Maldives=(3.2028, 73.2207), Nepal=(28.1667, 84.25), Sri Lanka=(7.873054, 80.77179699999998), Thailand=(15.870032, 100.992541), Timor-Leste=(-8.874217, 125.727539)} 
```

printConfirmedCasesWithCountryByDateRange(fullGroupData)
```
print Confirmed Cases With Country By Date Range:
2020-01-22 = {South Korea=1, Taiwan*=1, China=548, Japan=2, Thailand=2, US=1}
2020-01-23 = {South Korea=1, Taiwan*=1, Vietnam=2, Singapore=1, China=643, Japan=2, Thailand=3, US=1}
2020-01 .....
```

printConfirmedCasesAndNewCases(fullGroupData)
```
printConfirmedCasesAndNewCases - {date=[confirmed, newCases],..}
 {2020-01-22=[548, 0], 2020-01-23=[643, 95], 2020-01-24=[920, 277], 2020-01-25=[1406, 486], 2020-01-26=[2075, 669], 2020-01-27=[2877, 802], 2020-01-28=[5509, 2632], 2020-01-29=[6087, 578], 2020-01-30=[8141, 2054], 2020-01-31=[9802, 1661], 2020-02-01=[11891, 2089], 2020-02-02=[16630, 4739], 2020-02-03=[19716, 3086], 2020-02-04=[23707, 3991], 2020-02-05=[27440, 3733], 2020-02-06=[30587, 3147], 2020-02-07=[34110, 3523], 2020-02-08=[36814, 2704], 2020-02-09=[39829, 3015], 2020-02-10=[42354, 2525], 2020-02-11=[44386, 2032], 2020-02-12=[46267, 1881], 2020-02-13=[59895, 13628], 2020-02-14=[66358, 6463], 2020-02-15=[68413, 2055], 2020-02-16=[70513, 2100], 2020-02-17=[72434, 1921], 2020-02-18=[74211, 1777], 2020-02-19=[74619, 408], 2020-02-20=[75077, 458], 2020-02-21=[75550, 473], 2020-02-22=[77001, 1451], 2020-02-23=[77022, 21], 2020-02-24=[77241, 219], 2020-02-25=[77754, 513], 2020-02-26=[78166, 412], 2020-02-27=[78600, 434], 2020-02-28=[78928, 328], 2020-02-29=[79356, 428]}
```






