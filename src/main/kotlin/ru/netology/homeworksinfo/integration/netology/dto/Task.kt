package ru.netology.homeworksinfo.integration.netology.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class Task(@JsonProperty("title") var title: String?)