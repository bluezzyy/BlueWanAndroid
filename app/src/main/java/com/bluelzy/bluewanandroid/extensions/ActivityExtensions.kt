package com.bluelzy.bluewanandroid.extensions

import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialContainerTransformSharedElementCallback

fun getContentTransform(): MaterialContainerTransform {
    return MaterialContainerTransform().apply {
        addTarget(android.R.id.content)
        duration = 300
        pathMotion = MaterialArcMotion()
    }
}

fun AppCompatActivity.applyExitMaterialTransform() {
    window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
    setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
    window.sharedElementsUseOverlay = false
}

fun AppCompatActivity.applyMaterialTransform(transitionName: String) {
    window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
    ViewCompat.setTransitionName(findViewById(android.R.id.content), transitionName)

    setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
    setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
    window.sharedElementEnterTransition = getContentTransform()
    window.sharedElementReturnTransition = getContentTransform()
}
