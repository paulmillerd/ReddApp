package com.paulmillerd.redditapp.redditApi

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.paulmillerd.redditapp.redditApi.deserializers.ThingDataDeserializer
import com.paulmillerd.redditapp.redditApi.responseModels.listing.ThingData

class GsonInstances {

    val vanillaGson = Gson()
    val gsonForThingData: Gson = GsonBuilder()
            .registerTypeAdapter(ThingData::class.java, ThingDataDeserializer(this))
            .create()

}