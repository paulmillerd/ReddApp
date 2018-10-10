package com.paulmillerd.redditapp.api.responseModels

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class Preview(

	@field:SerializedName("images")
	val images: List<ImagesItem?>? = null,

	@field:SerializedName("enabled")
	val enabled: Boolean? = null,

	@field:SerializedName("reddit_video_preview")
	val redditVideoPreview: RedditVideoPreview? = null
)