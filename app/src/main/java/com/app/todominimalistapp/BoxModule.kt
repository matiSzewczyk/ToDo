package com.app.todominimalistapp

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.objectbox.Box
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BoxModule {

    @Singleton
    @Provides
    fun provideBox(): Box<TaskOB> {
        return ObjectBox.store.boxFor(TaskOB::class.java)
    }
}