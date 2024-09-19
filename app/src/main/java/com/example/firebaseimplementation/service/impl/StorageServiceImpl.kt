package com.example.firebaseimplementation.service.impl

import com.example.firebaseimplementation.service.AccountService
import com.example.firebaseimplementation.service.StorageService
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val accountService: AccountService
) : StorageService {

    override suspend fun createDocument(collection: String, documentId: String, data: Map<String, Any>) {
        firestore.collection(collection).document(documentId).set(data).await()
    }

    override suspend fun readDocument(collection: String, documentId: String): Map<String, Any>? {
        val snapshot = firestore.collection(collection).document(documentId).get().await()
        return if (snapshot.exists()) snapshot.data else null
    }

    override suspend fun updateDocument(collection: String, documentId: String, data: Map<String, Any>) {
        firestore.collection(collection).document(documentId).update(data).await()
    }

    override suspend fun deleteDocument(collection: String, documentId: String) {
        firestore.collection(collection).document(documentId).delete().await()
    }

}