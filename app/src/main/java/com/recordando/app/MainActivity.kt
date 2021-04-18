package com.recordando.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.recordando.app.entities.Infos
import com.recordando.app.game.MainGame
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*


private const val TAG = "MainActivity"
private lateinit var realm: Realm



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        Realm.init(applicationContext)
        realm = Realm.getDefaultInstance()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            nomeED.text = acct.displayName
        }


        game.setOnClickListener{
            jogo()
        }

        notas.setOnClickListener{
            notas()
        }
        info.setOnClickListener{
            infos()
        }
        emer.setOnClickListener{
            abrirDiscador()
        }
        sair.setOnClickListener{
            signOut()

        }
    }

    private fun signOut() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }


    private fun abrirDiscador() {
        realm.beginTransaction()

        val nomes = realm.where(Infos::class.java).findAll().last()
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:"+nomes?.tell2)
        startActivity(intent)
    }

    private fun jogo() {
        val intent = Intent(this, MainGame::class.java)
        startActivity(intent)
    }

    private fun notas() {
        val intent = Intent(this,MainActivityNotes::class.java)
        startActivity(intent)
    }
    private fun infos() {
        val intent = Intent(this,InfosActivity::class.java)
        startActivity(intent)
    }

}

