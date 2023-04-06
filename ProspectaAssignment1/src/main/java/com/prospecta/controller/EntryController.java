package com.prospecta.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.prospecta.DTO.EntryDTO;
import com.prospecta.DTO.EntryDTOdata;
import com.prospecta.DTO.EntryData;
import com.prospecta.exception.EntryException;
import com.prospecta.model.Entry;
import com.prospecta.service.EntryServiceImpl;

@RestController
public class EntryController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private EntryServiceImpl entryServiceImpl;
	
//	Create an API that lists the title, description based on the category passed as an input parameter.
	
	@GetMapping("/entries")
	public ResponseEntity<EntryDTOdata> getRequiredEntriesHandler(@RequestParam String category) throws EntryException{
		
		String url="https://api.publicapis.org/entries";
		
		EntryData data = restTemplate.getForObject(url, EntryData.class);
		
		List<Entry> entries = data.getEntries();
		
		List<EntryDTO> dtos = new ArrayList<>();
		
		for(int i=0; i<entries.size(); i++) {
			String[] cat = entries.get(i).getCategory().split(" ");
			
			if(cat[0].equalsIgnoreCase(category)) {
				
				for(Entry entry : entries) {
					
					if(entry.getCategory().equalsIgnoreCase(entries.get(i).getCategory())) {
						
						dtos.add(new EntryDTO(entry.getApi(), entry.getDescription()));
					}
				}
				
				break;
			}
		}
		
		EntryDTOdata entryDTOdata = new EntryDTOdata();
		entryDTOdata.setCount(dtos.size());
		entryDTOdata.setFilteredList(dtos);
	
		return new ResponseEntity<EntryDTOdata>(entryDTOdata, HttpStatus.OK);
		
	}
	
	
//	Create an API that would save a new entry with all the relevant properties which retrieves values from the endpoint GET /entries.
	
	@PostMapping("/entries")
	public ResponseEntity<List<Entry>> saveEntriesHandler(@RequestBody List<Entry> entry)throws Exception{
		
		String url = "https://api.publicapis.org/entries";
		
		EntryData data = restTemplate.getForObject(url, EntryData.class);
		
		List<Entry> entries = data.getEntries();
		
		entries.addAll(entry);
		
		List<Entry> list = entryServiceImpl.saveNewEntries(entries);
		
		return new ResponseEntity<List<Entry>>(list, HttpStatus.CREATED);
		
	}

}
