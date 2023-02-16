/* Copyright Urban Airship and Contributors */

package com.urbanairship.reactnative

import android.content.Context
import com.facebook.react.module.annotations.ReactModule
import com.urbanairship.AirshipConfigOptions
import com.urbanairship.android.framework.proxy.BaseNotificationProvider
import com.urbanairship.push.notifications.NotificationArguments
import com.urbanairship.push.notifications.NotificationResult
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.runBlocking

/**
 * React Native notification provider.
 */
// Empty to prevent breaking existing integrations
open class ReactNotificationProvider(context: Context, configOptions: AirshipConfigOptions)
    : BaseNotificationProvider(context, configOptions) {


    interface ForegroundNotificationDisplayListener {
        suspend fun shouldDisplayInForeground(message: Map<String, Any>): Boolean
    }

    var listener: ForegroundNotificationDisplayListener? = null



    override fun onCreateNotification(context: Context, arguments: NotificationArguments): NotificationResult {
        val result = listener?.let { listener ->
            runBlocking {
                listener.shouldDisplayInForeground(com.urbanairship.android.framework.proxy.Utils.notificationMap(arguments.message))
            }
        } ?: true

        if (!result) {
            return NotificationResult.cancel()
        }

        return super.onCreateNotification(context, arguments)
    }
}