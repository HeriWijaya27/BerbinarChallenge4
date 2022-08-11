package com.example.binarch4.modul



enum class Choice {
    ROCK,PAPER,SCISSOR;

    companion object {
        fun random(): Choice {
            return values()[(Math.random() * (values().size)).toInt()]
        }
    }
}