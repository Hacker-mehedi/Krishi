package com.example.data.model

data class PostComment(
    val id: String,
    val authorNameBn: String,
    val authorLocationBn: String,
    val commentTextBn: String,
    val timeAgoBn: String
)

data class CommunityPost(
    val id: String,
    val authorNameBn: String,
    val authorRoleBn: String = "কৃষক",
    val authorDistrictBn: String,
    val timeAgoBn: String,
    val textContentBn: String,
    val imageUrl: String? = null,
    val videoUrl: String? = null,
    val videoTitleBn: String? = null,
    val isVideo: Boolean = false,
    var likesCount: Int,
    var commentsCount: Int,
    val sharesCount: Int,
    var isLikedByUser: Boolean = false,
    val comments: MutableList<PostComment> = mutableListOf()
)
