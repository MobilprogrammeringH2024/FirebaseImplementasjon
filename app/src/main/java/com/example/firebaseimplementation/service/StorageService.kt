package com.example.firebaseimplementation.service

import kotlinx.coroutines.flow.Flow

interface StorageService {
    suspend fun createDocument(collection: String, documentId: String, data: Map<String, Any>)
    suspend fun readDocument(collection: String, documentId: String): Map<String, Any>?
    suspend fun updateDocument(collection: String, documentId: String, data: Map<String, Any>)
    suspend fun deleteDocument(collection: String, documentId: String)
}