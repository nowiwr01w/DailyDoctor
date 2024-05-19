package com.nowiwr01p.model.work.timer

sealed class TimerType(val value: Int) {
    data class Up(val endValue: Int): TimerType(endValue)
    data class Down(val startValue: Int): TimerType(startValue)
}