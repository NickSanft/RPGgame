package nick.dev.base;

import nick.dev.audio.AudioManager;
import nick.dev.base.entities.Player;
import nick.dev.dialog.DialogManager;
import nick.dev.display.Display;
import nick.dev.gfx.GameCamera;
import nick.dev.input.KeyManager;
import nick.dev.input.MouseManager;
import nick.dev.utilities.SaveManager;
import nick.dev.utilities.Utilities;
import nick.dev.worlds.World;

public final class Handler {

	private static Game game;
	private static World world;
	private static AudioManager audioManager;
	private static KeyManager keyManager;
	private static SaveManager saveManager;
	private static MouseManager mouseManager;
	private static DialogManager dialogManager;
	private static Display display;
	
	public static void init(Game g) {
		game = g;
		audioManager = new AudioManager();
		saveManager = new SaveManager();
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	public static void update() {
		keyManager.update();
		audioManager.update();
		mouseManager.update();
	}

	public static GameCamera getGameCamera() {
		return game.getGameCamera();
	}

	public static KeyManager getKeyManager() {
		return keyManager;
	}

	public static MouseManager getMouseManager() {
		return mouseManager;
	}

	public static Integer getWidth() {
		return game.getWidth();
	}

	public static Integer getHeight() {
		return game.getHeight();
	}

	public static Game getGame() {
		return game;
	}

	public static World getWorld() {
		return world;
	}

	public static void setWorld(World w) {
		world = w;
	}
	
	public static void setDisplay(Display d) {
		display = d;
	}

	public static Player getPlayer() {
		return getWorld().getEntityManager().getPlayer();
	}

	public static AudioManager getAudioManager() {
		return audioManager;
	}

	public static SaveManager getSaveManager() {
		return saveManager;
	}
	

	public static Display getDisplay() {
		return display;
	}

	public static DialogManager getDialogManager() {
		return dialogManager;
	}

}
