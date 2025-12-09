package com.lab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Password implementations.
 * 
 * To test different buggy versions, simply uncomment the corresponding
 * getPassword() method and comment out the others.
 * 
 * Available implementations:
 * - Password: Correct implementation
 * - BugDoesNotTrim: Does not trim whitespace
 * - BugToShortPassword: Allows passwords shorter than 12 characters
 * - BugVeryShort: Allows way to short passwords
 * - BugWrongExceptionMessage: Wrong exception message for short passwords
 * - BugMissingPasswordLengthCheck: Does not throw exception for short passwords
 * - BugMissingNumberCheck: Does not throw exception if password lacks a number
 * - BugIsPasswordSameAlwaysTrue: isPasswordSame always returns true
 * - BugWrongHashingAlgorithm: Wrong hashing algorithm
 */

public class PasswordTest {
    private IPassword getPassword(String s) throws Exception {
        // return (IPassword) new Password(s);
        // return (IPassword) new BugDoesNotTrim(s);
        // return (IPassword) new BugToShortPassword(s);
        // return (IPassword) new BugToShortPassword(s);
        // return (IPassword) new BugVeryShort(s);
        // return (IPassword) new BugWrongExceptionMessage(s);
        // return (IPassword) new BugMissingPasswordLengthCheck(s);
        // return (IPassword) new BugMissingNumberCheck(s);
        // return (IPassword) new BugIsPasswordSameAlwaysTrue(s);
         return (IPassword) new BugWrongHashingAlgorithm(s);
    }

    @Test
    public void trimTest() throws Exception {
        String noTrim = " 1234567890 ";

        assertThrows(Exception.class, () -> {
            getPassword(noTrim);
        }, "Expected exception because password is not trimed");
    }

    @Test
    public void Passlength() throws Exception {
        String shortPass = "12345678901";
        
        assertThrows(Exception.class, () -> {
            getPassword(shortPass);
        }, "Expected exception because password is less than 12 chars");
    }

    @Test
    public void veryShort() throws Exception {
        String shorterPass = "123456";

        assertThrows(Exception.class, () -> {
            getPassword(shorterPass);
        }, "Expected exception because password is less than 6 chars");
    }

    @Test
    public void Wrongexception() {
        String shortPass = "12345678901";
        Exception exception = assertThrows(Exception.class, () -> {
            getPassword(shortPass);
        }, "Expected exception because password is less then 12 chars");

        String correctMessage = "To short password";
        String actualMessage = exception.getMessage();

        assertEquals(correctMessage, actualMessage, "Message should match the requirement!");
    }

    @Test
    public void lengthCheck() throws Exception {
        String shortPass = "12345678901";
        assertDoesNotThrow(() -> {
            getPassword(shortPass);
        }, "This password should of had an exception thrown but none was presented");
    }

    @Test
    public void numCheck() throws Exception {
        String noNum = "HahaNoNumber";

        assertThrows(Exception.class, () -> {
            getPassword(noNum);
        },"Expected exception since password has no number");
    }

    @Test
    public void duplicateTrue() throws Exception {
        String pass = "123456789012";
        String pass2 = "123456789013";
        IPassword og = getPassword(pass);
        assertFalse(og.isPasswordSame(getPassword(pass2)), "Password should be false");
    }

    @Test
    public void hashError() throws Exception {
        String pass = "12345678901A";
        String pass2 = "12345678901F";
        IPassword p1 = getPassword(pass);
        assertFalse(p1.isPasswordSame(getPassword(pass2)), "Passwords are different, buggy hash");
    }
}
