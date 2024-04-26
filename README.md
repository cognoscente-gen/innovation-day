## Rounding Library

This library provides a simple utility class for rounding double values to a specified number of decimal places.

**Prerequisites**

The Rounding library has no specific external dependencies and should work with most Java environments. 

**Installation**

If you're using a dependency management system like Maven, you can add the following dependency to your project's pom.xml file:

```xml
<dependency>
  <groupId>your-group-id</groupId>
  <artifactId>rounding-lib</artifactId>
  <version>your-version</version>
</dependency>
```

**Usage**

The `Rounding` class offers a static method `round` that takes a double value and returns the rounded value to two decimal places by default.

```java
import com.example.rounding.Rounding;

public class Main {
  public static void main(String[] args) {
    double value = 3.14159;
    double roundedValue = Rounding.round(value);
    System.out.println("Rounded value (default 2 decimal places): " + roundedValue); // Output: Rounded value (default 2 decimal places): 3.14
  }
}
```

**Additional Features**

- The `round` method can handle positive and negative infinity (`Double.POSITIVE_INFINITY` and  `Double.NEGATIVE_INFINITY`), returning the same infinity value after rounding.
- NaN (Not-a-Number) values are preserved during rounding (e.g., `Rounding.round(Double.NaN)` remains `NaN`).
- The `round` method throws a `NumberFormatException` if the input is not a valid double value (e.g., trying to round a String like "hello").

**Testing**

The library includes unit tests to ensure its functionality for various inputs and edge cases.

**License**

(Include your chosen open-source license information here)

**Getting Started**

1. Add the dependency to your project (if using a dependency management system).
2. Import the `Rounding` class and use the `round` method in your code.

This README provides a basic overview of the Rounding library. Feel free to explore the source code and experiment with the functionality in your projects.
