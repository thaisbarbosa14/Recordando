package com.recordando.app

import android.content.Intent
import android.os.Bundle
import android.util.Patterns.EMAIL_ADDRESS
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.recordando.app.entities.Infos
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_infos.*
import kotlinx.android.synthetic.main.activity_infos.view.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Pattern


class InfosActivity : AppCompatActivity() {
    private lateinit var nomeI: EditText
    private lateinit var idadeI: EditText
    private lateinit var telefoneI: EditText
    private lateinit var telefoneI2: EditText
    private lateinit var Iemail: EditText
    private lateinit var saveInf: Button
    private lateinit var deleteInf: Button
    private lateinit var infoList: ArrayList<Infos>
    private lateinit var realm:Realm




    override fun onCreate(savedInstanceState: Bundle?) {
        Realm.init(applicationContext)
        realm = Realm.getDefaultInstance()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infos)


        nomeI = findViewById(R.id.nomeI)
        idadeI = findViewById(R.id.idadeI)
        telefoneI = findViewById(R.id.telI)
        telefoneI2 = findViewById(R.id.tellI)
        Iemail = findViewById(R.id.Iemail)
        saveInf = findViewById(R.id.saveInf)
        deleteInf = findViewById(R.id.deleteInf)
        updateInfos()



        deleteInf.setOnClickListener{


        }
        saveInf.setOnClickListener {
            saveInfos()


        }

    }




    private fun saveInfos() {
        realm.beginTransaction()


                var info = Infos()
                info.nome = nomeI.text.toString()
                info.idade = idadeI.text.toString()
                info.tell = telefoneI.text.toString()
                info.tell2 = telefoneI2.text.toString()
                info.email = Iemail.text.toString()


                if(info.nome.isNullOrEmpty()) {
                    Toast.makeText(this, "Insira um nome!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, InfosActivity::class.java))
                    finish()

                }else if (info.idade.isNullOrEmpty()) {
                    Toast.makeText(this, "Insira a idade", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, InfosActivity::class.java))
                    finish()

                }else if (info.tell.isNullOrEmpty()) {
                    Toast.makeText(this, "Insira seu telefone", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, InfosActivity::class.java))
                    finish()


                }else if (info.tell2.isNullOrEmpty()) {
                    Toast.makeText(this, "Insira seu telefone de emergência", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, InfosActivity::class.java))
                    finish()


                }else if (info.email.isNullOrEmpty()) {
                    Toast.makeText(this, "Insira seu email", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, InfosActivity::class.java))
                    finish()


                }else if( android.util.Patterns.EMAIL_ADDRESS.matcher(info.email).matches()){

                    var info = Infos()
                    info.nome = nomeI.text.toString()
                    info.idade = idadeI.text.toString()
                    info.tell = telefoneI.text.toString()
                    info.tell2 = telefoneI2.text.toString()
                    info.email = Iemail.text.toString()


                    realm.insertOrUpdate(info)
                    realm.commitTransaction()





                    Toast.makeText(this, "salvo", Toast.LENGTH_SHORT).show()

                    startActivity(Intent(this, MainActivity::class.java))
                    finish()


                }else{
                    Toast.makeText(this, "email invalido", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, InfosActivity::class.java))
                    finish()


                }



    }
    private fun updateInfos() {

        val nomes = realm.where(Infos::class.java).findAll().last()
        nomeI.setHint("Seu Nome:  "+ nomes?.nome)
        idadeI.setHint("Sua Idade:  "+ nomes?.idade)
        telefoneI.setHint("Seu telefone:  "+ nomes?.tell)
        telefoneI2.setHint("Telefone de Emergência:  "+ nomes?.tell2)
        Iemail.setHint("Seu Email:  "+ nomes?.email)
    }


    private fun voltar() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}
