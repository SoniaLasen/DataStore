package com.example.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreUserEmail (private val context: Context) {

    companion object{
        private val Context.dataStore : DataStore<Preferences> by preferencesDataStore("UserEmail")//nombre del proyecto en DataStore Preferences
        val USER_EMAIL = stringPreferencesKey("user_email")//nombre de la clave, del campo que vamos a almacenar
    }

    val getEmail : Flow<String> =context.dataStore.data //así decimos que es una variable flow sin ser en el ViewModel
        .map {
            it[USER_EMAIL] ?: "" //se puede quitar el operador elvis y que sea de tipo String?
        }

    suspend fun saveEmail(email: String){//usamos parámetros porque luego los usamos desde la vista, lo que escribamos en el TextField se guarda en este DataStore
        context.dataStore.edit {
            it[USER_EMAIL] = email
        }
    }
}