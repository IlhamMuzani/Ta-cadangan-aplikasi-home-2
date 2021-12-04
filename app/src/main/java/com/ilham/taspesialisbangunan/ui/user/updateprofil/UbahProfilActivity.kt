package com.ilham.taspesialisbangunan.ui.user.updateprofil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ilham.taspesialisbangunan.R
import com.ilham.taspesialisbangunan.data.database.PrefsManageruser
import com.ilham.taspesialisbangunan.data.model.Constant
import com.ilham.taspesialisbangunan.data.model.userpelanggan.ResponsePelangganUpdate
import com.ilham.taspesialisbangunan.data.model.userpelanggan.ResponsePelanggandetail
import kotlinx.android.synthetic.main.activity_ubah_profil.*

class UbahProfilActivity : AppCompatActivity(), UbahProfilContract.View {

    lateinit var presenter: UbahProfilPresenter
    lateinit var prefsManageruser: PrefsManageruser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubah_profil)
        presenter = UbahProfilPresenter(this)
        prefsManageruser = PrefsManageruser(this)

    }

    override fun onStart() {
        super.onStart()
        presenter.getDetailProfil( Constant.USERPELANGGAN_ID )
    }

    override fun initActivity() {
        supportActionBar!!.title = "Perbarui profil"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun initListener() {
        btn_ubah.setOnClickListener {
            val username = edt_ubahusername.text
            val email = edt_ubahemaiil.text
            val alamat = edt_ubahalamat.text
            val phone = edt_ubahphone.text

            if ( username.isNullOrEmpty() || email.isNullOrEmpty() || alamat.isNullOrEmpty() || phone.isNullOrEmpty()) {
                showMessage("Lengkapi Data Benar")
            } else {
                presenter.updateProfil(Constant.USERPELANGGAN_ID, username.toString(), email.toString(), alamat.toString(), phone.toString())
            }
        }
    }

    override fun onLoading(loading: Boolean) {
        when(loading){
            true -> {
                progressubah.visibility = View.VISIBLE
                btn_ubah.visibility = View.GONE
            }
            false -> {
                progressubah.visibility = View.GONE
                btn_ubah.visibility = View.VISIBLE
            }
        }
    }

    override fun onResultDetailProfil(responsePelanggandetail: ResponsePelanggandetail) {
        val pelanggan = responsePelanggandetail.dataPelanggan

        edt_ubahusername.setText( pelanggan.username )
        edt_ubahemaiil.setText( pelanggan.email )
        edt_ubahalamat.setText( pelanggan.alamat )
        edt_ubahphone.setText( pelanggan.phone )

    }

    override fun onResultUpdateProfil(responsePelangganUpdate: ResponsePelangganUpdate) {
        showMessage(responsePelangganUpdate.msg)
//        presenter.setPrefs(prefsManageruser, responsePelangganUpdate)
        finish()
//        onBackPressed()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}