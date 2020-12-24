
/*  Student information for assignment:

*
*  On my honor, Ayush Patel, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  UTEID: ap55837
*  email address: patayush01@utexas.edu
*  Number of slip days I am using: 0
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * A collection of NameRecords. Stores NameRecord objects and provides methods
 * to select NameRecords based on various criteria.
 */
public class Names {
	private ArrayList<NameRecord> names;

	/**
	 * Construct a new Names object based on the data source the Scanner sc is
	 * connected to. Assume the first two lines in the data source are the base
	 * year and number of decades to use. Any lines without the correct number
	 * of decades are discarded and are not part of the resulting Names object.
	 * Any names with ranks of all 0 are discarded and not part of the resulting
	 * Names object.
	 * 
	 * @param sc Is connected to a data file with baby names and positioned at
	 *           the start of the data source.
	 */
	public Names(Scanner sc) {
		if (sc == null)
			throw new IllegalArgumentException(
					"Scanner must point to data files with baby names");
		names = new ArrayList<>();
		int baseDecade = sc.nextInt();
		int numDecades = sc.nextInt();
		// while data file has not been fully parsed
		while (sc.hasNextLine()) {
			// store the entire line into a string
			String line = sc.nextLine();
			// split the string into parts, where to part determined by white
			// space, and store into array
			String[] parsedData = line.split("\\s+");
			ArrayList<Integer> ranks = new ArrayList<>();
			boolean atLeastOneRank = false;
			// only add if there is an appropriate amount of ranks (1 per
			// decade)
			if (parsedData.length == numDecades + 1) {
				// starting at 1 because the first index is the name
				for (int i = 1; i < parsedData.length; i++) {
					if (Integer.parseInt(parsedData[i]) != 0)
						// used to know ranks are not all 0, because if they
						// are, should not be added to names
						atLeastOneRank = true;
					// Adding the integer (rank) to the arraylist of ranks
					ranks.add(Integer.parseInt(parsedData[i]));

				}
				// if there is at least 1 rank that is not 0 for the name, then
				// add that record to names
				if (atLeastOneRank)
					names.add(new NameRecord(parsedData[0], baseDecade, ranks));
			}

		}
		//sort arrayList to alphabetize by names 
		Collections.sort(names);
	}

	/**
	 * Returns an ArrayList of NameRecord objects that contain a given
	 * substring, ignoring case. The names must be in sorted order based on
	 * name.
	 * 
	 * @param partialName != null, partialName.length() > 0
	 * @return an ArrayList of NameRecords whose names contains partialName. If
	 *         there are no NameRecords that meet this criteria returns an empty
	 *         list.
	 */
	public ArrayList<NameRecord> getMatches(String partialName) {
		ArrayList<NameRecord> recordsOfPartials = new ArrayList<>();
		// for each record, if the record's name contains the partial name, ignoring
		// case, add it to arrayList storing NameRecords
		partialName= partialName.toUpperCase();
		for (NameRecord record : names) {
			// both casted to uppercase to ignore case
			if (record.getName().toUpperCase()
					.contains(partialName))
				recordsOfPartials.add(record);
		}
		return recordsOfPartials;
	}

	/**
	 * Returns an ArrayList of Strings of names that have been ranked in the top
	 * 1000 or better for every decade. The Strings must be in sorted order
	 * based on name.
	 * 
	 * @return A list of the names that have been ranked in the top 1000 or
	 *         better in every decade. The list is in sorted ascending order. If
	 *         there are no NameRecords that meet this criteria returns an empty
	 *         list.
	 */

	public ArrayList<String> rankedEveryDecade() {
		// for each record, if record is in top 1000 every decade, add its to
		// name to arrayList storing Strings
		ArrayList<String> namesRanked = new ArrayList<>();
		for (NameRecord record : names) {
			if (record.top1000EveryDecade())
				namesRanked.add(record.getName());
		}
		return namesRanked;
	}

	/**
	 * Returns an ArrayList of Strings of names that have been ranked in the top
	 * 1000 or better in exactly one decade. The Strings must be in sorted order
	 * based on name.
	 * 
	 * @return A list of the names that have been ranked in the top 1000 or
	 *         better in exactly one decade. The list is in sorted ascending
	 *         order. If there are no NameRecords that meet this criteria
	 *         returns an empty list.
	 */
	public ArrayList<String> rankedOnlyOneDecade() {
		ArrayList<String> namesRanked = new ArrayList<>();
		// for each record, if record is in top 1000 in only one decade, add its
		// to name to arrayList storing Strings
		for (NameRecord record : names) {
			if (record.top1000OnceDecade())
				namesRanked.add(record.getName());
		}
		return namesRanked;
	}

	/**
	 * Returns an ArrayList of Strings of names that have been getting more
	 * popular every decade. The Strings must be in sorted order based on name.
	 * 
	 * @return A list of the names that have been getting more popular in every
	 *         decade. The list is in sorted ascending order. If there are no
	 *         NameRecords that meet this criteria returns an empty list.
	 */
	public ArrayList<String> alwaysMorePopular() {
		ArrayList<String> namesPopular = new ArrayList<>();
		// for each record, if the name gets more popular every decade, then add
		// its to name to arrayList storing Strings
		for (NameRecord record : names) {
			if (record.morePopular())
				namesPopular.add(record.getName());
		}
		return namesPopular;
	}

	/**
	 * Returns an ArrayList of Strings of names that have been getting less
	 * popular every decade. The Strings must be in sorted order based on name.
	 * 
	 * @return A list of the names that have been getting less popular in every
	 *         decade. The list is in sorted ascending order. If there are no
	 *         NameRecords that meet this criteria returns an empty list.
	 */
	public ArrayList<String> alwaysLessPopular() {
		ArrayList<String> namesPopular = new ArrayList<>();
		// for each record, if the name gets less popular every decade, then add
		// its to name to arrayList storing Strings
		for (NameRecord record : names) {
			if (record.lessPopular())
				namesPopular.add(record.getName());
		}
		return namesPopular;
	}

	/**
	 * Return the NameRecord in this Names object that matches the given String.
	 * <br>
	 * <tt>pre: name != null</tt>
	 * 
	 * @param name The name to search for.
	 * @return The name record with the given name or null if no NameRecord in
	 *         this Names object contains the given name.
	 */
	public NameRecord getName(String name) {
		if (name == null)
			throw new IllegalArgumentException(
					"The parameter name cannot be null");
		// for each record, if the name stored in the record is the same as the
		// parameter, then return that record
		for (NameRecord record : names) {
			if (record.getName().equalsIgnoreCase(name))
				return record;
		}
		// if name not found in NameRecords, then return null
		return null;
	}

	// pre: none
	// returns int[] with index representing letter (index 0 = A), and the value
	// being the number of times that letter is the first letter in a name
	// ranked number 1
	public int[] getStartLetterRank1() {
		final int NUM_LETTERS_ALPHABET = 26;
		int[] startLetterRank1 = new int[NUM_LETTERS_ALPHABET];
		// for each record, get the first letter in order to determine which
		// index to store it at. Then, add the number of times that record was
		// rank 1 to the value at the index
		for (NameRecord record : names) {
			int asciiFirstLetter = record.getName().toUpperCase().charAt(0);
			// index is determined by ascii value of first letter in name - the
			// ascii value of the first letter in alphabet, A (using capitals).
			// add number of times that record is rank 1 to that index.
			
			//'A' is the ascii value of A
			startLetterRank1[asciiFirstLetter - 'A'] += record
					.getTimesRank1();
		}
		return startLetterRank1;
	}
}