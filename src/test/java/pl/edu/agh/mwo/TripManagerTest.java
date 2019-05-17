package pl.edu.agh.mwo;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TripManagerTest {

	TripManager tripManager;
	Trip trip;
	
	@Before
	public void setUp() {
		tripManager = new TripManager();
		trip = new Trip("name", "description");
	}
	
	@Test
	public void testAdd() throws TripAlreadyExistsException {
		assertEquals(0, tripManager.getTrips().size());
		tripManager.add(trip);
		assertEquals(1, tripManager.getTrips().size());
	}

	@Test(expected = TripAlreadyExistsException.class)
	public void testAddTripTwice() throws TripAlreadyExistsException {
		assertEquals(0, tripManager.getTrips().size());
		tripManager.add(trip);
		assertEquals(1, tripManager.getTrips().size());
		tripManager.add(trip);
	}

	@Test
	public void testRemoveTrip() throws Exception {
		tripManager.add(trip);
		assertEquals(1, tripManager.getTrips().size());
		tripManager.remove(trip.getName());
		assertEquals(0, tripManager.getTrips().size());		
		}
	
	@Test
	public void testFindTripByEmptyName() throws Exception {
		tripManager.add(trip);
		assertSame(trip, tripManager.findTrip(""));
	}
	
	@Test
	public void testFindByTripName() throws Exception {
		Trip trip2 = new Trip("fun", "description");
		tripManager.add(trip);
		tripManager.add(trip2);
		ArrayList<Trip> found = tripManager.findTrip("fun");
		assertEquals(1, found.size());
		assertEquals(trip, found.get(0));
	}	
	
	@Test
	public void testFindByDescription() throws Exception {
		Trip trip2 = new Trip("name", "my secret description");
		tripManager.add(trip);
		tripManager.add(trip2);
		assertSame(trip2, tripManager.findTrip("secret"));
	}
	
	@Test
	public void testFindByPhotoCommentKeyword() throws Exception {
		Trip trip2 = new Trip("", "description");
		Photo photo = new Photo();
		photo.setComment("comment");
		trip2.addPhoto(photo);
		tripManager.add(trip2);
		tripManager.add(trip);
		ArrayList<Trip> found = tripManager.findTrip("comment");
		assertEquals(1, found.size());
	}
	
}
