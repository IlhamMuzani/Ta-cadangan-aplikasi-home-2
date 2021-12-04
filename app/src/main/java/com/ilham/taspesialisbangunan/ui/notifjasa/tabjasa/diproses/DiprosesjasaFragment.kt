package com.ilham.taspesialisbangunan.ui.notifjasa.tabjasa.diproses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.ilham.taspesialisbangunan.data.database.PrefsManager
import com.ilham.taspesialisbangunan.data.database.PrefsManageruser
import com.ilham.taspesialisbangunan.data.model.Constant
import com.ilham.taspesialisbangunan.data.model.pengajuan.DataPengajuan
import com.ilham.taspesialisbangunan.data.model.pengajuan.ResponsePengajuanList1
import com.ilham.taspesialisbangunan.data.model.produk.DataProduk
import com.ilham.taspesialisbangunan.data.model.produk.ResponseProdukList
import com.ilham.taspesialisbangunan.ui.produkuser_materialuser.tabs.produk.ProdukuserAdapter

class DiprosesjasaFragment : Fragment(), DiprosesjasaContract.View {

    lateinit var presenter: DiprosesjasaPresenter
    lateinit var diprosesjasaAdapter: DiprosesjasaAdapter
    lateinit var datapengajuan: DataPengajuan
    lateinit var prefsManager: PrefsManager

    lateinit var rcvDiprosesjasa: RecyclerView
    lateinit var swipeDiprosesjasa: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_diprosesjasa, container, false)

        presenter = DiprosesjasaPresenter(this)
        prefsManager = PrefsManager(requireActivity())

        initFragment(view)

        return view
    }

    override fun onStart() {
        super.onStart()
        presenter.getPengajuanjasadiproses(prefsManager.prefsId)
    }

    override fun initFragment(view: View) {
        (activity as AppCompatActivity).supportActionBar!!.hide()

        rcvDiprosesjasa = view.findViewById(R.id.rcv_Diprosesjasa)
        swipeDiprosesjasa = view.findViewById(R.id.swipe_Diprosesjasa)

        diprosesjasaAdapter = DiprosesjasaAdapter(requireActivity(), arrayListOf()){
                dataPengajuan: DataPengajuan, position: Int, type: String ->
            Constant.PENGAJUAN_ID = datapengajuan.kd_pengajuan!!

            datapengajuan = dataPengajuan

        }

        rcvDiprosesjasa.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = diprosesjasaAdapter
        }
    }

    override fun onLoadingPengajuandiproses(loading: Boolean) {
        when (loading) {
            true -> swipeDiprosesjasa.isRefreshing = true
            false -> swipeDiprosesjasa.isRefreshing = false
        }
    }

    override fun onResultPengajuandiproses(responsePengajuanList1: ResponsePengajuanList1) {
        val pengajuan: List<DataPengajuan> = responsePengajuanList1.pengajuan
        diprosesjasaAdapter.setData(pengajuan)
    }

    override fun showMessage(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

}