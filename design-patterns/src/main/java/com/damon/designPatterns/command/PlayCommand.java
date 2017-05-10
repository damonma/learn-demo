package com.damon.designPatterns.command;

/**
 * PlayCommand
 *
 * @author damon
 * @date 2017/5/5
 */
public class PlayCommand implements Command {

    private AudioPlayer audioPlayer;

    public PlayCommand(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    public void execute() {
        audioPlayer.play();
    }
}
