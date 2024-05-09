import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TestRound {

    public static Map<String, Double> readCurrencyData(String fileName) throws IOException {
        Map<String, Double> values = new HashMap<>();
        try (Scanner scanner = new Scanner(new FileReader(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Assuming each line is formatted as "currencyCode value" (adapt if different)
                String[] parts = line.split(" ");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Invalid line format in test_data.json");
                }
                String currencyCode = parts[0];
                double value;
                try {
                    value = Double.parseDouble(parts[1]);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid value format in test_data.json: " + e.getMessage());
                }
                values.put(currencyCode, value);
            }
        } catch (FileNotFoundException e) {
            throw new IOException("File not found: " + fileName);
        }
        return values;
    }

    public static void main(String[] args) throws IOException {
        String fileName = "test_data.json"; // Replace with your actual file path

        // Test case 1: Successful reading and processing
        try {
            Map<String, Double> values = readCurrencyData(fileName);
            round.adjustValues(values); // Call your round.adjustValues() method here
            // Add assertions to verify the adjusted values in Map (if applicable)
        } catch (IOException e) {
            System.err.println("Error reading data: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid data format: " + e.getMessage());
        }

        // Test case 2: Handle errors (invalid file path, invalid data format)
        String invalidFileName = "invalid_file.json"; // Example of an invalid filename
        try {
            readCurrencyData(invalidFileName);
            System.err.println("Test failed: Expected exception for invalid file path");
        } catch (IOException e) {
            System.out.println("Test passed: Exception caught for invalid file path (" + e.getMessage() + ")");
        }
    }
}




import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class TestRoundTest {

    @TempDir
    public File tempDir;

    @Test
    public void testReadCurrencyData_ValidData() throws IOException {
        // Create a test data file with valid content
        File testDataFile = new File(tempDir, "test_data.json");
        String testData = "USD 10.50\nEUR 12.34\n";
        writeStringToFile(testDataFile, testData);

        // Call the method under test
        Map<String, Double> values = TestRound.readCurrencyData(testDataFile.getPath());

        // Verify the parsed data
        Map<String, Double> expectedValues = new HashMap<>();
        expectedValues.put("USD", 10.50);
        expectedValues.put("EUR", 12.34);
        assertEquals(expectedValues, values);
    }

    @Test
    public void testReadCurrencyData_InvalidFile() throws IOException {
        // Use a non-existent file path
        String invalidFilePath = "invalid_file.json";

        // Expect an IOException
        assertThrows(IOException.class, () -> TestRound.readCurrencyData(invalidFilePath));
    }

    @Test
    public void testReadCurrencyData_InvalidFormat() throws IOException {
        // Create a test data file with invalid format
        File testDataFile = new File(tempDir, "test_data.json");
        String invalidData = "This is not valid data\n";
        writeStringToFile(testDataFile, invalidData);

        // Expect an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> TestRound.readCurrencyData(testDataFile.getPath()));
    }

    private void writeStringToFile(File file, String content) throws IOException {
        try (java.io.FileWriter writer = new java.io.FileWriter(file)) {
            writer.write(content);
        }
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

