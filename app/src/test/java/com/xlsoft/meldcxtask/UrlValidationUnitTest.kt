package com.xlsoft.meldcxtask

import org.junit.Assert
import org.junit.Test

class UrlValidationUnitTest {
    @Test
    fun url_isValid() {
        val url1 = "http://www.example.com"
        val url2 = "https://www.example.com"

        Assert.assertEquals(isValidUrl(url1), true)
        Assert.assertEquals(isValidUrl(url2), true)
    }

    @Test
    fun url_isNotValid() {
        val url1 = "www.example.com"
        val url2 = "example.com"

        Assert.assertEquals(isValidUrl(url1), false)
        Assert.assertEquals(isValidUrl(url2), false)
    }

    private fun isValidUrl(url : String) : Boolean{
        return url.startsWith("http://") || url.startsWith("https://")
    }

}