package com.bluelzy.bluewanandroid.utils

/**
 *   @author : BlueLzy
 *   @email  : bluehobert@gmail.com
 *   @date   : 2020/05/17
 *   @desc   :
 */
@Target(
    AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY,
    AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER
)
@DslMarker
@Retention(AnnotationRetention.BINARY)
internal annotation class WhatIfInlineOnly