/*
*   Classes generated with Json Schema to Kotlin parser
*   generated classes has been renamed to give more context to the data
*
*/

package io.github.michaelbui99.atlas.model.network.responseobjects

import com.google.gson.annotations.SerializedName


/**
 * Response object wrapping the data received, when fetching post data (comments, post content etc.)
 * of a subreddit post e.g. GET https://www.reddit.com/r/Art/comments/t3s5hb.json
 * */
data class SubredditPostDataResponse(val data: List<ResponseDataWrapper<ResponseData>>)

data class ResponseData(
    val after: Any?,
    val dist: Long?,
    val modhash: String,
    @SerializedName("geo_filter")
    val geoFilter: String,
    val children: List<ResponseDataWrapper<PostData>>,
    val before: Any?,
)

data class PostData(
    @SerializedName("approved_at_utc")
    val approvedAtUtc: Any?,
    val subreddit: String,
    val selfText: String?,
    @SerializedName("user_reports")
    val userReports: List<Any?>,
    val saved: Boolean,
    @SerializedName("mod_reason_title")
    val modReasonTitle: Any?,
    val gilded: Long,
    val clicked: Boolean?,
    val title: String?,
    @SerializedName("link_flair_richtext")
    val linkFlairRichtext: List<LinkFlairRichtext>?,
    @SerializedName("subreddit_name_prefixed")
    val subredditNamePrefixed: String,
    val hidden: Boolean?,
    val pwls: Long?,
    @SerializedName("link_flair_css_class")
    val linkFlairCssClass: String?,
    val downs: Long,
    @SerializedName("thumbnail_height")
    val thumbnailHeight: Any?,
    @SerializedName("top_awarded_type")
    val topAwardedType: Any?,
    @SerializedName("parent_whitelist_status")
    val parentWhitelistStatus: String?,
    @SerializedName("hide_score")
    val hideScore: Boolean?,
    val name: String,
    val quarantine: Boolean?,
    @SerializedName("link_flair_text_color")
    val linkFlairTextColor: String?,
    @SerializedName("upvote_ratio")
    val upvoteRatio: Double?,
    @SerializedName("author_flair_background_color")
    val authorFlairBackgroundColor: String?,
    @SerializedName("subreddit_type")
    val subredditType: String,
    val ups: Long,
    @SerializedName("total_awards_received")
    val totalAwardsReceived: Long,
    @SerializedName("media_embed")
    val mediaEmbed: Map<String, Any>?,
    @SerializedName("thumbnail_width")
    val thumbnailWidth: Any?,
    @SerializedName("author_flair_template_id")
    val authorFlairTemplateId: String?,
    @SerializedName("is_original_content")
    val isOriginalContent: Boolean?,
    @SerializedName("author_fullname")
    val authorFullname: String?,
    @SerializedName("secure_media")
    val secureMedia: Any?,
    @SerializedName("is_reddit_media_domain")
    val isRedditMediaDomain: Boolean?,
    @SerializedName("is_meta")
    val isMeta: Boolean?,
    val category: Any?,
    @SerializedName("secure_media_embed")
    val secureMediaEmbed: Map<String, Any>?,
    @SerializedName("link_flair_text")
    val linkFlairText: String?,
    @SerializedName("can_mod_post")
    val canModPost: Boolean,
    val score: Long,
    @SerializedName("approved_by")
    val approvedBy: Any?,
    @SerializedName("is_created_from_ads_ui")
    val isCreatedFromAdsUi: Boolean?,
    @SerializedName("author_premium")
    val authorPremium: Boolean?,
    val thumbnail: String?,
    val edited: Any?,
    @SerializedName("author_flair_css_class")
    val authorFlairCssClass: Any?,
    @SerializedName("author_flair_richtext")
    val authorFlairRichtext: List<AuthorFlairRichtext>?,
    val gildings: Gildings,
    @SerializedName("content_categories")
    val contentCategories: List<String>?,
    @SerializedName("is_self")
    val isSelf: Boolean?,
    @SerializedName("mod_note")
    val modNote: Any?,
    val created: Long,
    @SerializedName("link_flair_type")
    val linkFlairType: String?,
    val wls: Long?,
    @SerializedName("removed_by_category")
    val removedByCategory: Any?,
    @SerializedName("banned_by")
    val bannedBy: Any?,
    @SerializedName("author_flair_type")
    val authorFlairType: String?,
    val domain: String?,
    @SerializedName("allow_live_comments")
    val allowLiveComments: Boolean?,
    @SerializedName("selftext_html")
    val selftextHtml: String?,
    val likes: Any?,
    @SerializedName("suggested_sort")
    val suggestedSort: Any?,
    @SerializedName("banned_at_utc")
    val bannedAtUtc: Any?,
    @SerializedName("view_count")
    val viewCount: Any?,
    val archived: Boolean,
    @SerializedName("no_follow")
    val noFollow: Boolean,
    @SerializedName("is_crosspostable")
    val isCrosspostable: Boolean?,
    val pinned: Boolean?,
    @SerializedName("over_18")
    val over18: Boolean?,
    @SerializedName("all_awardings")
    val allAwardings: List<AllAwarding>,
    val awarders: List<Any?>,
    @SerializedName("media_only")
    val mediaOnly: Boolean?,
    @SerializedName("link_flair_template_id")
    val linkFlairTemplateId: String?,
    @SerializedName("can_gild")
    val canGild: Boolean,
    val spoiler: Boolean?,
    val locked: Boolean,
    @SerializedName("author_flair_text")
    val authorFlairText: String?,
    @SerializedName("treatment_tags")
    val treatmentTags: List<Any?>,
    val visited: Boolean?,
    @SerializedName("removed_by")
    val removedBy: Any?,
    @SerializedName("num_reports")
    val numReports: Any?,
    val distinguished: String?,
    @SerializedName("subreddit_id")
    val subredditId: String,
    @SerializedName("author_is_blocked")
    val authorIsBlocked: Boolean,
    @SerializedName("mod_reason_by")
    val modReasonBy: Any?,
    @SerializedName("removal_reason")
    val removalReason: Any?,
    @SerializedName("link_flair_background_color")
    val linkFlairBackgroundColor: String?,
    val id: String,
    @SerializedName("is_robot_indexable")
    val isRobotIndexable: Boolean?,
    @SerializedName("num_duplicates")
    val numDuplicates: Long?,
    @SerializedName("report_reasons")
    val reportReasons: Any?,
    val author: String,
    @SerializedName("discussion_type")
    val discussionType: Any?,
    @SerializedName("num_comments")
    val numComments: Long?,
    @SerializedName("send_replies")
    val sendReplies: Boolean,
    val media: Any?,
    @SerializedName("contest_mode")
    val contestMode: Boolean?,
    @SerializedName("author_patreon_flair")
    val authorPatreonFlair: Boolean?,
    @SerializedName("author_flair_text_color")
    val authorFlairTextColor: String?,
    val permalink: String,
    @SerializedName("whitelist_status")
    val whitelistStatus: String?,
    val stickied: Boolean,
    val url: String?,
    @SerializedName("subreddit_subscribers")
    val subredditSubscribers: Long?,
    @SerializedName("created_utc")
    val createdUtc: Long,
    @SerializedName("num_crossposts")
    val numCrossposts: Long?,
    @SerializedName("mod_reports")
    val modReports: List<Any?>,
    @SerializedName("is_video")
    val isVideo: Boolean?,
    @SerializedName("comment_type")
    val commentType: Any?,
    val replies: Any?,
    @SerializedName("collapsed_reason_code")
    val collapsedReasonCode: String?,
    @SerializedName("parent_id")
    val parentId: String?,
    val collapsed: Boolean?,
    val body: String?,
    @SerializedName("is_submitter")
    val isSubmitter: Boolean?,
    @SerializedName("body_html")
    val bodyHtml: String?,
    @SerializedName("collapsed_reason")
    val collapsedReason: Any?,
    @SerializedName("associated_award")
    val associatedAward: Any?,
    @SerializedName("unrepliable_reason")
    val unrepliableReason: Any?,
    @SerializedName("score_hidden")
    val scoreHidden: Boolean?,
    @SerializedName("link_id")
    val linkId: String?,
    val controversiality: Long?,
    val depth: Long?,
    @SerializedName("collapsed_because_crowd_control")
    val collapsedBecauseCrowdControl: Any?,
)


