package com.damon.designPatterns.command;

/**
 * RewindCommand
 *
 * @author damon
 * @date 2017/5/5
 */
public class RewindCommand implements Command {

    private AudioPlayer audioPlayer;

    public RewindCommand(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    @Override
    public void execute() {
        audioPlayer.rewind();
    }
}
