package repository

import entity.ContactEntity

class ContactRepository {

    companion object {

        private val contactList = mutableListOf<ContactEntity>()

        fun save(contact: ContactEntity) {
            contactList.add(contact)
        }

        fun delete(contact: ContactEntity) {
            var indexContact = 0
            for (item in contactList.withIndex()) {
                if (item.value.name == contact.name && item.value.phone == contact.phone) {
                    indexContact = item.index
                    break
                }
                contactList.removeAt(indexContact)
            }
        }

        fun getList(): List<ContactEntity> {
            return contactList
        }

    }

}