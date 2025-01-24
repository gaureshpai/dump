//F5_MultiplePublicClasses.java
/*
	Multiple classes can be written in a single source code
	Among them only one class can be declared as public
		=> (public class name and source code name must be same)
*/

//Manga.java
public class Manga{
	public static void main(String[] args){
		System.out.println("Hello Manga");
	}
}

//public class Dinga{
//CTE:: Dinga is public; declare in Dinga.java
class Dinga{
	public static void main(String[] args){
		System.out.println("Hello Dinga");
	}
}

class Tinga{
	public static void main(String[] args){
		System.out.println("Hello Tinga");
	}
}
