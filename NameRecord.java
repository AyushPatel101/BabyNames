
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

//a class that creates a NameRecord object. The class implements 
//comparable<NameRecord> in order to override and then use compareTo method

public class NameRecord implements Comparable<NameRecord> {
	private String name;
	private int baseDecade;
	private ArrayList<Integer> ranks;
	// represents the lowest rank that would still be on list
	// ex: for top 1000, lowest rank still ranked is 1000
	private static final int BOTTOM_RANK = 1000;

	/*
	 * Creates a NameRecord with a name, base decade, and an ArrayList of
	 * Integers that stores the names rank per decade. A "deep" copy of ranks is
	 * made. Changes to ranks after hits constructor do not affect this
	 * NameRecord and changes to this NameRecord do not affect ranks
	 * 
	 * @param name contains at least one letter, ranks!=null
	 */
	public NameRecord(String name, int baseDecade, ArrayList<Integer> ranks) {
		if (name == null || name.trim().isEmpty() || ranks == null)
			throw new IllegalArgumentException(
					"name cannot be empty and ranks cannot be null");
		this.ranks = new ArrayList<>();
		this.name = name;
		this.baseDecade = baseDecade;
		// store make a deep copy of ranks to store in this.ranks
		for (int rank : ranks) {
			if (rank != 0)
				this.ranks.add(rank);
			else
				// storing BOTTOM_RANK + 1 as placeholder for 0 for easier
				// computations, using BOTTOM_RANK + 1 because no name can have
				// that rank and 0 is lower rank than BOTTOM_RANK
				this.ranks.add(BOTTOM_RANK + 1);
		}

	}

	// pre: none
	// returns name
	public String getName() {
		return name;
	}

	// pre: none
	// returns base decade
	public int getBaseDecade() {
		return baseDecade;
	}

	// pre: 0 <= index < ranks.size()
	// returns the rank at that index
	public int getRank(int index) {
		if (index < 0 || index >= ranks.size())
			throw new IllegalArgumentException("index out of bounds");
		// if found placeholder for 0 (BOTTOM_RANK + 1), returns 0
		if (ranks.get(index) == BOTTOM_RANK + 1)
			return 0;
		return ranks.get(index);
	}

	// pre: none
	// returns an int decade of highest rank for the name
	public int getBestDecade() {
		// highestRank starting at placeholder for 0, the lowest rank
		int highestRank = BOTTOM_RANK + 1;
		for (int rank : ranks) {
			highestRank = Math.min(rank, highestRank);
		}
		// using lastIndexOf to get most recent decade of highestRank
		return baseDecade + (ranks.lastIndexOf(highestRank) * 10);
	}

	// pre: none
	// returns the number of times name is in top 1000
	public int numTimesTop1000() {
		int timesTop1000 = 0;
		for (int rank : ranks)
			if (rank <= BOTTOM_RANK)
				timesTop1000++;
		return timesTop1000;
	}

	// pre: none
	// returns true if name is in top 1000 in every decade, false otherwise
	public boolean top1000EveryDecade() {
		for (int rank : ranks)
			// for each rank, if its >BOTTOM_RANK (1000), then its not in top
			// 1000 every decade
			if (rank > BOTTOM_RANK)
				return false;
		return true;

	}

	// pre: none
	// returns true if name is in top 1000 in only one decade, false otherwise
	public boolean top1000OnceDecade() {

		int timesTop1000 = 0;
		for (int rank : ranks) {
			// for each rank, if its <=BOTTOM_RANK (1000), then it is in top
			// 1000, so increment timesTop1000
			if (rank <= BOTTOM_RANK)
				timesTop1000++;
			// if timesTop1000 > 1, then in top 1000 more than 1 time, so return
			// false
			if (timesTop1000 > 1)
				return false;

		}
		// timesTop1000 can only be 0 or 1 at this point (because of previous
		// if), so if its 0 want to return false but if 1 want to return true
		return timesTop1000 == 1;

	}

	// pre: none
	// returns true if name gains popularity every decade
	public boolean morePopular() {
		for (int index = 1; index < ranks.size(); index++)
			// for each rank, if the rank in the previous decade is <= the rank
			// in the current decade, then name doesn't get more popular every
			// decade
			if (ranks.get(index - 1) <= ranks.get(index))
				return false;
		return true;
	}

	// pre: none
	// returns true if name loses popularity every decade
	public boolean lessPopular() {
		for (int index = 1; index < ranks.size(); index++)
			// for each rank, if the rank in the previous decade is >= the rank
			// in the current decade, then name doesn't get less popular every
			// decade
			if (ranks.get(index - 1) >= ranks.get(index))
				return false;
		return true;
	}

	// pre: none
	// returns formatted string of NameRecord
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append(name + '\n');
		for (int index = 0; index < ranks.size(); index++) {
			// baseDecade + (index *10) because index 0 represents base decade,
			// and each index represents the next decade.

			// printing the remainder of rank/(BOTTOM_RANK+1) because if its a
			// 0, then it will be stored as BOTTOM_RANK + 1, therefore by
			// printing remainder of previous statement we can print 0 when
			// appropriate
			output.append(baseDecade + (index * 10) + ": "
					+ ranks.get(index) % (BOTTOM_RANK + 1) + '\n');
		}
		return output.toString();
	}

	// pre: other!=null
	// return 0 if names are the same, <0 if this name is lexicography less
	// than other.getName(), >0 if this name is lexicographically greater than
	// other.getName() (ignoring case)
	public int compareTo(NameRecord other) {
		if (other == null)
			throw new IllegalArgumentException("NameRecord cannot be null");
		// ignores case and calls String compareTo method on the names
		return getName().compareToIgnoreCase(other.getName());
	}

	// pre: none
	// return number of times name is rank 1 over all decades
	public int getTimesRank1() {
		int numTimesRank1 = 0;
		for (int rank : ranks)
			// for each rank, if its = 1, then add to numTimesRank1
			if (rank == 1)
				numTimesRank1++;
		return numTimesRank1;
	}

}
