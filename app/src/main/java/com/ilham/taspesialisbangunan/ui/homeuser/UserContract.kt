package com.ilham.taspesialisbangunan.ui.homeuser

interface UserContract {

    interface View{
        fun initListener()
        fun showMessage(message: String)
    }
}