package is.ru.stringcalculator;

public class Calculator {

	public static int add(String text) {
		if(text.equals("")){
			return 0;
		}
        	else if (text.length() > 3 && text.substring(0, 2).equals("//") ) {
        		if(text.substring(2, 3).equals("[")) {	
        		    	String delimiter = (text.substring(text.indexOf("["), text.indexOf("\n")));
        		    	text = (text.substring(text.indexOf("\n") + 1));
        		    	while(delimiter.contains("[")) {
        		    		String singleDelimiter = "";
        		    		singleDelimiter = (delimiter.substring(delimiter.indexOf("[") + 1, delimiter.indexOf("]")));
        		    		delimiter = delimiter.substring(delimiter.indexOf("]") + 1);
        		    		String endDelimenator = "";
        		    		for(int i = 0; i< singleDelimiter.length(); i++) {
        		    			endDelimenator += "\\" +  singleDelimiter.charAt(i);
        		    		}
        		    		text = text.replaceAll(endDelimenator, ",");
        		    	}
        		    	return sum(splitNumbers(text));
			}
			String delimiter = text.substring(2, 3);
        		text = (text.substring(4)).replaceAll(delimiter, ",") ;
        		return sum(splitNumbers(text) );
        	}
		else if(text.contains(",") || text.contains("\n") ) {
			return sum(splitNumbers(text));
		}
		else
			return 1;
	}

	private static int toInt(String number) {
		return Integer.parseInt(number);
	}

	private static String[] splitNumbers(String numbers) {
	    	return numbers.split("(,)|(\n)");
	}
      
        private static int sum(String[] numbers) {
 	    	String negative = "";
		int total = 0;
            	for(String number : numbers) {
	        	if(toInt(number) > 1000) {
	        	return 2;
	        }
			if(toInt(number) < 0) {
	        		negative += (number + ",");
	        	}
	        	else {
	        		total += toInt(number);
	        	}
	 	}
		if(!negative.isEmpty()) {
	        	throw new RuntimeException("Negatives not allowed: " + negative.substring(0, negative.length() - 1));
	       	}
		return total;
        }
}
