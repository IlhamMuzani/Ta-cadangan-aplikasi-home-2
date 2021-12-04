package com.ilham.taspesialisbangunan.ui.homeuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ilham.taspesialisbangunan.R
import com.ilham.taspesialisbangunan.data.database.PrefsManageruser
import com.ilham.taspesialisbangunan.ui.Aduanjasa.ReportActivity
import com.ilham.taspesialisbangunan.ui.loginuser.LoginuserActivity
import com.ilham.taspesialisbangunan.ui.notifpelanggan.NotifikasiPelangganActivity
import com.ilham.taspesialisbangunan.ui.user.AkunuserActivity
import com.ilham.taspesialisjasabangunan.ui.produkuser.MatrialprodukActivity
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity(), UserContract.View {

    lateinit var prefsManageruser: PrefsManageruser
    lateinit var presenter: UserPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        prefsManageruser = PrefsManageruser(this)
        presenter = UserPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        when (prefsManageruser.prefsisLoginuser) {
            true -> {
                crv_profiluser.visibility = View.VISIBLE
                btn_loginuser.visibility = View.GONE
            }
            false -> {
                crv_profiluser.visibility = View.GONE
                btn_loginuser.visibility = View.VISIBLE
            }
        }
    }

    override fun initListener() {

//        crv_lihatMaterial.setOnClickListener {
//            if (prefsManageruser.prefsisLoginuser) {
//                startActivity(Intent(this, MaterialuserActivity::class.java))
//            } else {
//                showMessage("Silakan Login Terlebih Dahulu")
//            }
//        }

        crv_lihatproduk.setOnClickListener {
            if (prefsManageruser.prefsisLoginuser) {
                startActivity(Intent(this, MatrialprodukActivity::class.java))
            } else {
                showMessage("Silakan Login Terlebih Dahulu")
            }
        }

        crv_Laporkan.setOnClickListener {
            if (prefsManageruser.prefsisLoginuser) {
                startActivity(Intent(this, ReportActivity::class.java))
            } else {
                showMessage("Silakan Login Terlebih Dahulu")
            }
        }

        crv_profiluser.setOnClickListener {
            if (prefsManageruser.prefsisLoginuser) {
                startActivity(Intent(this, AkunuserActivity::class.java))
            } else {
                showMessage("Silakan Login Terlebih Dahulu")
            }
        }

            crv_notifpelanggan.setOnClickListener {
                if (prefsManageruser.prefsisLoginuser) {
                    startActivity(Intent(this, NotifikasiPelangganActivity::class.java))
                } else {
                    showMessage("Silakan Login Terlebih Dahulu")
                }
        }

        btn_loginuser.setOnClickListener{
            startActivity(Intent(this, LoginuserActivity::class.java))
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}