/**
 * 
 */
package com.bookkeepo.accounting.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sachingoyal
 *
 */
public class Constants {

	public static String SUPPLY_OUTWARDS_REPORTS[] = { "Document type", "Document no.", "Document date",
			"Date of supply", "Place of Supply", "Name of party", "GSTIN of party", "Taxable Value", "CGST", "SGST",
			"IGST", "Total invoice amount" };

	public static String SUPPLY_INWARDS_REPORTS[] = { "Document type", "Document no.", "Document date", "Name of party",
			"GSTIN of party", "Taxable Value", "CGST", "SGST", "IGST", "Total invoice amount" };

	public static String CREDIT_DEBIT_NOTE_COLUMNS[] = { "Document type", "Document no.", "Document date",
			"Original invoice no.", "Original invoice date", "Name of party", "GSTIN of party", "Taxable Value", "CGST",
			"SGST", "IGST", "Total invoice amount" };
	
	public static String BILL_OF_SUPPLY[] = {"Document no.", "Document date",
			"Date of supply", "Place of Supply", "Name of party", "Total invoice amount" };

	public static String FORGET_PASSWORD_IDENTIFIER = "0";
	
	public static int INVOICE_REF_COUNT = 5;
	
    public static final Map<String, String> gstCodeStateMap = initMap();
    
    public static final List<String> expenseAccountTypes = initExpenseList();
    
    public static final List<String> incomeAccountTypes = initIncomeList();

    private static Map<String, String> initMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("00","Select State");
        map.put("01", "JAMMU & KASHMIR");
        map.put("02", "HIMACHAL PRADESH	");
        map.put("03", "PUNJAB");
        map.put("04", "CHANDIGARH");
        map.put("05", "UTTARAKHAND");
        map.put("06", "DELHI");
        map.put("08", "RAJASTHAN");
        map.put("09", "UTTAR PRADESH");
        map.put("10", "BIHAR");
        map.put("11", "SIKKIM");
        map.put("12", "ARUNACHAL PRADESH");
        map.put("13", "NAGALAND");
        map.put("14", "MANIPUR");        
        map.put("15", "MIZORAM");
        map.put("16", "TRIPURA");
        map.put("17", "MEGHLAYA");
        map.put("18", "ASSAM");
        map.put("19", "WEST BENGAL");
        map.put("20", "JHARKHAND");
        map.put("21", "ODISHA");
        map.put("22", "CHATTISGARH");
        map.put("22", "MADHYA PRADESH");
        map.put("24", "GUJARAT");
        map.put("25", "DAMAN AND DIU");
        map.put("26", "DADRA AND NAGAR HAVELI");
        map.put("27", "MAHARASHTRA");        
        map.put("28", "ANDHRA PRADESH (Old)");
        map.put("29", "KARNATAKA");
        map.put("30", "GOA");
        map.put("31", "LAKSHWADEEP");
        map.put("32", "KERALA");
        map.put("33", "TAMIL NADU");
        map.put("34", "PUDUCHERRY");
        map.put("35", "ANDAMAN AND NICOBAR ISLANDS");
        map.put("36", "TELANGANA");
        map.put("37", "ANDHRA PRADESH (New)");
        map.put("38", "LADAKH");
        map.put("-1","OTHER");
        return Collections.unmodifiableMap(map);
    }

	private static List<String> initExpenseList() {
		List<String> expenseList = new ArrayList<String>();
		expenseList.add("Indirect Expenses");
		expenseList.add("Direct Expenses");
		return Collections.unmodifiableList(expenseList);
	}
	
	private static List<String> initIncomeList() {
		List<String> incomeList = new ArrayList<String>();
		incomeList.add("Indirect Incomes");
		incomeList.add("Direct Incomes");
		return Collections.unmodifiableList(incomeList);
	}
    
}
