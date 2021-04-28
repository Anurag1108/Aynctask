package com.example.customasynctask.framework.executors

import com.example.customasynctask.framework.threads.MyThreadFactory
import java.util.concurrent.*

object ExecutorProvider {
    private val NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors()
    private var backgroundTasksThreadFactory: ThreadFactory = MyThreadFactory()

    var backgroundTaskExecutor: ThreadPoolExecutor? = null
    var mainThreadTaskExecutor: Executor? = null

    init {

        backgroundTaskExecutor = ThreadPoolExecutor(
            NUMBER_OF_CORES * 2,
            NUMBER_OF_CORES * 2,
            60L,
            TimeUnit.SECONDS,
            LinkedBlockingDeque<Runnable>(),
            backgroundTasksThreadFactory
        )
        mainThreadTaskExecutor = MainThreadExecutor()
    }
}