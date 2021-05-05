package tw.jbi.ModbusSlave;

import com.intelligt.modbus.jlibmodbus.Modbus;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;

public class JlibmodbusReadValues {

	public static  int[] readValues() {
		ModbusMaster master;
		int[] registerValues = null;
		// 建立主機
		master = JlibmodbusConnect.masterFactory();
		
		Modbus.setAutoIncrementTransactionId(true);
		int slaveId = 1; //modbus地址
		int offset = 0; //寄存器讀取開始地址
		int quantity = 30; //讀取寄存器數量
		try {
			if (!master.isConnected()) {
				master.connect(); //開啟連接
			}
			// 讀取對應modbus數據，readInputRegisters讀取的寫寄讀器，功能碼04
			// 讀取對應modbus數據，readHoldingRegisters讀取的寫寄讀器，功能碼03
			registerValues = master.readHoldingRegisters(slaveId, offset, quantity);
			// 控制台输出
//			for (int value : registerValues) {
//				System.out.println("Address: " + offset++ + ", Value: " + value);
//			}
		} catch (ModbusProtocolException e) {
			e.printStackTrace();
		} catch (ModbusNumberException e) {
			e.printStackTrace();
		} catch (ModbusIOException e) {
			e.printStackTrace();
		} finally {
			try {
				master.disconnect();
			} catch (ModbusIOException e) {
				e.printStackTrace();
			}
		}
		return registerValues;
	}
}
