package com.eddie.composeplayground.utils

import androidx.lifecycle.LifecycleObserver

interface ConnectionUtil: LifecycleObserver {

    val isOnline: Boolean
}