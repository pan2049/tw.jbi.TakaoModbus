package tw.jbi.ModbusDemo;


import tw.jbi.Database.DatabaseCheckClass;


public class TakaoModbusDemo {

	public static void main(String[] args) {
		
		DatabaseCheckClass.dbCheck();
		SetTimerAction.action();
	}

}
