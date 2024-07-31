private static void adjustSumToTarget(Map<String, BigDecimal> values, double targetSum) {
  // Sort values based on absolute difference from rounded value
  List<Map.Entry<String, BigDecimal>> sortedValues = new ArrayList<>(values.entrySet());
  sortedValues.sort(Comparator.comparingDouble(e -> Math.abs(e.getValue().doubleValue() - Math.round(e.getValue().doubleValue()))));

  double currentSum = values.values().stream().mapToDouble(BigDecimal::doubleValue).sum();
  double difference = targetSum - currentSum;
  int numIterations = 0; // Optional: Limit the number of iterations to avoid infinite loops

  while (Math.abs(difference) > 0.00001 && (numIterations == 0 || // Optional: Check for convergence criteria
          Math.abs(difference) > Math.abs(values.values().stream().mapToDouble(BigDecimal::doubleValue).sum() - currentSum))) {
    for (int i = 0; i < 2; i++) { // Adjust two values in each iteration (adjust this as needed)
      if (i >= sortedValues.size()) {
        break;
      }
      Map.Entry<String, BigDecimal> entry = sortedValues.get(i);
      BigDecimal adjustmentFactor = BigDecimal.valueOf(Math.signum(difference) * 0.01);
      BigDecimal adjustedValue = entry.getValue().add(adjustmentFactor);
      values.put(entry.getKey(), adjustedValue);
    }
    currentSum = values.values().stream().mapToDouble(BigDecimal::doubleValue).sum();
    difference = targetSum - currentSum;
    numIterations++;
  }
}
