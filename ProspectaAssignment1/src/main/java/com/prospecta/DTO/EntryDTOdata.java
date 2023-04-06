package com.prospecta.DTO;

import java.util.List;
import lombok.Data;


@Data
public class EntryDTOdata {

	
	private int count;
	private List<EntryDTO> filteredList;
}
