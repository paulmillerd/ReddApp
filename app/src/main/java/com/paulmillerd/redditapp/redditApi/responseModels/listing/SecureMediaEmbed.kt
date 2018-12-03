package com.paulmillerd.redditapp.redditApi.responseModels.listing

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class SecureMediaEmbed(

	@field:SerializedName("oembed")
	val oembed: Oembed? = null,

	@field:SerializedName("type")
	val type: String? = null
): Serializable