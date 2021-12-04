package com.ilham.taspesialisjasabangunan.ui.produkuser

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ilham.taspesialisbangunan.R
import com.ilham.taspesialisbangunan.ui.homeuser.UserActivity
import com.ilham.taspesialisbangunan.ui.produk_materialjasa.tabs.ProdukJasa.ProdukFragment
import com.ilham.taspesialisbangunan.ui.produk_materialjasa.tabs.ViewPagerAdapterProduk
import com.ilham.taspesialisbangunan.ui.produkuser_materialuser.ViewPagerAdapter
import com.ilham.taspesialisbangunan.ui.produkuser_materialuser.tabs.material.MaterialuserFragment
import com.ilham.taspesialisbangunan.ui.produkuser_materialuser.tabs.produk.ProdukuserFragment
import kotlinx.android.synthetic.main.activity_produkuser.*


class ProdukMaterialJasaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produkuser)


        val adapter = ViewPagerAdapterProduk(supportFragmentManager)
        adapter.addFragment(ProdukFragment(), "Produk")
//        adapter.addFragment(MaterialuserFragment(), "Material")
        btn_viepager.adapter = adapter
        btn_tabs.setupWithViewPager(btn_viepager)
    }
}