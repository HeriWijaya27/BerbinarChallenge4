package com.example.binarch4



enum class Choice {
    Rock,Paper,Scissor;

    companion object {
        fun random(): Choice {
            return values()[(Math.random() * (values().size)).toInt()]
        }
    }
}