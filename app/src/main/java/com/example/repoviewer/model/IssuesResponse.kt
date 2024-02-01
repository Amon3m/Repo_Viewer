package com.example.repoviewer.model

data class IssuesResponse(
	val issuesResponse: List<IssuesResponseItem?>? = null
)

data class PullRequest(
	val patchUrl: String? = null,
	val htmlUrl: String? = null,
	val mergedAt: Any? = null,
	val diffUrl: String? = null,
	val url: String? = null
)

data class IssuesResponseItem(
	val assignees: List<Any?>? = null,
	val createdAt: String? = null,
	val title: String? = null,
	val body: String? = null,
	val labelsUrl: String? = null,
	val authorAssociation: String? = null,
	val number: Int? = null,
	val updatedAt: String? = null,
	val performedViaGithubApp: Any? = null,
	val draft: Boolean? = null,
	val commentsUrl: String? = null,
	val activeLockReason: Any? = null,
	val repositoryUrl: String? = null,
	val id: Int? = null,
	val state: String? = null,
	val locked: Boolean? = null,
	val timelineUrl: String? = null,
	val pullRequest: PullRequest? = null,
	val stateReason: Any? = null,
	val comments: Int? = null,
	val closedAt: Any? = null,
	val url: String? = null,
	val labels: List<Any?>? = null,
	val milestone: Any? = null,
	val eventsUrl: String? = null,
	val htmlUrl: String? = null,
	val reactions: Reactions? = null,
	val assignee: Any? = null,
	val user: User? = null,
	val nodeId: String? = null
)

data class User(
	val gistsUrl: String? = null,
	val reposUrl: String? = null,
	val followingUrl: String? = null,
	val starredUrl: String? = null,
	val login: String? = null,
	val followersUrl: String? = null,
	val type: String? = null,
	val url: String? = null,
	val subscriptionsUrl: String? = null,
	val receivedEventsUrl: String? = null,
	val avatarUrl: String? = null,
	val eventsUrl: String? = null,
	val htmlUrl: String? = null,
	val siteAdmin: Boolean? = null,
	val id: Int? = null,
	val gravatarId: String? = null,
	val nodeId: String? = null,
	val organizationsUrl: String? = null
)

data class Reactions(
	val confused: Int? = null,
	val jsonMember1: Int? = null,
	val totalCount: Int? = null,
	val jsonMember2: Int? = null,
	val rocket: Int? = null,
	val hooray: Int? = null,
	val eyes: Int? = null,
	val url: String? = null,
	val laugh: Int? = null,
	val heart: Int? = null
)

