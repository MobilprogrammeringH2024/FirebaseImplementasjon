package com.example.firebaseimplementation.service

import com.example.firebaseimplementation.model.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AccountService {
    val currentUserId: String
    val hasUser: Boolean
    val currentUser: Flow<FirebaseUser>
    suspend fun createAnonymousAccount()
    suspend fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit)
    suspend fun linkAccount(email: String, password: String, onResult: (Throwable?) -> Unit)
    suspend fun signOut()
    suspend fun sendPasswordResetEmail(email: String, onResult: (Throwable?) -> Unit) // New method

}