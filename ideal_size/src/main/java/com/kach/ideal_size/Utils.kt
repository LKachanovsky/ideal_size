package com.kach.ideal_size

fun processSizeRecommendation(height: String, weight: String): Size? {
    if (height.isBlank() || height.isNotFloatNumber()) return null
    if (weight.isBlank() || weight.isNotFloatNumber()) return null

    if (height.toFloat() <= 0f) return null
    if (weight.toFloat() <= 0f) return null

    return calculateSizeRecommendation(height.toFloat(), weight.toFloat())
}

private fun calculateSizeRecommendation(height: Float, weight: Float): Size {
    val heightM2 = (height / 100) * (height / 100)
    return when (weight / heightM2) {
        in 0f..<18.5f -> Size.S
        in 18.5f..<25f -> Size.M
        in 25f..<30f -> Size.L
        else -> Size.XL
    }
}