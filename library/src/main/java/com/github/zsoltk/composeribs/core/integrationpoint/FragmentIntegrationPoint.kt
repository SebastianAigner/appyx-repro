package com.github.zsoltk.composeribs.core.integrationpoint

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.zsoltk.composeribs.core.integrationpoint.activitystarter.ActivityBoundary
import com.github.zsoltk.composeribs.core.integrationpoint.activitystarter.ActivityStarter
import com.github.zsoltk.composeribs.core.integrationpoint.permissionrequester.PermissionRequestBoundary
import com.github.zsoltk.composeribs.core.integrationpoint.permissionrequester.PermissionRequester

open class FragmentIntegrationPoint(
    private val fragment: Fragment,
    savedInstanceState: Bundle?
) : IntegrationPoint(savedInstanceState = savedInstanceState) {
    private val activityBoundary = ActivityBoundary(fragment, requestCodeRegistry)
    private val permissionRequestBoundary = PermissionRequestBoundary(fragment, requestCodeRegistry)

    override val activityStarter: ActivityStarter
        get() = activityBoundary

    override val permissionRequester: PermissionRequester
        get() = permissionRequestBoundary


    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        activityBoundary.onActivityResult(requestCode, resultCode, data)
    }

    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        permissionRequestBoundary.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun handleUpNavigation() {
        val activity = fragment.requireActivity()
        if (!activity.onNavigateUp()) {
            activity.onBackPressed()
        }
    }
}