package com.paulmillerd.redditapp.api.responseModels.subredditAutocomplete

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class SubredditsItem(

	@field:SerializedName("allowedPostTypes")
	val allowedPostTypes: AllowedPostTypes? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("primaryColor")
	val primaryColor: String? = null,

	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("communityIcon")
	val communityIcon: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("numSubscribers")
	val numSubscribers: Int? = null
)