package com.paulmillerd.redditapp.redditApi.responseModels.listing

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class Preview(

        @field:SerializedName("images")
	val images: List<ImagesItem?>? = null,

        @field:SerializedName("enabled")
	val enabled: Boolean? = null,

        @field:SerializedName("reddit_video_preview")
	val redditVideoPreview: RedditVideoPreview? = null
): Serializable