package com.example.config

import com.example.consumer.MessageTopic
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig
import io.confluent.kafka.serializers.KafkaAvroDeserializer
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.apache.kafka.common.serialization.ByteArrayDeserializer
import org.apache.kafka.common.serialization.ByteArraySerializer
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@Configuration
data class KafkaConfiguration (
  @Value("\${spring.kafka.properties.schema.registry.url}")
  private val schemaRegistryUrl: String,
  @Value("\${spring.kafka.bootstrap.server}")
  private val bootstrapServer: String
) {
  fun getProperties() =  mapOf(
    KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG to "true",
    AbstractKafkaSchemaSerDeConfig.AUTO_REGISTER_SCHEMAS to true,

    KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG to schemaRegistryUrl,

    ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServer,

    ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
    ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,

    ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
    ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,

    ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS to StringDeserializer::class.java,
    ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS to KafkaAvroDeserializer::class.java,
  )

}

suspend fun <K, V> Producer<K, V>.asyncSend(record: ProducerRecord<K, V>) =
  suspendCoroutine<RecordMetadata> { continuation ->
    send(record) { metadata, exception ->
      exception?.let(continuation::resumeWithException)
        ?: continuation.resume(metadata)
    }
  }