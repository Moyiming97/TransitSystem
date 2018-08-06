package edu.toronto.group0162.service;


import edu.toronto.group0162.dao.*;
import edu.toronto.group0162.entity.Card;
import edu.toronto.group0162.entity.TripSegment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class TripSegmentService {

    @Getter
    private final TripSegmentDao tripSegmentDao;
    private final GeneralTripDao generaltripDao;
    private final CardService cardService;
    private final StationService staionService;
    //private final TimeService timeService;
    private final double BUS_FARE = 2;
    private final double SUBWAY_FARE = 0.5;
    private final double CAP = 6;

    public TripSegment updateEnter(TripSegment tripSegment, Card card, String transitLine){
        if(transitLine.contains("Bus")){
            //update tripSegment's cap
            double capUpdated = tripSegment.getCap();
            if(0.0<=capUpdated && capUpdated <=BUS_FARE){tripSegment.setCap(0);}
            if(capUpdated >BUS_FARE){
            tripSegment.setCap(capUpdated-BUS_FARE);}
        //update(reduce) user's card balance
        this.cardService.addBalance(card,-BUS_FARE);
        //update tripSegment tables' fare with tsid only
        tripSegment.setFare(BUS_FARE);
        this.tripSegmentDao.updateEnterBus(tripSegment);}
        return tripSegment;
    }

    public TripSegment updateExit(TripSegment tripSegment, Card card, String transitLine){
        if(transitLine.contains("Bus")){
            this.tripSegmentDao.updateExitBus(tripSegment);}
        if(transitLine.contains("Subway")){
            double capUpdated = tripSegment.getCap();
            double expectedCharge = (this.staionService.calculatePoints(transitLine,tripSegment.getEntrance(),tripSegment.getExit()))*SUBWAY_FARE;
            //long tripDuration = this.timeService.getHourDifference(tripSegment.getEnterTime(),tripSegment.getExitTime());
            if(expectedCharge <= CAP){
                if(expectedCharge<=capUpdated){
                tripSegment.setFare(expectedCharge);
                tripSegment.setCap(capUpdated-expectedCharge);
                this.cardService.addBalance(card,-expectedCharge);}
                else{
                    tripSegment.setFare(capUpdated);
                    tripSegment.setCap(0);
                    this.cardService.addBalance(card,-capUpdated);
                }
            }
            if(expectedCharge >= CAP){
                tripSegment.setFare(CAP);
                tripSegment.setCap(0);
                this.cardService.addBalance(card,-CAP);
            }
            this.tripSegmentDao.updateExitSubway(tripSegment);
        }
        return tripSegment;
    }



}
