package com.pins.exampleapp.adapters

import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.pins.exampleapp.R
import com.pins.exampleapp.databinding.UserLayoutBinding
import com.pins.exampleapp.models.LiveTvDetail
import com.squareup.picasso.Picasso

class TestUserAdapter(
    var items: ArrayList<LiveTvDetail>,
    var itemListner: TestInterestListner
) :
    RecyclerView.Adapter<TestInterestViewHolder>() {
    private val TAG = TestUserAdapter::class.java.simpleName

    override fun onBindViewHolder(viewHolder: TestInterestViewHolder, position: Int) =
        viewHolder.bind(items[position], itemListner, position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestInterestViewHolder {
        Log.d(TAG, "[onCreateViewHolder]")
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserLayoutBinding.inflate(inflater, parent, false)
        return TestInterestViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size
}

interface TestInterestListner {
    fun onIterestItemClick(position: Int, interestId: String)
}

class TestInterestViewHolder(var itemViewBinding: UserLayoutBinding) :
    RecyclerView.ViewHolder(itemViewBinding.root) {

    private val TAG = TestInterestViewHolder::class.java.simpleName

    companion object{
        var posi = 0
    }

    fun bind(interest: LiveTvDetail, listener: TestInterestListner, position: Int) {
        Log.d(TAG, "[bind]")
        itemViewBinding.interestTv.setText(interest.name)

//        Picasso.get().load(interest.im)

        itemViewBinding.interestTv.setOnClickListener {
            listener.onIterestItemClick(position,interest.id.toString())
        }
    }

}