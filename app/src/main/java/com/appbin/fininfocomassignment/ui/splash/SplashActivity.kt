package com.appbin.fininfocomassignment.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewTreeObserver
import com.appbin.fininfocomassignment.MainActivity
import com.appbin.fininfocomassignment.R
import dagger.hilt.android.AndroidEntryPoint


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var mHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mHandler = Handler(Looper.getMainLooper())

        val content: View = findViewById(android.R.id.content)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {

            mHandler.postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()
                //supportActionBar?.show()

            }, 2000)

        } else {

            content.viewTreeObserver.addOnPreDrawListener(
                object : ViewTreeObserver.OnPreDrawListener {
                    override fun onPreDraw(): Boolean {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        return true

                    }
                }
            )
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
            //supportActionBar?.show()

        }
    }
}