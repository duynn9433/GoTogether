package duynn.gotogether.service;

import duynn.gotogether.entity.Trip;
import duynn.gotogether.entity.TripStopPlace;
import duynn.gotogether.entity.place.Location;
import duynn.gotogether.repository.ClientRepository;
import duynn.gotogether.repository.TripRepository;
import duynn.gotogether.repository.TripStopPlaceRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class TripStopPlaceService {

    @Autowired
    private TripStopPlaceRepository tripStopPlaceRepository;

    public TripStopPlace save(TripStopPlace tripStopPlace) {
        return tripStopPlaceRepository.save(tripStopPlace);
    }

}
