package com.globe.tipcalculator


class TipCalculator {

    var tip: Double = 0.0
    var total: Double = 0.0

    fun computeTip(amount: Double, percentage:Int) {

        tip = amount * (percentage / 100.0)
        total = amount + tip

    }
}