package com.drivertest.donatenowapp.di.module

import androidx.lifecycle.LifecycleRegistry
import com.drivertest.donatenowapp.di.ViewModelScope

import com.mindorks.bootcamp.instagram.ui.base.BaseItemViewHolder
import dagger.Module
import dagger.Provides

@Module
class ViewHolderModule(private val viewHolder: BaseItemViewHolder<*, *>) {

    @Provides
    @ViewModelScope
    fun provideLifecycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewHolder)
}