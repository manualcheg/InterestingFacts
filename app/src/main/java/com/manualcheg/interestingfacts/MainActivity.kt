package com.manualcheg.interestingfacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

const val APP_PREFS = "practicum_example_preferences"
const val FACTS_LIST_KEY = "key_for_facts_list"

class MainActivity : AppCompatActivity() {

//    var adapter = AdapterFact(facts)
//    var facts: ArrayList<Fact> = ArrayList<Fact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//  чтение фактов из файла
        val sharedPrefs = getSharedPreferences(APP_PREFS, MODE_PRIVATE)
        val json = sharedPrefs.getString(FACTS_LIST_KEY, "[]") // костыль - null по умолчанию быть не должно
        val typeToken = object : TypeToken<ArrayList<Fact>>() {}.type
        var facts :ArrayList<Fact> = Gson().fromJson(json, typeToken)
//        adapter.factArrayList = Gson().fromJson(json, typeToken)

//  создание RecycleView
        var adapter = AdapterFact(facts)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

        val buttonSave = findViewById<Button>(R.id.button_save)
        val buttonSync = findViewById<Button>(R.id.button_sync)
        val editFact = findViewById<EditText>(R.id.input_fact)
        val editDiscipline = findViewById<EditText>(R.id.input_discipline)

//  подписка на изменения в SharedPrefs
        sharedPrefs.registerOnSharedPreferenceChangeListener{ sharedPrefs, key ->
            adapter.notifyItemRangeChanged(0,facts.size)
//            adapter.notifyItemRangeChanged(0,adapter.factArrayList.size)
        }

        buttonSave.setOnClickListener{
            if (facts.size >= 3){
                facts.removeAt(facts.size-1)
                facts.add(0, Fact(editFact.text.toString(),editDiscipline.text.toString()))
            } else {
                facts.add(0, Fact(editFact.text.toString(),editDiscipline.text.toString()))
            }
/*            val fact = Fact(editFact.text.toString(),editDiscipline.text.toString())
            sharedPrefs.edit()
                .putString("New fact key", Gson().toJson(fact))
                .apply()*/

            sharedPrefs.edit()
                .putString(FACTS_LIST_KEY,Gson().toJson(facts))
                .apply()
        }

        buttonSync.setOnClickListener{
            adapter.notifyItemRangeChanged(0,facts.size)
        }
/*        buttonSync.setOnClickListener{
            val fact = sharedPrefs.getString("New fact key", null)
            if (fact != null){
                adapter.factArrayList.add(0, Gson().fromJson(fact, Fact::class.java))
            }
            adapter.notifyItemInserted(0)

            sharedPrefs.edit()
                .putString(FACTS_LIST_KEY, Gson().toJson(adapter.factArrayList))
                .apply()
        }*/

    }

    override fun onStop() {
        super.onStop()

    }
}