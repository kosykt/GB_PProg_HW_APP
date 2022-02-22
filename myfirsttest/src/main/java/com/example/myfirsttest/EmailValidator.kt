package com.example.myfirsttest

import android.text.Editable
import android.text.TextWatcher

import java.util.regex.Pattern

class EmailValidator : TextWatcher {

    internal var isValid = false

    override fun afterTextChanged(editableText: Editable?) {
        isValid = isValidEmail(editableText)
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) = Unit

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) = Unit

    companion object {

        const val EQUALS_EMAIL = "equals@email.com"
        const val NOT_EQUALS_EMAIL = "notequals@email.com"

        private val EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )

        fun isValidEmail(email: CharSequence?): Boolean {
            return email != null && EMAIL_PATTERN.matcher(email).matches()
        }

        fun isEqualsEmail(email: CharSequence?): Boolean {
            return email != null && email == EQUALS_EMAIL
        }

        fun isEntryNull(email: CharSequence?): CharSequence? {
            return email
        }
    }
}
