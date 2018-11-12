package com.paulmillerd.redditapp.api.responseModels.listing

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class ListingData(

		@field:SerializedName("modhash")
	val modhash: String? = null,

		@field:SerializedName("before")
	val before: String? = null,

		@field:SerializedName("dist")
	val dist: Int? = null,

		@field:SerializedName("after")
	val after: String? = null,

		@field:SerializedName("children")
        val children: List<Thing>? = null
): Serializable