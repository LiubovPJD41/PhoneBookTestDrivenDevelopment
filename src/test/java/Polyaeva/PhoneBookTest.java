package Polyaeva;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhoneBookTest {
    private static PhoneBook testPhoneBook;

    @BeforeEach
    public void init() {
        testPhoneBook = new PhoneBook();
    }

    @ParameterizedTest
    @MethodSource("sourceForAdd")
    public void addTest(String name, String number, int count) {
        int result = testPhoneBook.add(name, number);
        assertEquals(count, result);
    }

    private static Stream<Arguments> sourceForAdd() {
        return Stream.of(
                Arguments.of("Ivan", "+79161234567", 1),
                Arguments.of("Ivan", "+79161234567", 1)
        );
    }

    @ParameterizedTest
    @MethodSource("addNullCase")
    public void addNotEnoughDataTest(String name, String number) {
        Assertions.assertThrows(Exception.class, () -> testPhoneBook.add(name, number));
    }

    private static Stream<Arguments> addNullCase() {
        return Stream.of(
                Arguments.of(null, "+78121234567"),
                Arguments.of("Maria", null),
                Arguments.of(null, null)
        );
    }

    @Test
    public void findByNumberTest() {
        testPhoneBook.phoneBook.put("Maria", "+79151234567");
        testPhoneBook.phoneBook.put("Olga", "+74981234567");
        String expectedName = "Maria";
        String result = testPhoneBook.findByNumber("+79151234567");
        assertEquals(expectedName, result);
    }

    @Test
    public void findByNumberNullCaseTest() {
        testPhoneBook.phoneBook.put("Ivan", "+79161234567");
        testPhoneBook.phoneBook.put("Elena", "+74951234567");
        Assertions.assertThrows(Exception.class, () -> testPhoneBook.findByNumber(null));
    }

    @Test
    public void findByNameTest() {
        testPhoneBook.phoneBook.put("Maria", "+79151234567");
        testPhoneBook.phoneBook.put("Olga", "+74981234567");
        String expectedNumber = "+79151234567";
        String result = testPhoneBook.findByName("Maria");
        assertEquals(expectedNumber, result);
    }

    @Test
    public void findByNameNullCaseTest() {
        testPhoneBook.phoneBook.put("Ivan", "+79161234567");
        testPhoneBook.phoneBook.put("Elena", "+74951234567");
        Assertions.assertThrows(Exception.class, () -> testPhoneBook.findByName(null));
    }

    @Test
    public void printAllNamesTest() {
        testPhoneBook.phoneBook.put("Ivan", "+79161234567");
        testPhoneBook.phoneBook.put("Elena", "+74951234567");
        testPhoneBook.phoneBook.put("Maria", "+79151234567");
        String expected = "[Elena, Ivan, Maria]";
        String result = testPhoneBook.printAllNames();
        assertEquals(expected, result);
    }

    @AfterEach
    public void tearDown() {
        testPhoneBook = null;
    }
}