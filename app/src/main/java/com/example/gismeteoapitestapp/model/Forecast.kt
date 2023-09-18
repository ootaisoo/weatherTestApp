package com.example.gismeteoapitestapp.model

import com.google.gson.annotations.SerializedName

data class Forecast(
    val kind: String,
    val date: Date,
    val temperature: Temperature,
    val description: Description,
    val humidity: Humidity,
    val pressure: Pressure,
    val cloudiness: Сloudiness,
    val storm: Storm,
    val precipitation: Precipitation,
    val phenomenon: Int?,
    val icon: String,
    val gm: Int,
    val wind: Wind,
)

data class Date(
    val utc: String,
    val unix: Int,
    val local: String,
    val timeZoneOffset: Int,
)

data class Temperature(
    val air: TemperatureType,
    val comfort: TemperatureType,
    val water: TemperatureType,
)

data class TemperatureType(@SerializedName("C") val celcius: Float?)

data class Description(val full: String)

data class Humidity(val percent: Int)

data class Pressure(@SerializedName("mm_hg_atm") val value: Int)

data class Сloudiness(
    val percent: Int,
    val type: Int
)

data class Storm(val prediction: Boolean)

data class Precipitation(
    val type: Int,
    val amount: Float?,
    val intensity: Int,
)

data class Wind(
    val direction: Direction,
    val speed: Speed,
)

data class Direction(
    val degree: Int,
    @SerializedName("scale_8") val scale: Int,
)

data class Speed(@SerializedName("m_s") val mS: Float)