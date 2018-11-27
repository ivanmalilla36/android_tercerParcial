package com.example.egghunt3r.tercerparcial

data class VehiculoResult(val vehiculos: List<Item>)  // favoristos es la llave por la cual va a buscar en el arreglo de json

data class Item (
    val id: String?,
    val Marca: String?,
    val Modelo: String?,
    val Año: String?,
    val Deuda: String?,
    val url: String?
)

