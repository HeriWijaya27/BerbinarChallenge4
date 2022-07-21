package com.example.binarch4



enum class Hand {
    Batu,Gunting,Kertas;

    companion object {
        fun random(): Hand {
            return values()[(Math.random() * (values().size)).toInt()]
        }
    }
}