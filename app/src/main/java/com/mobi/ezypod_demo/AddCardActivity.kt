package com.mobi.ezypod_demo

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.mobi.ezypod.Common
import com.mobi.ezypod.Payment
import com.mobi.ezypod.service.network.*
import kotlinx.android.synthetic.main.activity_add_card.*

class AddCardActivity : AppCompatActivity(), View.OnClickListener {

    val payment: Payment = Payment()
    private var mProgressDialog: ProgressDialog? = null

    var mobiApiKey = "132fe8ed2715bc0fb4fe16c55acbd6d4" //for demo
    var username = "Mobiversa" // for demo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)

        mProgressDialog = ProgressDialog(this@AddCardActivity)
        mProgressDialog!!.isIndeterminate = false
        mProgressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        mProgressDialog!!.setMessage("Loading")
        mProgressDialog!!.setCancelable(false)

        btn_submit!!.setOnClickListener(this)


        Payment.getInstance(this@AddCardActivity, paymentResponse, false)

        edit_latitude.setText("")
        edit_longitude.setText("")
        edit_mobileNo.setText("+919943291177")
        edit_nameOnCard.setText("Mobiversa")
//        edit_cardNumber.setText("5422882800700007")
        edit_cardNumber.setText("4918914107195005")
        edit_cardCVV.setText("123")
        edit_cardExpirymonth.setText("01")
        edit_cardExpiryYear.setText("23")
        edit_email.setText("sangavi@gomobi.com")
    }

    //Payment Connection Interface
    private val paymentResponse = object : PaymentResponse {
        override fun getCardList(success: CardList) {

        }

        override fun addCard(cardData: AddCardResponse) {

        }

        override fun setPaymentSuccess(success: PaymentResult) {
            Log.e("Response Success Result", "${success.responseDescription}")

        }

        override fun setFailure(failure: String) {
            Log.e("Response Failure", "$failure")
            Toast.makeText(applicationContext, failure, Toast.LENGTH_SHORT).show()
        }

        override fun setRemoveCard(success: RemoveCardPojo) {

        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_submit -> {

                val cardNumber = edit_cardNumber.text.toString()
                val cvvNumber = edit_cardCVV.text.toString()
                val expiryMonth = edit_cardExpirymonth.text.toString()
                val expiryYear = edit_cardExpiryYear.text.toString()

                val requestMap: HashMap<String, String> = HashMap()
                requestMap[Common.latitude] = edit_latitude.text.toString()
                requestMap[Common.longitude] = edit_longitude.text.toString()
                requestMap[Common.mobileNo] = edit_mobileNo.text.toString()
                requestMap[Common.NameOnCard] = edit_nameOnCard.text.toString()
                requestMap[Common.email] = edit_email.text.toString()

                val cardData: HashMap<String, String> = HashMap()
                cardData[Common.mobiApiKey] = mobiApiKey.trim { it <= ' ' }
                cardData[Common.loginId] = username.trim { it <= ' ' }
                cardData[Common.CardNumber] = cardNumber
                cardData[Common.CVVNumber] = cvvNumber
                cardData[Common.ExpiryMonth] = expiryMonth
                cardData[Common.ExpiryYear] = expiryYear

                payment.jsonAddCard(requestMap, cardData)
            }
        }
    }
}