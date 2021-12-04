package com.ilham.taspesialisbangunan.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ilham.taspesialisbangunan.R
import com.ilham.taspesialisbangunan.data.model.register.ResponseModel
import com.ilham.taspesialisbangunan.network.ApiConfig
import com.ilham.taspesialisbangunan.ui.loginjasa.LoginjasaActivity
import com.ilham.taspesialisbangunan.ui.loginuser.LoginuserActivity
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register_pelanggan.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterPelangganActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_pelanggan)
        supportActionBar!!.title = "Register"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        btn_registeruser.setOnClickListener {
            registeruser()
        }
//        btn_googleuser.setOnClickListener {
//            dataDUmmyuser()
//        }
    }


//    fun dataDUmmyuser() {
//        edt_usernameuser.setText("ilhammzni")
//        edt_emailuser.setText("ilhammuzani5@gmail.com")
//        edt_alamatuser.setText("Tegal")
//        edt_phoneuser.setText("089087546765")
//        edt_passworduser.setText("ilham123")
//    }


    fun registeruser() {
        if (edt_usernameuser.text.isEmpty()) {
            edt_usernameuser.error = "Kolom Nama Tidak Boleh Kosong"
            edt_usernameuser.requestFocus()
            return
        } else if (edt_emailuser.text.isEmpty()) {
            edt_emailuser.error = "Kolom Email Tidak Boleh Kosong"
            edt_emailuser.requestFocus()
            return
        } else if (edt_alamatuser.text.isEmpty()) {
            edt_alamatuser.error = "Kolom Email Tidak Boleh Kosong"
            edt_alamatuser.requestFocus()
            return
        } else if (edt_phoneuser.text.isEmpty()) {
            edt_phoneuser.error = "Kolom Nomor Telpone Tidak Boleh Kosong"
            edt_phoneuser.requestFocus()
            return
        } else if (edt_passworduser.text.isEmpty()) {
            edt_passworduser.error = "Kolom Password Tidak Boleh Kosong"
            edt_passworduser.requestFocus()
            return
        }
        progresbar.visibility = View.VISIBLE
        ApiConfig.endpoint.registerpelanggan(edt_usernameuser.text.toString(), edt_emailuser.text.toString(), edt_alamatuser.text.toString(),
        edt_phoneuser.text.toString(), edt_passworduser.text.toString()).enqueue(object :Callback<ResponseModel>{
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                progresbar.visibility = View.VISIBLE
                val respon = response.body()!!

                if (respon.succes == 1) {
                    val intent = Intent(this@RegisterPelangganActivity, LoginuserActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@RegisterPelangganActivity, "Succes:"+respon.message, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@RegisterPelangganActivity, "Error:"+respon.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                progresbar.visibility = View.VISIBLE
                Toast.makeText(this@RegisterPelangganActivity, "Error:"+t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}