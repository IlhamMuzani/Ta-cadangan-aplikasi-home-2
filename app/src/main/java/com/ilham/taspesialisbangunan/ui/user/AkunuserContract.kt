package com.ilham.taspesialisbangunan.ui.user

import com.ilham.taspesialisbangunan.data.database.PrefsManageruser
import com.ilham.taspesialisbangunan.data.model.loginuser.DataLoginuser
import com.ilham.taspesialisbangunan.data.model.userpelanggan.DataPelanggan

interface AkunuserContract {

    interface Presenter {
        fun doLogin(prefsManageruser: PrefsManageruser)
        fun doLogout(prefsManageruser: PrefsManageruser)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onResultLogin(prefsManageruser: PrefsManageruser)
        fun onResultLogout()
        fun showMessage(message: String)
    }

}