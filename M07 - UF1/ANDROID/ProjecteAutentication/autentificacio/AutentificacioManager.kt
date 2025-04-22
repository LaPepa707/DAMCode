package com.montilivi.projecteautentificacio.autentificacio

import android.content.Context
import android.util.Log
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.montilivi.llistacompra.model.report_autentification
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException

class autentificationManager(private val context: Context){
    private val client_id = "574813251868-17e3ogbltu28qunc8r8n9im0os04lrf1.apps.googleusercontent.com"
    private val autentication: FirebaseAuth by lazy{
        Firebase.auth
    }
    private val tag = "AUTENTIFICATION_MANAGER"
    private val credentialManager = CredentialManager.create(context)

    fun current_user(): FirebaseUser?{
        return autentication.currentUser
    }

    fun current_user_exists(): Boolean{
        return autentication.currentUser != null
    }

    suspend fun close_session(){
        credentialManager.clearCredentialState(ClearCredentialStateRequest())
        autentication.signOut()
    }

    //region ANONYMOUS SESSION
    suspend fun iniciarSessioAnonima():report_autentification<FirebaseUser>{

        try {
            val resultat = autentication.signInAnonymously().await()
            return report_autentification.Exit(resultat.user?:throw Exception("Error en iniciar sessió"))

        }
        catch (e: Exception){
            return report_autentification.Fracas(e.message ?: "Error desconegut")
            throw Exception(e.message)
        }
    }
    //endregion

    //region USER AND PASSWORD
    suspend fun start_with_email_password(email: String, password: String): report_autentification<Boolean>{
        var strat = false;
        return try {
            val result = autentication.signInWithEmailAndPassword(email, password).await()
            val user = result.user ?: throw Exception("Error en iniciar sessió")
            return report_autentification.Exit(result.user != null)
        }
        catch (e: Exception){
            return report_autentification.Fracas(e.message ?: "Error desconegut")
        }
    }

    suspend fun reset_email_password(email: String): report_autentification<Boolean>{
        try {
            autentication.sendPasswordResetEmail(email).await()
            return report_autentification.Exit(true)
        }
        catch (e: Exception){
            return report_autentification.Fracas(e.message ?: "Error desconegut")
            throw Exception(e.message)
        }
    }

    suspend fun creat_user_with_email_password(email: String, password: String, name: String): report_autentification<Boolean>{
        return try {
            val result = autentication.createUserWithEmailAndPassword(email, password).await()
            report_autentification.Exit(result.user?:throw Exception("Error en iniciar sessió"))
            val user = result.user ?: throw Exception("Error en iniciar sessió")
            val profileUpdates = userProfileChangeRequest {
                displayName = name
            }
            user.updateProfile(profileUpdates).await()
            report_autentification.Exit(user != null)
        }
        catch (e: Exception){
            report_autentification.Fracas(e.message ?: "Error desconegut")
        }
    }
    //endregion

    //region GOOGLE

    private suspend fun create_credencial_request(): GetCredentialResponse {
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(
                GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(client_id)
                    .setAutoSelectEnabled(false)
                    .build()
            ).build()
        return credentialManager.getCredential(request = request, context = context)
    }

    private suspend fun session_start_manager(result: GetCredentialResponse): Boolean{
        val credetials = result.credential
        if(credetials is CustomCredential && credetials.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL){
            try {
                val token = GoogleIdTokenCredential.createFrom(credetials.data)
                Log.i(tag, "Token: ${token.displayName}")
                Log.i(tag, "Token: ${token.familyName}")
                Log.i(tag, "Token: ${token.phoneNumber}")
                Log.i(tag, "Token: ${token.profilePictureUri}")
                val google_credential = GoogleAuthProvider.getCredential(token.idToken, null)
                val autentification_result = autentication.signInWithCredential(google_credential).await()
                return autentification_result.user != null
            }
            catch (e: GoogleIdTokenParsingException) {
                Log.e(tag, "Error al parsejar el token: ${e.message}")
                throw Exception(e.message)
                return false
            }
        }
        return false
    }


    suspend fun inici_Sessio_Google(): Boolean {
        if(current_user_exists())
        {
            Log.d(tag,"Ja hi ha un usuari iniciat" )
            return true;
        }
        else{
            try {
                val resultat = create_credencial_request()
                session_start_manager(resultat)
                return current_user_exists()
            }
            catch (e:Exception)
            {
                if (e is CancellationException) throw e
                Log.e(tag, "Error al iniciar sessió amb Google: ${e.message}")
                return false
            }
        }

    }
    //endregion


}