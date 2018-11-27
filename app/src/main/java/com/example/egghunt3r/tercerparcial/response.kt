package com.example.egghunt3r.tercerparcial

data class VehiculoResult(val vehiculo: List<Item>)
data class Item (
    val id: String?,
    val Marca: String?,
    val Modelo: String?,
    val Año: String?,
    val Deuda: String?,
    val url: String?
)

