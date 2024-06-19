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

private static void roundBenchmarkExposures(BenchmarkExposureBreakdownDto<T> benchmark) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
  Map<String, Double> unroundedValuesMap = new HashMap<>();
  for (T exposure : benchmark.getExposures()) {
    // Get methods using reflection (assuming getCode and getValue exist)
    Method getCodeMethod = exposure.getClass().getMethod("getCode");
    Method getValueMethod = exposure.getClass().getMethod("getValue");
    
    // Cast return values to avoid unchecked warnings
    String code = (String) getCodeMethod.invoke(exposure);
    Double value = (Double) getValueMethod.invoke(exposure);
    unroundedValuesMap.put(code, value);
  }
  
  // ... rest of the code for rounding logic using unroundedValuesMap
}



