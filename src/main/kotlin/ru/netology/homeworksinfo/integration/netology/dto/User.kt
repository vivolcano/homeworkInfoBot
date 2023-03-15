package ru.netology.homeworksinfo.integration.netology.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties("id", "avatar_path")
data class User(@JsonProperty("full_name") var fullName: String?)