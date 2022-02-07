package com.fahrym.tmpr.ui.job_list

import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.fahrym.tmpr.data.remote.model.JobListResponse
import com.fahrym.tmpr.ui.base.BaseAdapter
import com.fahrym.tmpr.ui.base.ListAdapterItem
import java.time.ZonedDateTime
import java.util.*


@BindingAdapter("setAdapter")
fun setAdapter(
    recyclerView: RecyclerView,
    adapter: BaseAdapter<ViewDataBinding, ListAdapterItem>?
) {
    adapter?.let {
        recyclerView.adapter = it
    }
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun submitList(recyclerView: RecyclerView, list: List<ListAdapterItem>?) {
    val adapter = recyclerView.adapter as BaseAdapter<ViewDataBinding, ListAdapterItem>?
    adapter?.updateData(list ?: listOf())
}

@BindingAdapter("manageState")
fun manageState(progressBar: ProgressBar, state: Boolean) {
    progressBar.visibility = if (state) View.VISIBLE else View.GONE
}

@BindingAdapter("setImage")
fun setImage(imageView: ImageView, image: String) {
    Glide.with(imageView.context)
        .load(image)
        .transform(CenterCrop(), RoundedCorners(10))
        .into(imageView)
}

@BindingAdapter("setCurrency")
fun setCurrency(tv: TextView, currency: JobListResponse.Earn) {
    val pound: Currency = Currency.getInstance(currency.currency)

    tv.text = pound.symbol + currency.amount
}

@RequiresApi(Build.VERSION_CODES.O)
@BindingAdapter(value = ["bind:startAt", "bind:endsAt"])
fun setTime(tv: TextView, startAt: String, endsAt: String) {
    tv.text =
        ZonedDateTime.parse(startAt).toLocalTime().toString() + " - " + ZonedDateTime.parse(endsAt)
            .toLocalTime().toString()

}
