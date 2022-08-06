package com.example.binarch4.modul



enum class Choice {
    Batu,Kertas,Gunting;

    companion object {
        fun random(): Choice {
            return values()[(Math.random() * (values().size)).toInt()]
        }
    }
}