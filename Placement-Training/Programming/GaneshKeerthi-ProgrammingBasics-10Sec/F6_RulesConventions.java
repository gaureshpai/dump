//F6_RulesConventions.java

/*
	Identifiers Conventions::
	class		System,String	AccountBalance 
	method		println()	accountBalance()
	variable	out		accountBalance

	----------------------------------------------------

	Identifier Rule:: shouldn't start with number
	XUV700, Inteli7, JamesBond 007, Henry IV

	----------------------------------------------------

	int i = 10;
	int i20;
	
	int 20i; //CTE:: invalid identifier

	int i_20, i20_, _i20;	// _, $

	-----------------------------------------------------

	Valid
	__i20; __20; __

	Caution::
	From Java version 9 onwards single '_' cannot be used as identifier
	Hence cannot be used as identifier

*/

//class _{
//CTE
class __{
	public static void __(){
		int __ = 100;
		System.out.println(__);
	}

	public static void main(String[] args){
		__();
	}
}
