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
        return contactDAO.getAllContacts()
                .stream()
                .filter(contact -> isContactMatched(contact, query))
                .toList();
    }

    private boolean isContactMatched(Contact contact, String query) {
        if (query == null || query.isEmpty()) return true;
        query = query.toLowerCase();
        String searchString = contact.getFullName()
                + " " + contact.getEmail()
                + " " + contact.getPhone();
        return searchString.toLowerCase().contains(query);
    }

    public void addContact(Contact contact) {
        contactDAO.addContact(contact);
    }

    public List<Contact> getAllContacts() {
        return contactDAO.getAllContacts();
    }

    public void updateContact(Contact contact) {
        contactDAO.updateContact(contact);
    }

    public void deleteContact(Contact contact) {
        contactDAO.deleteContact(contact);
    }
}