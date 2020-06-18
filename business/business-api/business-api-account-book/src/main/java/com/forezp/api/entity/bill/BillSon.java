package com.forezp.api.entity.bill;

import com.forezp.api.entity.bill_subtable.BillSubtable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

@Data
public class BillSon extends Bill {
    private List<BillSubtable> billSubtableList;
}
