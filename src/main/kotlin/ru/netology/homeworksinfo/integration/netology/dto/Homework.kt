package ru.netology.homeworksinfo.integration.netology.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(
    "status", "score", "updated_at", "last_payout_date",
    "payout_status", "last_solution_submit", "last_reviewed_at",
    "lesson_task", "group_users", "solutions", "lesson_task_experts",
    "reviewer", "program"
)
data class Homework(
    @JsonProperty("id") var id: Int?,
    @JsonProperty("task") var task: Task?,
    @JsonProperty("user") var user: User?
)