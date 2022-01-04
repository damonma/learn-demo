package com.damon.designPatterns.state;

/**
 * Room
 *
 * @author damon
 * @date 2017/5/8
 */
public class Room {

    private State freeTimeState;

    private State checkInState;

    private State bookedState;

    private State state;

    public Room() {
        this.freeTimeState = new FreeTimeState(this);
        this.checkInState = new CheckInState(this);
        this.bookedState = new BookedState(this);
        this.state = freeTimeState;
    }

    public void bookRoom()
    {
        state.bookRoom();
    }
    public void unsubscribeRoom()
    {
        state.unsubscribeRoom();
    }
    public void checkInRoom()
    {
        state.checkInRoom();
    }
    public void checkOutRoom()
    {
        state.checkOutRoom();
    }

    public String toString()
    {
        return "该房间的状态是:"+getState().getClass().getName();
    }

    public State getFreeTimeState()
    {
        return freeTimeState;
    }

    public void setFreeTimeState(State freeTimeState)
    {
        this.freeTimeState = freeTimeState;
    }

    public State getCheckInState()
    {
        return checkInState;
    }

    public void setCheckInState(State checkInState)
    {
        this.checkInState = checkInState;
    }

    public State getBookedState()
    {
        return bookedState;
    }

    public void setBookedState(State bookedState)
    {
        this.bookedState = bookedState;
    }

    public State getState()
    {
        return state;
    }

    public void setState(State state)
    {
        this.state = state;
    }
}
