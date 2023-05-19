package com.example.repository

import com.example.entity.MessageEntity
import kotlinx.coroutines.flow.toList
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.stereotype.Repository
import java.util.*
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.r2dbc.core.flow
import org.springframework.data.r2dbc.core.select
import java.time.LocalDateTime

@Repository
class MessageRepository(
  private val template: R2dbcEntityTemplate,
) {
  suspend fun create(content: String): MessageEntity =
    template.insert(
      MessageEntity(
        id = UUID.randomUUID(),
        content = content,
        createdAt = LocalDateTime.now(),
      ),
    ).awaitSingle()

  suspend fun getList() =
    template.select<MessageEntity>().flow().toList()
}