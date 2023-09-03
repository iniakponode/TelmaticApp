package com.uoa.telmaticsapp.domain.repository

import com.uoa.telmaticsapp.data.model.SafetyTips
import kotlinx.coroutines.flow.Flow

interface SafeDrivingTipsRepository {
    fun getSafetyTips(safetyTips: SafetyTips): Flow<List<SafetyTips>>
    suspend fun saveSafetyTips(safetyTips: SafetyTips)
    suspend fun DeleteSafetyTips(safetyTips: SafetyTips)
}