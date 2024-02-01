package com.example.repoviewer.model

data class RepositoriesResponse(
	val repositoriesResponse: List<RepositoriesResponseItem?>? = null
)


data class RepositoriesResponseItem(
	val tagsUrl: String? = null,
	val jsonMemberPrivate: Boolean? = null,
	val contributorsUrl: String? = null,
	val notificationsUrl: String? = null,
	val description: String? = null,
	val subscriptionUrl: String? = null,
	val keysUrl: String? = null,
	val branchesUrl: String? = null,
	val deploymentsUrl: String? = null,
	val issueCommentUrl: String? = null,
	val labelsUrl: String? = null,
	val subscribersUrl: String? = null,
	val releasesUrl: String? = null,
	val commentsUrl: String? = null,
	val stargazersUrl: String? = null,
	val id: Int? = null,
	val owner: Owner? = null,
	val archiveUrl: String? = null,
	val commitsUrl: String? = null,
	val gitRefsUrl: String? = null,
	val forksUrl: String? = null,
	val compareUrl: String? = null,
	val statusesUrl: String? = null,
	val gitCommitsUrl: String? = null,
	val blobsUrl: String? = null,
	val gitTagsUrl: String? = null,
	val mergesUrl: String? = null,
	val downloadsUrl: String? = null,
	val url: String? = null,
	val contentsUrl: String? = null,
	val milestonesUrl: String? = null,
	val teamsUrl: String? = null,
	val fork: Boolean? = null,
	val issuesUrl: String? = null,
	val fullName: String? = null,
	val eventsUrl: String? = null,
	val issueEventsUrl: String? = null,
	val languagesUrl: String? = null,
	val htmlUrl: String? = null,
	val collaboratorsUrl: String? = null,
	val name: String? = null,
	val pullsUrl: String? = null,
	val hooksUrl: String? = null,
	val assigneesUrl: String? = null,
	val treesUrl: String? = null,
	val nodeId: String? = null
)

