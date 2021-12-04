package com.ilham.taspesialisbangunan.ui.homeuser

class UserPresenter(val view : UserContract.View) {

    init {
        view.initListener()
    }
}