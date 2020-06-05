package com.example.first_coroutine

import android.graphics.DiscretePathEffect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val job = GlobalScope.launch(Dispatchers.Default) {
            Log.d(TAG, "Starting long running calculation , ${Thread.currentThread().name}")
            withTimeout(3000) {
                for (i in 30..40) {
                    if (isActive) {
                        Log.d(TAG, "Result fot i = $i : ${fib(i)} , ${Thread.currentThread().name}")
                    }

                }
            }

            Log.d(TAG, "Ending long running calculation")

        }

        /*runBlocking {
            delay(2000)
            job.cancel()
            Log.d(TAG, "Cancel job")
        }*/

    }

    fun fib(n: Int): Long {
        return if (n == 0) 0
        else if (n == 1) 1
        else fib(n - 1) + fib(n - 2)
    }
}
