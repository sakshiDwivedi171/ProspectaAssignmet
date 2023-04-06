package com.prospecta.DTO;

import java.util.List;
import com.prospecta.model.Entry;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EntryData {
	
	private int count;
	private List<Entry> entries;

}
