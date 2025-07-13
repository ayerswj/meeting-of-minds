/**
 * TypeConversion.java
 * Demonstrates type conversion and casting in Java
 */
public class TypeConversion {
    public static void main(String[] args) {
        System.out.println("=== Java Type Conversion Demo ===\n");
        
        // 1. Implicit Casting (Widening)
        System.out.println("1. Implicit Casting (Widening):");
        System.out.println("Java automatically converts smaller types to larger types\n");
        
        byte byteValue = 100;
        short shortValue = byteValue;  // byte to short
        int intValue = shortValue;     // short to int
        long longValue = intValue;     // int to long
        float floatValue = longValue;  // long to float
        double doubleValue = floatValue; // float to double
        
        System.out.println("byte: " + byteValue);
        System.out.println("short: " + shortValue);
        System.out.println("int: " + intValue);
        System.out.println("long: " + longValue);
        System.out.println("float: " + floatValue);
        System.out.println("double: " + doubleValue);
        
        // 2. Explicit Casting (Narrowing)
        System.out.println("\n2. Explicit Casting (Narrowing):");
        System.out.println("You must manually convert larger types to smaller types\n");
        
        double doubleNum = 9.78;
        int intNum = (int) doubleNum;  // double to int
        short shortNum = (short) intNum; // int to short
        byte byteNum = (byte) shortNum;  // short to byte
        
        System.out.println("Original double: " + doubleNum);
        System.out.println("Casted to int: " + intNum);
        System.out.println("Casted to short: " + shortNum);
        System.out.println("Casted to byte: " + byteNum);
        
        // 3. Data Loss in Casting
        System.out.println("\n3. Data Loss in Casting:");
        double largeNumber = 123456.789;
        int truncatedNumber = (int) largeNumber;
        
        System.out.println("Original double: " + largeNumber);
        System.out.println("After casting to int: " + truncatedNumber);
        System.out.println("Decimal part was lost!");
        
        // 4. Character to Integer Conversion
        System.out.println("\n4. Character to Integer Conversion:");
        char charA = 'A';
        char charZ = 'Z';
        char char0 = '0';
        char char9 = '9';
        
        int asciiA = charA;  // Implicit casting
        int asciiZ = (int) charZ;  // Explicit casting
        int ascii0 = char0;
        int ascii9 = char9;
        
        System.out.println("Character 'A' ASCII value: " + asciiA);
        System.out.println("Character 'Z' ASCII value: " + asciiZ);
        System.out.println("Character '0' ASCII value: " + ascii0);
        System.out.println("Character '9' ASCII value: " + ascii9);
        
        // 5. Integer to Character Conversion
        System.out.println("\n5. Integer to Character Conversion:");
        int asciiValue = 65;
        char character = (char) asciiValue;
        
        System.out.println("ASCII value " + asciiValue + " represents character: " + character);
        
        // 6. String to Number Conversion
        System.out.println("\n6. String to Number Conversion:");
        String numberString = "123";
        String doubleString = "123.45";
        
        int parsedInt = Integer.parseInt(numberString);
        double parsedDouble = Double.parseDouble(doubleString);
        
        System.out.println("String '" + numberString + "' converted to int: " + parsedInt);
        System.out.println("String '" + doubleString + "' converted to double: " + parsedDouble);
        
        // 7. Number to String Conversion
        System.out.println("\n7. Number to String Conversion:");
        int number = 42;
        double decimal = 3.14159;
        
        String numberAsString = String.valueOf(number);
        String decimalAsString = String.valueOf(decimal);
        
        System.out.println("int " + number + " converted to String: '" + numberAsString + "'");
        System.out.println("double " + decimal + " converted to String: '" + decimalAsString + "'");
        
        // 8. Practical Example: Temperature Conversion
        System.out.println("\n8. Practical Example: Temperature Conversion");
        double celsius = 25.0;
        int celsiusInt = (int) celsius;  // Truncate decimal
        double fahrenheit = (celsius * 9/5) + 32;
        int fahrenheitInt = (int) fahrenheit;
        
        System.out.println("Celsius: " + celsius + "°C");
        System.out.println("Celsius (int): " + celsiusInt + "°C");
        System.out.println("Fahrenheit: " + fahrenheit + "°F");
        System.out.println("Fahrenheit (int): " + fahrenheitInt + "°F");
        
        System.out.println("\n=== Type Conversion Demo Complete ===");
    }
}