data class LinkFlairRichtext(
    val e: String,
    val t: String,
)

data class AuthorFlairRichtext(
    val e: String,
    val t: String,
)

data class Gildings(
    @SerializedName("gid_1")
    val gid1: Long?,
)

data class AllAwarding(
    @SerializedName("giver_coin_reward")
    val giverCoinReward: Any?,
    @SerializedName("subreddit_id")
    val subredditId: Any?,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("days_of_drip_extension")
    val daysOfDripExtension: Any?,
    @SerializedName("coin_price")
    val coinPrice: Long,
    val id: String,
    @SerializedName("penny_donate")
    val pennyDonate: Any?,
    @SerializedName("coin_reward")
    val coinReward: Long,
    @SerializedName("icon_url")
    val iconUrl: String,
    @SerializedName("days_of_premium")
    val daysOfPremium: Any?,
    @SerializedName("icon_height")
    val iconHeight: Long,
    @SerializedName("tiers_by_required_awardings")
    val tiersByRequiredAwardings: Any?,
    @SerializedName("resized_icons")
    val resizedIcons: List<ResizedIcon>,
    @SerializedName("icon_width")
    val iconWidth: Long,
    @SerializedName("static_icon_width")
    val staticIconWidth: Long,
    @SerializedName("start_date")
    val startDate: Any?,
    @SerializedName("is_enabled")
    val isEnabled: Boolean,
    @SerializedName("awardings_required_to_grant_benefits")
    val awardingsRequiredToGrantBenefits: Any?,
    val description: String,
    @SerializedName("end_date")
    val endDate: Any?,
    @SerializedName("subreddit_coin_reward")
    val subredditCoinReward: Long,
    val count: Long,
    @SerializedName("static_icon_height")
    val staticIconHeight: Long,
    val name: String,
    @SerializedName("resized_static_icons")
    val resizedStaticIcons: List<ResizedStaticIcon>,
    @SerializedName("icon_format")
    val iconFormat: Any?,
    @SerializedName("award_sub_type")
    val awardSubType: String,
    @SerializedName("penny_price")
    val pennyPrice: Any?,
    @SerializedName("award_type")
    val awardType: String,
    @SerializedName("static_icon_url")
    val staticIconUrl: String,
)

data class ResizedIcon(
    val url: String,
    val width: Long,
    val height: Long,
)

data class ResizedStaticIcon(
    val url: String,
    val width: Long,
    val height: Long,
)