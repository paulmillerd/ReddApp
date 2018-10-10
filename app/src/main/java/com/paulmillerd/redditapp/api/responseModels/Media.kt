package com.paulmillerd.redditapp.api.responseModels

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class Media(

	@field:SerializedName("oembed")
	val oembed: Oembed? = null,

	@field:SerializedName("type")
	val type: String? = null
)