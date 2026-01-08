package com.lab;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SwedishSocialSecurityNumberTest {
    
    private SSNHelper mockHelper;
    
    @BeforeEach
    public void setUp() {
        mockHelper = mock(SSNHelper.class);
    }
    
    @Test
    public void shouldCreateValidSSNWhenAllChecksPass() throws Exception {
        when(mockHelper.isCorrectLength("900101-0017")).thenReturn(true);
        when(mockHelper.isCorrectFormat("900101-0017")).thenReturn(true);
        when(mockHelper.isValidMonth("01")).thenReturn(true);
        when(mockHelper.isValidDay("01")).thenReturn(true);
        when(mockHelper.luhnIsCorrect("900101-0017")).thenReturn(true);
        
        SwedishSocialSecurityNumber ssn = new SwedishSocialSecurityNumber("900101-0017", mockHelper);
        
        // Assert: Verify the SSN was created and methods work
        assertEquals("90", ssn.getYear());
        assertEquals("01", ssn.getMonth());
        assertEquals("01", ssn.getDay());
        assertEquals("0017", ssn.getSerialNumber());
        
        // Verify that the mock methods were called
        verify(mockHelper).isCorrectLength("900101-0017");
        verify(mockHelper).isCorrectFormat("900101-0017");
        verify(mockHelper).isValidMonth("01");
        verify(mockHelper).isValidDay("01");
        verify(mockHelper).luhnIsCorrect("900101-0017");
    }

    @Test
    public void noLenCheck() {
        String input = "900101-001";

        when(mockHelper.isCorrectLength(input)).thenReturn(false);
        when(mockHelper.isCorrectFormat(input)).thenReturn(true);
        when(mockHelper.isValidMonth("01")).thenReturn(true);
        when(mockHelper.isValidDay("01")).thenReturn(true);
        when(mockHelper.luhnIsCorrect(input)).thenReturn(true);

        assertThrows(Exception.class, () ->
            new SwedishSocialSecurityNumber(input, mockHelper)
            //new BuggySwedishSocialSecurityNumberNoLenCheck(input, mockHelper)

        );


        verify(mockHelper).isCorrectLength(input);
    }

    @Test
    public void noLuhnCheck() {
        // Arrange
        String input = "900101-0017";

        when(mockHelper.isCorrectLength(input)).thenReturn(true);
        when(mockHelper.isCorrectFormat(input)).thenReturn(true);
        when(mockHelper.isValidMonth("01")).thenReturn(true);
        when(mockHelper.isValidDay("01")).thenReturn(true);
        when(mockHelper.luhnIsCorrect(input)).thenReturn(false);

        assertThrows(Exception.class, () ->
            new SwedishSocialSecurityNumber(input, mockHelper)
            //new BuggySwedishSocialSecurityNumberNoLuhn(input, mockHelper)
        );


        verify(mockHelper).luhnIsCorrect(input);
    }

    @Test
    public void noTrimCheck() {
        String input = " 900101-0017 ";
        String trimmedInput = input.trim();

        when(mockHelper.isCorrectLength(trimmedInput)).thenReturn(true);
        when(mockHelper.isCorrectFormat(trimmedInput)).thenReturn(false);
        when(mockHelper.isValidMonth("01")).thenReturn(true);
        when(mockHelper.isValidDay("01")).thenReturn(true);
        when(mockHelper.luhnIsCorrect(trimmedInput)).thenReturn(true);

        assertThrows(Exception.class, () ->
            new SwedishSocialSecurityNumber(input, mockHelper)
            //new BuggySwedishSocialSecurityNumberNoTrim(input, mockHelper)

        );
        verify(mockHelper).isCorrectFormat(trimmedInput);
    }

    @Test
    public void wrongYearCheck() throws Exception {
        String input = "900101-0017";

        when(mockHelper.isCorrectLength(input)).thenReturn(true);
        when(mockHelper.isCorrectFormat(input)).thenReturn(true);
        when(mockHelper.isValidMonth("01")).thenReturn(true);
        when(mockHelper.isValidDay("01")).thenReturn(true);
        when(mockHelper.luhnIsCorrect(input)).thenReturn(true);

        SwedishSocialSecurityNumber ssn1 = new SwedishSocialSecurityNumber(input, mockHelper);
        //BuggySwedishSocialSecurityNumberWrongYear ssn1 = new BuggySwedishSocialSecurityNumberWrongYear(input, mockHelper);

        assertEquals("90", ssn1.getYear());  
    }

}