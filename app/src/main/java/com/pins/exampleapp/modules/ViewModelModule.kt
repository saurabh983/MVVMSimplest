package com.woo.groupchat.modules

import com.woo.groupchat.viewmodels.addmember.GetUsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { GetUsersViewModel(get()) }
//    viewModel { PostFoodViewModel(get()) }
//    viewModel { AccountViewModel(get()) }
//    viewModel { PaymentViewModel(get()) }
//    viewModel { FoodMenuViewModel(get())}
//    viewModel { OrderCartViewModel(get()) }
}