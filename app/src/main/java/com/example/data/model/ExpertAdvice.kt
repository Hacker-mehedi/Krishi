package com.example.data.model

data class ExpertAdvice(
    val id: String,
    val titleBn: String,
    val expertNameBn: String,
    val expertTitleBn: String,
    val institutionBn: String,
    val summaryBn: String,
    val fullArticleBn: String,
    val videoUrl: String? = null,
    val isVideo: Boolean = false,
    val videoDuration: String? = null,
    val categoryBn: String,
    val publishDateBn: String,
    val readTimeMinutes: Int,
    val likesCount: Int = 142
)
