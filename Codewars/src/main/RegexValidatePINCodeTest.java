package main;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RegexValidatePINCodeTest {
    @Test
    public void validPins() {
        assertTrue(RegexValidatePINCode.validatePin("1234"));
        assertTrue(RegexValidatePINCode.validatePin("0000"));
        assertTrue(RegexValidatePINCode.validatePin("1111"));
        assertTrue(RegexValidatePINCode.validatePin("123456"));
        assertTrue(RegexValidatePINCode.validatePin("098765"));
        assertTrue(RegexValidatePINCode.validatePin("000000"));
        assertTrue(RegexValidatePINCode.validatePin("090909"));
    }

    @Test
    public void nonDigitCharacters() {
        assertFalse(RegexValidatePINCode.validatePin("a234"));
        assertFalse(RegexValidatePINCode.validatePin(".234"));
    }

    @Test
    public void invalidLengths() {
        assertFalse(RegexValidatePINCode.validatePin("1"));
        assertFalse(RegexValidatePINCode.validatePin("12"));
        assertFalse(RegexValidatePINCode.validatePin("123"));
        assertFalse(RegexValidatePINCode.validatePin("12345"));
        assertFalse(RegexValidatePINCode.validatePin("1234567"));
        assertFalse(RegexValidatePINCode.validatePin("-1234"));
        assertFalse(RegexValidatePINCode.validatePin("1.234"));
        assertFalse(RegexValidatePINCode.validatePin("00000000"));
    }

    @Test
    public void valid() {
        assertFalse(RegexValidatePINCode.validatePin("-111"));
    }
}