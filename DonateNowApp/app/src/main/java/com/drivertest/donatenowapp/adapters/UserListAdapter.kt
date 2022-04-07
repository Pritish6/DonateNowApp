package com.drivertest.donatenowapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drivertest.donatenowapp.Model.User
import com.drivertest.donatenowapp.databinding.AdapterItemPeopleBinding


//import kotlinx.android.synthetic.main.item_animal.view.*

class UserListAdapter(private val userList: ArrayList<User>):RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {
    // private lateinit var binding:
    fun updateUserList(newUserList: ArrayList<User>){
        userList.clear()
        newUserList.let { userList.addAll(it.toList()) }
       // newUserList.results?.let { userList.results?.addAll(it) }

        notifyDataSetChanged()
    }
    class UserListViewHolder(var view: AdapterItemPeopleBinding):RecyclerView.ViewHolder(view.root)


//    class PaymentHolder(private val itemBinding: RowPaymentBinding) : RecyclerView.ViewHolder(itemBinding.root) {
//        fun bind(paymentBean: PaymentBean) {
//            itemBinding.tvPaymentInvoiceNumber.text = paymentBean.invoiceNumber
//            itemBinding.tvPaymentAmount.text = paymentBean.totalAmount
//        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val employeeListItemBinding: ItemAnimalBinding = DataBindingUtil.inflate(
//            LayoutInflater.from(parent.getContext()),
//            R.layout., parent, false
//        )
//        val view = inflater.inflate(R.layout.item_animal, parent, false)
        val itemBinding = AdapterItemPeopleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.view.userName.text = userList.get(position).name.toString()
          }

    override fun getItemCount() = userList.size
}