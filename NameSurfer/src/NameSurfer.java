
/*
 * Student information for assignment: Replace <NAME> in the following with your



 * name. You are stating, on your honor you did not copy any other code on this
 * assignment and have not provided your code to anyone. 
 * 
 * On my honor, Ayush Patel, this programming assignment is my own work 
 * and I have not provided this code
 * to any other student. 
 * UTEID: ap55837
 * email address: patayush01@utexas.edu
 * Number of slip days I am using: 0
 */

import java.io.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class NameSurfer {
	// CS314 students, explain your menu option 7 here:
	// my menu 7 option traverses the namesDatabase, and prints out each time
	// each letter is the first letter in a rank 1 name. If the letter is never
	// the first letter of a rank 1 name, then that letter is not printed
	// ex: if only name was apple and it was rank 1 twice, then the output would
	// be A 2, because A was the first letter of a rank 1 name twice.

	// pre : namesDatabase!=null
	// prints out the number of times each letter was the first letter of the
	// top name in a decade, excluding the letters that were never.
	private static void startLetterRank1(Names namesDatabase) {
		if (namesDatabase == null) {
			throw new IllegalArgumentException(
					"The parameter namesDatabase cannot be null");
		}
		// get the number of times each letter is the first name in a rank 1
		// name
		int[] timesRank1 = namesDatabase.getStartLetterRank1();
		System.out.println(
				"The amount of times each letter is the first letter of the top name in a decade is as follows:");
		for (int index = 0; index < timesRank1.length; index++) {
			// prints out the number of times each letter was the first letter
			// of the
			// top name in a decade, excluding the letters that were never
			if (timesRank1[index] != 0)
				// index + 'A' done to convert index back to the
				// letter it represents
				// prints out letter the number of times it was first letter of
				// rank 1 name
				//  'A' is the ascii value of capital A
				System.out.println(
						(Character.toString((char) (index + 'A'))
								+ " " + timesRank1[index]));
		}

	}
	// CS314 students, Explain your interesting search / trend here:
	// If you look at every valid entry starting with a K, you will notice that
	// vast majority of them have ONLY appeared in the top 1000 in the last 5
	// decades. In other others, for the most part, names starting with a K have
	// gained much more popularity in the past half century compared to the
	// beginning of the 1900s. Out of 259 names that start with a k, only ~20 do
	// not follow this trend.
	// Example: Kacey 0 0 0 0 0 0 0 0 750 684 855
	// Kaci 0 0 0 0 0 0 0 0 792 727 961
	// Kacie 0 0 0 0 0 0 0 0 589 547 636

	// CS314 students, add test code for NameRecord class here:
	private static void testCases() {
		getNameTests();
		getBaseDecadeTests();
		getRankTests();
		getBestDecadeTests();
		numTimesTop1000Tests();
		top1000EveryDecadeTests();
		top1000OnceDecadeTests();
		morePopularTests();
		lessPopularTests();
		toStringTests();
		compareToTests();
		getTimesRank1Tests();
	}

	private static void getNameTests() {
		ArrayList<Integer> listOfRanks = new ArrayList<>();
		listOfRanks.add(0);
		listOfRanks.add(100);
		NameRecord namerecord1 = new NameRecord("Phineas", 2000, listOfRanks);
		NameRecord namerecord2 = new NameRecord("and Ferb", 1900, listOfRanks);
		if (namerecord1.getName().equals("Phineas"))
			System.out.println("Test 1 passed: getName");
		else
			System.out.println("Test 1 failed: getName");
		if (namerecord2.getName().equals("and Ferb"))
			System.out.println("Test 2 passed: getName");
		else
			System.out.println("Test 2 failed: getName");
	}

	private static void getBaseDecadeTests() {
		ArrayList<Integer> listOfRanks = new ArrayList<>();
		listOfRanks.add(0);
		listOfRanks.add(100);
		NameRecord namerecord1 = new NameRecord("Phineas", 2000, listOfRanks);
		NameRecord namerecord2 = new NameRecord("and Ferb", 1900, listOfRanks);
		if (namerecord1.getBaseDecade() == 2000)
			System.out.println("Test 3 passed: getBaseDecade");
		else
			System.out.println("Test 3 failed: getBaseDecade");
		if (namerecord2.getBaseDecade() == 1900)
			System.out.println("Test 4 passed: getBaseDecade");
		else
			System.out.println("Test 4 failed: getBaseDecade");
	}

	private static void getRankTests() {
		ArrayList<Integer> listOfRanks = new ArrayList<>();
		listOfRanks.add(0);
		listOfRanks.add(100);
		NameRecord namerecord1 = new NameRecord("Phineas", 2000, listOfRanks);
		listOfRanks.clear();
		listOfRanks.add(300);
		listOfRanks.add(1000);
		NameRecord namerecord2 = new NameRecord("and Ferb", 1900, listOfRanks);
		if (namerecord1.getRank(0) == 0)
			System.out.println(
					"Test 5 passed: getRank (this test also checks constructor)");
		else
			System.out.println(
					"Test 5 failed: getRank (this test also checks constructor)");
		if (namerecord2.getRank(1) == 1000)
			System.out.println(
					"Test 6 passed: getRank (this test also checks constructor)");
		else
			System.out.println(
					"Test 6 failed: getRank (this test also checks constructor)");
	}

	private static void getBestDecadeTests() {
		ArrayList<Integer> listOfRanks = new ArrayList<>();
		listOfRanks.add(0);
		listOfRanks.add(100);
		NameRecord namerecord1 = new NameRecord("Phineas", 2000, listOfRanks);
		listOfRanks.clear();
		listOfRanks.add(300);
		listOfRanks.add(1000);
		NameRecord namerecord2 = new NameRecord("and Ferb", 1900, listOfRanks);
		if (namerecord1.getBestDecade() == 2010)
			System.out.println("Test 7 passed: getBestDecade");
		else
			System.out.println("Test 7 failed: getBestDecade");
		if (namerecord2.getBestDecade() == 1900)
			System.out.println("Test 8 passed: getBestDecade");
		else
			System.out.println("Test 8 failed: getBestDecade");
	}

	private static void numTimesTop1000Tests() {
		ArrayList<Integer> listOfRanks = new ArrayList<>();
		listOfRanks.add(0);
		listOfRanks.add(100);
		NameRecord namerecord1 = new NameRecord("Phineas", 2000, listOfRanks);
		listOfRanks.clear();
		listOfRanks.add(300);
		listOfRanks.add(300);
		listOfRanks.add(1000);
		listOfRanks.add(0);
		NameRecord namerecord2 = new NameRecord("and Ferb", 1900, listOfRanks);
		if (namerecord1.numTimesTop1000() == 1)
			System.out.println("Test 9 passed: numTimesTop1000");
		else
			System.out.println("Test 9 failed: numTimesTop1000");
		if (namerecord2.numTimesTop1000() == 3)
			System.out.println("Test 10 passed: numTimesTop1000");
		else
			System.out.println("Test 10 failed: numTimesTop1000");
	}

	private static void top1000EveryDecadeTests() {
		ArrayList<Integer> listOfRanks = new ArrayList<>();
		listOfRanks.add(0);
		listOfRanks.add(100);
		NameRecord namerecord1 = new NameRecord("Phineas", 2000, listOfRanks);
		listOfRanks.clear();
		listOfRanks.add(300);
		listOfRanks.add(300);
		listOfRanks.add(1000);
		listOfRanks.add(10);
		NameRecord namerecord2 = new NameRecord("and Ferb", 1900, listOfRanks);
		if (!namerecord1.top1000EveryDecade())
			System.out.println("Test 11 passed: top1000EveryDecade");
		else
			System.out.println("Test 11 failed: top1000EveryDecade");
		if (namerecord2.top1000EveryDecade())
			System.out.println("Test 12 passed: top1000EveryDecade");
		else
			System.out.println("Test 12 failed: top1000EveryDecade");
	}

	private static void top1000OnceDecadeTests() {
		ArrayList<Integer> listOfRanks = new ArrayList<>();
		listOfRanks.add(0);
		listOfRanks.add(0);
		NameRecord namerecord1 = new NameRecord("Phineas", 2000, listOfRanks);
		listOfRanks.clear();
		listOfRanks.add(0);
		listOfRanks.add(0);
		listOfRanks.add(0);
		listOfRanks.add(10);
		NameRecord namerecord2 = new NameRecord("and Ferb", 1900, listOfRanks);
		if (!namerecord1.top1000OnceDecade())
			System.out.println("Test 13 passed: top1000OnceDecade");
		else
			System.out.println("Test 13 failed: top1000Onceecade");
		if (namerecord2.top1000OnceDecade())
			System.out.println("Test 14 passed: top1000OnceDecade");
		else
			System.out.println("Test 14 failed: top1000OnceDecade");
	}

	private static void morePopularTests() {
		ArrayList<Integer> listOfRanks = new ArrayList<>();
		listOfRanks.add(0);
		listOfRanks.add(0);
		NameRecord namerecord1 = new NameRecord("Phineas", 2000, listOfRanks);
		listOfRanks.clear();
		listOfRanks.add(0);
		listOfRanks.add(3);
		listOfRanks.add(2);
		listOfRanks.add(1);
		NameRecord namerecord2 = new NameRecord("and Ferb", 1900, listOfRanks);
		if (!namerecord1.morePopular())
			System.out.println("Test 15 passed: morePopular");
		else
			System.out.println("Test 15 failed: morePopular");
		if (namerecord2.morePopular())
			System.out.println("Test 16 passed: morePopular");
		else
			System.out.println("Test 16 failed: morePopular");
	}

	private static void lessPopularTests() {
		ArrayList<Integer> listOfRanks = new ArrayList<>();
		listOfRanks.add(1);
		listOfRanks.add(0);
		NameRecord namerecord1 = new NameRecord("Phineas", 2000, listOfRanks);
		listOfRanks.clear();
		listOfRanks.add(200);
		listOfRanks.add(201);
		listOfRanks.add(220);
		listOfRanks.add(203);
		NameRecord namerecord2 = new NameRecord("and Ferb", 1900, listOfRanks);
		if (namerecord1.lessPopular())
			System.out.println("Test 17 passed: lessPopular");
		else
			System.out.println("Test 17 failed: lessPopular");
		if (!namerecord2.lessPopular())
			System.out.println("Test 18 passed: lessPopular");
		else
			System.out.println("Test 18 failed: lessPopular");
	}

	private static void toStringTests() {
		ArrayList<Integer> listOfRanks = new ArrayList<>();
		listOfRanks.add(1);
		listOfRanks.add(0);
		NameRecord namerecord1 = new NameRecord("Phineas", 2000, listOfRanks);
		listOfRanks.clear();
		listOfRanks.add(200);
		listOfRanks.add(201);
		listOfRanks.add(220);
		listOfRanks.add(203);
		String expected1 = "Phineas\n2000: 1\n2010: 0\n";
		NameRecord namerecord2 = new NameRecord("and Ferb", 1900, listOfRanks);
		String expected2 = "and Ferb\n1900: 200\n1910: 201\n1920: 220\\n1910: 203\n";
		if (namerecord1.toString().equals(expected1))
			System.out.println("Test 19 passed: toString");
		else
			System.out.println("Test 19 failed: toString");
		if (!namerecord2.toString().equals(expected2))
			System.out.println("Test 20 passed: toString");
		else
			System.out.println("Test 20 failed: toString");
	}

	private static void compareToTests() {
		ArrayList<Integer> listOfRanks = new ArrayList<>();
		listOfRanks.add(1);
		listOfRanks.add(0);
		NameRecord namerecord1 = new NameRecord("Phineas", 2000, listOfRanks);
		NameRecord namerecord2 = new NameRecord("and Ferb", 1900, listOfRanks);
		NameRecord namerecord3 = new NameRecord("And Ferb", 1900, listOfRanks);
		if (namerecord1.compareTo(namerecord2) > 0)
			System.out.println("Test 21 passed: compareTo");
		else
			System.out.println("Test 21 failed: compareTo");
		if (namerecord2.compareTo(namerecord3) == 0)
			System.out.println("Test 22 passed: compareTo");
		else
			System.out.println("Test 22 failed: compareTo");
	}

	private static void getTimesRank1Tests() {
		ArrayList<Integer> listOfRanks = new ArrayList<>(
				Arrays.asList(0, 1, 2, 200, 4, 1000, 1));
		NameRecord namerecord1 = new NameRecord("Phineas", 2000, listOfRanks);
		listOfRanks = new ArrayList<>(
				Arrays.asList(0, 0, 1000, 200, 4, 1000, 1));
		NameRecord namerecord2 = new NameRecord("And Ferb", 1900, listOfRanks);
		if (namerecord1.getTimesRank1() == 2)
			System.out.println("Test 23 passed: getTimesRank1");
		else
			System.out.println("Test 23 failed: getTimesRank1");
		if (namerecord2.getTimesRank1() == 1)
			System.out.println("Test 24 passed: getTimesRank1");
		else
			System.out.println("Test 24 failed: getTimesRank1");
	}

	// A few simple tests for the Names and NameRecord class.
	public static void simpleTest() {
		// raw data for Jake. Alter as necessary for your NameRecord
		String jakeRawData = "Jake 262 312 323 479 484 630 751 453 225 117 97";
		int baseDecade = 1900;
		String[] jakeRaw = jakeRawData.split("\\s+");
		// r we allowed to add this import?
		ArrayList<Integer> jakeRanks = new ArrayList<>();
		for (int i = 1; i < jakeRaw.length; i++)
			jakeRanks.add(Integer.parseInt(jakeRaw[i]));
		NameRecord jakeRecord = new NameRecord("Jake", baseDecade, jakeRanks);
		String expected = "Jake\n1900: 262\n1910: 312\n1920: 323\n1930: 479\n1940: "
				+ "484\n1950: 630\n1960: 751\n1970: 453\n1980: 225\n1990: 117\n2000: 97\n";
		String actual = jakeRecord.toString();
		System.out.println("expected string:\n" + expected);
		System.out.println("actual string:\n" + actual);
		if (expected.equals(actual)) {
			System.out.println("passed Jake toString test.");
		} else {
			System.out.println("FAILED Jake toString test.");
		}

		// Some getName Tests
		final String NAME_FILE = "names.txt";
		Names names = new Names(getFileScannerForNames(NAME_FILE));
		String[] testNames = { "Isabelle", "isabelle", "sel", "X1178", "ZZ",
				"via", "kelly" };
		boolean[] expectNull = { false, false, true, true, true, true, false };
		for (int i = 0; i < testNames.length; i++) {
			performGetNameTest(names, testNames[i], expectNull[i]);
		}
	}

	private static void performGetNameTest(Names names, String name,
			boolean expectNull) {
		System.out.println("Performing test for this name: " + name);
		if (expectNull) {
			System.out.println("Expected return value is null");
		} else {
			System.out.println("Expected return value is not null");
		}
		NameRecord result = names.getName(name);
		if ((expectNull && result == null) || (!expectNull && result != null)) {
			System.out.println("PASSED TEST.");
		} else {
			System.out.println("Failed test");
		}
	}

	// main method. Driver for the whole program
	public static void main(String[] args) {
		testCases();
		final String NAME_FILE = "names.txt";
		Scanner fileScanner = getFileScannerForNames(NAME_FILE);
		Names namesDatabase = new Names(fileScanner);
		fileScanner.close();
		runOptions(namesDatabase);

	}

	// pre: namesDatabase != null
	// ask user for options to perform on the given Names object.
	// Creates a Scanner connected to System.in.
	private static void runOptions(Names namesDatabase) {
		if(namesDatabase==null)
			throw new IllegalArgumentException("Names object cannot be null");
		Scanner keyboard = new Scanner(System.in);
		MenuChoices[] menuChoices = MenuChoices.values();
		MenuChoices menuChoice;
		do {
			showMenu();
			int userChoice = getChoice(keyboard) - 1;
			menuChoice = menuChoices[userChoice];
			if (menuChoice == MenuChoices.SEARCH) {
				search(namesDatabase, keyboard);
			} else if (menuChoice == MenuChoices.ONE_NAME) {
				oneName(namesDatabase, keyboard);
			} else if (menuChoice == MenuChoices.APPEAR_ONCE) {
				appearOnce(namesDatabase);
			} else if (menuChoice == MenuChoices.APPEAR_ALWAYS) {
				appearAlways(namesDatabase);
			} else if (menuChoice == MenuChoices.ALWAYS_MORE) {
				alwaysMore(namesDatabase);
			} else if (menuChoice == MenuChoices.ALWAYS_LESS) {
				alwaysLess(namesDatabase);
			} else if (menuChoice == MenuChoices.STUDENT_SEARCH) {
				startLetterRank1(namesDatabase);
			}
		} while (menuChoice != MenuChoices.QUIT);
		keyboard.close();
	}

	// pre: fileName != null
	// create a Scanner and return connected to a File with the given name.
	private static Scanner getFileScannerForNames(String fileName) {
		if (fileName == null)
			throw new IllegalArgumentException("file name cannot be null");
		Scanner sc = null;
		try {
			sc = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println(
					"Problem reading the data file. Returning null for Scanner"
							+ "object. Problems likely to occur." + e);
		}
		return sc;
	}

	// method that shows names that have appeared in ever decade
	// pre: n != null
	// post: print out names that have appeared in ever decade
	private static void appearAlways(Names namesDatabase) {
		if (namesDatabase == null) {
			throw new IllegalArgumentException(
					"The parameter namesDatabase cannot be null");
		}
		// get every name that has appeared in every decade
		ArrayList<String> names = namesDatabase.rankedEveryDecade();
		System.out.println(
				names.size() + " names appear in every decade. The names are:");
		// print out every name that appears in every decade
		for (String name : names)
			System.out.println(name);

	}

	// method that shows names that have appeared in only one decade
	// pre: n != null
	// post: print out names that have appeared in only one decade
	private static void appearOnce(Names namesDatabase) {
		if (namesDatabase == null) {
			throw new IllegalArgumentException(
					"The parameter namesDatabase cannot be null");
		}
		// get every name that has appeared in only one decade
		ArrayList<String> names = namesDatabase.rankedOnlyOneDecade();
		System.out.println(names.size()
				+ " names appear in exactly one decade. The names are:");
		// print out every name that appears in only one decade
		for (String name : names)
			System.out.println(name);
	}

	// method that shows names that have gotten more popular
	// in each successive decade
	// pre: n != null
	// post: print out names that have gotten more popular in each decade
	private static void alwaysMore(Names namesDatabase) {
		if (namesDatabase == null) {
			throw new IllegalArgumentException(
					"The parameter namesDatabase cannot be null");
		}
		// get every name that has gotten more popular in each decade
		ArrayList<String> names = namesDatabase.alwaysMorePopular();
		System.out.println(
				names.size() + " names are more popular in every decade.");
		// print out every name that has gotten more popular
		for (String name : names)
			System.out.println(name);
	}

	// method that shows names that have gotten less popular
	// in each successive decade
	// pre: n != null
	// post: print out names that have gotten less popular in each decade
	private static void alwaysLess(Names namesDatabase) {
		if (namesDatabase == null) {
			throw new IllegalArgumentException(
					"The parameter namesDatabase cannot be null");
		}
		// get every name that has gotten less popular in each decade
		ArrayList<String> names = namesDatabase.alwaysLessPopular();
		System.out.println(
				names.size() + " names are less popular in every decade.");
		for (String name : names)
			// print out every name that has gotten more popular in each decade
			System.out.println(name);
	}

	// method that shows data for one name, or states that name has never been
	// ranked
	// pre: n != null, keyboard != null and is connected to System.in
	// post: print out the data for n or a message that n has never been in the
	// top 1000 for any decade
	private static void oneName(Names namesDatabase, Scanner keyboard) {
		// Note, no way to check if keyboard actually connected to System.in
		// so we simply assume it is.
		if (namesDatabase == null || keyboard == null) {
			throw new IllegalArgumentException("The parameters cannot be null");
		}
		System.out.print("Enter a name: ");
		// scan the name in from keyboard input
		String name = keyboard.next();
		// get the NameRecord for the name typed in
		NameRecord containNameInput = namesDatabase.getName(name);
		System.out.println();
		// if the NameRecord for the name exists
		if (containNameInput != null)
			System.out.println(containNameInput.toString());
		// if it does not, print the following statement
		else
			System.out.println(name + " does not appear in any decade.");
	}

	// method that shows all names that contain a substring from the user
	// and the decade they were most popular in
	// pre: n != null, keyboard != null and is connected to System.in
	// post: show the correct data
	private static void search(Names namesDatabase, Scanner keyboard) {
		// Note, no way to check if keyboard actually connected to System.in
		// so we simply assume it is.
		if (namesDatabase == null || keyboard == null) {
			throw new IllegalArgumentException("The parameters cannot be null");
		}
		System.out.print("Enter a partial name: ");
		// scan in the partialName from keyboard input
		String partialName = keyboard.next();
		// get all the NameRecords that have that partialName
		ArrayList<NameRecord> records = namesDatabase.getMatches(partialName);
		System.out.println();
		System.out.println("There are " + records.size() + " matches for "
				+ partialName + ".");
		// if there are records for that partialName
		if (records.size() != 0) {
			System.out.println();
			System.out.println(
					"The matches with their highest ranking decade are:");
			// print each name as well as the best decade
			for (NameRecord record : records)
				System.out.println(
						record.getName() + " " + record.getBestDecade());
		}

	}

	// get choice from the user
	// keyboard != null and is connected to System.in
	// return an int that is >= SEARCH and <= QUIT
	private static int getChoice(Scanner keyboard) {
		// Note, no way to check if keyboard actually connected to System.in
		// so we simply assume it is.
		if (keyboard == null) {
			throw new IllegalArgumentException(
					"The parameter keyboard cannot be null");
		}
		int choice = getInt(keyboard, "Enter choice: ");
		keyboard.nextLine();
		// add one due to zero based indexing of enums, but 1 based indexing of
		// menu
		final int MAX_CHOICE = MenuChoices.QUIT.ordinal() + 1;
		while (choice < 1 || choice > MAX_CHOICE) {
			System.out.println();
			System.out.println(choice + " is not a valid choice");
			choice = getInt(keyboard, "Enter choice: ");
			keyboard.nextLine();
		}
		return choice;
	}

	// ensure an int is entered from the keyboard
	// pre: s != null and is connected to System.in
	private static int getInt(Scanner s, String prompt) {
		// Note, no way to check if keyboard actually connected to System.in
		// so we simply assume it is.
		if (s == null) {
			throw new IllegalArgumentException(
					"The parameter s cannot be null");
		}
		System.out.print(prompt);
		while (!s.hasNextInt()) {
			s.next();
			System.out.println("That was not an int.");
			System.out.print(prompt);
		}
		return s.nextInt();
	}

	// show the user the menu
	private static void showMenu() {
		System.out.println();
		System.out.println("Options:");
		System.out.println("Enter 1 to search for names.");
		System.out.println("Enter 2 to display data for one name.");
		System.out.println("Enter 3 to display all names that appear in only "
				+ "one decade.");
		System.out.println("Enter 4 to display all names that appear in all "
				+ "decades.");
		System.out.println("Enter 5 to display all names that are more popular "
				+ "in every decade.");
		System.out.println("Enter 6 to display all names that are less popular "
				+ "in every decade.");
		System.out.println(
				"Enter 7 to display all letters that are the first letter of rank 1 name.");
		System.out.println("Enter 8 to quit.");
		System.out.println();
	}

}