package com.uoa.telmaticsapp.domain.usecase

import com.uoa.telmaticsapp.data.model.SafetyTips
import com.uoa.telmaticsapp.domain.repository.SafeDrivingTipsRepository

class RetrieveSafeDrivingTips(private val safetyTips: SafeDrivingTipsRepository) {
    fun execute(safetyT: SafetyTips)=safetyTips.getSafetyTips(safetyT)
}