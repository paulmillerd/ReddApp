package com.paulmillerd.redditapp.api.responseModels.Listing

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class Preview(

        @field:SerializedName("images")
	val images: List<ImagesItem?>? = null,

        @field:SerializedName("enabled")
	val enabled: Boolean? = null,

        @field:SerializedName("reddit_video_preview")
	val redditVideoPreview: RedditVideoPreview? = null
)