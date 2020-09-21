package com.mittas.moviebrowser.domain.usecases

import io.reactivex.rxjava3.core.Scheduler

abstract class BaseRxUseCase {
    abstract val workScheduler: Scheduler
    abstract val postScheduler: Scheduler
}
