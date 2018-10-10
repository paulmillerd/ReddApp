package com.paulmillerd.redditapp.api.responseModels

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class SecureMediaEmbed(

	@field:SerializedName("scrolling")
	val scrolling: Boolean? = null,

	@field:SerializedName("media_domain_url")
	val mediaDomainUrl: String? = null,

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("content")
	val content: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
)