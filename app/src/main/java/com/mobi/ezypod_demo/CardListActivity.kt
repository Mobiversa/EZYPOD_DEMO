package com.mobi.ezypod_demo

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobi.ezypod.Common
import com.mobi.ezypod.Payment
import com.mobi.ezypod.service.network.*
import com.mobi.ezypod_demo.adapter.CardListAdapter
import kotlinx.android.synthetic.main.activity_card_list.*

class CardListActivity : AppCompatActivity() {

    val payment: Payment = Payment()
    var btn_submit: Button? = null
    private var mProgressDialog: ProgressDialog? = null

    var mobiApiKey = "132fe8ed2715bc0fb4fe16c55acbd6d4" //for demo
    var username = "Mobiversa" // for demo
    val requestMap: HashMap<String, String> = HashMap()

    private lateinit var cardListAdapter: CardListAdapter
    private var cardList : ArrayList<CardWallet> = ArrayList()
    private var position : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_list)

        card_list_rec.layoutManager =
            LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        card_list_rec.addItemDecoration(
            DividerItemDecoration(
                applicationContext,
                LinearLayoutManager.VERTICAL
            )
        )
        cardListAdapter = CardListAdapter( applicationContext!!,cardList)
        card_list_rec.adapter = cardListAdapter

        Payment.getInstance(this@CardListActivity, paymentResponse, false)

        requestMap[Common.mobiApiKey] = mobiApiKey.trim { it <= ' ' }
        requestMap[Common.loginId] = username.trim { it <= ' ' }
        requestMap[Common.mobileNo] = "+919943291177"

        payment.jsonGetCardList(requestMap)
    }

    fun deleteCard(cardToken: String, position: Int){
        this.position = position
        requestMap[Common.mobiApiKey] = mobiApiKey.trim { it <= ' ' }
        requestMap[Common.loginId] = username.trim { it <= ' ' }
        requestMap[Common.cardToken] = cardToken
        payment.jsonRemoveCard(requestMap)
    }

    fun payByCard(cardToken : String){
        requestMap[Common.mobiApiKey] = mobiApiKey.trim { it <= ' ' }
        requestMap[Common.loginId] = username.trim { it <= ' ' }
        requestMap[Common.cardToken] = cardToken
        requestMap[Common.invoiceId] = "Testing"
        requestMap[Common.amount] = "1.00"
        payment.jsonPayByCard(requestMap)
    }

    //Payment Connection Interface
    private val paymentResponse = object : PaymentResponse {
        override fun getCardList(success: CardList) {
            Log.e("Result", success.responseDescription)
            cardList.addAll(success.responseData.cardWalletList)
            cardListAdapter.notifyDataSetChanged()

        }

        override fun addCard(cardData: AddCardResponse) {
            Log.e("Result", cardData.responseDescription)
            Toast.makeText(applicationContext,cardData.responseDescription,Toast.LENGTH_SHORT).show()
        }

        override fun setPaymentSuccess(success: PaymentResult) {
            Log.e("Result", success.responseDescription)
            Toast.makeText(applicationContext,success.responseDescription,Toast.LENGTH_SHORT).show()

        }

        override fun setFailure(failure: String) {
            Log.e("Response Failure", "$failure")
            Toast.makeText(applicationContext,failure,Toast.LENGTH_SHORT).show()
        }

        override fun setRemoveCard(success: RemoveCardPojo) {
            Log.e("Result", success.responseDescription)
            cardList.removeAt(position)
            cardListAdapter.notifyDataSetChanged()
            Toast.makeText(applicationContext,success.responseDescription,Toast.LENGTH_SHORT).show()
        }
    }
}