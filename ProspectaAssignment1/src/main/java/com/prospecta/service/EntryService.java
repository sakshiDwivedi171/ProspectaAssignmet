package com.prospecta.service;

import java.util.List;

import com.prospecta.exception.EntryException;
import com.prospecta.model.Entry;


public interface EntryService {

	public List<Entry> saveNewEntries(List<Entry> entry) throws EntryException;	
}
