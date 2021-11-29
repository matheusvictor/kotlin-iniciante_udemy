package business

import entity.ContactEntity
import repository.ContactRepository

class ContactBusiness {

    fun validate(name: String, phone: String) {
        if (name.equals("")) throw Exception("Nome é obrigatório!")
        if (phone.equals("")) throw Exception("Telefone é obrigatório!")
    }

    fun validateDelete(name: String, phone: String) {
        if (name.equals("") || phone.equals("")) throw Exception("É necessário selecionar um contato antes de remover.")
    }

    fun save(name: String, phone: String) {
        validate(name, phone)

        val contact = ContactEntity(name, phone)
        ContactRepository.save(contact)
    }

    fun delete(name: String, phone: String) {
        validateDelete(name, phone)

        val contact = ContactEntity(name, phone)
        ContactRepository.delete(contact)
    }
}