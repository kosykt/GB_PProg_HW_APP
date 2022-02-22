package com.example.myfirsttest

import org.junit.Assert.*
import org.junit.Test

class EmailValidatorTest {

    @Test
    fun emailValidator_isEmailsEquals_ReturnTrue(){
        assertTrue(EmailValidator.isEqualsEmail(EmailValidator.EQUALS_EMAIL))
    }

    @Test
    fun emailValidator_isNotEmailsEquals_ReturnFalse(){
        assertFalse(EmailValidator.isEqualsEmail(EmailValidator.NOT_EQUALS_EMAIL))
    }

    @Test
    fun emailValidator_isEntryNull_ReturnNull(){
        assertNull(EmailValidator.isEntryNull(null))
    }

    @Test
    fun emailValidator_isEntryNotNull_ReturnNotNull(){
        assertNotNull(EmailValidator.isEntryNull("name@email.com"))
    }

    @Test
    fun emailValidator_isConstantSame_ReturnSame(){
        assertSame(EmailValidator.EQUALS_EMAIL, EmailValidator.EQUALS_EMAIL)
    }

    @Test
    fun emailValidator_isConstantNotSame_ReturnNotSame(){
        assertNotSame(EmailValidator.EQUALS_EMAIL, EmailValidator.NOT_EQUALS_EMAIL)
    }

    @Test
    fun emailValidator_InvalidDomain_ReturnFalse(){
        assertFalse(EmailValidator.isValidEmail("name@email.0_1"))
    }

    @Test
    fun emailValidator_OnlyNumbersEntry_ReturnsFalse(){
        assertFalse(EmailValidator.isValidEmail("123123"))
    }

    @Test
    fun emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertTrue(EmailValidator.isValidEmail("name@email.com"))
    }

    @Test
    fun emailValidator_CorrectEmailSubDomain_ReturnsTrue() {
        assertTrue(EmailValidator.isValidEmail("name@email.co.uk"))
    }

    @Test
    fun emailValidator_InvalidEmailNoTld_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("name@email"))
    }

    @Test
    fun emailValidator_InvalidEmailDoubleDot_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("name@email..com"))
    }

    @Test
    fun emailValidator_InvalidEmailNoUsername_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("@email.com"))
    }

    @Test
    fun emailValidator_EmptyString_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail(""))
    }

    @Test
    fun emailValidator_NullEmail_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail(null))
    }
}
