import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Test;

public class RoundingTest {

  @Test
  public void testRound_PositiveValues() {
    assertEquals(3.14, Rounding.round(3.14159), 0.001);
    assertEquals(2.50, Rounding.round(2.5), 0.001);
    assertEquals(10.01, Rounding.round(10.005), 0.001);
  }

  @Test
  public void testRound_NegativeValues() {
    assertEquals(-3.14, Rounding.round(-3.14159), 0.001);
    assertEquals(-2.50, Rounding.round(-2.5), 0.001);
    assertEquals(-10.01, Rounding.round(-10.005), 0.001);
  }

  @Test
  public void testRound_RandomValues() {
    double[] values = {1.2345, 5.6789, 9.0123, -4.5678, -8.9012, 0.0005, 0.9999};
    double[] expected = {1.23, 5.68, 9.01, -4.57, -8.90, 0.00, 1.00};

    for (int i = 0; i < values.length; i++) {
      assertEquals(expected[i], Rounding.round(values[i]), 0.001);
    }
  }

  @Test
  public void testRound_NonNumericInput() {
    assertThrows(NumberFormatException.class, () -> Rounding.round("hello"));
  }

  @Test
  public void testRound_PositiveInfinity() {
    assertEquals(Double.POSITIVE_INFINITY, Rounding.round(Double.POSITIVE_INFINITY), 0.0);
  }

  @Test
  public void testRound_NegativeInfinity() {
    assertEquals(Double.NEGATIVE_INFINITY, Rounding.round(Double.NEGATIVE_INFINITY), 0.0);
  }

  @Test
  public void testRound_NaN() {
    assertTrue(Double.isNaN(Rounding.round(Double.NaN)));
  }
}
