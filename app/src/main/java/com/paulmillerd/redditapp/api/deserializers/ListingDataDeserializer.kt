package com.paulmillerd.redditapp.api.deserializers

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.paulmillerd.redditapp.api.responseModels.Data
import java.lang.reflect.Type

class ListingDataDeserializer: JsonDeserializer<Data> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Data {
        val data = Gson().fromJson(json, Data::class.java)
        val jsonObject = json?.asJsonObject

        // "edited" field
        if (jsonObject?.has("edited") == true) {
            val elem = jsonObject.get("edited")
            if (elem != null && !elem.isJsonNull) {
                if (elem.isJsonPrimitive) {
                    when {
                        elem.asJsonPrimitive.isBoolean -> data.editedBool = elem.asBoolean
                        elem.asJsonPrimitive.isNumber -> data.editedTime = elem.asInt
                        else -> data.editedBool = false
                    }
                } else {
                    data.editedBool = false
                }
            }
        }

        return data
    }
}