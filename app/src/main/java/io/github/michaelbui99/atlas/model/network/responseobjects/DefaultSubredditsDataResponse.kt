package io.github.michaelbui99.atlas.model.network.responseobjects

import com.google.gson.annotations.SerializedName

data class DefaultSubredditsDataResponse(val kind: String, val data: Data)

data class Data(
    val after: String,
    val dist: Long,
    val modhash: String,
    @SerializedName("geo_filter")
    val geoFilter: String,
    val children: List<ResponseDataWrapper<DefaultSubredditsData>>,
    val before: Any?,
)


data class DefaultSubredditsData(
    @SerializedName("user_flair_background_color")
    val userFlairBackgroundColor: Any?,
    @SerializedName("submit_text_html")
    val submitTextHtml: String?,
    @SerializedName("restrict_posting")
    val restrictPosting: Boolean,
    @SerializedName("user_is_banned")
    val userIsBanned: Boolean,
    @SerializedName("free_form_reports")
    val freeFormReports: Boolean,
    @SerializedName("wiki_enabled")
    val wikiEnabled: Boolean?,
    @SerializedName("user_is_muted")
    val userIsMuted: Boolean,
    @SerializedName("user_can_flair_in_sr")
    val userCanFlairInSr: Any?,
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("header_img")
    val headerImg: String?,
    val title: String,
    @SerializedName("original_content_tag_enabled")
    val originalContentTagEnabled: Boolean,
    @SerializedName("allow_galleries")
    val allowGalleries: Boolean,
    @SerializedName("icon_size")
    val iconSize: List<Long>?,
    @SerializedName("primary_color")
    val primaryColor: String,
    @SerializedName("active_user_count")
    val activeUserCount: Any?,
    @SerializedName("icon_img")
    val iconImg: String,
    @SerializedName("display_name_prefixed")
    val displayNamePrefixed: String,
    @SerializedName("accounts_active")
    val accountsActive: Any?,
    @SerializedName("public_traffic")
    val publicTraffic: Boolean,
    val subscribers: Long,
    @SerializedName("user_flair_richtext")
    val userFlairRichtext: List<Any?>,
    val name: String,
    val quarantine: Boolean,
    @SerializedName("hide_ads")
    val hideAds: Boolean,
    @SerializedName("prediction_leaderboard_entry_type")
    val predictionLeaderboardEntryType: String,
    @SerializedName("emojis_enabled")
    val emojisEnabled: Boolean,
    @SerializedName("advertiser_category")
    val advertiserCategory: String,
    @SerializedName("public_description")
    val publicDescription: String,
    @SerializedName("comment_score_hide_mins")
    val commentScoreHideMins: Long,
    @SerializedName("allow_predictions")
    val allowPredictions: Boolean,
    @SerializedName("user_has_favorited")
    val userHasFavorited: Boolean,
    @SerializedName("user_flair_template_id")
    val userFlairTemplateId: Any?,
    @SerializedName("community_icon")
    val communityIcon: String,
    @SerializedName("banner_background_image")
    val bannerBackgroundImage: String,
    @SerializedName("header_title")
    val headerTitle: String,
    @SerializedName("community_reviewed")
    val communityReviewed: Boolean,
    @SerializedName("submit_text")
    val submitText: String,
    @SerializedName("description_html")
    val descriptionHtml: String,
    @SerializedName("spoilers_enabled")
    val spoilersEnabled: Boolean,
    @SerializedName("allow_talks")
    val allowTalks: Boolean,
    @SerializedName("header_size")
    val headerSize: List<Long>?,
    @SerializedName("user_flair_position")
    val userFlairPosition: String,
    @SerializedName("all_original_content")
    val allOriginalContent: Boolean,
    @SerializedName("has_menu_widget")
    val hasMenuWidget: Boolean,
    @SerializedName("is_enrolled_in_new_modmail")
    val isEnrolledInNewModmail: Any?,
    @SerializedName("key_color")
    val keyColor: String,
    @SerializedName("can_assign_user_flair")
    val canAssignUserFlair: Boolean,
    val created: Double,
    val wls: Long,
    @SerializedName("show_media_preview")
    val showMediaPreview: Boolean,
    @SerializedName("submission_type")
    val submissionType: String,
    @SerializedName("user_is_subscriber")
    val userIsSubscriber: Boolean,
    @SerializedName("disable_contributor_requests")
    val disableContributorRequests: Boolean,
    @SerializedName("allow_videogifs")
    val allowVideogifs: Boolean,
    @SerializedName("should_archive_posts")
    val shouldArchivePosts: Boolean,
    @SerializedName("user_flair_type")
    val userFlairType: String,
    @SerializedName("allow_polls")
    val allowPolls: Boolean,
    @SerializedName("collapse_deleted_comments")
    val collapseDeletedComments: Boolean,
    @SerializedName("emojis_custom_size")
    val emojisCustomSize: List<Long>?,
    @SerializedName("public_description_html")
    val publicDescriptionHtml: String,
    @SerializedName("allow_videos")
    val allowVideos: Boolean,
    @SerializedName("is_crosspostable_subreddit")
    val isCrosspostableSubreddit: Boolean?,
    @SerializedName("suggested_comment_sort")
    val suggestedCommentSort: String?,
    @SerializedName("can_assign_link_flair")
    val canAssignLinkFlair: Boolean,
    @SerializedName("accounts_active_is_fuzzed")
    val accountsActiveIsFuzzed: Boolean,
    @SerializedName("allow_prediction_contributors")
    val allowPredictionContributors: Boolean,
    @SerializedName("submit_text_label")
    val submitTextLabel: String,
    @SerializedName("link_flair_position")
    val linkFlairPosition: String,
    @SerializedName("user_sr_flair_enabled")
    val userSrFlairEnabled: Any?,
    @SerializedName("user_flair_enabled_in_sr")
    val userFlairEnabledInSr: Boolean,
    @SerializedName("allow_discovery")
    val allowDiscovery: Boolean,
    @SerializedName("accept_followers")
    val acceptFollowers: Boolean,
    @SerializedName("user_sr_theme_enabled")
    val userSrThemeEnabled: Boolean,
    @SerializedName("link_flair_enabled")
    val linkFlairEnabled: Boolean,
    @SerializedName("subreddit_type")
    val subredditType: String,
    @SerializedName("notification_level")
    val notificationLevel: String,
    @SerializedName("banner_img")
    val bannerImg: String?,
    @SerializedName("user_flair_text")
    val userFlairText: Any?,
    @SerializedName("banner_background_color")
    val bannerBackgroundColor: String,
    @SerializedName("show_media")
    val showMedia: Boolean,
    val id: String,
    @SerializedName("user_is_contributor")
    val userIsContributor: Boolean,
    val over18: Boolean,
    val description: String,
    @SerializedName("submit_link_label")
    val submitLinkLabel: String,
    @SerializedName("user_flair_text_color")
    val userFlairTextColor: Any?,
    @SerializedName("restrict_commenting")
    val restrictCommenting: Boolean,
    @SerializedName("user_flair_css_class")
    val userFlairCssClass: Any?,
    @SerializedName("allow_images")
    val allowImages: Boolean,
    val lang: String,
    @SerializedName("whitelist_status")
    val whitelistStatus: String,
    val url: String,
    @SerializedName("created_utc")
    val createdUtc: Double,
    @SerializedName("banner_size")
    val bannerSize: List<Long>?,
    @SerializedName("mobile_banner_image")
    val mobileBannerImage: String,
    @SerializedName("user_is_moderator")
    val userIsModerator: Boolean,
    @SerializedName("allow_predictions_tournament")
    val allowPredictionsTournament: Boolean,
    @SerializedName("videostream_links_count")
    val videostreamLinksCount: Long?,
    @SerializedName("allow_chat_post_creation")
    val allowChatPostCreation: Boolean?,
    @SerializedName("is_chat_post_feature_enabled")
    val isChatPostFeatureEnabled: Boolean?,
    @SerializedName("content_category")
    val contentCategory: String?,
)
