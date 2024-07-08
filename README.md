@Test
public void testAdjustSumToTargetEdgeCases() {
  // Edge case 1: Empty map
  Map<String, BigDecimal> emptyMap = new HashMap<>();
  double targetSum = 10.0;
  assertThrows(IllegalArgumentException.class, () -> adjustSumToTarget(emptyMap, targetSum));

  // Edge case 2: Target sum less than current sum
  Map<String, BigDecimal> values = new HashMap<>();
  values.put("item1", BigDecimal.valueOf(5.0));
  values.put("item2", BigDecimal.valueOf(6.0));
  targetSum = 9.0;
  // No exception expected, but sum should not be adjusted
  adjustSumToTarget(values, targetSum);
  assertEquals(targetSum, values.values().stream().mapToDouble(BigDecimal::doubleValue).sum(), 0.00001);

  // Edge case 3: Target sum equal to current sum
  targetSum = 11.0;
  // No exception expected, but minimal adjustment should occur
  adjustSumToTarget(values, targetSum);
  assertTrue(values.values().stream().anyMatch(value -> value.compareTo(BigDecimal.valueOf(5.00001)) == 0));
}
