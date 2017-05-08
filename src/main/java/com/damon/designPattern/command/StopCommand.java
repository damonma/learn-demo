package com.damon.designPattern.command;

/**
 * StopCommand
 *
 * @author damon
 * @date 2017/5/5
 */
public class StopCommand implements Command {

    private AudioPlayer audioPlayer;

    public StopCommand(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    @Override
    public void execute() {
        audioPlayer.stop();
    }
}
