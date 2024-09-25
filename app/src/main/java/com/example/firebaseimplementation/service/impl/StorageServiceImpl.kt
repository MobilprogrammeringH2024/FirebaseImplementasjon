package com.example.firebaseimplementation.service.impl

import com.example.firebaseimplementation.service.AccountService
import com.example.firebaseimplementation.service.StorageService
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val accountService: AccountService
) : StorageService {

    override suspend fun <T : Any> createDocument(collection: String, data: T) {
        firestore.collection(collection).add(data).await()
    }

    override suspend fun <T> readDocument(collection: String, documentId: String, clazz: Class<T>): T? {
        val snapshot = firestore.collection(collection).document(documentId).get().await()
        return snapshot.toObject(clazz)
    }

    override suspend fun <T : Any> updateDocument(collection: String, documentId: String, data: T) {
        firestore.collection(collection).document(documentId).set(data).await()
    }

    override suspend fun deleteDocument(collection: String, documentId: String) {
        firestore.collection(collection).document(documentId).delete().await()
    }

}
