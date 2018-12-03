package com.paulmillerd.redditapp.redditApi.responseModels.listing

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class Listing(

		@field:SerializedName("data")
	val data: ListingData? = null,

		@field:SerializedName("kind")
	val kind: String? = null

)