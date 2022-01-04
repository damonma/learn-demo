package com.damon.designPatterns.state;

/**
 * State
 *
 * @author damon
 * @date 2017/5/8
 */
public interface State {

    public void bookRoom();

    public void unsubscribeRoom();

    public void checkInRoom();

    public void checkOutRoom();
}
