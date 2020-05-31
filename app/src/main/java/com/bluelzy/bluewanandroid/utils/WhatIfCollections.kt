package com.bluelzy.bluewanandroid.utils

/**
 *   @author : BlueLzy
 *   @email  : bluehobert@gmail.com
 *   @date   : 2020/05/17
 *   @desc   : The Functions for judging if the value is Empty or Null.
 */

@WhatIfInlineOnly
inline fun <T> T?.whatIfNotNull(
    whatIf: (T) -> Unit
) {

    this.whatIfNotNull(
        whatIf = { whatIf(it) },
        whatIfNot = { }
    )
}

@WhatIfInlineOnly
inline fun <T> T?.whatIfNotNull(
    whatIf: (T) -> Unit,
    whatIfNot: (T?) -> Unit
) {

    if (this != null) {
        whatIf(this)
    } else {
        whatIfNot(this)
    }
}

@WhatIfInlineOnly
inline fun <CharSequence> CharSequence?.whatIfNotNullAndEmpty(
    whatIf: (CharSequence) -> Unit,
    whatIfNot: (CharSequence?) -> Unit
) {
    if (this != null && this != "" && this != "null") {
        whatIf(this)
    } else {
        whatIfNot(this)
    }
}

@WhatIfInlineOnly
inline fun <T> T?.whatIfNull(
    defaultValue: () -> T
): T {
    return this ?: defaultValue()
}

@WhatIfInlineOnly
inline fun <T> List<T>?.whatIfNotNullOrEmpty(
    whatIf: (List<T>) -> Unit
) {
    this.whatIfNotNullOrEmpty(
        whatIf = { whatIf(it) },
        whatIfNot = {}
    )
}

@WhatIfInlineOnly
inline fun <T> List<T>?.whatIfNotNullOrEmpty(
    whatIf: (List<T>) -> Unit,
    whatIfNot: () -> Unit
) {
    if (!this.isNullOrEmpty()) {
        whatIf(this)
    } else {
        whatIfNot()
    }
}

@WhatIfInlineOnly
inline fun <T> Set<T>?.whatIfNotNullOrEmpty(
    whatIf: (Set<T>) -> Unit
) {
    this.whatIfNotNullOrEmpty(
        whatIf = { whatIf(it) },
        whatIfNot = {}
    )
}

@WhatIfInlineOnly
inline fun <T> Set<T>?.whatIfNotNullOrEmpty(
    whatIf: (Set<T>) -> Unit,
    whatIfNot: () -> Unit
) {
    if (!this.isNullOrEmpty()) {
        whatIf(this)
    } else {
        whatIfNot()
    }
}

@WhatIfInlineOnly
inline fun <T, R> Map<T, R>?.whatIfNotNullOrEmpty(
    whatIf: (Map<T, R>) -> Unit
) {

    this.whatIfNotNullOrEmpty(
        whatIf = { whatIf(it) },
        whatIfNot = { }
    )
}

@WhatIfInlineOnly
inline fun <T, R> Map<T, R>?.whatIfNotNullOrEmpty(
    whatIf: (Map<T, R>) -> Unit,
    whatIfNot: () -> Unit
) {

    if (!this.isNullOrEmpty()) {
        whatIf(this)
    } else {
        whatIfNot()
    }
}
