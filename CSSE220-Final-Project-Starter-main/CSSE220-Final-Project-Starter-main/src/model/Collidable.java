package model;

/**
 * Interface for objects that deal with collisions
 * @author Nathan Milhorn
 */

public interface Collidable {
	boolean collidesWith(Player player);

}
