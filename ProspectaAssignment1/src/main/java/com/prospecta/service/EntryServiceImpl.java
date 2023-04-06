package com.prospecta.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.prospecta.exception.EntryException;
import com.prospecta.model.Entry;
import com.prospecta.repository.EntryRepo;

public class EntryServiceImpl implements EntryService{
	
	@Autowired
	private EntryRepo entryRepo;

	@Override
	public List<Entry> saveNewEntries(List<Entry> entry) throws EntryException{
		
    List<Entry> entries = entryRepo.saveAll(entry);
		
		if(entries.isEmpty()) throw new EntryException("List is empty...");
		
		return entries;
		
//		return null;
	}

}
