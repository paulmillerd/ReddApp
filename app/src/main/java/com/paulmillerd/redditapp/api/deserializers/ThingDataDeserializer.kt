package com.paulmillerd.redditapp.api.deserializers

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.paulmillerd.redditapp.api.responseModels.listing.Listing
import com.paulmillerd.redditapp.api.responseModels.listing.ThingData
import java.lang.reflect.Type

class ThingDataDeserializer: JsonDeserializer<ThingData> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ThingData {
        val gson = Gson()
        val data = gson.fromJson(json, ThingData::class.java)
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

        // "replies" field
        if (jsonObject?.has("replies") == true) {
            val elem = jsonObject.get("replies")
            if (elem != null && !elem.isJsonNull) {
                if (elem.isJsonPrimitive && elem.asJsonPrimitive.isString) {
                    data.repliesString = elem.asString
                } else if (elem.isJsonObject) {
                    data.repliesListing = gson.fromJson(elem, Listing::class.java)
                }
            }
        }

        return data
    }
}