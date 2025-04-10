package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

    private Integer roomNumber;
    private Date checkIn;
    private Date checkinOut;

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Reservation(Integer roomNumber, Date checkIn, Date checkinOut) {
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkinOut = checkinOut;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckinOut() {
        return checkinOut;
    }

    public long duration() {
        long diff = checkinOut.getTime() - checkIn.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public String updateDates(Date checkIn, Date checkinOut) {
        Date now = new Date();
        if (checkIn.before(now) || checkinOut.before(now)) {
            return "Reservation dates for update must be future dates!";
        } else if (!checkinOut.after(checkIn)) {
            return  "Error in reservation: Check-out date must be after check-in date!";
        }
        this.checkIn = checkIn;
        this.checkinOut = checkinOut;
        return null;
    }

    @Override
    public String toString() {
        return "Room "
                + roomNumber
                + ", check-in: "
                + sdf.format(checkIn)
                + ", check-out: "
                + sdf.format(checkinOut)
                + ", "
                + duration()
                + " nights";
    }
}