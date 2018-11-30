/**
 * @author Yannic
 *
 */
public class Bigs {
	
	// addiert die Ziffernfelder a und b
	 public static int[ ] add (int[ ] a, int[ ] b) { 
		 int ueberschlag = 0;
		 int summe = 0;
		 int[] outArr;
		 if (a.length > b.length) {
			 outArr = new int[a.length];
			 for (int i=0; i<b.length; i++) {
				 summe = a[i] + b[i] + ueberschlag;
				 outArr[i] = summe % 10;
				 ueberschlag = (summe - (summe % 10))/10;
			 }
			 for (int j=b.length; j<a.length; j++) {
				 summe = a[j] + ueberschlag;
				 outArr[j] = summe % 10;
				 ueberschlag = (summe - (summe % 10))/10;
			 }
		 } else if (a.length < b.length) {
			 outArr = new int[b.length];
			 for (int i=0; i<a.length; i++) {
				 summe = a[i] + b[i] + ueberschlag;
				 outArr[i] = summe % 10;
				 ueberschlag = (summe - (summe % 10))/10;
			 }
			 for (int j=a.length; j<b.length; j++) {
				 summe = b[j] + ueberschlag;
				 outArr[j] = summe % 10;
				 ueberschlag = (summe - (summe % 10))/10;
			 }
		 } else {
			 outArr = new int[a.length];
			 for (int i=0; i<a.length; i++) {
				 summe = a[i] + b[i] + ueberschlag;
				 outArr[i] = summe % 10;
				 ueberschlag = (summe - (summe % 10))/10;
			 }
		 }
		 if (ueberschlag > 0) {
			 int [] finalArr = extendArrayByOne(outArr);
			 finalArr[finalArr.length - 1] = ueberschlag;
			 return finalArr;
		 } else {
			 return outArr;
		 }
	 }

	 // gibt das Ziffernfeld n in lesbarer dezimaler Form aus
	 // bei sehr langen Zahlen soll das Format verwendet werden, welches auch von
	 // bc (s.u.) benutzt wird: Umbruch nach 68 Ziffern mit einem \ am Ende
	 static void print (int[ ] n) { 
		 String outLine = ""; 
		 int indexReverse = n.length - 1;
		 for (int i = 0; i < n.length; i++) {
			 indexReverse = n.length - 1 - i;
			 outLine = outLine + Integer.toString(n[indexReverse]);
			 if ((i+1) % 68 == 0) {
				 outLine = outLine + "\\";
				 System.out.println(outLine);
				 outLine = "";
			 }
		 }
		 if (!outLine.equals("")) {
			 System.out.println(outLine);
		 }
	 }
	 
	 // konstruiert ein einstelliges Ziffernfeld aus der Ziffer d
	 static int[ ] digit(int d) { 
		 int[] outArr = new int[1];
		 outArr[0] = d;
		 return outArr;
	 }

	 // konstruiert das Ziffernfeld, welches den Wert Null repraesentiert
	 static int[ ] Null() { 
		 int[] outArr = new int[1];
		 outArr[0] = 0;
		 return outArr;
	 }

	 // konstruiert das Ziffernfeld, welches den Wert Eins repraesentiert
	 static int[ ] One() { 
		 int[] outArr = new int[1];
		 outArr[0] = 1;
		 return outArr;
	 }

	 // Rest des Ziffernfeldes n bei Division durch 10 (eine int-Zahl!)
	 static int mod10(int[ ] n) { 
		 return n[0];
	 }

	 // ganzzahliger Quotient bei Division durch 10
	 static int[ ] div10(int[ ] n) { 
		 int[] outArr = new int[n.length-1];
		 for (int i=0; i<outArr.length; i++) {
			 outArr[i] = n[i+1];
		 }
		 return outArr;
	 }

	 // Umwandlung einer beliebigen int-Zahl in ein Ziffernfeld
	 static int[ ] fromInt(int n) { 
		 int multiplicator = 1;
		 while (n/(Math.pow(10, multiplicator)) >= 1) {
			 multiplicator++;
		 }
		 int[] outArr = new int[multiplicator];
		 int rest = n;
		 int digit = 0;
		 for (int i=0; i<multiplicator; i++) {
			 digit = rest % 10;
			 rest = (rest-digit) / 10;
			 outArr[i] = digit; 
		 }
		 return outArr;
	 }

	 // kopiert den Wert von n
	 static int[ ] copy(int[ ] n) { 
		 int[] outArr = new int[n.length];
		 for (int i=0; i<outArr.length; i++) {
			 outArr[i] = n[i];
		 }
		 return outArr;
	 }

