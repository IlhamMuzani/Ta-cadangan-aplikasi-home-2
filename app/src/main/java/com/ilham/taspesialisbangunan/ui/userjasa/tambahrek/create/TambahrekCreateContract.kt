package com.ilham.taspesialisbangunan.ui.userjasa.tambahrek.create

import com.ilham.taspesialisbangunan.data.model.material.ResponseMaterialUpdate
import com.ilham.taspesialisbangunan.data.model.produk.ResponseProdukUpdate
import com.ilham.taspesialisbangunan.data.model.tambahrek.ResponseTambahrekUpdate
import java.io.File

interface TambahrekCreateContract {

    interface Presenter {
        fun insertTambahrek(jasausers_id: String, jenis_bank: String, norek: String, nama: String)
    }


    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResult(responseTambahrekUpdate: ResponseTambahrekUpdate)
        fun showMessage(message: String)
    }
}