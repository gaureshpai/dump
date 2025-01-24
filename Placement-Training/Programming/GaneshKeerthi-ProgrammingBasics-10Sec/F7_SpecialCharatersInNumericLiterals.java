//F7_SpecialCharatersInNumericLiterals.java

/*
	KK		SK :: 91_9743419125
			VK :: 91_9743411125
			------------------
			91_ to 11_
			------------------

	Credit card number::
			1234_5678_8765_4321
			1st  6 digit :: RuPay/Visa/Master
			Last 6 digit :: Customer

	IFSC :: SBIN_000_4051

	Use cases::
	1. Search operations :: Find and Replace
	2. Debugging :: (modulewise)
*/


class A{
	public static void main(String[] args){
		int i = 10;
		System.out.prinltn(i);	//10

		i = 97_434;
		System.out.println(i);	//97434

		//i = 97434_;
		//CTE:: illegal underscore

		//int _97434 = 1000;
		i = _97434;		//gets treated as variable
		//CTE :: variable not found

		//int x = 2000;
		int y = x;
		//CTE :: variable not found
	}
}












