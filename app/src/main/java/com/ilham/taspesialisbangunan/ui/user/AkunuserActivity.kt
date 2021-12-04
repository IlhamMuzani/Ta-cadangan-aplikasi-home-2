package com.ilham.taspesialisbangunan.ui.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ilham.taspesialisbangunan.R
import com.ilham.taspesialisbangunan.data.database.PrefsManageruser
import com.ilham.taspesialisbangunan.data.model.Constant
import com.ilham.taspesialisbangunan.ui.user.updateprofil.UbahProfilActivity
import kotlinx.android.synthetic.main.activity_akunuser.*

class AkunuserActivity : AppCompatActivity(), AkunuserContract.View {

    lateinit var prefsManageruser: PrefsManageruser
    lateinit var presenter: AkunuserPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_akunuser)
        prefsManageruser = PrefsManageruser(this)
        presenter = AkunuserPresenter(this)
        presenter.doLogin(prefsManageruser)
    }

    override fun initActivity() {
        supportActionBar!!.hide()
    }

    override fun initListener() {
        btn_ubahProfil.setOnClickListener{
            Constant.USERPELANGGAN_ID = prefsManageruser.prefsId.toLong()
            startActivity(Intent(this, UbahProfilActivity::class.java))
        }
        txvLogoutuser.setOnClickListener {
            presenter.doLogout(prefsManageruser)
        }
    }

    override fun onResultLogin(prefsManageruser: PrefsManageruser) {
        txvAkunuser.text = prefsManageruser.prefsUsername
        txvEmailuser.text = prefsManageruser.prefsEmail
        txvAlamatuser.text = prefsManageruser.prefsAlamat
        txvPhoneuser.text = prefsManageruser.prefsPhone

    }

    override fun onResultLogout() {
        finish()
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}