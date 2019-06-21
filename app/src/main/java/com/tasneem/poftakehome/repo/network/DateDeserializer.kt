package com.tasneem.poftakehome.repo.network

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.tasneem.poftakehome.repo.model.POFDate
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

class DateDeserializer: JsonDeserializer<POFDate> {
    companion object {
        const val SERVER_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        const val EXPECTED_DATE_FORMAT = "MMM dd, yyyy"
    }
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): POFDate? {
        try {
            json?.asString ?: return null

            val date = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.ENGLISH).parse(json.asString)

            return POFDate(SimpleDateFormat(
                    EXPECTED_DATE_FORMAT,
                Locale.ENGLISH
            ).format(date))
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}