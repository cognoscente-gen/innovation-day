import org.junit.Test;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Verifications;

@RunWith(JUnit4.class)
public class RoundingUtilTest {

    @Injectable
    private List<CurrencyExposureBreakdownDto> exposureBreakdownDtos; // Mock the exposureBreakdownDtos list

    @Test
    public void testRoundingCalledWhenHeaderIsTrue() {
        List<String> roundingHeader = new ArrayList<>();
        roundingHeader.add("true");

        RoundingUtil.isRoundingRequired(roundingHeader, exposureBreakdownDtos);

        new Expectations() {
            {
                exposureBreakdownDtos.clear(); // Verify the list is cleared for rounding (optional)
                exposureBreakdownDtos.addAll(withAnyList()); // Verify any modifications to the list
            }
        };

        new Verifications() {
            {
                RoundingUtil.getRoundedExposures(exposureBreakdownDtos); times(1);
            }
        };
    }

    @Test
    public void testRoundingNotCalledWhenHeaderIsFalse() {
        List<String> roundingHeader = new ArrayList<>();
        roundingHeader.add("false");

        RoundingUtil.isRoundingRequired(roundingHeader, exposureBreakdownDtos);

        new Verifications() {
            {
                RoundingUtil.getRoundedExposures(exposureBreakdownDtos); times(0);
            }
        };
    }

    @Test
    public void testRoundingNotCalledWhenHeaderIsEmpty() {
        List<String> roundingHeader = new ArrayList<>();

        RoundingUtil.isRoundingRequired(roundingHeader, exposureBreakdownDtos);

        new Verifications() {
            {
                RoundingUtil.getRoundedExposures(exposureBreakdownDtos); times(0);
            }
        };
    }

    @Test
    public void testRoundingNotCalledWhenHeaderHasOtherValue() {
        List<String> roundingHeader = new ArrayList<>();
        roundingHeader.add("abc");

        RoundingUtil.isRoundingRequired(roundingHeader, exposureBreakdownDtos);

        new Verifications() {
            {
                RoundingUtil.getRoundedExposures(exposureBreakdownDtos); times(0);
            }
        };
    }
}


**Pros of using a Header for Rounding:**

* **Standardized behavior:**  By using a header like `X-Precision` (or `Prefer-Round-Digits`), you can define specific rounding behavior (e.g., 2 decimal places, half-up rounding) within your library. This ensures consistency across all requests that include the header.
* **Reduced query string clutter:**  If rounding is a common functionality for many API calls, keeping it out of the query string can make your URLs cleaner and less cluttered. 
* **Aligns with request-specific details:**  As you mentioned, headers are often used for request-specific details like versioning or language preference.  Rounding precision could be considered another detail specific to how the user wants the data presented.

**Addressing Query Parameter Concerns:**

* **Limited flexibility:**  While query parameters are great for user-specified input, your scenario doesn't require users to define the number of decimal places.  The header can provide a pre-defined, standardized behavior.

**Here's how your API could work with a rounding header:**

* Define a custom header  like `X-Precision` or `Prefer-Round-Digits`.
* In your API code, check for the presence of this header.
* If the header exists, use the specified value (e.g., 2) and half-up rounding from your library.
* If the header is absent, use a default rounding behavior (e.g., no rounding or a different default precision).

**Overall:**

Both headers and query parameters have their merits.  In your case, using a header for rounding can provide a clean, standardized approach that aligns well with existing header usage for request-specific details.  It reduces query string clutter and enforces consistent rounding behavior based on your library's configuration.




/**
 * Adjusts the values in a map of currency codes and their corresponding double values
 * to ensure they sum to a target sum, while maintaining rounding to two decimal places.
 *
 * This method iteratively rounds each value in the map to two decimal places using the
 * HALF_UP rounding mode. Then, it calculates the difference between the current sum of
 * the rounded values and the target sum. If the absolute value of the difference is
 * greater than a tolerance (e.g., 0.00001), the method identifies the value in the map that
 * would contribute the least to the difference if its rounded value were adjusted by one
 * in either direction (up or down). This value is then adjusted slightly (up or down)
 * to bring the sum closer to the target sum. The iteration continues until the difference
 * between the current sum and the target sum is within the tolerance.
 *
 * @param values The map of currency codes (String) and their corresponding double values
 * @param targetSum The target sum to which the adjusted values should add up
 * @return A new map containing the adjusted rounded values for each currency code
 */
public Map<String, BigDecimal> adjustRoundedValues(Map<String, Double> values, double targetSum) {
    // ... (implementation of the method)
}

