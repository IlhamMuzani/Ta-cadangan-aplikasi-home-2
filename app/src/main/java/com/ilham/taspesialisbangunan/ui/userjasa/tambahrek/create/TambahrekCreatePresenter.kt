package com.ilham.taspesialisbangunan.ui.userjasa.tambahrek.create

import com.ilham.taspesialisbangunan.data.model.material.ResponseMaterialUpdate
import com.ilham.taspesialisbangunan.data.model.produk.ResponseProdukUpdate
import com.ilham.taspesialisbangunan.data.model.tambahrek.ResponseTambahrekUpdate
import com.ilham.taspesialisbangunan.network.ApiConfig
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class TambahrekCreatePresenter (val view: TambahrekCreateContract.View) : TambahrekCreateContract.Presenter{

    init {
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }


    override fun insertTambahrek(
        jasausers_id: String,
        jenis_bank: String,
        norek: String,
        nama: String
    ) {

        view.onLoading(true)
        ApiConfig.endpoint.insertTambahrek(
            jasausers_id,
            jenis_bank,
            norek,
            nama
        )
            .enqueue(object : Callback<ResponseTambahrekUpdate>{
                override fun onResponse(
                    call: Call<ResponseTambahrekUpdate>,
                    response: Response<ResponseTambahrekUpdate>
                ) {
                    view.onLoading(false)
                    if (response.isSuccessful){
                        val responseTambahrekUpdate: ResponseTambahrekUpdate? = response.body()
                        view.onResult( responseTambahrekUpdate!! )
                    }
                }

                override fun onFailure(call: Call<ResponseTambahrekUpdate>, t: Throwable) {
                    view.onLoading(false)
                }

            })
    }

}