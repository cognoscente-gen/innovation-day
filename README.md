public static <T extends ExposureBreakdownDto.T> void roundFundExposures(List<? extends FundBenchmarkExposureBreakdownDto> breakdownDtos) {
    for (FundBenchmarkExposureBreakdownDto breakdownDto : breakdownDtos) {
        breakdownDto.getBenchmarks().forEach(benchmark -> roundBenchmarkExposures(benchmark));
    }
}

private static <T extends ExposureData<T>> void roundBenchmarkExposures(BenchmarkExposureBreakdownDto<T> benchmark) {
    Map<String, Double> unroundedValuesMap = new HashMap<>();
    benchmark.getExposures().forEach(exposure -> {
        ExposureData<T> exposureData = (ExposureData<T>) exposure;  // Cast to ExposureData
        unroundedValuesMap.put(exposureData.getCode(), exposureData.getValue());
    });
    Map<String, BigDecimal> roundedValues = Rounding.adjustRoundedValues(unroundedValuesMap);
    benchmark.getExposures().forEach(exposure -> {
        ExposureData<T> exposureData = (ExposureData<T>) exposure;
        exposureData.setValue(roundedValues.get(exposure.getCode()).doubleValue());
    });
}
