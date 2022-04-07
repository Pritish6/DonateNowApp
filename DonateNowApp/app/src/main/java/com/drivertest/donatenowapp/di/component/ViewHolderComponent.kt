package com.drivertest.donatenowapp.di.component


import com.drivertest.donatenowapp.di.ViewModelScope
import com.drivertest.donatenowapp.di.module.ViewHolderModule
import dagger.Component

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {

  //  fun inject(viewHolder: DummyItemViewHolder)

   // fun inject(viewHolder: PostItemViewHolder)
}