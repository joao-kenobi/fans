package fans.core.interfaces;

public interface IJoypadReader {
	void onKeyLeft();
	
	default void onKeyNotLeft() {
	}
	
	void onKeyRight();
	
	default void onKeyNotRight() {
	}
	
	void onKeyUp();
	
	default void onKeyNotUp() {
	}
	
	void onKeyDown();
	
	default void onKeyNotDown() {
	}
}	