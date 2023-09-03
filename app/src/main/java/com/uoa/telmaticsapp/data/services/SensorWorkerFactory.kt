//package com.uoa.telmaticsapp.data.services
//
//import android.content.Context
//import androidx.work.ListenableWorker
//import androidx.work.Worker
//import androidx.work.WorkerFactory
//import androidx.work.WorkerParameters
//import javax.inject.Inject
//import javax.inject.Provider
//
//class SensorWorkerFactory @Inject constructor(
//    val workerFactories: Map<Class<out Worker>, @JvmSuppressWildcards Provider<ChildWorkerFactory>>
//) : WorkerFactory() {
//
//    override fun createWorker(
//        appContext: Context,
//        workerClassName: String,
//        workerParameters: WorkerParameters
//    ): ListenableWorker? {
//
//        val workerFactoryProvider = //Iterate over available workers to get MyWorker factory
//
//            return workerFactoryProvider.get().create(appContext, workerParameters)
//
//    }
//}