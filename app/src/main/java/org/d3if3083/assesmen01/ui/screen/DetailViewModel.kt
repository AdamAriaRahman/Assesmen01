package org.d3if3083.assesmen01.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3083.assesmen01.database.MbtiDao
import org.d3if3083.assesmen01.model.Mbti

class DetailViewModel(private val dao: MbtiDao) : ViewModel() {

    fun insert(nama: String, nim: String, kelas: String) {
        val mbti = Mbti(
            namaNya = nama,
            nimNya = nim,
            kelasNya = kelas
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(mbti)
        }
    }

    suspend fun getMahasiswa(id: Long): Mbti? {
        return dao.getMbtiById(id)
    }

    fun update(id: Long, nama: String, nim: String, kelas: String) {
        val mahasiswa = Mbti(
            id = id,
            namaNya = nama,
            nimNya = nim,
            kelasNya = kelas
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(mahasiswa)
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)
        }
    }
}