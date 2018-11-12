package com.paulmillerd.redditapp.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.paulmillerd.redditapp.api.deserializers.ThingDataDeserializer
import com.paulmillerd.redditapp.api.responseModels.listing.ThingData

class GsonInstances {

    val vanillaGson = Gson()
    val gsonForThingData: Gson = GsonBuilder()
            .registerTypeAdapter(ThingData::class.java, ThingDataDeserializer(this))
            .create()

}