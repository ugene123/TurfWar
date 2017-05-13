package me.armorofglory.tools;

import org.bukkit.scheduler.BukkitRunnable;

import me.armorofglory.Turfwar;

public abstract class Timer extends BukkitRunnable {
	
	private final int start;
	private final int stop;
	private final int step;
	private int time;
	
	public Timer(int start) {
		this(start, 0);
	}
	
	public Timer(int start, int stop) {
		this(start, stop, (start < stop) ? 1 : -1);
	}
	
	public Timer(int start, int stop, int step) {
		this.start = start;
		this.stop = stop;
		this.step = step;
		this.time = start;
	}
	
	public void start() {
		time = start;
		this.runTaskTimer(Turfwar.getPlugin(), 0, 20);
	}
	
	public void run() {
		check(time);
		
		if (time == stop) {
			this.cancel();
		} else {
			time += step;
		}
	}
	
	protected abstract void check(int time);
	
}
