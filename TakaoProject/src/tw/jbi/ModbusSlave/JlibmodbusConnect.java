package tw.jbi.ModbusSlave;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.intelligt.modbus.jlibmodbus.Modbus;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.tcp.TcpParameters;

public class JlibmodbusConnect {

	
	public static ModbusMaster masterFactory() {
		TcpParameters tcpParameters = new TcpParameters();
		// 设置TCP的ip地址
		InetAddress adress;
		ModbusMaster master = null;
		try {
			adress = InetAddress.getByName("127.0.0.1");
		// TCP参数设置ip地址
		// tcpParameters.setHost(InetAddress.getLocalHost());
		tcpParameters.setHost(adress);
		// TCP设置长连接
		tcpParameters.setKeepAlive(true);
		// TCP设置端口，这里设置是默认端口502
		tcpParameters.setPort(Modbus.TCP_PORT);
		// 创建一个主机
		master = ModbusMasterFactory.createModbusMasterTCP(tcpParameters);
		
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
		
	}


}
