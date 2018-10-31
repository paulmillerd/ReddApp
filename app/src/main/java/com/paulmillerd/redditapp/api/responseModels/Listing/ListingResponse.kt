package com.paulmillerd.redditapp.api.responseModels.Listing

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class ListingResponse(

        @field:SerializedName("data")
	val data: Data? = null,

        @field:SerializedName("kind")
	val kind: String? = null
)