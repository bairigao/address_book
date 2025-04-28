package com.example.addressbook.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ContactManager {
    private IContactDAO contactDAO;
    public ContactManager(IContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

    public List<Contact> searchContacts(String query) {
        if (query == null || query.isEmpty()) {
            return contactDAO.getAllContacts();
        }
        query = query.toLowerCase();
        ArrayList<Contact> results = new ArrayList<>();
        for (Contact contact : contactDAO.getAllContacts()) {
            String searchString = contact.getFullName().toLowerCase()
                    + " " + contact.getEmail().toLowerCase()
                    + " " + contact.getPhone().toLowerCase();
            if (searchString.contains(query)) {
                results.add(contact);
            }
        }
        return results;
    }

    public void addContact(Contact contact) {
        contactDAO.addContact(contact);
    }
}