package com.example.gismeteoapitestapp.model

import com.google.gson.annotations.SerializedName


data class Forecast(
    @SerializedName("meta"     ) var meta     : Meta?     = Meta(),
    @SerializedName("response" ) var response : ForecastResponse? = ForecastResponse()
)

data class Precipitation (

    @SerializedName("type_ext"   ) var typeExt    : String? = null,
    @SerializedName("intensity"  ) var intensity  : Int?    = null,
    @SerializedName("correction" ) var correction : String? = null,
    @SerializedName("amount"     ) var amount     : Int?    = null,
    @SerializedName("duration"   ) var duration   : Int?    = null,
    @SerializedName("type"       ) var type       : Int?    = null

)

data class Pressure (

    @SerializedName("h_pa"      ) var hPa     : Int?    = null,
    @SerializedName("mm_hg_atm" ) var mmHgAtm : Int?    = null,
    @SerializedName("in_hg"     ) var inHg    : Double? = null

)

data class Humidity (

    @SerializedName("percent" ) var percent : Int? = null

)



data class Direction (

    @SerializedName("degree"  ) var degree : Int? = null,
    @SerializedName("scale_8" ) var scale8 : Int? = null

)


data class Speed (

    @SerializedName("km_h" ) var kmH : Int? = null,
    @SerializedName("m_s"  ) var mS  : Int? = null,
    @SerializedName("mi_h" ) var miH : Int? = null

)

data class Wind (

    @SerializedName("direction" ) var direction : Direction? = Direction(),
    @SerializedName("speed"     ) var speed     : Speed?     = Speed()

)

data class Cloudiness (

    @SerializedName("type"    ) var type    : Int? = null,
    @SerializedName("percent" ) var percent : Int? = null

)


data class Date (

    @SerializedName("UTC"              ) var UTC            : String? = null,
    @SerializedName("local"            ) var local          : String? = null,
    @SerializedName("time_zone_offset" ) var timeZoneOffset : Int?    = null,
    @SerializedName("hr_to_forecast"   ) var hrToForecast   : String? = null,
    @SerializedName("unix"             ) var unix           : Int?    = null

)

data class Radiation (

    @SerializedName("uvb_index" ) var uvbIndex : String? = null,
    @SerializedName("UVB"       ) var UVB      : String? = null

)

data class Comfort (

    @SerializedName("C" ) var C : Double? = null,
    @SerializedName("F" ) var F : Double? = null

)

data class Water (

    @SerializedName("C" ) var C : Double? = null,
    @SerializedName("F" ) var F : Double? = null

)


data class Air (

    @SerializedName("C" ) var C : Double? = null,
    @SerializedName("F" ) var F : Double? = null

)

data class Temperature (

    @SerializedName("comfort" ) var comfort : Comfort? = Comfort(),
    @SerializedName("water"   ) var water   : Water?   = Water(),
    @SerializedName("air"     ) var air     : Air?     = Air()

)


data class Description (

    @SerializedName("full" ) var full : String? = null

)

data class ForecastResponse (

    @SerializedName("precipitation" ) var precipitation : Precipitation? = Precipitation(),
    @SerializedName("pressure"      ) var pressure      : Pressure?      = Pressure(),
    @SerializedName("humidity"      ) var humidity      : Humidity?      = Humidity(),
    @SerializedName("icon"          ) var icon          : String?        = null,
    @SerializedName("gm"            ) var gm            : Int?           = null,
    @SerializedName("wind"          ) var wind          : Wind?          = Wind(),
    @SerializedName("cloudiness"    ) var cloudiness    : Cloudiness?    = Cloudiness(),
    @SerializedName("date"          ) var date          : Date?          = Date(),
    @SerializedName("radiation"     ) var radiation     : Radiation?     = Radiation(),
    @SerializedName("city"          ) var city          : Int?           = null,
    @SerializedName("kind"          ) var kind          : String?        = null,
    @SerializedName("storm"         ) var storm         : Boolean?       = null,
    @SerializedName("temperature"   ) var temperature   : Temperature?   = Temperature(),
    @SerializedName("description"   ) var description   : Description?   = Description()

)