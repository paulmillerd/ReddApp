package com.paulmillerd.redditapp.redditApi.responseModels.subredditAutocomplete

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class AllowedPostTypes(

	@field:SerializedName("images")
	val images: Boolean? = null,

	@field:SerializedName("spoilers")
	val spoilers: Boolean? = null,

	@field:SerializedName("videos")
	val videos: Boolean? = null,

	@field:SerializedName("links")
	val links: Boolean? = null,

	@field:SerializedName("text")
	val text: Boolean? = null
)