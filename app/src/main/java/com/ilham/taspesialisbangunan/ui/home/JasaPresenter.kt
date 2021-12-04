package com.ilham.taspesialisbangunan.ui.home

class JasaPresenter(val view : JasaContract.View) {

    init {
        view.initListener()
    }
}