	 // multipliziert das Ziffernfeld n mit einer (einstelligen!) int-Zahl
	 static int[ ] times(int[ ] n, int d) { 
		 int ueberschlag = 0;
		 int produkt = 0;
		 int[] outArr = new int[n.length];
		 for (int i=0; i<n.length; i++) {
			 produkt = n[i] * d + ueberschlag;
			 outArr[i] = produkt % 10;
			 ueberschlag = (produkt - (produkt % 10))/10;
		 }
		 if (ueberschlag > 0) {
			 int [] finalArr = extendArrayByOne(outArr);
			 finalArr[finalArr.length - 1] = ueberschlag;
			 return finalArr;
		 } else {
			 return outArr;
		 }
	 }

	 // multipliziert das Ziffernfeld n mit 10
	 static int[ ] times10(int[ ] n) { 
		 int[] outArr = new int[n.length + 1];
		 outArr[0] = 0;
		 for (int i=1; i<outArr.length; i++) {
			 outArr[i] = n[i-1];
		 }
		 return outArr;
	 }

	 
	 // multipliziert zwei Ziffernfelder
	 static int[ ] times(int[ ]a, int[ ] b) {
		 int[] summe = Null();
		 for (int i=0; i<b.length; i++) {
			 if (b[i] != 0) {
			 	int[] summand = times(a,b[i]);
			 	for (int j=0; j<i; j++) {
			 		summand = times10(summand);
			 	}
			 	summe = add(summe,summand);
			 }
		 }
		 return summe;
	 }

	 // Test auf kleiner-Relation zweier Ziffernfelder: a < b ?
	 static boolean less (int[ ] a, int[ ] b) { 
		 if (a.length > b.length) {
			 return false;
		 } else if (a.length < b.length) {
			 return true;
		 } else {
			 for (int i=a.length-1; i>-1; i--) {
				 if (a[i] < b[i]) {
					 return true;
				 } else if (a[i] > b[i]) {
					 return false;
				 }
			 }
			 return false;
		 }
	 }

	 // Test auf Gleichheit zweier Ziffernfelder
	 static boolean equal (int[ ] a, int[ ] b) { 
		 boolean eq = true;
		 if (a.length == b.length) {
			 for (int i=0; i<a.length; i++) {
				 if (a[i] != b[i]) {
					 eq = false;
				 }
			 }
		 } else {
			 eq = false;
		 }
		 return eq;
	 }

	 // Test auf Korrektheit eines Ziffernfeldes: Feld existiert und enthaelt
	 // mindenstens eine Ziffer, alle Positionen liegen zwischen 0 und 9
	 // keine fuehrenden Nullen (ausser bei Null selbst)
	 static boolean ok (int[ ] n) { 
		 if ((n instanceof int []) && (!n.equals(null))) {
			 if (n.length == 1) {
				 if ((n[0] < 10) && (n[0] >= 0)) {
					 return true;
				 } else {
					 return false;
				 }
			 } else {
				 if (n[n.length-1] == 0) {
					 return false;
				 }
				 for (int i=0; i<n.length; i++) {
					 if ((n[i] >= 10) || (n[i] < 0)) {
						 return false;
					 }
				 }
				 return true;
			 }
		 } else {
			 return false;
		 }
	 }

	 // gibt die (kleinste) Ziffer mit der groessten Haeufigkeit in n aus
	 static void maxDigit(int[] n) { 
		 int[] haeufigkeit = new int[10];
		 for (int i=0; i<n.length; i++) {
			 haeufigkeit[n[i]]++;
		 }
		 int[] maxPair = new int[2];
		 maxPair[1] = 0;
		 for (var j=9; j >= 0; j--) {
			 if (haeufigkeit[j] >= maxPair[1]) {
				 maxPair[0] = j;
				 maxPair[1] = haeufigkeit[j];
			 }
		 }
		 System.out.println(Integer.toString(maxPair[0]) + ": " + Integer.toString(maxPair[1]));
	 }

	 public static void main (String[] s) {
		 int[ ] a = One();
		 for (int i=0; i<33222; ++i) { 
			 a = times(a, 2); 
		 }
		 System.out.println("2^33222 hat " + a.length + " Stellen");
		 print(a);
		 System.out.println();
		 int[ ] b = fromInt(13);
		 int[ ] c = copy(b);
		 for (int i=1; i<8978; ++i) { 
			 c = times(c, b); 
		 }
		 System.out.println("13^8978 hat " + c.length + " Stellen");
		 print(c);
		 System.out.println();
		 System.out.println(less(a, c)); 	// beantwortet die Frage aus der
		 									// Aufgabenueberschrift
		 maxDigit(a);
		 maxDigit(c);
	 } 
	 
	 
	 // erweitert ein eingegebenes Array um 1 Stelle
	 private static int[] extendArrayByOne(int[] n) {
		 int[] outArr = new int[n.length + 1];
		 outArr[n.length] = 0;
		 for (int i=0; i<n.length; i++) {
			 outArr[i] = n[i];
		 }
		 return outArr;
	 }
	 
}
