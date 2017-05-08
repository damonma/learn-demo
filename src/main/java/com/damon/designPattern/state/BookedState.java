package com.damon.designPattern.state;

/**
 * BookedState
 *
 * @author damon
 * @date 2017/5/8
 */
public class BookedState implements State {

    private Room hotelManagement;

    public BookedState(Room hotelManagement) {
        this.hotelManagement = hotelManagement;
    }

    public void bookRoom() {
        System.out.println("该房间已经预定了");
    }

    public void unsubscribeRoom() {
        System.out.println("成功退订");
        this.hotelManagement.setState(this.hotelManagement.getFreeTimeState());
    }

    public void checkInRoom() {
        System.out.println("入住成功");
        this.hotelManagement.setState(this.hotelManagement.getCheckInState());
    }

    public void checkOutRoom() {
    }
}
