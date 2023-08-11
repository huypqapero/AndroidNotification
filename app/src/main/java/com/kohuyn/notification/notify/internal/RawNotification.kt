package com.kohuyn.notification.notify.internal

import com.kohuyn.notification.notify.entities.Payload
import com.kohuyn.notification.notify.internal.utils.Action

internal data class RawNotification(
    internal val meta: Payload.Meta,
    internal val alerting: Payload.Alerts,
    internal val header: Payload.Header,
    internal val content: Payload.Content,
    internal val bubblize: Payload.Bubble?,
    internal val stackable: Payload.Stackable?,
    internal val actions: ArrayList<Action>?,
    internal val progress: Payload.Progress
)
