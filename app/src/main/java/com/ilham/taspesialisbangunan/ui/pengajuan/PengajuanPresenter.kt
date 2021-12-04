package com.ilham.taspesialisbangunan.ui.pengajuan

import com.ilham.taspesialisbangunan.data.model.aduanjasa.ResponseAduanInsert
import com.ilham.taspesialisbangunan.data.model.pengajuan.ResponsePengajuanInsert
import com.ilham.taspesialisbangunan.network.ApiConfig
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class PengajuanPresenter (val view: PengajuanContract.View) : PengajuanContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }

    override fun insertPengajuan(kd_jasa: String, kd_userpelanggan: String, gambar: File,
                                 deskripsi: String, status: String) {

        val requestBody: RequestBody = RequestBody.create(MediaType.parse("image/*"), gambar)
        val multipartBody: MultipartBody.Part? = MultipartBody. Part.createFormData("gambar",
            gambar.name, requestBody)

        view.onLoading(true)
        ApiConfig.endpoint.insertPengajuan(kd_jasa, kd_userpelanggan, multipartBody!!, deskripsi, status).enqueue(object: Callback<ResponsePengajuanInsert>{
            override fun onResponse(
                call: Call<ResponsePengajuanInsert>,
                response: Response<ResponsePengajuanInsert>
            ) {

                view.onLoading(true)
                if (response.isSuccessful) {
                    val responsePengajuanInsert: ResponsePengajuanInsert? = response.body()
                    view.onResultPengajuan(responsePengajuanInsert!!)
                }
            }

            override fun onFailure(call: Call<ResponsePengajuanInsert>, t: Throwable) {
                view.onLoading(true)
            }

        })
    }

}