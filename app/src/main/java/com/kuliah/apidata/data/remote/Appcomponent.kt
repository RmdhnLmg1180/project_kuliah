package com.kuliah.apidata.data.remote

@Component(modules = [ApiModule::class])
@Singleton
interface AppComponent {
    fun inject(homeActivity: HomeActivity)
}
