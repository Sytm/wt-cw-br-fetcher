package de.md5lukas.wtcwbr

import kotlinx.serialization.Serializable

@Serializable
internal class GenericLDJson(val `@type`: String)

@Serializable
internal class DiscussionForumPostingLDJson(val pageEnd: Int, val comment: List<ForumEntry>)

@Serializable
internal class ForumEntry(val text: String)