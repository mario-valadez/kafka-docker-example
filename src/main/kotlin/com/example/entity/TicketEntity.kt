package com.example.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*

@Table(name = "messages")
data class MessageEntity(
  @Id val id: UUID,
  val content: String,
  val createdAt: LocalDateTime,
)