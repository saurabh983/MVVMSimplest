package com.pins.exampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.cheffypartner.extensions.goToActivity
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.google.gson.Gson
import com.pins.exampleapp.adapters.TestInterestListner
import com.pins.exampleapp.adapters.TestUserAdapter
import com.pins.exampleapp.databinding.ActivityMainBinding
import com.pins.exampleapp.extensions.Outcome
import com.pins.exampleapp.models.LiveTvDetail
import com.woo.groupchat.viewmodels.addmember.GetUsersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), GetUsersViewModel.LoadUserListner,TestInterestListner {

    lateinit var dataBind : ActivityMainBinding
    val viewModel : GetUsersViewModel by viewModel()

    var listUser = ArrayList<LiveTvDetail>()

    lateinit var adapter : TestUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        dataBind = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel.setContext(dataBind.root.context)
        viewModel.setListener(this)
        dataBind.viewModel = viewModel
        lifecycle.addObserver(viewModel)

        adapter = TestUserAdapter(listUser,this)
        dataBind.rv.layoutManager = GridLayoutManager(this,2)
        dataBind.rv.adapter = adapter

        viewModel.outComeData.observe(this,
        Observer { outcome ->
            when (outcome) {
                is Outcome.Success -> {
                    listUser.addAll(outcome.data)
                    adapter.notifyDataSetChanged()
                }
                is Outcome.Failure -> {
                    Toast.makeText(this, outcome.e.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    override fun onLoadingStarted() {
        dataBind.signinButtonSend.showProgress {
            progressColor = ContextCompat.getColor(this@MainActivity, R.color.ms_white)
        }
    }

    override fun onLoadingEnded() {
        dataBind.signinButtonSend.hideProgress(getString(R.string.getUser))
    }

    override fun onIterestItemClick(position: Int, interestId: String) {

    }
}
