package com.damon.designPatterns.state;

/**
 * StateTest
 *
 * @author damon
 * @date 2017/5/8
 */
public class StateTest {

    public static void main(String[] args) {
        Room[] rooms = new Room[2];
        for(int i=0;i<rooms.length;i++)
        {
            rooms[i] = new Room();
        }

        rooms[0].bookRoom();
        rooms[0].checkInRoom();
        rooms[0].bookRoom();
        System.out.println(rooms[0]);

        rooms[1].checkInRoom();
        rooms[1].bookRoom();
        rooms[1].checkOutRoom();
        rooms[1].bookRoom();
        System.out.println(rooms[1]);
    }
}
