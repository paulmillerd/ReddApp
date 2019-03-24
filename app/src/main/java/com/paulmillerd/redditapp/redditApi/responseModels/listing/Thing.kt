package com.paulmillerd.redditapp.redditApi.responseModels.listing

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class Thing(

	@field:SerializedName("data")
	val data: ThingData? = null,

	@field:SerializedName("kind")
	val kind: String? = null,

	var tempLikes: Boolean? = null,

	var tempScore: Int? = null

): Serializable