package com.ilham.taspesialisbangunan.ui.produkuser_materialuser.tabs.produk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ilham.taspesialisbangunan.R
import com.ilham.taspesialisbangunan.data.database.PrefsManageruser
import com.ilham.taspesialisbangunan.data.model.Constant
import com.ilham.taspesialisbangunan.data.model.produk.DataProduk
import com.ilham.taspesialisbangunan.data.model.produk.ResponseProdukList
import kotlinx.android.synthetic.main.activity_materialuser.*
import kotlinx.android.synthetic.main.fragment_material.*
import kotlinx.android.synthetic.main.fragment_material.edtSearchMaterial
import kotlinx.android.synthetic.main.fragment_produk.*

class ProdukuserFragment : Fragment(), ProdukuserContract.View, OnMapReadyCallback {

    lateinit var presenter: ProdukuserPresenter
    lateinit var produkuserAdapter: ProdukuserAdapter
    lateinit var produk: DataProduk
    lateinit var prefsManageruser: PrefsManageruser

    lateinit var rcvProduk: RecyclerView
    lateinit var swipe: SwipeRefreshLayout
    lateinit var EdtSearch: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_produk, container, false)

        presenter = ProdukuserPresenter(this)
        prefsManageruser = PrefsManageruser(requireActivity())

        initFragment(view)

        return view
    }

    override fun onStart() {
        super.onStart()
        presenter.getProduk()
    }

    override fun initFragment(view: View) {
        (activity as AppCompatActivity).supportActionBar!!.hide()

        rcvProduk = view.findViewById(R.id.rcvProduk)
        swipe = view.findViewById(R.id.swipe)
        EdtSearch = view.findViewById(R.id.edtSearch)

        produkuserAdapter = ProdukuserAdapter(requireActivity(), arrayListOf()){
                dataProduk: DataProduk, position: Int, type: String ->
            Constant.PRODUK_ID = dataProduk.kd_produkjasa!!

            produk = dataProduk

        }

        EdtSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                presenter.searchProduk(EdtSearch.text.toString() )
                true
            } else {
                false
            }
        }

        rcvProduk.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = produkuserAdapter
        }
    }

    override fun onLoadingProduk(loading: Boolean) {
        when (loading) {
            true -> swipe.isRefreshing = true
            false -> swipe.isRefreshing = false
        }
    }

    override fun onResultProduk(responseProdukList: ResponseProdukList) {
        val dataProduk: List<DataProduk> = responseProdukList.dataProduk
        produkuserAdapter.setData(dataProduk)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val latLng = LatLng (produk.latitude!!.toDouble(), produk.longitude!!.toDouble())
        googleMap.addMarker ( MarkerOptions(). position(latLng).title( produk.nama_toko ))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f))
    }

    override fun showMessage(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }
}