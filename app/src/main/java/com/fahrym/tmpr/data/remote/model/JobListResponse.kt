package com.fahrym.tmpr.data.remote.model

import com.fahrym.tmpr.ui.base.ListAdapterItem
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class JobListResponse(
    @SerializedName("data") val data: List<Data>,
    @SerializedName("aggregations") val aggregations: Aggregations
) {
    data class Aggregations(
        @SerializedName("count") val count: Int
    )

    data class Data(
        @SerializedName("starts_at") val starts_at: String,
        @SerializedName("ends_at") val ends_at: String,
        @SerializedName("job") val job: Job,
        @SerializedName("earnings_per_hour") val earnings_per_hour: Earn,
        override val idd: Long = 0
    ) : ListAdapterItem, Serializable

    data class Job(
        @SerializedName("project") val project: Project,
        @SerializedName("category") val category: Category,
    )

    data class Earn(
        @SerializedName("currency") val currency: String,
        @SerializedName("amount") val amount: Double,
    )

    data class Category(
        @SerializedName("name") val name: String,
    )

    data class Project(
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String,
        @SerializedName("archived_at") val archived_at: String,
        @SerializedName("client") val client: Client
    )

    data class Client(
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String,
        @SerializedName("slug") val slug: String,
        @SerializedName("registration_name") val registration_name: String,
        @SerializedName("registration_id") val registration_id: Int,
        @SerializedName("description") val description: String,
        @SerializedName("allow_temper_trial") val allow_temper_trial: Boolean,
        @SerializedName("blocked_minutes_before_shift") val blocked_minutes_before_shift: String,
        @SerializedName("links") val links: Links,
        @SerializedName("average_response_time") val average_response_time: Double,
        @SerializedName("factoring_allowed") val factoring_allowed: Boolean,
        @SerializedName("factoring_payment_term_in_days") val factoring_payment_term_in_days: Int
    )

    data class Links(
        @SerializedName("hero_image") val hero_image: String,
        @SerializedName("thumb_image") val thumb_image: String,
    )
}
