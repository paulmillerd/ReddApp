package com.paulmillerd.redditapp.redditApi.responseModels.listing

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class Json(

		@field:SerializedName("data")
	val data: JsonData? = null,

		@field:SerializedName("errors")
	val errors: List<Any?>? = null
)