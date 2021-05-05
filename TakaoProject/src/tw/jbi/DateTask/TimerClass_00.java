package tw.jbi.DateTask;

import java.util.TimerTask;

import tw.jbi.Database.ModbusSenserValueInsert;

public class TimerClass_00 extends TimerTask{

	@Override
	public void run() {
		ModbusSenserValueInsert.insertSenserValue();
	}
	
}
