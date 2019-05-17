package pl.edu.agh.mwo;
import java.util.*;

public class TripManager {
	private HashMap<String,Trip> tripList;
	
	public TripManager() {
		tripList = new HashMap<String,Trip>();
	}
	
	public void add(Trip trip) throws TripAlreadyExistsException {
		if (tripList.get(trip.getName()) != null) {
			throw new TripAlreadyExistsException();
		}
		else {
			tripList.put(trip.getName(),trip);
		}
	}
	
	public HashMap<String,Trip> getTrips() {
		return tripList;
	}

	public void remove(String name) {
		tripList.remove(name);
	}
	
	public ArrayList<Trip> findTrip(String keyword) {	
		ArrayList<Trip> found = new ArrayList<Trip>();
		
        for (String trip : tripList.keySet()) {
            if (keyword.isEmpty()) {
            	found.add(tripList.get(trip));
            }
        	if (trip.contains(keyword) ||
                    tripList.get(trip).getDescription().contains(keyword)) {
        		found.add(tripList.get(trip));
                return found;
            }
        	ArrayList<Photo> photos = tripList.get(trip).getPhotos();
        	for (Photo photo : photos) {
        		if (photo.getComment().contains(keyword)) {
        			found.add(tripList.get(trip));
				}
			}
        }
        return found;
	}
}
