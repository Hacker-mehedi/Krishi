package com.example.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OpenWeatherResponse(
    @Json(name = "name") val name: String = "",
    @Json(name = "main") val main: MainWeatherData? = null,
    @Json(name = "weather") val weather: List<WeatherDescription> = emptyList(),
    @Json(name = "wind") val wind: WindData? = null,
    @Json(name = "clouds") val clouds: CloudData? = null
)

@JsonClass(generateAdapter = true)
data class MainWeatherData(
    @Json(name = "temp") val temp: Double = 0.0,
    @Json(name = "feels_like") val feelsLike: Double = 0.0,
    @Json(name = "temp_min") val tempMin: Double = 0.0,
    @Json(name = "temp_max") val tempMax: Double = 0.0,
    @Json(name = "humidity") val humidity: Int = 0,
    @Json(name = "pressure") val pressure: Int = 0
)

@JsonClass(generateAdapter = true)
data class WeatherDescription(
    @Json(name = "id") val id: Int = 800,
    @Json(name = "main") val main: String = "",
    @Json(name = "description") val description: String = "",
    @Json(name = "icon") val icon: String = "01d"
)

@JsonClass(generateAdapter = true)
data class WindData(
    @Json(name = "speed") val speed: Double = 0.0,
    @Json(name = "deg") val deg: Int = 0
)

@JsonClass(generateAdapter = true)
data class CloudData(
    @Json(name = "all") val all: Int = 0
)

data class KrishiWeather(
    val cityName: String,
    val cityNameBn: String,
    val tempCelsius: Int,
    val conditionBn: String,
    val conditionEn: String,
    val humidityPercent: Int,
    val windSpeedKmh: Int,
    val rainProbabilityPercent: Int,
    val farmingAdvisoryBn: String,
    val iconCode: String
)
