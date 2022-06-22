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

internal fun <T> List<T>.to2DList(columns: Int): List<List<T>> {
    val result = mutableListOf<List<T>>()
    val temp = mutableListOf<T>()

    forEach {
        if (temp.size == columns) {
            result.add(temp.toList())
            temp.clear()
        }
        temp.add(it)
    }

    result.add(temp)

    return result
}