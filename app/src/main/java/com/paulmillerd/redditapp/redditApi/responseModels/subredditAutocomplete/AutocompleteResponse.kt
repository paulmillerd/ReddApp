package com.paulmillerd.redditapp.redditApi.responseModels.subredditAutocomplete

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class AutocompleteResponse(

	@field:SerializedName("subreddits")
	val subreddits: List<SubredditsItem?>? = null
)