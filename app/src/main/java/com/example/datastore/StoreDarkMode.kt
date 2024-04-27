package com.example.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreDarkMode (private val context: Context) {

    companion object{
        private val Context.dataStore : DataStore<Preferences> by preferencesDataStore("DarkMode")//nombre del proyecto en DataStore Preferences
        val DARK_MODE = booleanPreferencesKey("dark_mode")//nombre de la clave, del campo que vamos a almacenar
    }

    val getDarkMode : Flow<Boolean> =context.dataStore.data //así decimos que es una variable flow sin ser en el ViewModel
        .map {
            it[DARK_MODE] ?: false //se puede quitar el operador elvis y que sea de tipo String?
        }

    suspend fun saveDarkMode(dMode : Boolean){//usamos parámetros porque luego los usamos desde la vista, lo que escribamos en el TextField se guarda en este DataStore
        context.dataStore.edit {
            it[DARK_MODE] = dMode
        }
    }
}