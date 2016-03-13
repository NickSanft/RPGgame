package nick.dev.base.entities;

import java.util.Arrays;

import nick.dev.base.Handler;
import nick.dev.gfx.Animation;
import nick.dev.gfx.Assets;
import nick.dev.utilities.Utilities;

public class Player extends Creature {
	private Integer battleChance, currentExperience;
	private boolean goToBattle = false;
	private int[] levelArray = new int[100];
	private boolean talk = false;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

		bounds.x = Integer.parseInt(Utilities.getPropValue("playerBoundsX", Utilities.getPropFile()));
		bounds.y = Integer.parseInt(Utilities.getPropValue("playerBoundsY", Utilities.getPropFile()));
		bounds.width = Integer.parseInt(Utilities.getPropValue("playerBoundswidth", Utilities.getPropFile()));
		bounds.height = Integer.parseInt(Utilities.getPropValue("playerBoundheight", Utilities.getPropFile()));

		// Animations

		Integer playerAnimSpeed = Integer.parseInt(Utilities.getPropValue("playerAnimSpeed", Utilities.getPropFile()));
		animDown = new Animation(playerAnimSpeed, Assets.player_down);
		animUp = new Animation(playerAnimSpeed, Assets.player_up);
		animLeft = new Animation(playerAnimSpeed, Assets.player_left);
		animRight = new Animation(playerAnimSpeed, Assets.player_right);

		this.setAttack(3);
		this.setDefense(2);
		this.setEntityName("Alex");
		this.setCurrentExperience(0);

		for (int i = 0; i < 100; i++) {
			if (i == 0) {
				levelArray[i] = 82;
			} else {
				levelArray[i] = levelArray[i - 1] + (levelArray[i - 1] / 2);
			}
		}

		Utilities.Debug("Levels: " + Arrays.toString(levelArray));
	}

	public Integer getCurrentExperience() {
		return currentExperience;
	}

	public void setCurrentExperience(Integer currentExperience) {
		this.currentExperience = currentExperience;
	}

	@Override
	public void update() {
		animDown.update();
		animUp.update();
		animLeft.update();
		animRight.update();

		if (!isGoToBattle()) {
			getInput();
			move();
			if (xMove > 0 || yMove > 0) {
				this.setGoToBattle(Utilities.battleChance(getBattleChance()));
			}
			handler.getGameCamera().centerOnEntity(this);
		}

		if (this.getCurrentExperience() > levelArray[this.getLevel()]) {
			System.out.println("Level Up!");
			this.levelUp();
		}
	}

	private void levelUp() {
		this.setLevel(this.getLevel() + 1);
		this.setAttack(this.getAttack() + 1);
		this.setDefense(this.getDefense() + 1);
		this.setSpeed(this.getSpeed() + 1);
		this.setCurrentHealth(this.getCurrentHealth() + 10);
		this.setMaxHealth(this.getMaxHealth() + 10);
		this.getStats();
	}

	public void getStats() {
		System.out.println("Level: " + this.getLevel());
		System.out.println("Attack: " + this.getAttack());
		System.out.println("Defense: " + this.getDefense());
		System.out.println("Speed: " + this.getSpeed());
		System.out.println("Maximum Health: " + this.getMaxHealth());
		System.out.println("Current Health: " + this.getCurrentHealth());
		System.out.println("Current Experience: " + this.getCurrentExperience());
	}

	private void getInput() {
		xMove = 0;
		yMove = 0;

		if (handler.getKeyManager().up) {
			yMove = -speed;
		}
		if (handler.getKeyManager().down) {
			yMove = +speed;
		}
		if (handler.getKeyManager().left) {
			xMove = -speed;
		}
		if (handler.getKeyManager().right) {
			xMove = +speed;
		}
		if(handler.getKeyManager().space){
			this.setTalk(true);
		}
	}

	public Integer getBattleChance() {
		return battleChance;
	}

	public void setBattleChance(Integer battleChance) {
		this.battleChance = battleChance;
	}

	public boolean isGoToBattle() {
		return goToBattle;
	}

	public void setGoToBattle(boolean goToBattle) {
		this.goToBattle = goToBattle;
	}

	public boolean isTalk() {
		return talk;
	}

	public void setTalk(boolean talk) {
		this.talk = talk;
	}

}