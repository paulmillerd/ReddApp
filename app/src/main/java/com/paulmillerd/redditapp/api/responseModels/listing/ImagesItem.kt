package com.paulmillerd.redditapp.api.responseModels.listing

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class ImagesItem(

        @field:SerializedName("resolutions")
	val resolutions: List<ResolutionsItem?>? = null,

        @field:SerializedName("source")
	val source: Source? = null,

        @field:SerializedName("variants")
	val variants: Variants? = null,

        @field:SerializedName("id")
	val id: String? = null
): Serializable