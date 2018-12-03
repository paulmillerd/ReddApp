package com.paulmillerd.redditapp.redditApi.responseModels.account

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class GeopopularTwHoldout(

	@field:SerializedName("owner")
	val owner: String? = null,

	@field:SerializedName("experiment_id")
	val experimentId: Int? = null,

	@field:SerializedName("variant")
	val variant: String? = null
)