package com.example.hackaproj.joinr.classes;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Put a short phrase describing the program here.
 * 
 * @author Chrissy Clyde
 * 
 */
public final class SwagCalendarSorter {

	/**
	 * Private constructor so this utility class cannot be instantiated.
	 */
	private SwagCalendarSorter() {
	}

	public static Comparator<Event> eventComparator = new Comparator<Event>() {

		@Override
		public int compare(Event e1, Event e2) {
			int startTimeComp = e1.getStart().compareTo(e2.getStart());
			if (startTimeComp == 0) //Sort first on start time of event
			{
				int endTimeComp = e1.getEnd().compareTo(e2.getEnd());
				if(endTimeComp == 0) //Sort second on end time
				{
					int titleComp = e1.getTitle().compareTo(e2.getTitle());
					if(titleComp == 0) //Sort third on title
					{
						return e1.getStatusId() - e2.getStatusId(); //If all those are equal, just compare IDs
					}
					else
					{
						return titleComp;
					}
				}
				else
				{
					return endTimeComp;
				}
			}
			else
			{
				return startTimeComp;
			}
		}
	};
	public static Comparator<String> userIdComparator = new Comparator<String>() {
		@Override
		public int compare(String u1, String u2){
			
			
			return userComparator.compare(User.importUserFromDataBase(u1), User.importUserFromDataBase(u2));
		}
	};

	public static Comparator<User> userComparator = new Comparator<User>() {

		@Override
		public int compare(User u1, User u2) {
			int availabilityComp = u1.getStatusId() - u2.getStatusId();
			if(availabilityComp == 0) //Sort first on availability type
			{
				int durationComp = u1.getEventList().get(0).getEnd().compareTo(u2.getEventList().get(0).getEnd());
				if(durationComp == 0) //Sort second on duration of availability
				{
					int userNameComp = u1.getName().compareTo(u2.getName());
					if(userNameComp == 0) //Sort third on username alphabetical
					{
						return u1.getID().compareTo(u2.getID()); //If all those are equal, just compare IDs
					}
					else
					{
						return userNameComp;
					}
				}
				else
				{
					return durationComp;
				}
			}
			else
			{
				return availabilityComp;
			}
		}
	};

	private static <T> void partition(ArrayList<T> q, T partitioner,
			ArrayList<T> front, ArrayList<T> back, Comparator<T> order) {
		while (q.size() > 0)
		{
			if(order.compare(q.get(0), partitioner) > 0)
			{
				back.add(q.remove(0));
			}
			else
			{
				front.add(q.remove(0));
			}
		}
	}

	public static <T> void sort(ArrayList<T> q, Comparator<T> order) {
		if (q.size() > 1) {
			//Dequeue the partitioning entry from this
			T partitioner = q.remove(0);

			// Partition this into two queues as discussed above
			ArrayList<T> front = new ArrayList<T>();
			ArrayList<T> back = new ArrayList<T>();
			partition(q, partitioner, front, back, order);

			// Recursively sort the two queues
			sort(front, order);
			sort(back, order);

			/*
			 * Reconstruct this by combining the two sorted queues and the
			 * partitioning entry in the proper order
			 */
			while(front.size() > 0)
			{
				q.add(front.get(0));
			}
			q.add(partitioner);
			while(front.size() > 0)
			{
				q.add(back.get(0));
			}
		}

	}
}
