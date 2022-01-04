package com.damon.designPatterns.state;

/**
 * CheckInState
 *
 * @author damon
 * @date 2017/5/8
 */
public class CheckInState implements State {

    private Room hotelManagement;

    public CheckInState(Room hotelManagement) {
        this.hotelManagement = hotelManagement;
    }

    public void bookRoom() {
        System.out.println("该房间已经入住了");
    }

    public void unsubscribeRoom() {
    }

    public void checkInRoom() {
        System.out.println("该房间已经入住了");
    }

    public void checkOutRoom() {
        System.out.println("退房成功");
        this.hotelManagement.setState(this.hotelManagement.getFreeTimeState());
    }
}
