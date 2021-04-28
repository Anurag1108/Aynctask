package com.example.customasynctask.framework.threads

import java.util.concurrent.ThreadFactory

class MyThreadFactory : ThreadFactory {
    override fun newThread(runnable: Runnable?) = Thread(runnable)
}