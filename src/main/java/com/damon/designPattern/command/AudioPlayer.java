package com.damon.designPattern.command;

/**
 * AudioPlayer
 *
 * @author damon
 * @date 2017/5/5
 */
public class AudioPlayer {

    public void play() {
        System.out.println("Play");
    }

    public void rewind() {
        System.out.println("Rewind");
    }

    public void stop() {
        System.out.println("Stop");
    }
}
