package com.woo.groupchat.modules

import com.woo.groupchat.viewmodels.addmember.GetUsersRepository
import org.koin.dsl.module

val repositoryModules = module {
    single { GetUsersRepository(get()) }
//    single { SignupRepository(get()) }
}