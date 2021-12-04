package com.ilham.taspesialisbangunan.ui.material.update

import com.ilham.taspesialisbangunan.data.model.material.ResponseMaterialDetail
import com.ilham.taspesialisbangunan.data.model.material.ResponseMaterialUpdate
import com.ilham.taspesialisbangunan.data.model.produk.ResponseProdukDetail
import com.ilham.taspesialisbangunan.data.model.produk.ResponseProdukUpdate
import java.io.File

interface MaterialUpdateContarct {

    interface Presenter {
        fun getDetail(kd_material: Long)
        fun updateMaterial(kd_material: Long, nama_toko: String, jenis_material: String, alamat: String, phone: String, harga: String,
                         latitude: String, longitude: String, gambar: File?, deskripsi: String
        )
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResultDetail(responseMaterialDetail: ResponseMaterialDetail)
        fun onResultUpdate(responseMaterialUpdate: ResponseMaterialUpdate)
        fun showMessage(message: String)
    }
}