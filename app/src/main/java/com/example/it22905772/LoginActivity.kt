package com.example.it22905772

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class LoginActivity : AppCompatActivity() {

    private lateinit var username:EditText
    private lateinit var password:EditText
    private lateinit var btnLog:Button
    private var count=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        username=findViewById(R.id.edtUsername)
        password=findViewById(R.id.edtPassword)
        btnLog=findViewById(R.id.btnLogin)

        btnLog.setOnClickListener {
            val myLog:ValidateLogin= ValidateLogin(
                username.text.toString(),
                password.text.toString()
            )

            val validateUsername=myLog.usernameValidate()
            val validatePassword=myLog.passwordValidate()

            when(validateUsername){
                is ValidationResults.Empty->{
                    username.error=validateUsername.errorMessage
                }
                is ValidationResults.Invalid->{
                    displayAlert("Error",validateUsername.errorMessage)
                }
                is ValidationResults.Valid->{
                    count++
                }
            }

            when(validatePassword){
                is ValidationResults.Empty->{
                    password.error=validatePassword.errorMessage
                }
                is ValidationResults.Invalid->{
                    displayAlert("Error",validatePassword.errorMessage)
                }
                is ValidationResults.Valid->{
                    count++
                }
            }

            if(count==2){
                val i= Intent(this,MainActivity::class.java)
                startActivity(i)
            }
        }
    }

    private fun displayAlert(title:String,message:String){
        val builder= AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK"){dialog, which->

        }
        val dialog=builder.create()
        dialog.show()
    }
}