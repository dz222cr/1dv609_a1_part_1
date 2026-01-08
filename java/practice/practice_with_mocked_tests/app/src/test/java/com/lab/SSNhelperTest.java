package com.lab;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SSNhelperTest {
    //private SSNHelper helper = new SSNHelper();
    //private BuggySSNHelperAllowDayUpTo30 helper = new BuggySSNHelperAllowDayUpTo30();
    //private BuggySSNHelperAllowMonth0 helper = new BuggySSNHelperAllowMonth0();
    private BuggySSNHelperIncorrectFormat helper = new BuggySSNHelperIncorrectFormat();
    //private BuggySSNHelperIncorrectFormatFalse helper = new BuggySSNHelperIncorrectFormatFalse();
    //private BuggySSNHelperMessyLuhn helper = new BuggySSNHelperMessyLuhn();
    //private BuggySSNHelperWrongLength helper = new BuggySwedishSocialSecurityNumberNoLenCheck

    @Test
    public void correctLength() {
        assertTrue(helper.isCorrectLength("040608-1234"));
    }

    @Test
    public void incorrectLength() {
        assertFalse(helper.isCorrectLength("04068-1234"));
    }

    @Test
    public void correctFormat() {
        assertTrue(helper.isCorrectFormat("040608-1234"));
    }

    @Test
    public void incorrectFormat() {
        assertFalse(helper.isCorrectFormat("0406081234"));
    }

    @Test
    public void correctMonth() {
        assertTrue(helper.isValidMonth("06"));
    }

    @Test
    public void incorrectMonth() {
        assertFalse(helper.isValidMonth("0"));
    }

    @Test
    public void correctDay() {
        assertTrue(helper.isValidDay("31"));
    }

    @Test
    public void incorrectDay() {
        assertFalse(helper.isValidDay("69"));
    }

    @Test
    public void correctConvert() { //Not using my personal number
        assertTrue(helper.luhnIsCorrect("900101-0017"));
    }

     @Test
    public void incorrectConvert() {
        assertFalse(helper.luhnIsCorrect("040608-1234"));
    }
}


