package com.damon.designPattern.state;

/**
 * FreeTimeState
 *
 * @author damon
 * @date 2017/5/8
 */
public class FreeTimeState implements State {

    private Room hotelManagement;

    public FreeTimeState(Room hotelManagement) {
        this.hotelManagement = hotelManagement;
    }

    public void bookRoom() {
        System.out.println("您已经预定成功了！");
        this.hotelManagement.setState(this.hotelManagement.getBookedState());
    }

    public void unsubscribeRoom() {
    }

    public void checkInRoom() {
        System.out.println("您已经入住了！");
        this.hotelManagement.setState(this.hotelManagement.getCheckInState());
    }

    public void checkOutRoom() {
    }
}
