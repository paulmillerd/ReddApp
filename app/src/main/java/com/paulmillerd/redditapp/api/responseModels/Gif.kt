package com.paulmillerd.redditapp.api.responseModels

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class Gif(

	@field:SerializedName("resolutions")
	val resolutions: List<ResolutionsItem?>? = null,

	@field:SerializedName("source")
	val source: Source? = null
)