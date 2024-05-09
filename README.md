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


