package com.uoa.telmaticsapp.domain.usecase

import com.uoa.telmaticsapp.data.model.SafetyTips
import com.uoa.telmaticsapp.domain.repository.SafeDrivingTipsRepository

class DeleteSafetyTips(private val safetyTips: SafeDrivingTipsRepository) {
    suspend fun execute(safetT: SafetyTips)=safetyTips.DeleteSafetyTips(safetT)
}