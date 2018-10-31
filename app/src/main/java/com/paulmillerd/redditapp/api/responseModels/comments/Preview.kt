package com.paulmillerd.redditapp.api.responseModels.comments

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class Preview(

	@field:SerializedName("images")
	val images: List<ImagesItem?>? = null,

	@field:SerializedName("enabled")
	val enabled: Boolean? = null
)