package com.paulmillerd.redditapp.api.responseModels.subredditAbout

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class AboutResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("kind")
	val kind: String? = null
)