package com.timmytruong.library.extension

internal fun <T> Array<T>.reverseWithIndices(start: Int, end: Int): Array<T> {
    if (start >= end || isEmpty() || start > size || end > size || start < 0) return this
    var startIdx = start
    var endIdx = end
    while (startIdx < endIdx) {
        val temp = get(endIdx)
        this[endIdx--] = this[startIdx]
        this[startIdx++] = temp
    }
    return this
}
