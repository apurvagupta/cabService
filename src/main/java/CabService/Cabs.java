package CabService;



import com.googlecode.totallylazy.Predicate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.googlecode.totallylazy.Sequences.sequence;

public class Cabs {
    private ArrayList<Cab> cabs = new ArrayList<Cab>();

    public Cabs(Cab ...cabPool) {
        Collections.addAll(cabs, cabPool);
    }

    public List<Cab> freeCabs(){
        return sequence(cabs).filter(new Predicate<Cab>() {
            public boolean matches(Cab cab) {
                return cab.getAvailability().equals(Availability.FREE);
            }
        }).toList();
    }

    public List<Cab> pinkAndFreeCabs(){
        return sequence(cabs).filter(new Predicate<Cab>() {
            public boolean matches(Cab cab) {
                return cab.getCarType().equals(CarType.PINK) && cab.getAvailability().equals(Availability.FREE);
            }
        }).toList();
    }
}

