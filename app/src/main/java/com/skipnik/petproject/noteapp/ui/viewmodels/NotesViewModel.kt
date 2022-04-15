package com.skipnik.petproject.noteapp.ui.viewmodels

import androidx.lifecycle.*
import com.skipnik.petproject.noteapp.database.Note
import com.skipnik.petproject.noteapp.database.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NoteViewModel(
    private val noteDao: NoteDao
) : ViewModel() {

    val notes: LiveData<List<Note>> = noteDao.getNotes().asLiveData()


    fun retrieveNote(id: Int): LiveData<Note> {
        return noteDao.getNote(id).asLiveData()
    }


    fun addNewNote(text: String) {
        val newNote = Note(
            text = text
        )
        insertNote(newNote)
    }

    private fun insertNote(newNote: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.insert(newNote)
        }
    }

    fun updateNote(
        id: Int,
        text: String
    ) {
        val updatedNote = Note(
            id = id,
            text = text
        )
        updateNote(updatedNote)
    }

    private fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.update(note)
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.delete(note)
        }
    }




    class Factory(private val noteDao: NoteDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return NoteViewModel(noteDao) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}