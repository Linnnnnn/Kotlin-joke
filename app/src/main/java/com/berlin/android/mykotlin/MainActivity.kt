package com.berlin.android.mykotlin

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var that : Context? = null
    var textJokeContainer : FragmentTextJoke? =  null
    var pictureJokeContainer : FragmentPictureJoke? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        that = this.baseContext
        navigation.setOnNavigationItemSelectedListener(listener)
        init()
    }

    var listener : BottomNavigationView.OnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        item ->
        when(item.itemId){
            R.id.navigation_home ->{
                if (textJokeContainer == null) {
                    textJokeContainer = FragmentTextJoke()
                }
                overrideContainer(textJokeContainer)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_dashboard ->{
                if (pictureJokeContainer == null) {
                    pictureJokeContainer = FragmentPictureJoke()
                }
                overrideContainer(pictureJokeContainer)
                return@OnNavigationItemSelectedListener true
            }

        }
        return@OnNavigationItemSelectedListener false
    }


    fun toast(message : String, direction : Int = Toast.LENGTH_SHORT) {
        Toast.makeText(that,message,direction).show()
    }

    fun init(){
        textJokeContainer = FragmentTextJoke()
        overrideContainer(textJokeContainer)
    }

    fun overrideContainer(fragment : Fragment?){
        var fm = supportFragmentManager
        var ft = fm.beginTransaction()
        ft.replace(R.id.container,fragment)
        ft.commit()
    }
}
