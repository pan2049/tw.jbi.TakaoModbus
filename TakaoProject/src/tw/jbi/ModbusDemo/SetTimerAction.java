package tw.jbi.ModbusDemo;

import java.util.Timer;

import tw.jbi.DateTask.*;

public class SetTimerAction {

	public static void action() {
		Timer timer = new Timer();
		timer.schedule(new TimerClass_00(), 0000, 15000);
		
	}
}
