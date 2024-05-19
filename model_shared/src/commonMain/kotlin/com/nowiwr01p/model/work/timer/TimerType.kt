package com.nowiwr01p.model.work.timer

sealed class TimerType(val value: Long) {
    data class Up(val endValue: Long): TimerType(endValue)
    data class Down(val startValue: Long): TimerType(startValue)
}