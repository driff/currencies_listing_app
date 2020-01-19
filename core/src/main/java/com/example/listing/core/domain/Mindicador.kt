package com.example.listing.core.domain

import com.google.gson.annotations.SerializedName

data class Mindicador(
    @SerializedName("autor")
    val autor: String,
    @SerializedName("bitcoin")
    val bitcoin: Currency,
    @SerializedName("dolar")
    val dolar: Currency,
    @SerializedName("dolar_intercambio")
    val dolarIntercambio: Currency,
    @SerializedName("euro")
    val euro: Currency,
    @SerializedName("fecha")
    val fecha: String,
    @SerializedName("imacec")
    val imacec: Currency,
    @SerializedName("ipc")
    val ipc: Currency,
    @SerializedName("ivp")
    val ivp: Currency,
    @SerializedName("libra_cobre")
    val libraCobre: Currency,
    @SerializedName("tasa_desempleo")
    val tasaDesempleo: Currency,
    @SerializedName("tpm")
    val tpm: Currency,
    @SerializedName("uf")
    val uf: Currency,
    @SerializedName("utm")
    val utm: Currency,
    @SerializedName("version")
    val version: String
)