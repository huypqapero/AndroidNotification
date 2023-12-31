package com.kohuyn.notification.notify.internal.utils

import androidx.annotation.IntDef
import com.kohuyn.notification.notify.Notify

/**
 * Denotes features which are considered experimental and are subject to change without notice.
 */
annotation class Experimental

@DslMarker
annotation class NotifyScopeMarker

@Retention(AnnotationRetention.SOURCE)
@IntDef(Notify.IMPORTANCE_MIN,
        Notify.IMPORTANCE_LOW,
        Notify.IMPORTANCE_NORMAL,
        Notify.IMPORTANCE_HIGH,
        Notify.IMPORTANCE_MAX)
annotation class NotifyImportance
