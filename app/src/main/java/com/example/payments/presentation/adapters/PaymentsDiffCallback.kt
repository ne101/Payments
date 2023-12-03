package com.example.payments.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.payments.domain.entities.paymentEntities.PaymentEntity

class PaymentsDiffCallback: DiffUtil.ItemCallback<PaymentEntity>() {
    override fun areItemsTheSame(oldItem: PaymentEntity, newItem: PaymentEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PaymentEntity, newItem: PaymentEntity): Boolean {
        return oldItem == newItem
    }
}