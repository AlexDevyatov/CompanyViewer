package com.alexdevyatov.testtask.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetModule::class])
interface AppComponent {
}