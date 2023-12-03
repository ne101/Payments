package com.example.payments.presentation.adapters



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.payments.R
import com.example.payments.databinding.PaymentCardBinding
import com.example.payments.domain.entities.paymentEntities.PaymentEntity
import java.text.SimpleDateFormat
import java.util.*

class PaymentsAdapter : ListAdapter<PaymentEntity, PaymentsViewHolder>(PaymentsDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentsViewHolder {
        val binding = PaymentCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PaymentsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PaymentsViewHolder, position: Int) {

        val context = holder.itemView.context
        val binding = holder.binding
        val paymentItem = getItem(position)
        binding.tvTitle.text = paymentItem.title.toString()
        binding.tvAmount.text =
            context.getString(R.string.amount, paymentItem.amount.toString())
        binding.tvTime.text =
            context.getString(R.string.time, unixTimestampToDate(paymentItem.created.toString().toLong())
        )
    }
    private fun unixTimestampToDate(timestamp: Long): String {
        val date = Date(timestamp * 1000L)
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss z")
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        return sdf.format(date)
    }
}