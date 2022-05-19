package tk.shanebee.hg;

import tk.shanebee.hg.data.Language;
import tk.shanebee.hg.util.Util;

/**
 * Game status types
 */
public enum Status {

	/**
	 * Game is running
	 */
	RUNNING,
	/**
	 * Game has stopped
	 */
	STOPPED,
	/**
	 * Game is ready to run
	 */
	READY,
	/**
	 * Game is waiting
	 */
	WAITING,
	/**
	 * Game is broken
	 */
	BROKEN,
	/**
	 * Game is currently rolling back blocks
	 */
	ROLLBACK,
	/**
	 * Game is not ready
	 */
	NOTREADY,
	/**
	 * Game is starting to run
	 */
	BEGINNING,
	/**
	 * Game is counting down to start
	 */
	COUNTDOWN;

	final Language lang = HG.getPlugin().getLang();

	public String getName() {
		return switch (this) {
			case RUNNING -> Util.getColString(lang.status_running);
			case STOPPED -> Util.getColString(lang.status_stopped);
			case READY -> Util.getColString(lang.status_ready);
			case WAITING -> Util.getColString(lang.status_waiting);
			case BROKEN -> Util.getColString(lang.status_broken);
			case ROLLBACK -> Util.getColString(lang.status_rollback);
			case NOTREADY -> Util.getColString(lang.status_not_ready);
			case BEGINNING -> Util.getColString(lang.status_beginning);
			case COUNTDOWN -> Util.getColString(lang.status_countdown);
		};
	}

}
