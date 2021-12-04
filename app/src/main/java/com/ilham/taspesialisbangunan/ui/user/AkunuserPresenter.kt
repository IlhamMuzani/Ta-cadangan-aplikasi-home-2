package com.ilham.taspesialisbangunan.ui.user

import com.ilham.taspesialisbangunan.data.database.PrefsManageruser
import com.ilham.taspesialisbangunan.data.model.loginuser.DataLoginuser
import com.ilham.taspesialisbangunan.data.model.userpelanggan.DataPelanggan

class AkunuserPresenter (val view:AkunuserContract.View): AkunuserContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override fun doLogin(prefsManageruser: PrefsManageruser) {
        if (prefsManageruser.prefsisLoginuser) view.onResultLogin(prefsManageruser)
    }

    override fun doLogout(prefsManageruser: PrefsManageruser) {
        prefsManageruser.logout()
        view.showMessage("Berhasil Keluar")
        view.onResultLogout()
    }

}