package com.paulmillerd.redditapp.api.responseModels.account

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class MwebXpromoRevampV2(

	@field:SerializedName("owner")
	val owner: String? = null,

	@field:SerializedName("experiment_id")
	val experimentId: Int? = null,

	@field:SerializedName("variant")
	val variant: String? = null
)