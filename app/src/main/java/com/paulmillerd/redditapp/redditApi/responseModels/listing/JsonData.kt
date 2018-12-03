package com.paulmillerd.redditapp.redditApi.responseModels.listing

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class JsonData(

	@field:SerializedName("things")
	val things: List<Thing>? = null
)