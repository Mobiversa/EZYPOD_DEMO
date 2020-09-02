package com.mobi.ezypod_demo

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.mobi.ezypod.Common
import com.mobi.ezypod.Payment
import com.mobi.ezypod.service.network.*

class CardListActivity : AppCompatActivity() {

    val payment: Payment = Payment()
    var btn_submit: Button? = null
    private var mProgressDialog: ProgressDialog? = null

    var mobiApiKey = "132fe8ed2715bc0fb4fe16c55acbd6d4" //for demo
    var username = "Mobiversa" // for demo
    val requestMap: HashMap<String, String> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_list)

        Payment.getInstance(this@CardListActivity, paymentResponse, false)

        requestMap[Common.mobiApiKey] = mobiApiKey.trim { it <= ' ' }
        requestMap[Common.loginId] = username.trim { it <= ' ' }
        requestMap[Common.mobileNo] = "+919943291177"

//        payment.jsonGetCardList(requestMap)

        /*card_list_btn.setOnClickListener {
            payment.jsonGetCardList(requestMap)
        }
        remove_card_btn.setOnClickListener {
            requestMap[Common.cardToken] = "f45ddde5652f23c7586e46be358569e4"
            payment.jsonRemoveCard(requestMap)
        }
        pay_card_btn.setOnClickListener {
            requestMap[Common.cardToken] = "f45ddde5652f23c7586e46be358569e4"
            requestMap[Common.invoiceId] = "Testing"
            requestMap[Common.amount] = "1.00"
            payment.jsonPayByCard(requestMap)
        }*/
    }

    //Payment Connection Interface
    private val paymentResponse = object : PaymentResponse {
        override fun getCardList(success: CardList) {
            Log.e("Result", success.responseDescription)


        }

        override fun addCard(cardData: AddCardResponse) {
            Log.e("Result", cardData.responseDescription)
        }

        override fun setPaymentSuccess(success: PaymentResult) {
            Log.e("Result", success.responseDescription)

        }

        override fun setFailure(failure: String) {
            Log.e("Response Failure", "$failure")

        }

        override fun setRemoveCard(success: RemoveCardPojo) {
            Log.e("Result", success.responseDescription)
        }
    }
}