package com.esaudev.mediagarden.data.remote

import com.esaudev.mediagarden.data.remote.FirestoreConstants.SONGS_COLLECTION
import com.esaudev.mediagarden.domain.Song
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class MusicDataSource {

    private val firestore = FirebaseFirestore.getInstance()
    private val songCollection = firestore.collection(SONGS_COLLECTION)

    suspend fun getAllSongs(): List<Song> {
        return try {
            songCollection.get().await().toObjects(Song::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }

}