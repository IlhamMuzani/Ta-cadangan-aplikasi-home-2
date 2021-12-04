package com.ilham.taspesialisbangunan.ui.produkuser_materialuser.tabs.produk.produkdetail

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ilham.taspesialisbangunan.R
import com.ilham.taspesialisbangunan.data.database.PrefsManageruser
import com.ilham.taspesialisbangunan.data.model.Constant
import com.ilham.taspesialisbangunan.data.model.produk.DataProduk
import com.ilham.taspesialisbangunan.data.model.produk.ResponseProdukDetail
import com.ilham.taspesialisbangunan.data.model.pengajuan.ResponsePengajuanInsert
import com.ilham.taspesialisbangunan.data.model.saran.ResponseSaranInsert
import com.ilham.taspesialisbangunan.ui.pengajuan.PengajuanActivity
import com.ilham.taspesialisbangunan.ui.utils.FileUtils
import com.ilham.taspesialisbangunan.ui.utils.GlideHelper
import com.lazday.poslaravel.util.GalleryHelper
import kotlinx.android.synthetic.main.activity_pengajuan.view.*
import kotlinx.android.synthetic.main.activity_produkdetail.*
import kotlinx.android.synthetic.main.activity_saran.view.*

class ProdukdetailActivity : AppCompatActivity(), ProdukdetailContract.View, OnMapReadyCallback {

    private var uriImage: Uri? = null
    private var pickImage = 1
    lateinit var presenter: ProdukdetailPresenter
    lateinit var produkdetail: DataProduk
    lateinit var prefsManageruser: PrefsManageruser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produkdetail)

        presenter = ProdukdetailPresenter(this)
        prefsManageruser = PrefsManageruser(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.getProdukdetail(Constant.PRODUK_ID)
    }

    override fun initActivity() {
        supportActionBar!!.title = "Detail Produk"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun initListener() {
        btnpengajuann.setOnClickListener{
            Constant.PRODUK_ID = produkdetail.kd_produkjasa!!
            startActivity(Intent(this, PengajuanActivity::class.java))
        }

    }

    override fun onLoadingProdukdetail(loading: Boolean) {
        when (loading) {
            true -> progressdetail.visibility = View.VISIBLE
            false -> progressdetail.visibility = View.GONE
        }
    }

    override fun onResultProdukdetail(responseProdukDetail: ResponseProdukDetail) {
        produkdetail = responseProdukDetail.dataProduk

        GlideHelper.setImage( applicationContext,"http://192.168.43.224/api_spesialisJB/public/"+ produkdetail.gambar!!, imvgambardetail)
        namadetail.text = produkdetail.nama_toko
        jenispembuatandetail.text = produkdetail.jenis_pembuatan
        alamatdetail.text = produkdetail.alamat
        phonedetail.text = produkdetail.phone
        hargadetail.text = produkdetail.harga
        deskripsidetail.text = produkdetail.deskripsi

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapdetail) as SupportMapFragment
        mapFragment.getMapAsync( this )

    }

    override fun onResultPengajuan(responsePengajuanInsert: ResponsePengajuanInsert) {
        showMessage("Pengajuan terkirim")
    }

    override fun showDialogPengajuan(dataProduk: DataProduk) {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.activity_pengajuan, null)
        view.imvPengajuan.setOnClickListener {
            if (GalleryHelper.permissionGallery(this, this, pickImage)) {
                GalleryHelper.openGallery(this)
            }
        }
        view.btn_pengajuann.setOnClickListener {
            val pengajuan = view.edtdeskripsipengajuann.text
            if (pengajuan.isNullOrEmpty() || uriImage == null ) {
                showMessage("Deskripsi harus diisi")
            } else {
                presenter.insertPengajuan(
                    dataProduk.jasausers_id!!,
                    prefsManageruser.prefsId,
                    FileUtils.getFile(this, uriImage),
                    pengajuan.toString(),
                    "Menunggu"
                )
            }
            dialog.dismiss()
        }

        dialog.setContentView(view)
        dialog.show()

    }

    override fun onResultSaran(responseSaranInsert: ResponseSaranInsert) {
        showMessage("Saran terkirim")
    }

    override fun showDialogSaran(dataProduk: DataProduk) {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.activity_saran, null)

        view.btn_sarann.setOnClickListener {
            val saran = view.edtdeskripsisaran.text
            if (saran.isNullOrEmpty()) {
                showMessage("Deskripsi harus diisi")
            } else {
                presenter.insertSaran(
                    dataProduk.jasausers_id!!,
                    prefsManageruser.prefsId,
                    saran.toString()
                )
            }
            dialog.dismiss()
        }

        dialog.setContentView(view)
        dialog.show()
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val latLng = LatLng (produkdetail.latitude!!.toDouble(), produkdetail.longitude!!.toDouble())
        googleMap.addMarker ( MarkerOptions(). position(latLng).title( produkdetail.nama_toko ))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pickImage && resultCode == Activity.RESULT_OK) {
            uriImage = data!!.data
            val view = layoutInflater.inflate(R.layout.activity_pengajuan, null)
            view.imvPengajuan.setImageURI( uriImage )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}