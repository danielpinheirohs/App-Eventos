package com.eventosappteste.appeventos.Model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Evento (
    @SerializedName("date")
    @Expose
    val date: Long? = null,

    @SerializedName("description")
    @Expose
    val description: String? = null,

    @SerializedName("image")
    @Expose
    val image: String? = null,

    @SerializedName("longitude")
    @Expose
    val longitude: Double? = null,

    @SerializedName("latitude")
    @Expose
    val latitude: Double? = null,

    @SerializedName("price")
    @Expose
    val price: Double? = null,

    @SerializedName("title")
    @Expose
    val title: String? = null,

    @SerializedName("id")
    @Expose
    val id: String? = null,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(date)
        parcel.writeString(description)
        parcel.writeString(image)
        parcel.writeValue(longitude)
        parcel.writeValue(latitude)
        parcel.writeValue(price)
        parcel.writeString(title)
        parcel.writeString(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Evento> {
        override fun createFromParcel(parcel: Parcel): Evento {
            return Evento(parcel)
        }

        override fun newArray(size: Int): Array<Evento?> {
            return arrayOfNulls(size)
        }
    }
}