package com.ilham.taspesialisbangunan.ui.userjasa.tambahrek.update

import com.ilham.taspesialisbangunan.data.model.material.DataMaterial
import com.ilham.taspesialisbangunan.data.model.material.ResponseMaterialDetail
import com.ilham.taspesialisbangunan.data.model.material.ResponseMaterialList
import com.ilham.taspesialisbangunan.data.model.material.ResponseMaterialUpdate
import com.ilham.taspesialisbangunan.data.model.tambahrek.DataTambahrek
import com.ilham.taspesialisbangunan.data.model.tambahrek.ResponseTambahrekDetail
import com.ilham.taspesialisbangunan.data.model.tambahrek.ResponseTambahrekList
import com.ilham.taspesialisbangunan.data.model.tambahrek.ResponseTambahrekUpdate
import java.io.File

interface TambahrekUpdateContract {

    interface Presenter {
        fun getDetailTambahrek(kd_rekening: Long)
        fun updateTambahrek(kd_rekening: Long, jenis_bank: String, norek: String, nama: String
        )
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResultDetailTambahrek(responseTambahrekDetail: ResponseTambahrekDetail)
        fun onResultUpdateTambahrek(responseTambahrekUpdate: ResponseTambahrekUpdate)
        fun showMessage(message: String)
    }
}