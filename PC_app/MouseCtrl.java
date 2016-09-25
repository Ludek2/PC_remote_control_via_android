import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class MouseCtrl {
	
	Robot robot;
	
	public MouseCtrl() throws AWTException{
		robot = new Robot();
		//robot.setAutoDelay(1);
	}
	
	public void process(int x, int y, String event){
		
		Point point = MouseInfo.getPointerInfo().getLocation();
		int CurrentX = (int)point.getX();
		int CurrentY = (int)point.getY();
		
		switch(event){
			case "MOUSE_MOVE": {
				move( CurrentX + x, CurrentY + y );
				break;
			}
			case "MOUSE_CLICKED": {
				click();	
				break;
			}
		}
		
	}
	
	public void move(int x, int y){
		robot.mouseMove(x,y);
	}
	
	public void click(){
		robot.mousePress(InputEvent.BUTTON1_MASK);
	    robot.delay(200);
	    robot.mouseRelease(InputEvent.BUTTON1_MASK);
	    robot.delay(200);
	}
}
