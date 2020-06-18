package com.forezp.api.entity.bill_subtable;

import com.forezp.api.entity.bill.BillSon;
import lombok.Data;

import java.util.List;

@Data
public class BillSubtableSon extends BillSubtable {
    List<BillSon> billList;
    List<BillSubtableSon> billSubtableMonth;
    List<BillSubtableSon> billSubtableDay;
}
