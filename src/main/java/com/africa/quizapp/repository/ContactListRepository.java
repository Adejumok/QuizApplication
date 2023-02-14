package com.africa.quizapp.repository;

import com.africa.quizapp.models.contactModels.ContactList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactListRepository extends JpaRepository<ContactList, Long> {
}
