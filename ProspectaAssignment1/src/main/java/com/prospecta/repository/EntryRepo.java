package com.prospecta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.prospecta.model.Entry;

public interface EntryRepo extends JpaRepository<Entry, String>{

}
