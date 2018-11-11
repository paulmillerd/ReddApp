package com.paulmillerd.redditapp.api.responseModels.listing

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class Gif(

        @field:SerializedName("resolutions")
	val resolutions: List<ResolutionsItem?>? = null,

        @field:SerializedName("source")
	val source: Source? = null
)