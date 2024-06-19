public static <T extends ExposureBreakdownDto.T> void roundFundExposures(List<? extends FundBenchmarkExposureBreakdownDto> breakdownDtos) {
    for (FundBenchmarkExposureBreakdownDto breakdownDto : breakdownDtos) {
        breakdownDto.getBenchmarks().forEach(benchmark -> roundBenchmarkExposures(benchmark));
    }
}

private static <T extends ExposureBreakdownDto.T> void roundBenchmarkExposures(BenchmarkExposureBreakdownDto<T> benchmark) {
    Map<String, Double> unroundedValuesMap = new HashMap<>();
    benchmark.getExposures().forEach(exposure -> unroundedValuesMap.put(exposure.getCode(), exposure.getValue()));
    Map<String, BigDecimal> roundedValues = Rounding.adjustRoundedValues(unroundedValuesMap);
    benchmark.getExposures().forEach(exposure -> exposure.setValue(roundedValues.get(exposure.getCode()).doubleValue()));
}
