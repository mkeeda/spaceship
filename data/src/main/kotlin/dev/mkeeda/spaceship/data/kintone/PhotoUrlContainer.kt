package dev.mkeeda.spaceship.data.kintone

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoUrlContainer(
    val small: String,
    val normal: String,
    val original: String,

    @SerialName("size_24") val size24: String,
    @SerialName("size_32") val size32: String,
    @SerialName("size_40") val size40: String,
    @SerialName("size_48") val size48: String,
    @SerialName("size_56") val size56: String,

    @SerialName("original_r") val originalResponsive: String,
    @SerialName("size_48_r") val size48Responsive: String,
    @SerialName("size_96_r") val size96Responsive: String,
)
