package ru.netology.homeworksinfo.integration.netology.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class HomeworksPage(
    @JsonProperty("homeworks") var homeworks: List<Homework>?,
    @JsonProperty("current_page") var currentPage: Int?,
    @JsonProperty("total_pages") var totalPages: Int?
)